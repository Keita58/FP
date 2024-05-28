using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bola : MonoBehaviour
{
    // Start is called before the first frame update
    Rigidbody2D rb;
    [SerializeField] int velocity;
    public delegate void vides();
    public vides perd;
    void Start()
    {
        
        int r = Random.Range(0, 360);
        this.transform.Rotate(0, 0, r);
        rb = GetComponent<Rigidbody2D>();
        rb.velocity = this.transform.up * velocity;
    }

    // Update is called once per frame
    void Update()
    {
        
    }


}
