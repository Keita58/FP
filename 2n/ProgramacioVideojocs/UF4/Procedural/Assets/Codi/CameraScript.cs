using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace m17
{
    public class CameraScript : MonoBehaviour
    {
        [SerializeField]
        [Min(0f)]
        private float _MovementSpeed = 40f;
        [SerializeField]
        [Range(1f, 5f)]
        private float _SprintMultiplier = 2f;
        [SerializeField]
        private float _RotationSpeed = 360f;
        [SerializeField]
        private bool _InvertMouseY = false;

        private void Awake()
        {
            Cursor.lockState = CursorLockMode.Locked;
        }

        // Update is called once per frame
        void Update()
        {
            bool sprint = Input.GetKey(KeyCode.LeftShift);
            //Move Camera
            Vector3 movement = Vector3.zero;

            if (Input.GetKey(KeyCode.W))
                movement += transform.forward;
            if (Input.GetKey(KeyCode.S))
                movement -= transform.forward;
            if (Input.GetKey(KeyCode.A))
                movement -= transform.right;
            if (Input.GetKey(KeyCode.D))
                movement += transform.right;

            transform.position += movement.normalized * _MovementSpeed * (sprint ? _SprintMultiplier : 1) * Time.deltaTime;

            //Rotate Camera
            if (Input.GetMouseButton(1))
                transform.localEulerAngles = transform.localEulerAngles
                                            + Vector3.right * Input.GetAxis("Mouse Y") * Time.deltaTime * _RotationSpeed * (_InvertMouseY ? 1 : -1)
                                            + Vector3.up * Input.GetAxis("Mouse X") * Time.deltaTime * _RotationSpeed;

            if (Input.GetKeyDown(KeyCode.F))
                transform.LookAt(Vector3.zero);

        }            
    }
}

