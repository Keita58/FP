using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class cano : MonoBehaviour
{
    public float speedMovement;
    public float speedBullet;
    public GameObject bullet;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        Vector2 mousePosition = Camera.main.ScreenToWorldPoint(Input.mousePosition);
        Vector2 direction = (mousePosition - (Vector2)transform.position);
        direction.Normalize();
        transform.up = direction*(speedMovement/100);

        if(Input.GetMouseButtonDown(0)) 
        {
            Instantiate(bullet);
            GameObject bulletNew = bullet;
            bulletNew.transform.position = new Vector2(transform.position.x, transform.position.y + 0.5f);
            bulletNew.GetComponent<Rigidbody2D>().velocity = transform.up * speedBullet;
        }
    }
}
