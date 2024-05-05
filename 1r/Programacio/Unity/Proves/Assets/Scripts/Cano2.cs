using System;
using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

public class Cano2 : MonoBehaviour
{
    public float speedBullet;
    public GameObject bullet;
    public GameObject tank;
    public GameObject target;
    public float secondsToShoot;
    private Vector2 distancia;

    // Start is called before the first frame update
    void Start()
    {
        target = GameObject.FindGameObjectWithTag("Player");
        StartCoroutine(disparar());
    }

    private IEnumerator disparar()
    {
        while (true)
        {
            yield return new WaitForSeconds(secondsToShoot);
            distancia.Normalize();
            GameObject bulletNew = Instantiate(bullet, new Vector2(tank.transform.position.x + (distancia.x / 1.38f), tank.transform.position.y + (distancia.y / 1.38f)), transform.rotation);
            bulletNew.GetComponent<Rigidbody2D>().velocity = transform.up * speedBullet;
            GetComponent<AudioSource>().Play();
        }
    }

    // Update is called once per frame
    void Update()
    {
        distancia = target.transform.position - this.transform.position;
        this.transform.up = distancia;
    }
}
