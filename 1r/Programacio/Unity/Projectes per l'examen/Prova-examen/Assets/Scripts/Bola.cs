using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bola : MonoBehaviour
{
    // Start is called before the first frame update
    Rigidbody2D rb;
    [SerializeField] int velocity;
    [SerializeField] BolaProperties groc;
    [SerializeField] BolaProperties verd;
    [SerializeField] BolaProperties blanc;
    public delegate void Delegat();
    public Delegat perd;
    void Start()
    {
        int r = Random.Range(0, 360);
        this.transform.Rotate(0, 0, r);
        rb = GetComponent<Rigidbody2D>();
        this.GetComponent<SpriteRenderer>().color = blanc.BolaColor;
        rb.velocity = this.transform.up * blanc.BolaSpd;
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
            this.GetComponent<SpriteRenderer>().color = blanc.BolaColor;
            this.transform.localScale = new Vector3(blanc.BolaScale, blanc.BolaScale, blanc.BolaScale);
            this.transform.position = new Vector2(0, -3.8f);
            int r = Random.Range(0, 360);
            this.transform.Rotate(0, 0, r);
            Rigidbody2D rbBola = this.GetComponent<Rigidbody2D>();
            rbBola.velocity = this.transform.up * blanc.BolaSpd;
        }
    }

    public void canviVerd()
    {
        this.GetComponent<SpriteRenderer>().color = verd.BolaColor;
        rb.velocity.Normalize();
        rb.velocity = this.transform.up * verd.BolaSpd;
        this.transform.localScale = new Vector3(verd.BolaScale, verd.BolaScale, verd.BolaScale);
    }

    public void canviGroc()
    {
        this.GetComponent<SpriteRenderer>().color = groc.BolaColor;
        rb.velocity.Normalize();
        rb.velocity = this.transform.up * groc.BolaSpd;
        this.transform.localScale = new Vector3(groc.BolaScale, groc.BolaScale, groc.BolaScale);
    }

    public void creaBola()
    {
        GameObject bola2 = Instantiate(this.gameObject);
        int r = Random.Range(0, 360);
        bola2.transform.Rotate(0, 0, r);
        bola2.transform.position = new Vector2(0, -3.8f);
        Rigidbody2D rb2 = bola2.GetComponent<Rigidbody2D>();
        bola2.GetComponent<SpriteRenderer>().color = Color.red;
        rb2.velocity = this.transform.up * blanc.BolaSpd;
    }
}
