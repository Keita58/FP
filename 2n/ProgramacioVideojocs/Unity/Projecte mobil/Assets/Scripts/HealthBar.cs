using UnityEngine;
using UnityEngine.UI;

public class HealthBar : MonoBehaviour
{
    private float restarBarra;
    private Vector3 escalaBarra;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    public void IniciarBarra(int vidaEnemic)
    {
        escalaBarra = transform.localScale;
        restarBarra = escalaBarra.x / vidaEnemic;
    }

    public void UpdateHealth()
    {
        this.transform.localScale -= new Vector3(restarBarra, 0, 0);
    }

    public void RetornALaPull()
    {
        transform.localScale = escalaBarra;
    }
}
