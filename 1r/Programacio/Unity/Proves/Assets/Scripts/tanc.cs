using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class tanc : MonoBehaviour
{
    public float speedMovement;
    public float speedBullets;
    private Rigidbody2D rb;

    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {

        if (Input.GetKey(KeyCode.W))
            rb.velocity = new Vector2(rb.velocity.x, speedMovement);
        else if (Input.GetKey(KeyCode.S))
            rb.velocity = new Vector2(rb.velocity.x, -speedMovement);
        else 
            rb.velocity = new Vector2(rb.velocity.x, 0);

        if (Input.GetKey(KeyCode.D))
            rb.velocity = new Vector2(speedMovement, rb.velocity.y);
        else if (Input.GetKey(KeyCode.A))
            rb.velocity = new Vector2(-speedMovement, rb.velocity.y);
        else
            rb.velocity = new Vector2(0, rb.velocity.y);
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.transform.tag == "Paret")
            rb.velocity = new Vector2(0, 0);
    }
}
