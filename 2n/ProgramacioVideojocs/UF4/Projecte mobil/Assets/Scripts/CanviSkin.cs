using UnityEngine;
using UnityEngine.EventSystems;


public class CanviSkin : MonoBehaviour, IPointerClickHandler
{
    [SerializeField] Jugador _Jugador;

    public void OnPointerClick(PointerEventData eventData)
    {
        _Jugador.SetSkin(GetComponent<SpriteRenderer>().sprite);
        GetComponent<AudioSource>().Play();
    }
}
