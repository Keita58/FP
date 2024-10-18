using System;
using UnityEngine;

public class AreaDeteccio : MonoBehaviour
{
    public event Action<GameObject> OnEnter;
    public event Action<GameObject> OnExit;

    private void OnTriggerEnter2D(Collider2D collision)
    {
        OnEnter?.Invoke(collision.gameObject);
    }

    private void OnTriggerExit2D(Collider2D collision)
    {
        OnExit?.Invoke(collision.gameObject);
    }
}
