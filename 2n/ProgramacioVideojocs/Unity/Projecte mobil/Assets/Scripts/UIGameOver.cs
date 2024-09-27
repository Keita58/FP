using TMPro;
using UnityEngine;

public class UIGameOver : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI _PuntuacioFinal;
    [SerializeField] TextMeshProUGUI _TempsFinal;
    [SerializeField] PuntsSO _PuntuacioSO;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        _PuntuacioFinal.text = "Punts totals: " + _PuntuacioSO.puntuacio;
        _TempsFinal.text = "Has durat un total de " + _PuntuacioSO.temps + " segons";
    }
}
