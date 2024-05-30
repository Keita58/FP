using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Escut : MonoBehaviour
{
    public delegate void Delegat();
    public Delegat patata;
    public delegate void Delegat2();
    public Delegat2 guarda;
    int carregues;
    // Start is called before the first frame update
    void Start()
    {
        carregues = 7;
        patata += restaCarrega;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.transform.tag == "Enemic")
        {
            for(int i = 0; i < collision.gameObject.GetComponent<Nau>().mal; i++)
            {
                patata.Invoke();
            }
            Destroy(collision.gameObject);
        }
    }

    void restaCarrega()
    {
        if(carregues > 1)
            carregues--;
        else
        {
            guarda.Invoke();
            this.enabled = false;
            SceneManager.LoadScene("B");
        }
    }
}
