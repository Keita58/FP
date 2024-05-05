using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEditor.SearchService;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class UImanager : MonoBehaviour
{
    [SerializeField] private TextMeshProUGUI puntuacioText;
    [SerializeField] private TextMeshProUGUI tempsText;
    [SerializeField] private TextMeshProUGUI puntuacioFinal;
    [SerializeField] private TextMeshProUGUI tempsFinal;
    private int puntuacio;
    private int temps;
    private int puntuaciofinal;
    private int tempsfinal;
    // Start is called before the first frame update
    void Start()
    {
        puntuacio = 0;
        temps = -1;
        puntuacioText.text = "Puntuació: " + puntuacio.ToString();
        tempsText.text = "Temps: " + temps.ToString() + "s";
        StartCoroutine(time());
    }

    void Update()
    {
        if (SceneManager.GetActiveScene().name == "Mort")
        {
            tempsFinal.text = "Temps final: " + tempsfinal.ToString() + "s";
            puntuacioFinal.text = "Puntuació final: " + puntuaciofinal.ToString();
        }
    }

    private IEnumerator time()
    {
        while (true)
        {
            temps++;
            tempsfinal++;
            tempsText.text = "Temps: " + temps.ToString() + "s";
            yield return new WaitForSeconds(1f);
        }
    }

    public void SumarPunts()
    {
        puntuacio += 5;
        puntuaciofinal += 5;
        puntuacioText.text = "Puntuació: " + puntuacio.ToString();
    }
}
