using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Movement : MonoBehaviour
{
    // Start is called before the first frame update
    [SerializeField] int velocity;
    Rigidbody2D rb;
    [SerializeField] GameObject bola;
    int vides;
    void Start()
    {
        vides = 3;
        rb = GetComponent<Rigidbody2D>();
        bola.GetComponent<Bola>().perd += perdVida;
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

    void perdVida()
    {
        if(vides > 0)
        {
            vides--;
            this.transform.position = new Vector2(0, -5);
        }
        else
        {
            SceneManager.LoadScene("Inici");
        }
    }
}
