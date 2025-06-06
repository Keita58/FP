using System;
using UnityEngine;

public class Enemic : MonoBehaviour, IPoolable
{
    public event Action<GameObject> OnDestroyed;
    public int mal;
    public int vides;
    public int punts;
    private GameObject soToc;
    [SerializeField] private GameEvent _Event;
    [SerializeField] HealthBar vidaPantalla;

    void Start()
    {
        soToc = GameObject.Find("SoToc");
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.transform.tag == "Player")
        {
            vidaPantalla.RetornALaPull();
            OnDestroyed?.Invoke(this.gameObject);
            _Event.Raise();
        }
        if (collision.transform.tag == "Mort")
        {
            vidaPantalla.RetornALaPull();
            OnDestroyed?.Invoke(this.gameObject);
        }
        if (collision.transform.tag == "BalaAmiga")
        {
            if (vides == 1)
            {
                _Event.Raise(punts);
                vidaPantalla.RetornALaPull();
                OnDestroyed?.Invoke(this.gameObject);
                soToc.GetComponent<AudioSource>().Play();
            }
            else
            {
                vides--;
                vidaPantalla.UpdateHealth();
            }
        }
    }
}
