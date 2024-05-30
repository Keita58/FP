using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Velocitst : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (this.transform.position.x >= 9 && this.GetComponent<SpriteRenderer>().color == Color.green)
           SceneManager.LoadScene("A");
        this.GetComponent<Rigidbody2D>().AddForce(this.transform.right * 1.05f);
    }

    private void OnMouseDown()
    {
        if(this.GetComponent<SpriteRenderer>().color == Color.green)
            Destroy(this.gameObject);
        else if(this.GetComponent<SpriteRenderer>().color == Color.red)
            SceneManager.LoadScene("A");
    }
}
