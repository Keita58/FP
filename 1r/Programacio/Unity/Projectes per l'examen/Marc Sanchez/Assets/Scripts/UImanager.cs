using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class UImanager : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI temps;
    [SerializeField] Button raig;
    int num;
    int vides;
    [SerializeField] GameObject escut;
    [SerializeField] TextMeshProUGUI videsText;
    [SerializeField] DadesScriptableObject dades;
    // Start is called before the first frame update
    void Start()
    {
        num = dades.temps;
        vides = dades.vides;
        temps.text = "Compte enrere: " + num;
        StartCoroutine(tempsPassa());
        escut.GetComponent<Escut>().patata += perdVida;
        escut.GetComponent<Escut>().guarda += guardaInfo;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    IEnumerator tempsPassa()
    {
        while (true)
        {
            yield return new WaitForSeconds(1f);
            if(num > 1)
            {
                num--;
                temps.text = "Compte enrere: " + num;
            }
            else
            {
                num--;
                temps.text = "Compte enrere: " + num;
                break;
            }
        }
        raig.gameObject.SetActive(true);
    }

    void perdVida()
    {
        vides--;
        videsText.text = "Vides: " + vides;
    }

    void guardaInfo()
    {
        dades.vides = vides;
        dades.temps = num;
    }
}
