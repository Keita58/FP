using UnityEngine;

public class CanviSkin : MonoBehaviour
{
    [SerializeField] JugadorSO pre;
    [SerializeField] Sprite post;
    [SerializeField] GameObject jugador;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        pre.sprite = post;
        jugador.GetComponent<SpriteRenderer>().sprite = post;
    }

    
}
