using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;
using static UnityEngine.GraphicsBuffer;

public class Spawn : MonoBehaviour
{
    [SerializeField] GameObject enemic;
    float velocity;
    [SerializeField] GameObject jugador;
    [SerializeField] TipusEnemicsProperties e1;
    [SerializeField] TipusEnemicsProperties e2;
    [SerializeField] TipusEnemicsProperties e3;
    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(crear());
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    IEnumerator crear()
    {
        while (true)
        {
            int num = Random.Range(1, 4);
            yield return new WaitForSeconds(num);
            int enemicNum = Random.Range(1, 4);
            GameObject nau = Instantiate(enemic);
            // x -> -9.5 a 9.5
            // y -> -5.5 a 5.5
            int posY = Random.Range(-5, 5);
            int posX = Random.Range(-10, 10);
            int toca = Random.Range(0, 4);
            switch(toca)
            {
                case 0:
                    nau.transform.position = new Vector2(-9.6f, posY);
                    break;
                case 1:
                    nau.transform.position = new Vector2(9.6f, posY);
                    break;
                case 2:
                    nau.transform.position = new Vector2(posX, -5.6f);
                    break;
                case 3:
                    nau.transform.position = new Vector2(posX, 5.6f);
                    break;
            }
            nau.transform.up = jugador.transform.position - nau.transform.position;
            nau.transform.Rotate(0, 0, 180);
            switch (enemicNum)
            {
                case 1:
                    nau.GetComponent<SpriteRenderer>().color = e1.color;
                    nau.GetComponent<Rigidbody2D>().velocity = nau.transform.up * -e1.velocitat;
                    nau.GetComponent<Nau>().mal = e1.perdreVides;
                    break;
                case 2:
                    nau.GetComponent<SpriteRenderer>().color = e2.color;
                    nau.GetComponent<Rigidbody2D>().velocity = nau.transform.up * -e2.velocitat;
                    nau.GetComponent<Nau>().mal = e2.perdreVides;
                    break;
                case 3:
                    nau.GetComponent<SpriteRenderer>().color = e3.color;
                    nau.GetComponent<Rigidbody2D>().velocity = nau.transform.up * -e3.velocitat;
                    nau.GetComponent<Nau>().mal = e3.perdreVides;
                    break;
            }
        }
    }
}
