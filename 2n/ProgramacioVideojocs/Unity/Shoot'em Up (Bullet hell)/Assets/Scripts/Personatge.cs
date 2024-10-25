using System;
using UnityEngine;
using UnityEngine.InputSystem;

[RequireComponent(typeof(Animator))]
public class Personatge : MonoBehaviour, IDamageable
{
    [SerializeField] InputActionAsset Action;
    [SerializeField] Hitbox hitbox;

    private Animator _Animator;
    private InputActionAsset _CopiaAction;
    private InputAction _Moviment;
    private Rigidbody2D _Rigidbody;
    private int _Vides;
    [SerializeField] private bool _Girat;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Awake()
    {
        _Vides = 0;
        _Girat = false;
        _Animator = GetComponent<Animator>();

        //Instancia de l'input original
        _CopiaAction = Instantiate(Action);

        //Afegim el boto a la funcio de disparar
        _CopiaAction.FindActionMap("Player").FindAction("Attack").performed += Atac;
        _CopiaAction.FindActionMap("Player").FindAction("HardAttack").performed += AtacFort;

        //Agafem les tecles del moviment
        _Moviment = _CopiaAction.FindActionMap("Player").FindAction("Move");

        //Activem l'input
        _CopiaAction.FindActionMap("Player").Enable();

        _Rigidbody = this.GetComponent<Rigidbody2D>();
    }

    private enum CatStates { IDLE, RUN, PUNCH, HARDPUNCH, LIGHTCOMBO, HARDCOMBO, HURT }
    [SerializeField] private CatStates _CurrentState;
    [SerializeField] private CatStates _BufferState;
    [SerializeField] private AnimationClip _AttackClip;
    [SerializeField] private AnimationClip _HardAttackClip;
    private bool _Combo;
    private float _StateTime;

    public event Action<float> OnDamaged;

    private void Start()
    {
        InitState(CatStates.IDLE);
        _Combo = false;
    }

    private void ChangeState(CatStates newState)
    {
        ExitState(_CurrentState);
        InitState(newState);
    }

    private void InitState(CatStates initState)
    {
        _CurrentState = initState;
        _StateTime = 0;

        switch (_CurrentState)
        {
            case CatStates.IDLE:
                _Animator.Play("Idle");
                _Rigidbody.velocity = Vector3.zero;
                break;
            case CatStates.RUN:
                _Animator.Play("Run");
                break;
            case CatStates.PUNCH:
                _Animator.Play("Attack1");
                hitbox.Damage = 4;
                break;
            case CatStates.HARDPUNCH:
                _Animator.Play("Attack2");
                hitbox.Damage = 7;
                break;
            case CatStates.LIGHTCOMBO:
                _Animator.Play("Attack1");
                hitbox.Damage = 4 * 0.3f;
                break;
            case CatStates.HARDCOMBO:
                _Animator.Play("Attack2");
                hitbox.Damage = 7 * 0.3f;
                break;
            case CatStates.HURT:
                _Animator.Play("Idle");
                break;
            default:
                break;
        }
    }

    private void UpdateState(CatStates updateState)
    {
        _StateTime += Time.deltaTime;
        //print(_StateTime);

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
                }
                _Rigidbody.velocity = _Moviment.ReadValue<Vector2>() * 2f;

                if(_Moviment.ReadValue<Vector2>().x > 0)
                {
                    this.transform.eulerAngles = Vector3.up * 0;
                }
                else if(_Moviment.ReadValue<Vector2>().x < 0)
                {
                    this.transform.eulerAngles = Vector3.up * 180;
                }

                break;
            case CatStates.PUNCH:
                if (_StateTime >= _AttackClip.length)
                {
                    if(_BufferState.Equals(CatStates.PUNCH))
                    {
                        ChangeState(CatStates.PUNCH);
                        _BufferState = CatStates.IDLE;
                    }
                    else if (_BufferState.Equals(CatStates.HARDPUNCH))
                    {
                        ChangeState(CatStates.HARDPUNCH);
                        _BufferState = CatStates.IDLE;
                    }
                    else
                        ChangeState(CatStates.IDLE);
                }
                break;
            case CatStates.HARDPUNCH:
                if (_StateTime >= _HardAttackClip.length)
                {
                    if (_BufferState.Equals(CatStates.PUNCH))
                    {
                        ChangeState(CatStates.PUNCH);
                        _BufferState = CatStates.IDLE;
                    }
                    else if (_BufferState.Equals(CatStates.HARDPUNCH))
                    {
                        ChangeState(CatStates.HARDPUNCH);
                        _BufferState = CatStates.IDLE;
                    }
                    else
                        ChangeState(CatStates.IDLE);
                }
                break;
            case CatStates.LIGHTCOMBO:
                if (_StateTime >= _AttackClip.length)
                    ChangeState(CatStates.IDLE);
                break;
            case CatStates.HARDCOMBO:
                if (_StateTime >= _HardAttackClip.length)
                    ChangeState(CatStates.IDLE);
                break;
            case CatStates.HURT:
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
            case CatStates.RUN:
                _Rigidbody.velocity = Vector2.zero;
                break;
            case CatStates.PUNCH:
                _Combo = false;
                break;
            case CatStates.HARDPUNCH:
                _Combo = false;
                break;
            default:
                break;
        }
    }

    private void Atac(InputAction.CallbackContext context)
    {
        switch (_CurrentState)
        {
            case CatStates.IDLE:
                ChangeState(CatStates.PUNCH);
                break;
            case CatStates.RUN:
                ChangeState(CatStates.PUNCH);
                break;
            case CatStates.PUNCH:
                _BufferState = CatStates.PUNCH;
                break;
            case CatStates.HARDPUNCH:
                if (_Combo)
                    ChangeState(CatStates.LIGHTCOMBO);
                else
                    _BufferState = CatStates.PUNCH;
                break;
            default:
                break;
        }
    }
    private void AtacFort(InputAction.CallbackContext context)
    {
        switch (_CurrentState)
        {
            case CatStates.IDLE:
                ChangeState(CatStates.HARDPUNCH);
                break;
            case CatStates.RUN:
                ChangeState(CatStates.HARDPUNCH);
                break;
            case CatStates.PUNCH:
                if (_Combo)
                    ChangeState(CatStates.HARDCOMBO);
                else
                    _BufferState = CatStates.HARDPUNCH;
                break;
            case CatStates.HARDPUNCH:
                _BufferState = CatStates.HARDPUNCH;
                break;
            default:
                break;
        }
    }

    public void StartCombo()
    {
        _Combo = true; 
    }

    public void EndCombo()
    {
        _Combo = false;
    }

    // Update is called once per frame
    void Update()
    {
        UpdateState(_CurrentState);
    }

    public void RebreMal(float damage)
    {
        if(_Vides > 0)
        {
            _Vides -= (int)damage;
            ChangeState(CatStates.HURT);
        }
        else
            Destroy(this.gameObject);
    }
}
