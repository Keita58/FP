using UnityEngine;
using UnityEngine.SceneManagement;

public class CanviEscena : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    public void Inici()
    {
        SceneManager.LoadScene("Inici");
    }

    public void Joc()
    {
        SceneManager.LoadScene("Joc");
    }

    public void Cosmetic()
    {
        SceneManager.LoadScene("Cosmetic");
    }

    public void Classificacio()
    {
        SceneManager.LoadScene("Classificacio");
    }
}
