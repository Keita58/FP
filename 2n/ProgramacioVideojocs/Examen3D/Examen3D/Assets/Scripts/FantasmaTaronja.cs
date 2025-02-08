using m17;
using System.Collections;
using UnityEngine;
using UnityEngine.AI;

public class FantasmaTaronja : MonoBehaviour
{
    [SerializeField] private NavMeshAgent _Fantasma;

    [SerializeField] private GameObject Power1;
    [SerializeField] private GameObject Power2;

    private bool _Power;

    private void Awake()
    {
        _Power = false;
        Power1.GetComponent<PowerUp>().PowerActivat += ActivaPower;
    }

    private void ActivaPower()
    {
        _Power = true;
        StartCoroutine(DesactivaPower());
    }

    IEnumerator DesactivaPower()
    {
        yield return new WaitForSeconds(5);
        _Power = false;
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.transform.tag.Equals("pacman") && !_Power)
            _Fantasma.SetDestination(other.transform.position);
        else if(_Power)
        {
            _Fantasma.SetDestination(_Fantasma.transform.position);
        }
    }

    private void OnTriggerStay(Collider other)
    {
        if (other.transform.tag.Equals("pacman") && !_Power)
            _Fantasma.SetDestination(other.transform.position);
        else if (_Power)
        {
            _Fantasma.SetDestination(_Fantasma.transform.position);
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.transform.tag.Equals("pacman"))
            _Fantasma.SetDestination(_Fantasma.transform.position);
    }
}
