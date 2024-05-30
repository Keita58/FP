using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawn : MonoBehaviour
{
    // Start is called before the first frame update
    [SerializeField] GameObject caixaOriginal;
    [SerializeField] CaixesProperties caixes;
    void Start()
    {
        float x = -4.7f;
        float y = 3.5f;

        if (caixes.caixes == 5)
        {
            for (int j = 0; j < 6; j++)
            {
                GameObject caixa = Instantiate(caixaOriginal.gameObject);
                caixa.transform.position = new Vector2(x, y);
                x += 2.2f;
            }
        }
        else if (caixes.caixes == 10)
        {
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    GameObject caixa = Instantiate(caixaOriginal.gameObject);
                    caixa.transform.position = new Vector2(x, y);
                    x += 2.2f;
                }
                x = -4.7f;
                y -= 1.2f;
            }
        }
        else
        {
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    GameObject caixa = Instantiate(caixaOriginal.gameObject);
                    caixa.transform.position = new Vector2(x, y);
                    x += 2.2f;
                }
                x = -4.7f;
                y -= 1.2f;
            }
            GameObject caixa2 = Instantiate(caixaOriginal.gameObject);
            caixa2.transform.position = new Vector2(x, y);
        }        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
