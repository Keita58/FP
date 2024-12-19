using System;
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
    
    [SerializeField] private Animator _Animator;
    [SerializeField] private GameObject _Camera;

    [Tooltip("Velocitat de moviment del jugador.")]
    [Range(0.1f, 20f)]
    [SerializeField] private float _Velocity = 3;

    [Tooltip("Velocitat de mouse en graus per segon.")]
    [Range(10f, 360f)]
    [SerializeField] private float _Velocity = 3;

    [Tooltip("Velocitat de mouse en graus per segon.")]
    [Range(10f, 360f)]
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

        _inputActions.Player.Interact.performed += Interact;
        _inputActions.Player.Attack.performed += Atac;
        _inputActions.Player.Attack.canceled += AtacFi;

        _inputActions.Player.Enable();

        _RigidBody = GetComponent<Rigidbody>();

    }

    private void Update()
    {
        //Càmera
        Vector2 lookInput = _LookAction.ReadValue<Vector2>();

        _LookRotation.x += lookInput.x * _LookVelocity * Time.deltaTime;
        _LookRotation.y += (_InvertY ? 1 : -1) * lookInput.y * _LookVelocity * Time.deltaTime;

        //_Camera.transform.rotation = Quaternion.Euler(_LookRotation.y, _LookRotation.x, 0);
        _LookRotation.y = Mathf.Clamp(_LookRotation.y, minAngle, maxAngle);
        transform.rotation = Quaternion.Euler(0, _LookRotation.x, 0);
        _Camera.transform.localRotation = Quaternion.Euler(_LookRotation.y, 0, 0);

        //Moviment
        Vector2 movementInput = _MoveAction.ReadValue<Vector2>();

        _RigidBody.linearVelocity =
            (transform.right * movementInput.x +
            transform.forward * movementInput.y)
            .normalized * _Velocity;

        UpdateState(_CurrentState);


        //_RigidBody.linearVelocity = 
        //    (_Camera.transform.right * movementInput.x + 
        //    Vector3.ProjectOnPlane(_Camera.transform.forward, Vector3.up).normalized * movementInput.y)
        //    .normalized * _Velocity;

        // Aquest moviment no interessa perquè no té en compte la rotació de l'objecte
        //_RigidBody.linearVelocity = (new Vector3(movementInput.x, 0, movementInput.y)).normalized * 3;
    }

    private void Interact(InputAction.CallbackContext context)
    {
        Debug.DrawRay(_Camera.transform.position, _Camera.transform.forward, Color.magenta, 5f);
        //Mirem a través de la càmera!!!!
        if (Physics.Raycast(_Camera.transform.position, _Camera.transform.forward, out RaycastHit hitInfo, 20f, _UseLayerMask))
        {
            Debug.Log($"He tocat l'objectiu {hitInfo.collider.gameObject.name} a {hitInfo.point}");
            Debug.DrawLine(_Camera.transform.position, hitInfo.point, Color.red, 5f);

            OnSetDestination?.Invoke(hitInfo.point);
        }
    }

    //Màquina d'estats
    private enum RobotStates { IDLE, RUN, PUNCH, RUNPUNCH, HURT }
    [SerializeField] private RobotStates _CurrentState;
    //[SerializeField] private RobotStates _BufferState;
    [SerializeField] private AnimationClip _AttackClip;
    [SerializeField] private AnimationClip _AttackRunClip;
    [SerializeField] private AnimationClip _RunClip;
    private float _StateTime;

    private void Start()
    {
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
                _Animator.Play("Idle");
                _RigidBody.linearVelocity = Vector3.zero;
                break;
            case RobotStates.RUN:
                _Animator.Play("Run");
                break;
            case RobotStates.PUNCH:
                _Animator.Play("Dispar");
                //Hitbox.Damage = 4;
                break;
            case RobotStates.RUNPUNCH:
                _Animator.Play("MovimentDispar");
                //Hitbox.Damage = 4;
                break;
            case RobotStates.HURT:
                this.GetComponent<SpriteRenderer>().color = Color.red;
                _Animator.Play("Idle");
                break;
            default:
                break;
        }
    }

    private void UpdateState(RobotStates updateState)
    {
        _StateTime += Time.deltaTime;
        //print(_StateTime);

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
                    this.GetComponent<SpriteRenderer>().color = Color.white;
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
                _RigidBody.linearVelocity = Vector2.zero;
                break;
            case RobotStates.PUNCH:
                break;
            case RobotStates.RUNPUNCH:
                break;
            case RobotStates.HURT:
                this.GetComponent<SpriteRenderer>().color = Color.white;
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
                ChangeState(RobotStates.PUNCH);
                break;
            case RobotStates.RUNPUNCH:
                //_BufferState = RobotStates.RUNPUNCH;
                break;
            default:
                break;
        }
    }

    private void AtacFi(InputAction.CallbackContext context)
    {
        ChangeState(RobotStates.IDLE);
    }
}
