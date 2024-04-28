using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Tanc : MonoBehaviour
{
    public float speedMovement;
    public float speedBullets;
    public float rotationSpeed;
    private Rigidbody2D rb;

    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        movement();
        
    }

    private void movement()
    {
        float moveX = Input.GetAxisRaw("Horizontal");
        float moveY = Input.GetAxisRaw("Vertical");

        Vector2 movement = new Vector2 (moveX, moveY);
        movement.Normalize();

        rb.velocity = movement * speedMovement;

        if(movement != Vector2.zero)
        {
            Quaternion toRotation = Quaternion.LookRotation(Vector3.forward, movement);
            transform.rotation = Quaternion.RotateTowards(transform.rotation, toRotation, rotationSpeed);
        }
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.transform.tag == "Paret")
            rb.velocity = new Vector2(0, 0);
        if (collision.transform.tag == "Obstacles")
            rb.velocity = new Vector2(0, 0);
    }
}
