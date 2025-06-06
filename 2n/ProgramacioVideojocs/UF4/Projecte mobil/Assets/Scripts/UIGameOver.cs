using System.IO;
using TMPro;
using UnityEngine;

public class UIGameOver : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI _PuntuacioFinal;
    [SerializeField] TextMeshProUGUI _TempsFinal;
    [SerializeField] PuntsSO _PuntuacioSO;
    [SerializeField] TextAsset json;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        Classificacio.Info infoJugador = new Classificacio.Info();
        _PuntuacioFinal.text = "Punts totals: " + _PuntuacioSO.puntuacio;
        _TempsFinal.text = "Has durat un total de " + _PuntuacioSO.temps + " segons";
        infoJugador.temps = _PuntuacioSO.temps;
        infoJugador.punts = _PuntuacioSO.puntuacio;
        print(json.ToString());
        Classificacio llista = new Classificacio();
        if(JsonUtility.FromJson<Classificacio>(json.ToString()) != null)
            llista = JsonUtility.FromJson<Classificacio>(json.ToString());

        llista.list.Add(infoJugador);
        print(llista.list);
        string info = JsonUtility.ToJson(llista, true);
        //Per guardar la info a l'arxiu s'utilitza el File.WriteAllText (https://learn.microsoft.com/en-us/dotnet/api/system.io.file.writealltext?view=netframework-4.8)
        File.WriteAllText("./Assets/JSON/ClassificacioJoc.json", info);
    }
}
