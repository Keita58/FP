using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movement : MonoBehaviour
{
    // Start is called before the first frame update
    [SerializeField] int velocity;
    Rigidbody2D rb;
    [SerializeField] GameObject bola;
    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKey(KeyCode.A))
        {
            rb.velocity = this.transform.right * -velocity;
        }
        else if(Input.GetKey(KeyCode.D))
        {
            rb.velocity = this.transform.right * velocity;
        }
        else
        {
            rb.velocity = new Vector2 (0, 0);
        }
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.transform.tag == "Power")
        {
            print(collision.GetComponent<SpriteRenderer>().color);
            Destroy(collision.gameObject);
        }
            
    }
}
