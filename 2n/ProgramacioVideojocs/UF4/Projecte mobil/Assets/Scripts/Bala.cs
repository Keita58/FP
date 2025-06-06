using System;
using UnityEngine;

public class Bala : MonoBehaviour, IPoolable
{
    public event Action<GameObject> OnDestroyed;
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.transform.tag == "EliminaBala" || collision.transform.tag == "Enemic")
        {
            OnDestroyed?.Invoke(this.gameObject); 
        }
    }
}
