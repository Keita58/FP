using System;
using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEditor.PackageManager;
using UnityEngine;
using UnityEngine.AI;
using UnityEngine.InputSystem;
using UnityEngine.Rendering.HighDefinition;

public class Enemic : MonoBehaviour{
    private enum EnemyStates { PATROL, DETECT, ATTACK, IDLE }
    [SerializeField] private EnemyStates _CurrentState;
    [SerializeField] private float _StateTime;
    [SerializeField] private float _hp;
    [SerializeField] private LayerMask _LayerJugador;
    [SerializeField] private LayerMask _LayerParets;
    [SerializeField] private GameObject _Jugador;
    [SerializeField] private bool _Detectat;
    [SerializeField] private bool _Cami;
    [SerializeField] private bool _AtacarBoolean;
    [SerializeField] private Collider[] _DetectarCollider;
    private ArrayList _PuntsMapa;
    private NavMeshAgent _NavMeshAgent;
    private System.Random _Random;
    private Rigidbody[] _Rigidbodies;
    private Collider[] _Atacar;
    private Animator _Animacio;
    public bool _RagdollActivat { get; private set; }

    private void Awake()
    {
        _NavMeshAgent = GetComponent<NavMeshAgent>();
        _Detectat = false;
        _Cami = false;
        _AtacarBoolean = false;
        _RagdollActivat = false;
        _PuntsMapa = new ArrayList();
        _Random = new System.Random();
        _Animacio = GetComponentInParent<Animator>();
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
                _Animacio.Play("Run");
                _Detectat = false;
                StartCoroutine(Patrullar());
                break;
            case EnemyStates.DETECT:
                _Animacio.Play("Run");
                break;
            case EnemyStates.ATTACK:
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
                _DetectarCollider = Physics.OverlapSphere(transform.position, 10f, _LayerJugador);
                _Atacar = Physics.OverlapSphere(transform.position, 5f, _LayerJugador);

                if (_Atacar.Length > 0)
                {
                    ChangeState(EnemyStates.ATTACK);
                }
                else
                {
                    Debug.Log("Detecto!");
                    if(_DetectarCollider.Length > 0)
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
                }
                break;
            case EnemyStates.ATTACK:
                AtacarDireccio();
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
            case EnemyStates.ATTACK:
                _AtacarBoolean = false;
                break;
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

    IEnumerator Atacar()
    {
        while(_AtacarBoolean)
        {
            int prob = _Random.Next(1, 6);
            if(prob > 1) //Probabilitat del 20% de que no dispari correctament
            {
                Physics.Raycast(transform.position, transform.forward, out RaycastHit hitJugador, 5f, _LayerJugador);
                Debug.DrawRay(transform.position, transform.forward, Color.red, 5f);
                Debug.Log(hitJugador);
            }
            else
            {
                Physics.Raycast(transform.position, (transform.forward + new Vector3(5, 3, 15)), out RaycastHit hitJugador, 5f, _LayerJugador);
                Debug.DrawRay(transform.position, transform.forward, Color.green, 5f);
                Debug.Log("He fallat!");
            }
            yield return new WaitForSeconds(0.75f);
        }
    }

    private void AtacarDireccio()
    {
        transform.LookAt(_Jugador.transform.position);
        _Atacar = Physics.OverlapSphere(transform.position, 5f, _LayerJugador);

        if (_Atacar.Length > 0)
        {
            if (Physics.Raycast(transform.position, transform.forward, out RaycastHit info, 5f, _LayerParets))
            {
                _Animacio.Play("Run");
                _AtacarBoolean = false;
                Debug.Log(info.ToString());
                _NavMeshAgent.destination = _Jugador.transform.position;
            }
            else
            {
                _Animacio.Play("Dispar");
                _AtacarBoolean = true;
                StartCoroutine(Atacar());
                _NavMeshAgent.destination = transform.position;
            }
        }
        else
        {
            ChangeState(EnemyStates.DETECT);
        }
    }

    IEnumerator Patrullar()
    {
        KeyValuePair<float, float> coordXZ = new KeyValuePair<float, float>(0, 0);
        while (!_Detectat)
        {
            if (!_Cami) 
            {
                _Animacio.Play("Run");
                coordXZ = (KeyValuePair<float, float>)_PuntsMapa[_Random.Next(0, _PuntsMapa.Count - 1)];
                _NavMeshAgent.destination = new Vector3(coordXZ.Key, transform.position.y, coordXZ.Value);
                _Cami = true;
            }

            if (transform.position == new Vector3(coordXZ.Key, transform.position.y, coordXZ.Value))
            {
                _Animacio.Play("Idle");
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

    IEnumerator CanviaPatrullar(int segons)
    {
        _Animacio.Play("Idle");
        yield return new WaitForSeconds(segons);
        ChangeState(EnemyStates.PATROL);
    }

    //Dibuixa esferes per veure els overlap
    private void OnDrawGizmos()
    {
        if (!this.isActiveAndEnabled)
            return;
        Gizmos.DrawWireSphere(transform.position, 10f);
        Gizmos.DrawWireSphere(transform.position, 5f);
    }

    public void EmDisparen(Rigidbody[] body, Rigidbody afegirForca, Vector3 direccio)
    {
        Debug.Log("M'han disparat!");
        ChangeState(EnemyStates.IDLE);
        _RagdollActivat = true;
        _AtacarBoolean = false;
        _Animacio.enabled = false;
        
        foreach (Rigidbody rigidbody in body)
        {
            rigidbody.isKinematic = false;
        }

        afegirForca.AddForce(direccio * 200);

        StartCoroutine(ActivaKinematic(1, body));
    }

    public void EmDisparenContinu(Rigidbody[] body, Rigidbody afegirForca, Vector3 direccio)
    {
        Debug.Log("M'han disparat!");
        ChangeState(EnemyStates.IDLE);
        _AtacarBoolean = false;
        _Animacio.enabled = false;

        foreach (Rigidbody rigidbody in body)
        {
            rigidbody.isKinematic = false;
        }

        afegirForca.AddForce(direccio * 200);
    }

    public void ActKinematic()
    {
        _RagdollActivat = true;

        StartCoroutine(ActivaKinematic(1.5f, _Rigidbodies));
    }

    IEnumerator ActivaKinematic(float segons, Rigidbody[] body)
    {
        yield return new WaitForSeconds(segons);

        _Animacio.enabled = true;

        foreach (Rigidbody rigidbody in body)
        {
            rigidbody.isKinematic = true;
        }

        ChangeState(EnemyStates.PATROL);
        _RagdollActivat = false;
        _Cami = false;
    }
}
