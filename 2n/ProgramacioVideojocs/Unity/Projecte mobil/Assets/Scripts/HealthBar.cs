using UnityEngine;
using UnityEngine.UI;

public class HealthBar : MonoBehaviour
{
    private int vidaMax;
    [SerializeField] Enemic enemic;
    private float restarBarra;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    public void Start()
    {
        vidaMax = enemic.videsMax;
        restarBarra = 0.36f / vidaMax;
    }

    public void UpdateHealth()
    {
        this.transform.localScale -= new Vector3(restarBarra, 0, 0);
    }
}
