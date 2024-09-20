using UnityEngine;
using UnityEngine.InputSystem;

namespace jugador
{
    public class Quadrat : MonoBehaviour
    {
        // Start is called once before the first execution of Update after the MonoBehaviour is created
        [SerializeField] GameObject _Bala;
        [SerializeField] InputActionAsset _Action;
        InputActionAsset _CopiaAction;

        private InputAction _Moviment;
        private InputAction _Gamepad;
        private Rigidbody2D _Rigidbody;

        void Start()
        {
            //Instància de l'input original
            _CopiaAction = Instantiate(_Action);

            //Afegim el botó a la funció de disparar
            _CopiaAction.FindActionMap("Player").FindAction("Botó").started += dispara;

            //Agafem les tecles del moviment
            _Moviment = _CopiaAction.FindActionMap("Player").FindAction("Moviment");

            //Activem l'input
            _CopiaAction.FindActionMap("Player").Enable();

            _Rigidbody = this.GetComponent<Rigidbody2D>();
        }

        // Update is called once per frame
        void Update()
        {
            moviment();
        }

        private void moviment()
        {
            _Rigidbody.velocity = _Moviment.ReadValue<Vector2>() * 1.5f;
        }

        private void dispara(InputAction.CallbackContext callbackContext)
        {
            if (callbackContext.phase.ToString() == "Started")
            {
                GameObject BalaCopia = Instantiate(_Bala);
                BalaCopia.transform.position = new Vector2(this.transform.position.x, this.transform.position.y + 0.5f);
                BalaCopia.GetComponent<Rigidbody2D>().velocity = this.transform.up * 2;
            }
        }

        private void OnDestroy()
        {
            _CopiaAction.FindActionMap("Player").FindAction("Botó").started -= dispara;
        }
    }
}

