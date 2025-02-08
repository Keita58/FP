using System.Collections;
using UnityEngine;

public class Point : MonoBehaviour
{
    [SerializeField] private Light llum;

    private void Start()
    {
        StartCoroutine(Canvi());
    }

    IEnumerator Canvi()
    {
        while (true)
        {
            yield return new WaitForSeconds(1);
            llum.intensity = 100000;
            yield return new WaitForSeconds(1);
            llum.intensity = 0;
        }
    }
}
