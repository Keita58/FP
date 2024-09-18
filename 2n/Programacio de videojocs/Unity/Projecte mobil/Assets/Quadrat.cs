using UnityEngine;
using UnityEngine.InputSystem;

public class Quadrat : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    [SerializeField] GameObject bala;
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void moviment(InputAction.CallbackContext callback)
    {
        Vector2 direccio = callback.ReadValue<Vector2>();
        //print("Em moc! "+direccio);
        this.GetComponent<Rigidbody2D>().velocity = direccio*2;
    }

    public void dispara(InputAction.CallbackContext callback)
    {
        GameObject a = Instantiate(bala, new Vector2(this.transform.position.x, this.transform.position.y), transform.rotation);
        //a.transform.up = callback.ReadValue<Vector2>();
        a.GetComponent<Rigidbody2D>().velocity = transform.up*2;
    }
}
