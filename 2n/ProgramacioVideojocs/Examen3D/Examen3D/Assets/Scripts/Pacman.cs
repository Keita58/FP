using System.Collections;
using UnityEngine;

namespace m17
{
    public class Pacman : MonoBehaviour
    {
        [SerializeField]
        private float _Speed = 3f;

        [SerializeField]
        private float _RotationSpeed = 180f;

        [SerializeField] private Light _Llanterna;

        [SerializeField] private GameObject TeleportEsq;
        [SerializeField] private GameObject TeleportDret;

        [SerializeField] private GameObject Power1;
        [SerializeField] private GameObject Power2;

        [SerializeField] private Camera camera1;
        [SerializeField] private Camera camera2;

        Vector3 _Movement = Vector3.zero;
        private Rigidbody _Rigidbody;
        private int _Punts;

        private void Awake()
        {
            _Punts = 0;
            _Rigidbody = GetComponent<Rigidbody>();
            Power1.GetComponent<PowerUp>().PowerActivat += ActivaPower;
        }

        void Update()
        {
            InputToMovement();
            if (Input.GetMouseButtonDown(0)) // Botó esquerre
            {
                camera1.enabled = !camera1.enabled;
                camera2.enabled = !camera2.enabled;
            }
        }

        private void InputToMovement()
        {
            //rotate
            if (Input.GetKey(KeyCode.A))
                transform.Rotate(-Vector3.up * _RotationSpeed * Time.deltaTime);
            if (Input.GetKey(KeyCode.D))
                transform.Rotate(Vector3.up * _RotationSpeed * Time.deltaTime);

            _Movement = Vector3.zero;

            if (Input.GetKey(KeyCode.W))
                _Movement += transform.forward;
            if (Input.GetKey(KeyCode.S))
                _Movement -= transform.forward;
        }

        private void FixedUpdate()
        {
            _Rigidbody.linearVelocity = _Movement.normalized * _Speed + Vector3.up * _Rigidbody.linearVelocity.y;
        }

        private void OnTriggerEnter(Collider other)
        {
            if(other.transform.tag.Equals("point"))
            {
                _Punts++;
                Destroy(other.gameObject);

                if(_Punts == 18)
                {
                    print("Has guanyat!");
                }
            }
            else if(other.transform.tag.Equals("leftTeleport"))
            {
                transform.position = TeleportDret.transform.position + new Vector3(-2, 0.5f, 0);
            }
            else if(other.transform.tag.Equals("rightTeleport"))
            {
                transform.position = TeleportEsq.transform.position + new Vector3(2, 0.5f, 0);
            }
        }

        private void ActivaPower()
        {
            _Llanterna.color = Color.red;
            _Llanterna.intensity = 100000;
            _Llanterna.innerSpotAngle = 40;
            _Llanterna.spotAngle = 70;
            StartCoroutine(DesactivaPower());
        }

        IEnumerator DesactivaPower()
        {
            yield return new WaitForSeconds(5);
            _Llanterna.color = Color.white;
            _Llanterna.intensity = 40000;
        }
    }

}