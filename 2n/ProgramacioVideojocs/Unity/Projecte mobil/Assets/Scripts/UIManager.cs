using TMPro;
using UnityEditor;
using UnityEngine;
using UnityEngine.SceneManagement;

public class UIManager : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    [SerializeField] TextMeshProUGUI _Puntuacio;
    [SerializeField] TextMeshProUGUI _Vida;
    int _Punts = 0;
    int _Vides = 5;

    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void IncrementarPuntuacio(int laguarrada)
    {
        _Punts += laguarrada;
        _Puntuacio.text = "Punts: " + (_Punts).ToString();
    }

    public void RestaVida()
    {
        if (_Vides > 1) 
        {
            _Vida.text = "Vida: " + (--_Vides).ToString();
        }
        else
            SceneManager.LoadScene("Fi");
        
    }
}
