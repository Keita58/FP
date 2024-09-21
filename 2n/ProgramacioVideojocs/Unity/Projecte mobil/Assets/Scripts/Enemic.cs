using UnityEngine;

public class Enemic : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    public int mal;
    public int vides;
    public int punts;

    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.transform.tag == "Player")
        {
            Destroy(this.gameObject);
        }
    }
}
