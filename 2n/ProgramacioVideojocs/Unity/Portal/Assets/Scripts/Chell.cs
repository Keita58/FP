using System;
using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

public class Chell : MonoBehaviour
{
    [SerializeField]
    private GameObject m_PortalBlauPrefab;
    private GameObject m_PortalBlau;
    [SerializeField]
    private GameObject m_PortalTaronjaPrefab;
    private GameObject m_PortalTaronja;

    [SerializeField] private LayerMask _LayerParets;
    [SerializeField] private Camera _Camera;
    private GameObject _PortalBlau;
    private GameObject _PortalTaronja;
    private bool _Teletransport;

    // Start is called before the first frame update
    void Start()
    {
        _Teletransport = true;
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0)) // Botó esquerre
        {
            if(Physics.Raycast(transform.position, _Camera.transform.forward, out RaycastHit info, float.PositiveInfinity, _LayerParets))
            {
                if (_PortalBlau == null)
                {
                    _PortalBlau = Instantiate(m_PortalBlauPrefab, transform.position + (_Camera.transform.forward * info.distance), Quaternion.identity);
                }
                else
                {
                    _PortalBlau.transform.position = transform.position + (_Camera.transform.forward * info.distance);
                }

                _PortalBlau.transform.forward = info.normal;

                if (_PortalTaronja != null)
                {
                    _PortalTaronja.transform.GetComponentInChildren<Light>().transform.LookAt(_PortalBlau.transform);
                    _PortalBlau.transform.GetComponentInChildren<Light>().transform.LookAt(_PortalTaronja.transform);
                }
            }
        }
        else if (Input.GetMouseButtonDown(1)) // Botó dret
        {
            if (Physics.Raycast(transform.position, _Camera.transform.forward, out RaycastHit info, float.PositiveInfinity, _LayerParets))
            {
                if (_PortalTaronja == null)
                {
                    _PortalTaronja = Instantiate(m_PortalTaronjaPrefab, transform.position + (_Camera.transform.forward * info.distance), Quaternion.identity);
                }
                else
                {
                    _PortalTaronja.transform.position = transform.position + (_Camera.transform.forward * info.distance);
                }

                _PortalTaronja.transform.forward = info.normal;

                if (_PortalBlau != null)
                {
                    _PortalTaronja.transform.GetComponentInChildren<Light>().transform.LookAt(_PortalBlau.transform);
                    _PortalBlau.transform.GetComponentInChildren<Light>().transform.LookAt(_PortalTaronja.transform);
                }
            }
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.transform.tag == "portal")
        {
            print("He tocat un portal!");

            if (other.transform.name == "PortaBlau(Clone)" && _Teletransport)
            {
                float velocitat = GetComponent<CharacterController>().velocity.magnitude;
                this.GetComponent<CharacterController>().enabled = false;
                transform.position = _PortalTaronja.transform.position + _PortalTaronja.transform.forward;
                transform.forward = transform.forward * velocitat;
                this.GetComponent<CharacterController>().enabled = true;
                _Teletransport = false;
                StartCoroutine(Canvia());
            }
            else if(_Teletransport)
            {
                float velocitat = GetComponent<CharacterController>().velocity.magnitude;
                this.GetComponent<CharacterController>().enabled = false;
                transform.position = _PortalBlau.transform.position + _PortalBlau.transform.forward;
                transform.forward = transform.forward * velocitat;
                this.GetComponent<CharacterController>().enabled = true;
                _Teletransport = false;
                StartCoroutine(Canvia());
            }
        }
    }

    IEnumerator Canvia()
    {
        yield return new WaitForSeconds(2);
        _Teletransport = true;
    }

    private void OnCollisionEnter(Collision collision)
    {
        print("Chell: " + collision.transform.tag);

        if (collision.transform.tag == "cake")
        {
            print("The cake is a lie!");
        }
        else if(collision.transform.tag == "party")
        {
            print("Ohno!");
            GameOver();
        }
    }

    public void GameOver()
    {
        print("Game over");
        Destroy(this.gameObject);
    }
}
