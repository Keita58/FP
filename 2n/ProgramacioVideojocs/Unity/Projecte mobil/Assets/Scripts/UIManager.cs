using System.Collections;
using TMPro;
using UnityEditor;
using UnityEngine;
using UnityEngine.SceneManagement;

public class UIManager : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    [SerializeField] TextMeshProUGUI _Puntuacio;
    [SerializeField] TextMeshProUGUI _Vida;
    [SerializeField] PuntsSO _PuntuacioSO;
    int _Punts;
    int _Vides;
    int _Segons;

    void Start()
    {
        _Punts = 0;
        _Vides = 5;
        _Segons = 0;
        StartCoroutine(temps());
    }

    private IEnumerator temps()
    {
        while (true)
        {
            _Segons++;
            yield return new WaitForSeconds(1f);
        }
    }

    public void IncrementarPuntuacio(int laguarrada)
    {
        _Punts += laguarrada;
        _Puntuacio.text = "Punts: " + (_Punts).ToString();
        _PuntuacioSO.puntuacio = _Punts;
    }

    public void RestaVida()
    {
        if (_Vides > 1)
        {
            _Vida.text = "Vida: " + (--_Vides).ToString();
        }
        else
        {
            _PuntuacioSO.temps = _Segons;
            SceneManager.LoadScene("Fi");
        }
    }
}
