using System.Collections;
using UnityEngine;
using UnityEngine.AI;

public class FantasmaVermell : MonoBehaviour
{
    [SerializeField] private NavMeshAgent _Agent;
    [SerializeField] private GameObject _Pacman;

    [SerializeField] private GameObject Power1;
    [SerializeField] private GameObject Power2;

    private bool _Power;

    private void Awake()
    {
        _Power = false;
        Power1.GetComponent<PowerUp>().PowerActivat += ActivaPower;
    }

    // Update is called once per frame
    void Update()
    {
        if (!_Power)
        {
            _Agent.SetDestination(_Pacman.transform.position);
        }
        else
        {
            _Agent.SetDestination(_Pacman.transform.position + new Vector3(3, 0, 3));
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.transform.tag.Equals("pacman") && !_Power)
        {
            Destroy(other.gameObject);
        }
        else if (_Power)
        {
            Destroy(gameObject);
        }
    }

    private void ActivaPower()
    {
        transform.rotation = Quaternion.Euler(0, 180, 0);
        _Power = true;
        StartCoroutine(DesactivaPower());
    }

    IEnumerator DesactivaPower()
    {
        yield return new WaitForSeconds(5);
        _Power = false;
    }
}
