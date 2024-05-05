using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class UImanager : MonoBehaviour
{
    [SerializeField] private TextMeshProUGUI puntuacioText;
    [SerializeField] private TextMeshProUGUI tempsText;
    private int puntuacio;
    private int temps;
    // Start is called before the first frame update
    void Start()
    {
        puntuacio = 0;
        temps = -1;
        puntuacioText.text = "Puntuació: " + puntuacio.ToString();
        tempsText.text = "Temps: " + temps.ToString() + "s";
        StartCoroutine(time());
    }

    private IEnumerator time()
    {
        while (true)
        {
            temps++;
            tempsText.text = "Temps: " + temps.ToString() + "s";
            yield return new WaitForSeconds(1f);
        }
    }

    public void SumarPunts()
    {
        puntuacio += 5;
        puntuacioText.text = "Puntuació: " + puntuacio.ToString();
    }
}
