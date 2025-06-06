using UnityEngine;
using UnityEngine.InputSystem;


public class Jugador : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    [SerializeField] GameObject _Bala;
    [SerializeField] InputActionAsset _Action;
    [SerializeField] JugadorSO _PlayerData;
    [SerializeField] PoolBales _Pool;
    InputActionAsset _CopiaAction;

    private InputAction _Moviment;
    private InputAction _Gamepad;
    private Rigidbody2D _Rigidbody;

    void Start()
    {
        //Instancia de l'input original
        _CopiaAction = Instantiate(_Action);

        //Afegim el boto a la funcio de disparar
        _CopiaAction.FindActionMap("Player").FindAction("Boto").started += dispara;

        //Agafem les tecles del moviment
        _Moviment = _CopiaAction.FindActionMap("Player").FindAction("Moviment");

        //Activem l'input
        _CopiaAction.FindActionMap("Player").Enable();

        _Rigidbody = this.GetComponent<Rigidbody2D>();

        InitPlayer();
    }

    public void SetSkin(Sprite sprite)
    {
        _PlayerData.sprite = sprite;
        InitPlayer();
    }

    private void InitPlayer()
    {
        GetComponent<SpriteRenderer>().sprite = _PlayerData.sprite;
    }

    // Update is called once per frame
    void Update()
    {
        moviment();
    }

    private void moviment()
    {
        _Rigidbody.velocity = _Moviment.ReadValue<Vector2>() * 2f;
    }

    private void dispara(InputAction.CallbackContext callbackContext)
    {
        if (callbackContext.phase.ToString() == "Started")
        {
            GetComponent<AudioSource>().Play();
            GameObject BalaCopia = _Pool.GetElement();
            BalaCopia.SetActive(true);
            BalaCopia.transform.position = new Vector2(this.transform.position.x, this.transform.position.y + 0.5f);
            BalaCopia.GetComponent<Rigidbody2D>().velocity = this.transform.up * 2;
            BalaCopia.GetComponent<IPoolable>().OnDestroyed += ReturnBalaToPool;
        }
    }

    private void ReturnBalaToPool(GameObject bala)
    {
        bala.GetComponent<IPoolable>().OnDestroyed -= ReturnBalaToPool;
        bala.SetActive(false);
        _Pool.ReturnElement(bala);
    }

    private void OnDestroy()
    {
        _CopiaAction.FindActionMap("Player").FindAction("Boto").started -= dispara;
    }
}


