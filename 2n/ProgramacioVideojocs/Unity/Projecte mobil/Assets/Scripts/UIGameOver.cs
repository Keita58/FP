using System.IO;
using TMPro;
using UnityEngine;

public class UIGameOver : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI _PuntuacioFinal;
    [SerializeField] TextMeshProUGUI _TempsFinal;
    [SerializeField] PuntsSO _PuntuacioSO;
    [SerializeField] TextAsset json;
    Classificacio fiPartida = new Classificacio();

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        Classificacio.Info infoJugador = new Classificacio.Info();
        _PuntuacioFinal.text = "Punts totals: " + _PuntuacioSO.puntuacio;
        _TempsFinal.text = "Has durat un total de " + _PuntuacioSO.temps + " segons";
        infoJugador.temps = _PuntuacioSO.temps;
        infoJugador.punts = _PuntuacioSO.puntuacio;

        Classificacio llista = JsonUtility.FromJson<Classificacio>(json.ToString());
        llista.list.Add(infoJugador);
        JsonUtility.ToJson(llista, true);
    }
}
