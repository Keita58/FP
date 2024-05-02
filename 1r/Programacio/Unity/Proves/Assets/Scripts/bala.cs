using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bala : MonoBehaviour
{
    private int cops = 2;
    private Rigidbody2D rb;
    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
    private void OnCollisionEnter2D(Collision2D collision)
    {
        if ((collision.transform.tag == "Paret" || collision.transform.tag == "Obstacles") && cops > 0)
        {
            cops--;
            transform.position = Vector2.Reflect(rb.velocity, collision.GetContact(0).normal);

        }
        else if (cops == 1)
            Destroy(this.gameObject);
    }
}
