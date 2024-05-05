using System.Collections;
using System.Collections.Generic;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;

public class Enemics : MonoBehaviour
{
    private Rigidbody2D rb;
    private GameObject ui;
    private GameObject death;
    public int velocity;
    public int rotation;
    // Start is called before the first frame update
    void Start()
    {
        death = GameObject.Find("Mort");
        ui = GameObject.Find("Canvas");
        rb = GetComponent<Rigidbody2D>();
        StartCoroutine(movement());
    }

    private IEnumerator movement()
    {
        while (true)
        {
            int num = Random.Range(0, 5);

            if (num == 1)
            {
                this.transform.Rotate(0, 0, (rotation / 10));
            }
            else if (num == 2)
            {
                this.transform.Rotate(0, 0, -(rotation / 10));
            }
            else
            {
                this.transform.Rotate(0, 0, 0);
                rb.angularVelocity = 0;
            }

            if (num == 3)
            {
                rb.velocity = this.transform.up * velocity;
            }
            else if (num == 4)
            {
                rb.velocity = this.transform.up * -velocity;
            }
            else
            {
                rb.velocity = new Vector2(0, 0);
                this.transform.Rotate(0, 0, 0);
            }

            yield return new WaitForSeconds(2f);
        }
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.transform.tag == "Paret")
            rb.velocity = new Vector2(0, 0);
        if (collision.transform.tag == "Obstacles")
            rb.velocity = new Vector2(0, 0);
        if (collision.transform.tag == "BalaBona")
        {
            if (this.gameObject.name == "EnemicVermell")
            {
                ui.GetComponent<UImanager>().SumarPunts();
                ui.GetComponent<UImanager>().SumarPunts();
                ui.GetComponent<UImanager>().SumarPunts();
            }
            else if(this.gameObject.name == "EnemicNegre")
            {
                ui.GetComponent<UImanager>().SumarPunts();
                ui.GetComponent<UImanager>().SumarPunts();
            }
            else
                ui.GetComponent<UImanager>().SumarPunts();
            death.GetComponent<AudioSource>().Play();
            Destroy(this.gameObject);
        }
    }
}
