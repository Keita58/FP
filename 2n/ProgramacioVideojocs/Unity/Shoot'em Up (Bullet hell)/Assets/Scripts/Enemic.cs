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
    [SerializeField] AreaDeteccio RangPerseguir;
    [SerializeField] AreaDeteccio RangAtac;
    [SerializeField] Hitbox hitbox;

    private Animator _Animator;
    private Rigidbody2D _Rigidbody;
    private float _Vida;
    private int _Mal;
    private int _Punts;
    private int _Velocitat;
    private bool _PlayerRangAtac;

    private void Awake()
    {
        _PlayerRangAtac = false;
        _Animator = GetComponent<Animator>();
        _Rigidbody = this.GetComponent<Rigidbody2D>();
        RangPerseguir.OnEnter += OnPlayerDetected;
        RangPerseguir.OnExit += OnPlayerNotDetected;
        RangAtac.OnEnter += OnPlayerAttackRange;
        RangAtac.OnExit += OnPlayerNotAttackRange;
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

    private void OnPlayerDetected(GameObject player)
    {
        RangPerseguir.OnStay += OnPlayerStay;
        ChangeState(EnemyStates.PURSUE);
        Vector2 direccio = player.transform.position - this.transform.position;
        direccio.Normalize();
        _Rigidbody.velocity = _Velocitat * direccio;
    }

    private void OnPlayerNotDetected(GameObject player)
    {
        RangPerseguir.OnStay -= OnPlayerStay;
        ChangeState(EnemyStates.IDLE);
        _Rigidbody.velocity = Vector3.zero;
    }

    private void OnPlayerStay(GameObject player)
    {
        Vector2 direccio = player.transform.position - this.transform.position;
        direccio.Normalize();
        _Rigidbody.velocity = _Velocitat * direccio;
    }

    private void OnPlayerExit(GameObject player)
    {
        _Rigidbody.velocity = Vector3.zero; 
    }

    private void OnPlayerAttackRange(GameObject player)
    {
        _PlayerRangAtac = true;
        RangPerseguir.OnStay -= OnPlayerStay;
        _Rigidbody.velocity = Vector3.zero;
        StartCoroutine(Atacar());
    }

    private void OnPlayerNotAttackRange(GameObject player)
    {
        _PlayerRangAtac = false;
        RangPerseguir.OnStay += OnPlayerStay;
        ChangeState(EnemyStates.PURSUE);
    }

    private enum EnemyStates { IDLE, PURSUE, ATTACK, DIE }
    [SerializeField] private EnemyStates _CurrentState;
    private void ChangeState(EnemyStates newState)
    {
        ExitState(_CurrentState);
        InitState(newState);
    }

    private void InitState(EnemyStates initState)
    {
        _CurrentState = initState;

        switch (_CurrentState)
        {
            case EnemyStates.IDLE:
                _Animator.Play("Idle");
                _Rigidbody.velocity = Vector3.zero;
                break;
            case EnemyStates.ATTACK:
                _Animator.Play("Attack");
                hitbox.Damage = _Mal;
                break;
            case EnemyStates.PURSUE:
                _Animator.Play("Idle");
                break;
            default:
                break;
        }
    }

    private void UpdateState(EnemyStates updateState)
    {
        switch (updateState)
        {
            case EnemyStates.IDLE:
                break;
            case EnemyStates.PURSUE:
                break;
            case EnemyStates.ATTACK:
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
                break;
            case EnemyStates.ATTACK:
                break;
            case EnemyStates.PURSUE: 
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
            ChangeState(EnemyStates.IDLE);
            yield return new WaitForSeconds(0.5f);          
        }        
    }

    void Update()
    {
        UpdateState(_CurrentState);
    }

    public void RebreMal(float damage)
    {
        if (_Vida > 0)
            _Vida -= damage;
        else
            OnDestroyed?.Invoke(this.gameObject);
    }
}
