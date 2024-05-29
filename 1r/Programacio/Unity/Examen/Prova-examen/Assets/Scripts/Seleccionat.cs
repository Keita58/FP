using System.Collections;
using System.Collections.Generic;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;

public class Seleccionat : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI text;
    public void print(int num)
    {
        switch (num)
        {
            case 0:
                text.text = "Has seleccionat 5 caixes";
                break;
            case 1:
                text.text = "Has seleccionat 10 caixes";
                break;
            case 2:
                text.text = "Has seleccionat 15 caixes";
                break;
        }
    }
}
