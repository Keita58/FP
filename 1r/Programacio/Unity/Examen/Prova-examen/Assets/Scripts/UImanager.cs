using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class UImanager : MonoBehaviour
{
    [SerializeField] GameObject bola;
    [SerializeField] TextMeshProUGUI vides;
    int videsNum;
    // Start is called before the first frame update
    void Start()
    {
        videsNum = 3;
        vides.text = "Vides: " + videsNum;
        bola.GetComponent<Bola>().perd += perdVida;
    }

    // Update is called once per frame
    public void perdVida()
    {
        videsNum--;
        vides.text = "Vides: " + videsNum;
    }
}
