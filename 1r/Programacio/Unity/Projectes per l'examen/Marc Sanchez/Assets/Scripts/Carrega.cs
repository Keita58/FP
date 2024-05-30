using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class Carrega : MonoBehaviour
{
    [SerializeField] GameObject carregaOriginal;
    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(carregaTemps());
    }

    void Update()
    {
       
    }

    IEnumerator carregaTemps()
    {
        while (true)
        {
            yield return new WaitForSeconds(0.5f);
            GameObject carrega = Instantiate(carregaOriginal);
            int color = Random.Range(0, 2);
            switch (color)
            {
                case 0:
                    carrega.GetComponent<SpriteRenderer>().color = Color.red;
                    break;
                case 1:
                    carrega.GetComponent<SpriteRenderer>().color = Color.green;
                    break;
            }
        }
    }
}
