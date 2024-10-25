using System;
using TMPro;
using UnityEngine;

public class UIManager : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI _Vides;
    [SerializeField] TextMeshProUGUI _Rondes;
    [SerializeField] Personatge _Jugador;
    [SerializeField] GameObject _Spawner;
    [SerializeField] PuntsSO RondaFinal;

    private int _VidesPersonatge;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        _Vides.text = "Vides: " + _Jugador.getVides().ToString();
        _Rondes.text = "1a Ronda";
        _Spawner.GetComponent<SpawnerEnemics>().augmentarRondaDelegat += actualitzaRonda;
        _VidesPersonatge = _Jugador.getVides();
    }

    public void restaVides(int damage)
    {
        _VidesPersonatge -= damage;
        _Vides.text = "Vides: " + _VidesPersonatge.ToString();
    }

    public void actualitzaRonda(int ronda)
    {
        RondaFinal.Ronda = (ronda + 1);
        Debug.Log(RondaFinal.Ronda);
        _Rondes.text = (ronda + 1).ToString() + "a Ronda";
    }
}
