using System.Linq;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using static Classificacio;

public class InfoClassificacio : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI _Classificacio;
    [SerializeField] TextMeshProUGUI _Punts;
    [SerializeField] TextMeshProUGUI _Segons;
    [SerializeField] TextAsset _JSON;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        Classificacio llista = new Classificacio();
        string aux = "\n\n";
        string auxPunts = "PUNTS\n\n";
        string auxSeg = "SEGONS\n\n";
        if (JsonUtility.FromJson<Classificacio>(_JSON.ToString()) != null) { 
            llista = JsonUtility.FromJson<Classificacio>(_JSON.ToString());

            llista.list.OrderBy(x => x.punts);
            int pos = 1;
            foreach (Info jugador in llista.list)
            {
                print(jugador.ToString());
                aux += pos;
                auxPunts += jugador.punts.ToString();
                auxSeg += jugador.temps.ToString() + "s";
                pos++;
                aux += "\n";
                auxPunts += "\n";
                auxSeg += "\n";
            }
            print("Classificacio: " + _Classificacio.text);
        }
        _Classificacio.text = aux;
        _Punts.text = auxPunts;
        _Segons.text = auxSeg;
    }
}
