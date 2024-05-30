using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Nau : MonoBehaviour
{
    public int mal;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.transform.tag == "Escut") 
        {
            Destroy(this.gameObject);
        }
        /*else if(collision.transform.tag == "Player")
            Destroy(collision.gameObject);*/
    }
}
