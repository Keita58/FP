using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class PartyEscort : MonoBehaviour
{
    private enum EnemyStates { PATROL, DETECT, ATTACK, IDLE }
    [SerializeField] private EnemyStates _CurrentState;
    [SerializeField] private float _StateTime;
    [SerializeField] private LayerMask _LayerJugador;
    [SerializeField] private GameObject _Jugador;
    [SerializeField] private bool _Detectat;
    [SerializeField] private bool _Cami;
    [SerializeField] private Collider[] _DetectarCollider;
    [SerializeField] private GameObject[] _Punts;
    private NavMeshAgent _NavMeshAgent;
    private System.Random _Random;
    [SerializeField] private bool _Canvi;

    private void Start()
    {
        _Canvi = true;
        _NavMeshAgent = GetComponent<NavMeshAgent>();
        InitState(EnemyStates.PATROL);
    }

    private void ChangeState(EnemyStates newState)
    {
        //tornar al mateix estat o no
        if (newState == _CurrentState)
            return;

        ExitState(_CurrentState);
        InitState(newState);
    }

    private void InitState(EnemyStates initState)
    {
        _CurrentState = initState;
        _StateTime = 0f;

        switch (_CurrentState)
        {
            case EnemyStates.PATROL:
                _Detectat = false;
                StartCoroutine(Patrullar());
                break;
            case EnemyStates.DETECT:
                break;
            case EnemyStates.IDLE:
                _NavMeshAgent.destination = transform.position;
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
            case EnemyStates.PATROL:
                break;
            case EnemyStates.DETECT:
                _DetectarCollider = Physics.OverlapSphere(transform.position, 8f, _LayerJugador);

                if (_DetectarCollider.Length > 0)
                {
                    Debug.Log("Detecto alguna cosa aprop!");
                    _NavMeshAgent.destination = _Jugador.transform.position;
                }
                else
                {
                    Debug.Log("No detecto res!");
                    _NavMeshAgent.destination = transform.position;
                    StartCoroutine(CanviaPatrullar(1));
                }
                
                break;
            case EnemyStates.IDLE:
                break;
            default:
                break;
        }
    }

    private void ExitState(EnemyStates exitState)
    {
        switch (exitState)
        {
            case EnemyStates.PATROL:
            case EnemyStates.DETECT:
            case EnemyStates.IDLE:
                break;
            default:
                break;
        }
    }

    private void Update()
    {
        UpdateState(_CurrentState);
    }

    IEnumerator Patrullar()
    {
        int i = 0;
        while (!_Detectat)
        {            
            if (_NavMeshAgent.remainingDistance <= 0)
            {
                _NavMeshAgent.SetDestination(_Punts[i%2].transform.position);
                i++;
            }

            _DetectarCollider = Physics.OverlapSphere(transform.position, 8f, _LayerJugador);

            if (_DetectarCollider.Length > 0 && _DetectarCollider[0].transform.tag.Equals("Player"))
            {
                ChangeState(EnemyStates.DETECT);
                _Detectat = true;
                _Cami = false;
            }

            yield return new WaitForSeconds(1);
        }
    }

    IEnumerator CanviaPatrullar(int segons)
    {
        yield return new WaitForSeconds(segons);
        ChangeState(EnemyStates.PATROL);
    }

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.transform.tag == "Player")
        {
            collision.gameObject.GetComponent<Chell>().GameOver();
        }
    }
}
