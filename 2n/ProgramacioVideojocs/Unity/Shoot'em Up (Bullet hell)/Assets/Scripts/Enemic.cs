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
    private float _PuntA;
    private float _PuntB;
    private bool _Viatge;

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

    public void setVides(float vida)
    {
        _Vida = vida;
    }

    public void setMal(int mal)
    {
        _Mal = mal;
    }

    public void setPunts(int punts)
    {
        _Punts = punts;
    }

    public void setVelocitat(int velocitat)
    {
        _Velocitat = velocitat;
    }

    public void setAnimacio(AnimatorController animacio)
    {
        _Animator.runtimeAnimatorController = animacio; //Afegim el controlador de les animacions amb el runtimeAnimationController
    }

    public void setRadiDeteccio(float radiDeteccio)
    {
        RangPerseguir.GetComponent<CircleCollider2D>().radius = radiDeteccio;
    }

    public void setRadiAtac(float radiAtac)
    {
        RangAtac.GetComponent<CircleCollider2D>().radius = radiAtac;
    }

    public void setTipusEnemic(bool tipus)
    {
        _TipusEnemic = tipus; 
    }

    public void setPosicio()
    {
        _PuntA = this.transform.position.x;
        _PuntB = -this.transform.position.x;
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
                if(_TipusEnemic)
                    _Rigidbody.velocity = Vector3.zero;
                else
                {
                    if(this.transform.position.x == _PuntA && _Viatge)
                    {
                        Debug.Log("Estic al punt A");
                        Vector2 direccio = (new Vector3(_PuntB, this.transform.position.y, this.transform.position.z)) - this.transform.position;
                        direccio.Normalize();
                        _Rigidbody.velocity = _Velocitat * direccio;
                    }
                    else if(this.transform.position.x == _PuntB && _Viatge)
                    {
                        Debug.Log("Estic al punt B");
                        Vector2 direccio = (new Vector3(_PuntA, this.transform.position.y, this.transform.position.z)) - this.transform.position;
                        direccio.Normalize();
                        _Rigidbody.velocity = _Velocitat * direccio;
                    }
                    else if(!_Viatge)
                    {
                        Debug.Log("Entro");
                        _Viatge = true;
                        Vector2 direccio = (new Vector3(_PuntB, this.transform.position.y, this.transform.position.z)) - this.transform.position;
                        direccio.Normalize();
                        _Rigidbody.velocity = _Velocitat * direccio;
                    }
                }
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
            yield return new WaitForSeconds(2);
            _Animator.Play("Idle"); //Aquí poso animació més que estat per evitar que es solapin l'estat PURSUE amb l'IDLE i que no pari de perseguir-lo si no pot atacar
            _Rigidbody.velocity = Vector3.zero;
            yield return new WaitForSeconds(0.5f);          
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
}
