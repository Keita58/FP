using UnityEngine;

public class SeguirJugador : MonoBehaviour
{
    [SerializeField] GameObject Jugador;
    
    // Update is called once per frame
    void Update()
    {
        this.transform.position = new Vector3(Jugador.transform.position.x, Jugador.transform.position.y, this.transform.position.z);
    }
}
