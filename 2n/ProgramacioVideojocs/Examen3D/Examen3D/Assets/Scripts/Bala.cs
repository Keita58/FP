using UnityEngine;

public class Bala : MonoBehaviour
{
    private void OnTriggerEnter(Collider other)
    {
        if(other.transform.tag.Equals("pacman"))
        {
            Destroy(other.gameObject);
        }
    }
}
