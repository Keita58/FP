using System.Collections;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;

public class SpawnEnemics : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    [SerializeField] EnemicsSO[] _Enemic;
    [SerializeField] GameObject _EnemicGO;
    [SerializeField] PoolBales _Pool;
    float min;
    float max;
    int count;

    void Start()
    {   
        min = 2.0f;
        max = 4.0f;
        count = 0;
        StartCoroutine(crear());
    }

    IEnumerator crear()
    {
        while (true)
        {
            float num = Random.Range(min, max);
            yield return new WaitForSeconds(num);
            int enemicNum = Random.Range(0, 5);
            GameObject enemicNau = _Pool.GetElement();
            enemicNau.SetActive(true);
            enemicNau.GetComponent<IPoolable>().OnDestroyed += ReturnEnemicToPool;

            float posX = Random.Range(-2.6f, 2.6f);
            enemicNau.transform.position = new Vector2(posX, 5.6f);
            enemicNau.transform.tag = "Enemic";
            
            enemicNau.GetComponent<Rigidbody2D>().velocity = _Enemic[enemicNum].velocitat * enemicNau.transform.up;
            enemicNau.GetComponent<Enemic>().mal = _Enemic[enemicNum].mal;
            enemicNau.GetComponent<Enemic>().vides = _Enemic[enemicNum].vides;
            enemicNau.GetComponent<Enemic>().punts = _Enemic[enemicNum].punts;
            enemicNau.GetComponent<SpriteRenderer>().sprite = _Enemic[enemicNum].sprite;
            enemicNau.GetComponentInChildren<HealthBar>().IniciarBarra(_Enemic[enemicNum].vides);
            count++;

            int ronda = 10;
            int diff = 2;

            if(count%ronda == 0 && max > 0.5f)
            {
                print(count);
                if(min > 1f)
                    min -= 0.5f;
                max -= 0.5f;
                ronda += 10*diff;
                diff++;
            }

            int aux = (count / ronda);
            
            switch (aux)
            {
                case 1:
                    enemicNau.GetComponent<SpriteRenderer>().color = Color.green;
                    break;
                case 2:
                    enemicNau.GetComponent<SpriteRenderer>().color = Color.blue;
                    break;
                case 3:
                    enemicNau.GetComponent<SpriteRenderer>().color = Color.yellow;
                    break;
                case 4:
                    enemicNau.GetComponent<SpriteRenderer>().color = Color.red;
                    break;
                case 5:
                    enemicNau.GetComponent<SpriteRenderer>().color = Color.gray;
                    break;
                case 6:
                    enemicNau.GetComponent<SpriteRenderer>().color = Color.black;
                    break;
            }
        }
    }

    private void ReturnEnemicToPool(GameObject enemic)
    {
        enemic.GetComponent<IPoolable>().OnDestroyed -= ReturnEnemicToPool;
        enemic.SetActive(false);
        _Pool.ReturnElement(enemic);
    }
}
