using UnityEngine;
using UnityEngine.InputSystem;

[RequireComponent(typeof(Animator))]
public class Personatge : MonoBehaviour
{
    [SerializeField] InputActionAsset Action;

    private Animator _Animator;
    private InputActionAsset _CopiaAction;
    private InputAction _Moviment;
    private Rigidbody2D _Rigidbody;
    private bool _Girat;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Awake()
    {
        _Girat = false;
        _Animator = GetComponent<Animator>();

        //Instancia de l'input original
        _CopiaAction = Instantiate(Action);

        //Afegim el boto a la funcio de disparar
        //_CopiaAction.FindActionMap("Player").FindAction("Attack").performed += dispara;
        //_CopiaAction.FindActionMap("Player").FindAction("HardAttack").performed += dispara;

        //Agafem les tecles del moviment
        _Moviment = _CopiaAction.FindActionMap("Player").FindAction("Move");

        //Activem l'input
        _CopiaAction.FindActionMap("Player").Enable();

        _Rigidbody = this.GetComponent<Rigidbody2D>();
    }

    private enum CatStates { IDLE, RUN, PUNCH, HARDPUNCH }
    [SerializeField] private CatStates _CurrentState;
    private void Start()
    {
        InitState(CatStates.IDLE);
    }

    private void ChangeState(CatStates newState)
    {
        //tornar al mateix estat o no
        if (newState == _CurrentState)
            return;

        ExitState(_CurrentState);
        InitState(newState);
    }

    private void InitState(CatStates initState)
    {
        _CurrentState = initState;

        switch (_CurrentState)
        {
            case CatStates.IDLE:
                _Animator.Play("Idle");
                break;
            case CatStates.RUN:
                _Animator.Play("Run");
                break;
            case CatStates.PUNCH:
                _Animator.Play("Attack1");
                break;
            case CatStates.HARDPUNCH:
                _Animator.Play("Attack2");
                break;
            default:
                break;
        }
    }


    private void UpdateState(CatStates updateState)
    {

        switch (updateState)
        {
            case CatStates.IDLE:
                if(_Moviment.ReadValue<Vector2>() != Vector2.zero)
                    ChangeState(CatStates.RUN);
                
                break;
            case CatStates.RUN:
                if (_Moviment.ReadValue<Vector2>() == Vector2.zero)
                {
                    ChangeState(CatStates.IDLE);
                    _Rigidbody.velocity = Vector2.zero;
                }
                _Rigidbody.velocity = _Moviment.ReadValue<Vector2>() * 1f;

                if (_Rigidbody.velocity.x == -1 && !_Girat)
                {
                    this.transform.Rotate(0, 180, 0);
                    _Girat = true;
                }
                else if (_Rigidbody.velocity.x == 1 && _Girat)
                {
                    this.transform.Rotate(0, 0, 0);
                    _Girat = false;
                }
                break;
            case CatStates.PUNCH:
                ChangeState(CatStates.IDLE);
                break;
            case CatStates.HARDPUNCH:
                ChangeState(CatStates.IDLE);
                break;
            default:
                break;
        }
    }

    private void ExitState(CatStates exitState)
    {
        switch (exitState)
        {
            case CatStates.IDLE:
                break;
            case CatStates.PUNCH:
                break;
            case CatStates.HARDPUNCH:
                break;
            default:
                break;
        }
    }

    // Update is called once per frame
    void Update()
    {
        UpdateState(_CurrentState);
    }
}
