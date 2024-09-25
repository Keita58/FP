using UnityEngine;
using UnityEngine.SceneManagement;

public class CanviEscena : MonoBehaviour
{
    [SerializeField] GameObject personatge;
    [SerializeField] JugadorSO jSO;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    public void Inici()
    {
        SceneManager.LoadScene("Inici");
        GameObject pers = Instantiate(personatge);
    }

    public void Joc()
    {
        SceneManager.LoadScene("Joc");
    }

    public void Cosmetic()
    {
        SceneManager.LoadScene("Cosmetic");
    }
}
