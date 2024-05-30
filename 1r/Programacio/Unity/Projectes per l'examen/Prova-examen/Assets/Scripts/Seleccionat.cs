using System.Collections;
using System.Collections.Generic;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;

public class Seleccionat : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI text;
    [SerializeField] CaixesProperties caixes;
    public void print(int num)
    {
        switch (num)
        {
            case 1:
                text.text = "Has seleccionat 5 caixes";
                caixes.caixes = 5;
                break;
            case 2:
                text.text = "Has seleccionat 10 caixes";
                caixes.caixes = 10;
                break;
            case 3:
                text.text = "Has seleccionat 15 caixes";
                caixes.caixes = 15;
                break;
        }
    }
}
