using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Moure : MonoBehaviour
{
    // Start is called before the first frame update
    Rigidbody2D rb;
    [SerializeField] int rotationSpeed;
    float z;
    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKey(KeyCode.A))
        {
            z += 1;
            this.transform.localEulerAngles = new Vector3(0, 0, z) * rotationSpeed;
        }
        else if (Input.GetKey(KeyCode.D))
        {
            z -= 1;
            this.transform.localEulerAngles = new Vector3(0, 0, z) * rotationSpeed;
        }
        else
        {
            this.transform.Rotate(0, 0, 0);
            rb.angularVelocity = 0;
        }
    }
}
