using System;
using System.Collections;
using UnityEngine;
using UnityEngine.InputSystem;

public class Jugador : MonoBehaviour
{
    //D'aquesta forma teniu l'input action a un json i podeu accedir
    //a totes les seves variables com a camps. Tot i això, no podreu
    //fer modificacions als bindings i d'altres.
    private InputSystem_Actions _inputActions;

    private InputAction _MoveAction;
    private InputAction _LookAction;
    private Rigidbody _RigidBody;
    [SerializeField] private Enemic _Enemic;
    
    [SerializeField] private GameObject _Camera;
    [SerializeField] private GameObject _CameraOcell;

    [Tooltip("Velocitat de moviment del jugador.")]
    [Range(0.1f, 20f)]
    [SerializeField] private float _Velocity = 3;

    [Tooltip("Velocitat de mouse en graus per segon.")]
    [Range(10f, 360f)]
    [SerializeField] private float _LookVelocity = 180;

    [SerializeField] private bool _InvertY = true;
    private Vector2 _LookRotation = Vector2.zero;

    [SerializeField] private LayerMask _UseLayerMask;

    public event Action<Vector3> OnSetDestination;

    private void Awake()
    {
        Debug.Assert(_Camera is not null, $"Camera del player no assignada.");

        _inputActions = new InputSystem_Actions();

        _MoveAction = _inputActions.Player.Move;
        _LookAction = _inputActions.Player.Look;

        //_inputActions.Player.Interact.performed += Interact;
        _inputActions.Player.Attack.performed += Atac;
        _inputActions.Player.Attack.canceled += AtacFi;
        _inputActions.Player.Attack2.performed += Atac2;
        _inputActions.Player.Attack2.canceled += AtacFi;
        _inputActions.Player.Interact.started += CanviaCamera;

        _inputActions.Player.Enable();

        _RigidBody = GetComponent<Rigidbody>();
    }

    private void Update()
    {
        //Càmera
        Vector2 lookInput = _LookAction.ReadValue<Vector2>();

        _LookRotation.x += lookInput.x * _LookVelocity * Time.deltaTime;
        _LookRotation.y += (_InvertY ? 1 : -1) * lookInput.y * _LookVelocity * Time.deltaTime;

        _LookRotation.y = Mathf.Clamp(_LookRotation.y, -20, 45);
        transform.rotation = Quaternion.Euler(0, _LookRotation.x, 0);
        _Camera.transform.localRotation = Quaternion.Euler(_LookRotation.y, 0, 0);
        
        //Moviment
        Vector2 movementInput = _MoveAction.ReadValue<Vector2>();

        Vector3 moviment = (transform.right * movementInput.x +
                transform.forward * movementInput.y).normalized * _Velocity;

        _RigidBody.linearVelocity = new Vector3(moviment.x, _RigidBody.linearVelocity.y, moviment.z);
        
        UpdateState(_CurrentState);

        // Aquest moviment no interessa perquè no té en compte la rotació de l'objecte
        //_RigidBody.linearVelocity = (new Vector3(movementInput.x, 0, movementInput.y)).normalized * 3;
    }

    //Màquina d'estats
    private enum RobotStates { IDLE, RUN, PUNCH, RUNPUNCH, HURT }
    [SerializeField] private RobotStates _CurrentState;
    [SerializeField] private AnimationClip _AttackClip;
    [SerializeField] private AnimationClip _AttackRunClip;
    [SerializeField] private AnimationClip _RunClip;
    private bool _AtacContinu;
    private float _StateTime;

    private void Start()
    {
        _AtacContinu = false;
        InitState(RobotStates.IDLE);
    }

    private void ChangeState(RobotStates newState)
    {
        ExitState(_CurrentState);
        InitState(newState);
    }

    private void InitState(RobotStates initState)
    {
        _CurrentState = initState;
        _StateTime = 0;

        switch (_CurrentState)
        {
            case RobotStates.IDLE:
                _RigidBody.linearVelocity = Vector3.zero;
                _RigidBody.angularVelocity = Vector3.zero;
                break;
            case RobotStates.RUN:
                break;
            case RobotStates.PUNCH:
                break;
            case RobotStates.RUNPUNCH:
                break;
            case RobotStates.HURT:
                this.GetComponent<SpriteRenderer>().color = Color.red;
                break;
            default:
                break;
        }
    }

    private void UpdateState(RobotStates updateState)
    {
        _StateTime += Time.deltaTime;

        switch (updateState)
        {
            case RobotStates.IDLE:
                if (_MoveAction.ReadValue<Vector2>() != Vector2.zero)
                    ChangeState(RobotStates.RUN);

                break;
            case RobotStates.RUN:
                if (_MoveAction.ReadValue<Vector2>() == Vector2.zero)
                    ChangeState(RobotStates.IDLE);
                break;
            case RobotStates.PUNCH:
                if (_MoveAction.ReadValue<Vector2>() != Vector2.zero)
                    ChangeState(RobotStates.RUN);
                break;
            case RobotStates.RUNPUNCH:
                if (_StateTime >= _AttackClip.length)
                {
                    ChangeState(RobotStates.IDLE);
                }
                break;
            case RobotStates.HURT:
                if (_StateTime > 0.15f)
                {
                    GetComponent<SpriteRenderer>().color = Color.white;
                    ChangeState(RobotStates.IDLE);
                }
                break;
            default:
                break;
        }
    }

    private void ExitState(RobotStates exitState)
    {
        switch (exitState)
        {
            case RobotStates.IDLE:
                break;
            case RobotStates.RUN:
                _RigidBody.linearVelocity = Vector3.zero;
                _RigidBody.angularVelocity = Vector3.zero;
                break;
            case RobotStates.PUNCH:
                break;
            case RobotStates.RUNPUNCH:
                break;
            case RobotStates.HURT:
                GetComponent<SpriteRenderer>().color = Color.white;
                break;
            default:
                break;
        }
    }

    private void Atac(InputAction.CallbackContext context)
    {
        switch (_CurrentState)
        {
            case RobotStates.IDLE:
                ChangeState(RobotStates.PUNCH);
                break;
            case RobotStates.RUN:
                ChangeState(RobotStates.RUNPUNCH);
                break;
            case RobotStates.RUNPUNCH:
                //_BufferState = RobotStates.RUNPUNCH;
                break;
            default:
                break;
        }

        Debug.DrawRay(_Camera.transform.position, _Camera.transform.forward, Color.magenta, 5f);
        //Mirem a través de la càmera!!!!
        if (Physics.Raycast(_Camera.transform.position, _Camera.transform.forward, out RaycastHit hitInfo, 20f, _UseLayerMask))
        {
            //Debug.Log($"He tocat l'objectiu {hitInfo.collider.gameObject.name} a {hitInfo.point}");
            Rigidbody[] zonaAfectada = hitInfo.collider.GetComponentsInChildren<Rigidbody>();

            if(!hitInfo.collider.GetComponentInParent<Enemic>()._RagdollActivat)
                hitInfo.collider.GetComponentInParent<Enemic>().EmDisparen(zonaAfectada, hitInfo.rigidbody, _Camera.transform.forward);

            Debug.DrawLine(_Camera.transform.position, hitInfo.point, Color.red, 5f);
            OnSetDestination?.Invoke(hitInfo.point);
        }
    }

    private void Atac2(InputAction.CallbackContext context)
    {
        _AtacContinu = true;
        switch (_CurrentState)
        {
            case RobotStates.IDLE:
                ChangeState(RobotStates.PUNCH);
                break;
            case RobotStates.RUN:
                ChangeState(RobotStates.RUNPUNCH);
                break;
            case RobotStates.RUNPUNCH:
                //_BufferState = RobotStates.RUNPUNCH;
                break;
            default:
                break;
        }

        StartCoroutine(AtacContinu());
    }

    private IEnumerator AtacContinu()
    {
        //Debug.Log("Abans de disparar");
        while (_AtacContinu)
        {
            //Debug.Log("Disparant");
            yield return new WaitForSeconds(0.05f);

            Debug.DrawRay(_Camera.transform.position, _Camera.transform.forward, Color.magenta, 5f);
            //Mirem a través de la càmera!!!!
            if (Physics.Raycast(_Camera.transform.position, _Camera.transform.forward, out RaycastHit hitInfo, 20f, _UseLayerMask))
            {
                Rigidbody[] zonaAfectada = hitInfo.collider.GetComponentsInChildren<Rigidbody>();

                if (!hitInfo.collider.GetComponentInParent<Enemic>()._RagdollActivat)
                    hitInfo.collider.GetComponentInParent<Enemic>().EmDisparenContinu(zonaAfectada, hitInfo.rigidbody, _Camera.transform.forward);

                Debug.DrawLine(_Camera.transform.position, hitInfo.point, Color.red, 5f);
                OnSetDestination?.Invoke(hitInfo.point);
            }
        }

        _Enemic.ActKinematic();
    }

    private void AtacFi(InputAction.CallbackContext context)
    {
        ChangeState(RobotStates.IDLE);
        _AtacContinu = false;
    }

    private void CanviaCamera(InputAction.CallbackContext context)
    {
        _Camera.SetActive(!_Camera.activeSelf);
        _CameraOcell.SetActive(!_CameraOcell.activeSelf);
    }
}
