using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawn : MonoBehaviour
{
    // Start is called before the first frame update
    [SerializeField] GameObject caixaOriginal;
    void Start()
    {
        float x = -7.5f;
        float y = 3.5f;
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                GameObject caixa = Instantiate(caixaOriginal.gameObject);
                caixa.transform.position = new Vector2(x, y);
                x += 3;
            }
            x = -7.5f;
            y -= 1.5f;
        }
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
