using System;
using System.Collections;
using UnityEngine;

public class Bala : MonoBehaviour, IPoolable, IAttack
{
    public event Action<GameObject> OnDestroyed;
    private float _Damage = 0;

    private void OnEnable()
    {
        StartCoroutine(Desapareixer());
    }

    public float Damage
    {
        set => _Damage = value;
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.TryGetComponent<IDamageable>(out IDamageable objective))
        {
            objective.RebreMal(_Damage);
            OnDestroyed?.Invoke(this.gameObject);
        }
    }

    IEnumerator Desapareixer()
    {
        yield return new WaitForSeconds(5);
        OnDestroyed?.Invoke(this.gameObject);
    }
}
