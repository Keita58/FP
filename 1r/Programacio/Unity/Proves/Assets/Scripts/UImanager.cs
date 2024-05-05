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
    [SerializeField] private TextMeshProUGUI vides;
    [SerializeField] private TextMeshProUGUI bales;
    public GameObject player;
    private GameObject playerBarrel;
    private int puntuacio;
    private int temps;
    // Start is called before the first frame update
    void Start()
    {
        puntuacio = 0;
        temps = -1;
        puntuacioText.text = "Puntuacio: " + puntuacio.ToString();
        tempsText.text = "Temps: " + temps.ToString() + "s";
        StartCoroutine(time());
        playerBarrel = GameObject.Find("/Personatge/Cano");
    }

    void Update()
    {
        if (SceneManager.GetActiveScene().name == "Mort")
        {
            tempsFinal.text = "Temps final: " + InfoCompartida.tempsFinal.ToString() + "s";
            puntuacioFinal.text = "Puntuacio final: " + InfoCompartida.puntsFinal.ToString();
        }
        vides.text = "Vides: " + player.GetComponent<Tanc>().getVides();
        bales.text = "Bales: " + playerBarrel.GetComponent<Cano>().getBales();
    }

    private IEnumerator time()
    {
        while (true)
        {
            temps++;
            InfoCompartida.tempsFinal++;
            tempsText.text = "Temps: " + temps.ToString() + "s";
            yield return new WaitForSeconds(1f);
        }
    }

    public void SumarPunts(int punts)
    {
        puntuacio += punts;
        InfoCompartida.puntsFinal += punts;
        puntuacioText.text = "Puntuacio: " + puntuacio.ToString();
    }
}
