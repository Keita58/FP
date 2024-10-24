using System;
using System.Collections;
using UnityEngine;
using UnityEngine.InputSystem;
using UnityEngine.XR;

public class Enemic : MonoBehaviour, IDamageable
{
    [SerializeField] AreaDeteccio RangPerseguir;
    [SerializeField] AreaDeteccio RangAtac;
    [SerializeField] EnemicSO EnemicSO;
    [SerializeField] Hitbox hitbox;

    private Animator _Animator;
    private Rigidbody2D _Rigidbody;
    private float _Vida;

    private void Awake()
    {
        _Vida = EnemicSO.vides;
        _Animator = GetComponent<Animator>();
        _Rigidbody = this.GetComponent<Rigidbody2D>();
        RangPerseguir.OnEnter += OnPlayerDetected;
        RangPerseguir.OnExit += OnPlayerNotDetected;
    }

    private void OnPlayerDetected(GameObject player)
    {

    }

    private void OnPlayerNotDetected(GameObject player)
    {

    }

    private void OnPlayerStay(GameObject player)
    {
        
    }

    private void OnPlayerAttackRange(GameObject player)
    {

    }

    private void OnPlayerNotAttackRange(GameObject player)
    {

    }

    private enum EnemyStates { IDLE, PURSUE, ATTACK, DIE }
    [SerializeField] private EnemyStates _CurrentState;

    public event Action<float> OnDamaged;

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
                break;
            case EnemyStates.ATTACK:
                _Animator.Play("Attack");
                hitbox.Damage = EnemicSO.mal;
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
                ChangeState(EnemyStates.PURSUE);
                break;
            case EnemyStates.PURSUE:
                ChangeState(EnemyStates.IDLE);
                break;
            case EnemyStates.ATTACK:
                ChangeState(EnemyStates.PURSUE);
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
        _Animator.Play("Attack");
        yield return new WaitForSeconds(2);
        _Animator.Play("Idle");
        yield return new WaitForSeconds(0.5f);
        /*if (_PlayerRangAtac)
            ChangeState(EnemyStates.ATTACK);
        else
            ChangeState(EnemyStates.PURSUE);*/
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
            Destroy(this.gameObject); /*Posar pool!!!!!*/
    }
}
