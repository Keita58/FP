using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Retry : MonoBehaviour
{
    public void Load()
    {
        SceneManager.LoadScene("Inici");
        InfoCompartida.puntsFinal = 0;
        InfoCompartida.tempsFinal = 0;
    }

    public void Exit()
    {
        Application.Quit();
    }
}
