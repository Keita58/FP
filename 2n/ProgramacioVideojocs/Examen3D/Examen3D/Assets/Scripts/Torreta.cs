using System.Collections;
using UnityEngine;

public class Torreta : MonoBehaviour
{
    [SerializeField] private GameObject _Pacman;
    [SerializeField] private LayerMask _Layers;
    [SerializeField] private GameObject _Projectil;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        StartCoroutine(Mirar());
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    IEnumerator Mirar()
    {
        while (true)
        {
            yield return new WaitForSeconds(0.5f);
            transform.LookAt(_Pacman.transform.position);
            if (Physics.Raycast(transform.position, transform.forward, out RaycastHit info, 15f, _Layers))
            {
                if (info.transform.tag.Equals("pacman"))
                {
                    GameObject p = Instantiate(_Projectil, transform.position, Quaternion.identity);
                    p.transform.position = new Vector3(p.transform.position.x, transform.position.y, p.transform.position.z);
                    p.GetComponent<Rigidbody>().linearVelocity = transform.forward * 2;
                    
                }
            }
        }
    }
}
