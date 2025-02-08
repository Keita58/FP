using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TorretaMK1 : MonoBehaviour
{
    [SerializeField]
    private GameObject m_VisorTorreta;

    [SerializeField] private LayerMask _LayerJugador;
    private float x;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if(Physics.Raycast(transform.position, transform.forward, out RaycastHit info, 15f, _LayerJugador))
        {
            Debug.DrawLine(transform.position, info.transform.position);
            StartCoroutine(Mor());
        }
        else
        {
            x = Mathf.PingPong(Time.time * 10, 60) - 30;
            m_VisorTorreta.transform.rotation = Quaternion.Euler(0, x, 0);
        }
        /**
         * Això és per substituïr el PingPong, fa el mateix
         * 
         * if(x >= 30) {
         *  direccio = -1;
         * }
         * else if(x <= -30) {
         *  direccio = 1;
         * }
         * x += Time.deltatime*10*direccio;
         */
    }

    IEnumerator Mor()
    {
        yield return new WaitForSeconds(1);
        if(Physics.Raycast(transform.position, transform.forward, out RaycastHit info, 15f))
        {
            Debug.DrawRay(transform.position, info.transform.position, Color.red);
            info.transform.gameObject.TryGetComponent<Chell>(out Chell chell);
            if(chell != null) 
                chell.GameOver();
        }
    }
}