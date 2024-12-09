using UnityEngine;
using UnityEngine.InputSystem;

public class Personatge : MonoBehaviour
{
    [SerializeField] InputActionAsset inputActionsOG;
    [SerializeField] private GameEvent _Event;
    [SerializeField] public Plantes AlSO;
    [SerializeField] public Plantes MaSO;
    [SerializeField] public Plantes GiSO;
    private InputActionAsset inputAsset;
    private InputAction _Moviment;
    private Rigidbody2D _Rigidbody;
    private Animator _Animator;
    private Vector3 _PosicioInicial;
    public int Al { get; set; }
    public int Ma { get; set; }
    public int Gi { get; set; }

    void Awake()
    {
        _PosicioInicial = transform.position;
        inputAsset = Instantiate(inputActionsOG);
        _Moviment = inputAsset.FindActionMap("Player").FindAction("Move");
        _Animator = GetComponent<Animator>();
        _Rigidbody = GetComponent<Rigidbody2D>();
        inputAsset.FindActionMap("Player").Enable();

        inputAsset.FindActionMap("Player").FindAction("Aixada").performed += Aixada;
        inputAsset.FindActionMap("Player").FindAction("Regadora").performed += Regadora;
        inputAsset.FindActionMap("Player").FindAction("Tisores").performed += Tisores;
        inputAsset.FindActionMap("Player").FindAction("Plantar_Al").performed += Plantar_Al;
        inputAsset.FindActionMap("Player").FindAction("Plantar_Ma").performed += Plantar_Ma;
        inputAsset.FindActionMap("Player").FindAction("Plantar_Gi").performed += Plantar_Gi;

        //inputAsset.FindActionMap("Player").FindAction("Canvi_Dia").performed += Canvi_Dia;
    }

    enum PlayerStates { DESCANS, AIXADA, REGADORA, TISORES, PLANTAR_AL, PLANTAR_MA, PLANTAR_GI }
    [SerializeField] PlayerStates actualState;
    [SerializeField] float stateTime;
    [SerializeField] LayerMask _CastLayerMask;
    [SerializeField] Collider2D _CircleCastFound;
    [SerializeField] Collider2D[] _CircleCastFoundAll;


    private void Start()
    {
        //this.transform.position = Vector3.zero;
        ChangeState(PlayerStates.DESCANS);
    }

    private void ChangeState(PlayerStates newstate)
    {
        ExitState(actualState);
        IniState(newstate);
    }

    private void IniState(PlayerStates initState)
    {
        actualState = initState;
        stateTime = 0f;

        switch (actualState)
        {
            case PlayerStates.DESCANS:
                _Animator.Play("Descans");
                break;
            case PlayerStates.AIXADA:
                _Animator.Play("Aixada");
                break;
            case PlayerStates.REGADORA:
                _Animator.Play("Regadora");
                break;
            case PlayerStates.TISORES:
                _Animator.Play("Tisores");
                break;
            case PlayerStates.PLANTAR_AL:
                _Animator.Play("Plantar_Al");
                break;
            case PlayerStates.PLANTAR_MA:
                _Animator.Play("Plantar_Ma");
                break;
            case PlayerStates.PLANTAR_GI:
                _Animator.Play("Plantar_Gi");
                break;
            default:
                break;
        }
    }
    private void UpdateState()
    {
        //_Moviment es l'action de l'InputAction especific de Player
        Vector2 direccio = _Moviment.ReadValue<Vector2>();

        stateTime += Time.deltaTime;

        switch (actualState)
        {
            case PlayerStates.DESCANS:
                
                this._Rigidbody.velocity = direccio * 4f;
                Debug.Log("Estic a Descans");
                //Moviment del prsonatge quan canvia de direccio
                if (_Moviment.ReadValue<Vector2>().x > 0)
                {
                    this.transform.eulerAngles = Vector3.up * 0;
                }
                else if (_Moviment.ReadValue<Vector2>().x < 0)
                {
                    this.transform.eulerAngles = Vector3.up * 180;
                }
                break;
            case PlayerStates.AIXADA:

                _CircleCastFound = Physics2D.OverlapCircle(transform.position, 1f, _CastLayerMask);
                _CircleCastFound.GetComponent<Parcela>().Remoguda();

                if (stateTime >= 0.3f)
                    ChangeState(PlayerStates.DESCANS);
                break;
            case PlayerStates.REGADORA:
                _CircleCastFoundAll = Physics2D.OverlapCircleAll(transform.position, 1f, _CastLayerMask);
                foreach(var c in _CircleCastFoundAll)
                {
                    c.GetComponent<Parcela>().Regada();
                }
                
                if (stateTime >= 0.3f)
                    ChangeState(PlayerStates.DESCANS);
                break;
            case PlayerStates.TISORES:
                _CircleCastFound = Physics2D.OverlapCircle(transform.position, 1f, _CastLayerMask);
                _CircleCastFound.GetComponent<Parcela>().Podada();

                if (stateTime >= 0.3f)
                    ChangeState(PlayerStates.DESCANS);
                break;
            case PlayerStates.PLANTAR_AL:
                _CircleCastFound = Physics2D.OverlapCircle(transform.position, 1f, _CastLayerMask);
                if (Al > 0)
                {
                    if(_CircleCastFound.GetComponent<Parcela>().Plant_Gi(AlSO));
                        Al--;
                }

                if (stateTime >= 0.3f)
                    ChangeState(PlayerStates.DESCANS);
                break;
            case PlayerStates.PLANTAR_MA:
                _CircleCastFound = Physics2D.OverlapCircle(transform.position, 1f, _CastLayerMask);
                if (Ma > 0)
                {
                    if(_CircleCastFound.GetComponent<Parcela>().Plant_Gi(MaSO));
                        Ma--;
                }

                if (stateTime >= 0.3f)
                    ChangeState(PlayerStates.DESCANS);
                break;
            case PlayerStates.PLANTAR_GI:
                _CircleCastFound = Physics2D.OverlapCircle(transform.position, 1f, _CastLayerMask);
                if (Gi > 0)
                {
                    if(_CircleCastFound.GetComponent<Parcela>().Plant_Gi(GiSO));
                        Gi--;
                }

                if (stateTime >= 0.3f)
                    ChangeState(PlayerStates.DESCANS);
                break;
        }
    }

    private void ExitState(PlayerStates exitState)
    {
        switch (exitState)
        {
            case PlayerStates.DESCANS:
                break;
            case PlayerStates.REGADORA:
                break;
            case PlayerStates.AIXADA:
                break;
            case PlayerStates.TISORES:
                break;
            case PlayerStates.PLANTAR_AL:
                break;
            case PlayerStates.PLANTAR_MA:
                break;
            case PlayerStates.PLANTAR_GI:
                break;
        }
    }

    private void Aixada(InputAction.CallbackContext context)
    {
        switch (actualState)
        {
            case PlayerStates.DESCANS:
            case PlayerStates.REGADORA:
            case PlayerStates.TISORES:
            case PlayerStates.PLANTAR_AL:
            case PlayerStates.PLANTAR_MA:
            case PlayerStates.PLANTAR_GI:
                ChangeState(PlayerStates.AIXADA);
                break;
            default:
                break;
        }
    }

    private void Regadora(InputAction.CallbackContext context)
    {
        switch (actualState)
        {
            case PlayerStates.DESCANS:
            case PlayerStates.AIXADA:
            case PlayerStates.TISORES:
            case PlayerStates.PLANTAR_AL:
            case PlayerStates.PLANTAR_MA:
            case PlayerStates.PLANTAR_GI:
                ChangeState(PlayerStates.REGADORA);
                break;
            default:
                break;
        }
    }

    private void Tisores(InputAction.CallbackContext context)
    {
        switch (actualState)
        {
            case PlayerStates.DESCANS:
            case PlayerStates.AIXADA:
            case PlayerStates.REGADORA:
            case PlayerStates.PLANTAR_AL:
            case PlayerStates.PLANTAR_MA:
            case PlayerStates.PLANTAR_GI:
                ChangeState(PlayerStates.TISORES);
                break;
            default:
                break;
        }
    }

    private void Plantar_Al(InputAction.CallbackContext context)
    {
        switch (actualState)
        {
            case PlayerStates.DESCANS:
            case PlayerStates.AIXADA:
            case PlayerStates.REGADORA:
            case PlayerStates.TISORES:
            case PlayerStates.PLANTAR_MA:
            case PlayerStates.PLANTAR_GI:
                ChangeState(PlayerStates.PLANTAR_AL);
                break;
            default:
                break;
        }
    }

    private void Plantar_Ma(InputAction.CallbackContext context)
    {
        switch (actualState)
        {
            case PlayerStates.DESCANS:
            case PlayerStates.AIXADA:
            case PlayerStates.REGADORA:
            case PlayerStates.TISORES:
            case PlayerStates.PLANTAR_AL:
            case PlayerStates.PLANTAR_GI:
                ChangeState(PlayerStates.PLANTAR_MA);
                break;
            default:
                break;
        }
    }

    private void Plantar_Gi(InputAction.CallbackContext context)
    {
        switch (actualState)
        {
            case PlayerStates.DESCANS:
            case PlayerStates.AIXADA:
            case PlayerStates.REGADORA:
            case PlayerStates.TISORES:
            case PlayerStates.PLANTAR_MA:
            case PlayerStates.PLANTAR_AL:
                ChangeState(PlayerStates.PLANTAR_GI);
                break;
            default:
                break;
        }
    }

    void Update()
    {
        UpdateState();
    }

    void Canvi_Dia()
    {
        transform.position = _PosicioInicial;
        ChangeState(PlayerStates.DESCANS);
        _Event.Raise();
    }
}
