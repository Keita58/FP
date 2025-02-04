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

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0)) // Botó esquerre
        {
            if(Physics.Raycast(transform.position, _Camera.transform.forward, out RaycastHit info, float.PositiveInfinity, _LayerParets))
            {
                if (_PortalBlau != null)
                {
                    Destroy(_PortalBlau);
                }
                else
                    print("Portal blau buit!");
                
                _PortalBlau = Instantiate(m_PortalBlauPrefab, transform.position + (_Camera.transform.forward * info.distance), info.transform.rotation);
            }
        }
        else if (Input.GetMouseButtonDown(1)) // Botó dret
        {
            if (Physics.Raycast(transform.position, _Camera.transform.forward, out RaycastHit info, float.PositiveInfinity, _LayerParets))
            {
                if (_PortalTaronja != null)
                {
                    Destroy(_PortalTaronja);
                }
                else
                    print("Portal taronja buit!");

                _PortalTaronja = Instantiate(m_PortalTaronjaPrefab, transform.position + (_Camera.transform.forward * info.distance), info.transform.rotation);
            }
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.transform.tag == "portal")
        {
            print("He tocat un portal!");

            if (other.transform.name == "PortalBlau")
            {
                transform.position = _PortalTaronja.transform.position + new Vector3(0, 0, 1 * Mathf.Sign(_PortalTaronja.transform.forward.z));
            }
            else
            {
                transform.position = _PortalBlau.transform.position + new Vector3(0, 0, 1 * Mathf.Sign(_PortalTaronja.transform.forward.z));
            }
        }
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
