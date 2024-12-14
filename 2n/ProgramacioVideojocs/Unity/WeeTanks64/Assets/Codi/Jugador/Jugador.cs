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

    [SerializeField] private GameObject _Camera;

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

        _inputActions.Player.Interact.performed += Interact;

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
        transform.rotation = Quaternion.Euler(0, _LookRotation.x, 0);
        _Camera.transform.localRotation = Quaternion.Euler(_LookRotation.y, 0, 0);

        //Moviment
        Vector2 movementInput = _MoveAction.ReadValue<Vector2>();

        _RigidBody.linearVelocity =
            (transform.right * movementInput.x +
            transform.forward * movementInput.y)
            .normalized * _Velocity;


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

}
