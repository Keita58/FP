using System;
using System.Collections;
using Unity.VisualScripting;
using UnityEditor.Animations;
using UnityEngine;
using UnityEngine.InputSystem;
using UnityEngine.XR;

public class Enemic : MonoBehaviour, IDamageable, IPoolable
{
    public event Action<GameObject> OnDestroyed;
    [SerializeField] GameObject RangPerseguir;
    [SerializeField] GameObject RangAtac;
    [SerializeField] Hitbox hitbox;
    [SerializeField] private GameEvent _Rondes;
    [SerializeField] Pool PoolBales;

    private Animator _Animator;
    private Rigidbody2D _Rigidbody;
    private float _Vida;
    private float _StateTime;
    private int _Mal;
    private int _Punts;
    private int _Velocitat;
    private bool _PlayerRangAtac;
    private bool _TipusEnemic; // True -> Melee | False -> Ranged
    private GameObject _Jugador;
    private Color _ColorOriginal;
    private float _PuntAX;
    private float _PuntBX;
    private float _PuntAY;
    private bool _Viatge;
    private Pool _PoolCopia;

    private void Awake()
    {
        _StateTime = 0;
        _PlayerRangAtac = false;
        _Viatge = false;
        _Animator = GetComponent<Animator>();
        _Rigidbody = this.GetComponent<Rigidbody2D>();
        RangPerseguir.GetComponent<AreaDeteccio>().OnEnter += OnPlayerDetected;
        RangPerseguir.GetComponent<AreaDeteccio>().OnExit += OnPlayerNotDetected;
        RangAtac.GetComponent<AreaDeteccio>().OnEnter += OnPlayerAttackRange;
        RangAtac.GetComponent<AreaDeteccio>().OnExit += OnPlayerNotAttackRange;
    }

    public void ValorsEnemic(EnemicSO enemic)
    {
        this.transform.tag = "Enemic";
        _Vida = enemic.vides;
        _Mal = enemic.mal;
        _Punts = enemic.punts;
        _Velocitat = enemic.velocitat;
        _Animator.runtimeAnimatorController = enemic.animacio;
        RangPerseguir.GetComponent<CircleCollider2D>().radius = enemic.radiDeteccio;
        RangAtac.GetComponent<CircleCollider2D>().radius = enemic.radiAtac;
        _TipusEnemic = enemic.tipus;
        if (!_TipusEnemic)
        {
            _PoolCopia = Instantiate(PoolBales);
        }
    }

    public void setPosicio()
    {
        _PuntAX = this.transform.position.x;
        _PuntBX = -this.transform.position.x;
        _PuntAY = this.transform.position.y;
        Debug.Log("Punt AX: " + _PuntAX + ", punt AY: " + _PuntAY);
    }

    private void OnPlayerDetected(GameObject player)
    {
        RangPerseguir.GetComponent<AreaDeteccio>().OnStay += OnPlayerStay;
        _Jugador = player;
        ChangeState(EnemyStates.PURSUE);
        Vector2 direccio = player.transform.position - this.transform.position;
        direccio.Normalize();
        _Rigidbody.velocity = _Velocitat * direccio;
    }

    private void OnPlayerNotDetected(GameObject player)
    {
        RangPerseguir.GetComponent<AreaDeteccio>().OnStay -= OnPlayerStay;
        ChangeState(EnemyStates.IDLE);
        _Rigidbody.velocity = Vector3.zero;
    }

    private void OnPlayerStay(GameObject player)
    {
        _Jugador = player;
    }

    private void OnPlayerAttackRange(GameObject player)
    {
        _Jugador = player;
        _PlayerRangAtac = true;
        _Rigidbody.velocity = Vector3.zero;
        StartCoroutine(Atacar());
    }

    private void OnPlayerNotAttackRange(GameObject player)
    {
        _PlayerRangAtac = false;
        _Jugador = player;
        ChangeState(EnemyStates.PURSUE);
    }

    private enum EnemyStates { IDLE, PURSUE, ATTACK, HURT, DIE, NULL }
    [SerializeField] private EnemyStates _CurrentState;
    private void ChangeState(EnemyStates newState)
    {
        ExitState(_CurrentState);
        InitState(newState);
    }

    private void InitState(EnemyStates initState)
    {
        _StateTime = 0;
        _CurrentState = initState;

        switch (_CurrentState)
        {
            case EnemyStates.IDLE:
                _Animator.Play("Idle");
                break;
            case EnemyStates.ATTACK:
                _Animator.Play("Attack");
                hitbox.Damage = _Mal;
                break;
            case EnemyStates.PURSUE:
                _Animator.Play("Idle");
                break;
            case EnemyStates.HURT:
                _ColorOriginal = this.GetComponent<SpriteRenderer>().color;
                this.GetComponent<SpriteRenderer>().color = Color.red;
                break;
            case EnemyStates.DIE:
                _Animator.Play("Dead");
                break;
            default:
                break;
        }
    }

    private void UpdateState(EnemyStates updateState)
    {
        _StateTime += Time.deltaTime;

        switch (updateState)
        {
            case EnemyStates.IDLE:
                Patrulla();
                break;
            case EnemyStates.PURSUE:
                Vector2 direccio = Vector2.zero;
                if(_Jugador != null)
                    direccio = _Jugador.transform.position - this.transform.position;
                direccio.Normalize();
                _Rigidbody.velocity = _Velocitat * direccio;
                if (direccio.x > 0)
                {
                    this.transform.eulerAngles = Vector3.up * 0;
                }
                else if (direccio.x < 0)
                {
                    this.transform.eulerAngles = Vector3.up * 180;
                }
                break;
            case EnemyStates.ATTACK:
                break;
            case EnemyStates.HURT:
                if (_StateTime > 0.15f)
                {
                    this.GetComponent<SpriteRenderer>().color = _ColorOriginal;
                    ChangeState(EnemyStates.IDLE);
                }
                break;
            case EnemyStates.DIE:
                if(_StateTime > 0.8f)
                    ChangeState(EnemyStates.NULL);
                break;
            default:
                break;
        }
    }

    private void ExitState(EnemyStates exitState)
    {
        switch (exitState)
        {
            case EnemyStates.IDLE:
                _Viatge = false;
                break;
            case EnemyStates.ATTACK:
                break;
            case EnemyStates.PURSUE: 
                break;
            case EnemyStates.HURT:
                this.GetComponent<SpriteRenderer>().color = _ColorOriginal;
                break;
            case EnemyStates.DIE:
                this.GetComponent<SpriteRenderer>().color = _ColorOriginal;
                OnDestroyed?.Invoke(this.gameObject);
                break;
            default:
                break;
        }
    }

    IEnumerator Atacar()
    {
        while (_PlayerRangAtac)
        {
            ChangeState(EnemyStates.ATTACK);
            if (_TipusEnemic)
                yield return new WaitForSeconds(2);
            else
                yield return new WaitForSeconds(0.6f);
            _Animator.Play("Idle"); //Aquí poso animació més que estat per evitar que es solapin l'estat PURSUE amb l'IDLE i que no pari de perseguir-lo si no pot atacar
            _Rigidbody.velocity = Vector3.zero;
            if (_TipusEnemic)
                yield return new WaitForSeconds(0.5f);
            else
                yield return new WaitForSeconds(1.5f);
        }        
    }

    void Update()
    {
        UpdateState(_CurrentState);
    }

    public void RebreMal(float damage)
    {
        if ((_Vida - (int)damage) > 0)
        {
            _Vida -= (int)damage;
            ChangeState(EnemyStates.HURT);
        } 
        else
        {
            RangPerseguir.GetComponent<AreaDeteccio>().OnStay -= OnPlayerStay;
            _Rigidbody.velocity = Vector3.zero;
            ChangeState(EnemyStates.DIE);
            _Rondes.Raise();
        }   
    }

    private void Patrulla() {
        if (_TipusEnemic)
            _Rigidbody.velocity = Vector3.zero;
        else
        {
            if ((this.transform.position.x == _PuntAX && this.transform.position.y == _PuntAY) && _Viatge)
            {
                Debug.Log("Estic al punt A");
                Vector2 direccio = (new Vector3(_PuntBX, _PuntAY, this.transform.position.z)) - this.transform.position;
                direccio.Normalize();
                _Rigidbody.velocity = _Velocitat * direccio;
                if (direccio.x > 0)
                {
                    this.transform.eulerAngles = Vector3.up * 0;
                }
                else if (direccio.x < 0)
                {
                    this.transform.eulerAngles = Vector3.up * 180;
                }
            }
            else if ((this.transform.position.x == _PuntBX && this.transform.position.y == _PuntAY) && _Viatge)
            {
                Debug.Log("Estic al punt B");
                Vector2 direccio = (new Vector3(_PuntAX, _PuntAY, this.transform.position.z)) - this.transform.position;
                direccio.Normalize();
                _Rigidbody.velocity = _Velocitat * direccio;
                if (direccio.x > 0)
                {
                    this.transform.eulerAngles = Vector3.up * 0;
                }
                else if (direccio.x < 0)
                {
                    this.transform.eulerAngles = Vector3.up * 180;
                }
            }
            else if (!_Viatge)
            {
                Debug.Log("Entro");
                _Viatge = true;
                Vector2 direccio = (new Vector3(_PuntBX, _PuntAY, this.transform.position.z)) - this.transform.position;
                direccio.Normalize();
                _Rigidbody.velocity = _Velocitat * direccio;
                if (direccio.x > 0)
                {
                    this.transform.eulerAngles = Vector3.up * 0;
                }
                else if (direccio.x < 0)
                {
                    this.transform.eulerAngles = Vector3.up * 180;
                }
            }
        }
    }

    public void Disparar()
    {
        GameObject Bala = _PoolCopia.GetElement();
        Bala.SetActive(true);
        Bala.GetComponent<IPoolable>().OnDestroyed += ReturnBalaToPool;
        Vector2 direccio = _Jugador.transform.position - this.transform.position;
        direccio.Normalize();
        Bala.transform.position = new Vector3(this.transform.position.x + (direccio.x / 1.4f), this.transform.position.y + (direccio.y / 1.4f), this.transform.rotation.z);
        Bala.GetComponent<Rigidbody2D>().velocity = 2 * direccio;
        Bala.GetComponent<Bala>().Damage = 1;
    }

    private void ReturnBalaToPool(GameObject bala)
    {
        bala.GetComponent<IPoolable>().OnDestroyed -= ReturnBalaToPool;
        bala.SetActive(false);
        _PoolCopia.ReturnElement(bala);
    }
}
