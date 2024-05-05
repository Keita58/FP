using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Cano : MonoBehaviour
{
    public float speedBullet;
    public GameObject bullet;
    public GameObject tank;
    private int totalBales;
    private int balesUsades;

    // Start is called before the first frame update
    void Start()
    {
        this.totalBales = 5;
    }

    // Update is called once per frame
    void Update()
    {
        GameObject[] bales = GameObject.FindGameObjectsWithTag("BalaBona");
        balesUsades = bales.Length;

        // Agafa les coordenades del ratolí (estigui on estigui) i les transforma a coordenades del mapa
        Vector2 mousePosition = Camera.main.ScreenToWorldPoint(Input.mousePosition);

        // Restem la posició actual del ratolí entre la posició actual del tanc
        Vector2 direction = mousePosition - (Vector2)transform.position;

        // Assignem la direcció del canó a la calculada anteriorment
        transform.up = direction;  

        if(Input.GetMouseButtonDown(0)) 
        {
            if (balesUsades < 5)
            {
                GetComponent<AudioSource>().Play();
                // Per a evitar que les bales apareguin dins del tanc podem sumar el seu punt d'aparició per la direcció calculada
                // anteriorment, el que ens dóna un radi pel que les bales dispararan
                // Però aquesta direcció no està normalitzada i les bales surten del mapa, pel que necessitem normalitzar-la abans
                direction.Normalize();
                // Dividim la direcció per a reduïr el radi d'on aparèixen les bales (el número escollit és perquè és el radi més petit que no toca
                // el tanc i que sembla que surti directament del canó)
                GameObject bulletNew = Instantiate(bullet, new Vector2(tank.transform.position.x + (direction.x / 1.38f), tank.transform.position.y + (direction.y / 1.38f)), transform.rotation);
                bulletNew.GetComponent<Rigidbody2D>().velocity = transform.up * speedBullet;
            }
        }
    }

    internal int getBales()
    {
        return totalBales - balesUsades;
    }
}
