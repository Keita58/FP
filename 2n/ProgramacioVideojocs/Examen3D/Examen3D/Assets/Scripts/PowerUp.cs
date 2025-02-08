using System;
using System.Collections;
using UnityEngine;

public class PowerUp : MonoBehaviour
{
    public event Action PowerActivat;

    private void OnTriggerEnter(Collider other)
    {
        if(other.transform.tag.Equals("pacman"))
        {
            PowerActivat?.Invoke();
            gameObject.SetActive(false);
        }
    }
}
