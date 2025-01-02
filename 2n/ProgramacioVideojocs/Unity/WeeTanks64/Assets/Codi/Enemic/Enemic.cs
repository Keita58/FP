using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;
using UnityEngine.InputSystem;
using UnityEngine.Rendering.HighDefinition;

public class Enemic : MonoBehaviour{
    private enum EnemyStates { PATROL, DETECT, ATTACK }
    [SerializeField] private EnemyStates _CurrentState;
    [SerializeField] private float _StateTime;
    [SerializeField] private float _hp;
    [SerializeField] private LayerMask _LayerJugador;
    [SerializeField] private LayerMask _LayerParets;
    [SerializeField] private GameObject _Jugador;
    [SerializeField] private bool _Detectat;
    [SerializeField] private bool _Cami;
    private ArrayList _PuntsMapa;
    private NavMeshAgent _NavMeshAgent;
    private System.Random _Random;
    private Rigidbody[] _Rigidbodies;
    [SerializeField] private Collider[] _DetectarCollider;
    private Collider[] _Atacar;

    private void Awake()
    {
        _NavMeshAgent = GetComponent<NavMeshAgent>();
        _Detectat = false;
        _Cami = false;
        _PuntsMapa = new ArrayList();
        _Random = new System.Random();
        _PuntsMapa.Add(new KeyValuePair<float, float>(5, 30.75f)); // Cantonada superior dreta
        _PuntsMapa.Add(new KeyValuePair<float, float>(5, 8)); // Cantonada superior esquerra
        _PuntsMapa.Add(new KeyValuePair<float, float>(27.5f, 30.75f)); // Cantonada inferior dreta
        _PuntsMapa.Add(new KeyValuePair<float, float>(27.5f, 8)); // Cantonada inferior esquerra
        _PuntsMapa.Add(new KeyValuePair<float, float>(33, 30.75f)); // Cantonada terrassa dreta
        _PuntsMapa.Add(new KeyValuePair<float, float>(33, 8)); // Cantonada terrassa esquerra
        _PuntsMapa.Add(new KeyValuePair<float, float>(16, 16)); // Centre mapa
        _PuntsMapa.Add(new KeyValuePair<float, float>(12, 30.75f)); // Terraplà
    }

    private void Start()
    {
        _Rigidbodies = GetComponentsInChildren<Rigidbody>();
        ToggleRagdoll(false);
        StartCoroutine(Patrullar());
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
            case EnemyStates.ATTACK:
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
                Vector3 direccio = Vector3.zero;

                _DetectarCollider = Physics.OverlapSphere(transform.position, 10f, _LayerJugador);
                _Atacar = Physics.OverlapSphere(transform.position, 5f, _LayerJugador);

                if (_Atacar.Length > 0 && !Physics.Raycast(transform.position, transform.forward, out RaycastHit hitInfo, 7f, _LayerParets))
                {
                    ChangeState(EnemyStates.ATTACK);
                }
                else
                {
                    if(_DetectarCollider.Length > 0)
                    {
                        _NavMeshAgent.destination = _Jugador.transform.position;
                    }
                    else
                        ChangeState(EnemyStates.PATROL);
                }

                break;
            case EnemyStates.ATTACK:
                Atacar();
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
            case EnemyStates.ATTACK:
                break;
            default:
                break;
        }
    }

    private void Update()
    {
        UpdateState(_CurrentState);
    }

    private void Atacar()
    {
        transform.LookAt(_Jugador.transform.position);
        _Atacar = Physics.OverlapSphere(transform.position, 5f, _LayerJugador);
        
        if (_Atacar.Length > 0)
        {
            if(Physics.Raycast(transform.position, transform.forward, out RaycastHit hitInfo, 5f, _LayerParets))
            {
                ChangeState(EnemyStates.DETECT);
            }
        }
        else
            ChangeState(EnemyStates.DETECT);

        StartCoroutine(Espera(1));
    }

    IEnumerator Patrullar()
    {
        KeyValuePair<float, float> coordXZ = new KeyValuePair<float, float>(0, 0);
        while (!_Detectat)
        {
            Debug.Log("Estic patrullant");
            if (!_Cami) 
            { 
                coordXZ = (KeyValuePair<float, float>)_PuntsMapa[_Random.Next(0, _PuntsMapa.Count - 1)];
                _NavMeshAgent.destination = new Vector3(coordXZ.Key, transform.position.y, coordXZ.Value);
                _Cami = true;
            }

            if (transform.position == new Vector3(coordXZ.Key, transform.position.y, coordXZ.Value))
            {
                _Cami = false;
            }

            _DetectarCollider = Physics.OverlapSphere(transform.position, 10f, _LayerJugador);

            if (_DetectarCollider.Length > 0 && _DetectarCollider[0].transform.tag.Equals("Player"))
            {
                ChangeState(EnemyStates.DETECT);
                _Detectat = true;
                _Cami = false;
            }
            
            yield return new WaitForSeconds(1);
        }
    }

    //Funció per activar o desactivar el ragdoll de l'enemic. Extret del tutorial d'Unity: https://learn.unity.com/tutorial/creating-ragdolls-2019
    private void ToggleRagdoll(bool v)
    {
        foreach(Rigidbody rb in _Rigidbodies)
        {
            rb.isKinematic = !v;
        }
    }

    IEnumerator Espera(int segons)
    {
        yield return new WaitForSeconds(segons);
    }
}
