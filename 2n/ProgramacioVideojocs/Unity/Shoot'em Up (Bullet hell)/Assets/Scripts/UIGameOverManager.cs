using UnityEngine;
using TMPro;

public class UIGameOverManager : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI Rondes;
    [SerializeField] PuntsSO RondaFinal;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        Rondes.text = "Has arribat fins l'onada " + RondaFinal.Ronda.ToString();
    }
}
