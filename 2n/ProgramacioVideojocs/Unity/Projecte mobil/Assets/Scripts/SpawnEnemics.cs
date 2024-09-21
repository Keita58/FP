using System.Collections;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;

public class SpawnEnemics : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    [SerializeField] EnemicsSO[] _Enemic;
    [SerializeField] Meteorits[] _Meteorit;
    [SerializeField] Boses[] _Boss;
    [SerializeField] GameObject _EnemicGO;
    // Enemics -> 5 - Meteorits -> 4 - Boses -> 3

    void Start()
    {
        StartCoroutine(crear());
        //StartCoroutine(boss());
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    IEnumerator crear()
    {
        while (true)
        {
            int num = Random.Range(2, 4);
            yield return new WaitForSeconds(num);
            int enemicNum = Random.Range(0, 5);
            GameObject enemicNau = Instantiate(_EnemicGO);

            float posX = Random.Range(-2.8f, 3.0f);
            enemicNau.transform.position = new Vector2(posX, 5.6f);
            enemicNau.transform.tag = "Enemic";
            enemicNau.GetComponent<Rigidbody2D>().velocity = -_Enemic[enemicNum].velocitat * enemicNau.transform.up;
            enemicNau.GetComponent<Enemic>().mal = _Enemic[enemicNum].mal;
            enemicNau.GetComponent<Enemic>().vides = _Enemic[enemicNum].vides;
            enemicNau.GetComponent<Enemic>().punts = _Enemic[enemicNum].punts;
            enemicNau.GetComponent<SpriteRenderer>().sprite = _Enemic[enemicNum].sprite;
            enemicNau.transform.Rotate(0, 0, 180);            
        }
    }

    IEnumerator boss()
    {
        yield return new WaitForSeconds(60);
    }
}
