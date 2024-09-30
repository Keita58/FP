using UnityEngine;

public class Enemic : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    public int mal;
    public int vides;
    public int punts;
    public int videsMax;
    [SerializeField] private GameEvent _Event;
    [SerializeField] HealthBar vidaPantalla;

    void Start()
    {
        videsMax = vides;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.transform.tag == "Player")
        {
            Destroy(this.gameObject);
            _Event.Raise();
        }
        if (collision.transform.tag == "Mort")
        {
            Destroy(this.gameObject);
        }
        if (collision.transform.tag == "BalaAmiga")
        {
            if (vides == 1)
            {
                _Event.Raise(punts);
                Destroy(this.gameObject);
            }
            else
            {
                vides--;
                vidaPantalla.UpdateHealth();
            }
            Destroy(collision.gameObject);
        }
    }
}
