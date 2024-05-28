using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Caixa : MonoBehaviour
{
    // Start is called before the first frame update
    [SerializeField] GameObject powerupOriginal;
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.transform.tag == "Bola")
        {
            Destroy(this.gameObject);
            int spawn = Random.Range(0, 2);
            if(spawn == 0)
            {
                GameObject powerup = Instantiate(powerupOriginal.gameObject);
                powerup.transform.position = this.transform.position;
                powerup.GetComponent<Rigidbody2D>().velocity = powerup.transform.up * -1;
                int color = Random.Range(0, 4);
                switch(color)
                {
                    case 0:
                        powerup.GetComponent<SpriteRenderer>().color = Color.blue;
                        break;
                    case 1:
                        powerup.GetComponent<SpriteRenderer>().color = Color.yellow;
                        break;
                    case 2:
                        powerup.GetComponent<SpriteRenderer>().color = Color.green;
                        break;
                    case 3:
                        powerup.GetComponent<SpriteRenderer>().color = Color.red;
                        break;
                }
            }
        }
    }
}
