using System.Collections;
using UnityEngine;
using UnityEngine.InputSystem;
using UnityEngine.XR;

public class Enemic : MonoBehaviour
{
    [SerializeField] AreaDeteccio RangPerseguir;
    [SerializeField] AreaDeteccio RangAtac;

    private Animator _Animator;

    private void Awake()
    {
        _Animator = GetComponent<Animator>();
        RangPerseguir.OnEnter += OnPlayerDetected;
        RangPerseguir.OnExit += OnPlayerNotDetected;
    }

    private void OnPlayerDetected(GameObject player)
    {

    }

    private void OnPlayerNotDetected(GameObject player)
    {

    }

    private void OnPlayerAttackRange(GameObject player)
    {

    }

    private void OnPlayerNotAttackRange(GameObject player)
    {

    }

    private enum EnemyStates { IDLE, ATTACK, DIE }
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
                break;
            default:
                break;
        }
    }

    private void UpdateState(EnemyStates updateState)
    {
        _StateTime += Time.deltaTime;
        print(_StateTime);

        switch (updateState)
        {
            case EnemyStates.IDLE:
                if (_Moviment.ReadValue<Vector2>() != Vector2.zero)
                    ChangeState(EnemyStates.RUN);

                break;
            case EnemyStates.RUN:
                if (_Moviment.ReadValue<Vector2>() == Vector2.zero)
                {
                    ChangeState(EnemyStates.IDLE);
                }
                _Rigidbody.velocity = _Moviment.ReadValue<Vector2>() * 1f;

                if (_Moviment.ReadValue<Vector2>().x > 0)
                {
                    this.transform.eulerAngles = Vector3.up * 0;
                }
                else if (_Moviment.ReadValue<Vector2>().x < 0)
                {
                    this.transform.eulerAngles = Vector3.up * 180;
                }
                print(this.transform.eulerAngles);

                break;
            case EnemyStates.ATTACK:
                if (_StateTime >= _AttackClip.length)
                {
                    ChangeState(EnemyStates.IDLE);
                }
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
            case EnemyStates.RUN:
                _Rigidbody.velocity = Vector2.zero;
                break;
            case EnemyStates.ATTACK:
                break;
            default:
                break;
        }
    }

    IEnumerator Atacar()
    {
        _Animator.Play("atac");
        yield return new WaitForSeconds(2);
        _Animator.Play("idle");
        yield return new WaitForSeconds(0.5f);
        if (_PlayerRangAtac)
            ChangeState(EnemyStates.ATTACK);
        else
            ChangeState(EnemyStates.IDLE);
    }

    void Update()
    {
        UpdateState(_CurrentState);
    }
}
