using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bola : MonoBehaviour
{
    // Start is called before the first frame update
    Rigidbody2D rb;
    [SerializeField] int velocity;
    public delegate void Delegat();
    public Delegat perd;
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

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.transform.tag == "Terra")
        {
            perd.Invoke(); // El que hi ha a dins del delegat no es perd!!!! Només s'executa cada vegada que es crida al delegat.
            this.transform.position = new Vector2(0, -3.8f);
            int r = Random.Range(0, 360);
            this.transform.Rotate(0, 0, r);
            Rigidbody2D rbBola = this.GetComponent<Rigidbody2D>();
            rbBola.velocity = this.transform.up * velocity;
        }
    }

}
