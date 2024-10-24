using System.Collections;
using Unity.VisualScripting;
using UnityEngine;

public class SpawnerEnemics : MonoBehaviour
{
    [SerializeField] EnemicSO[] _Enemics;
    [SerializeField] GameObject _EnemicGO;
    [SerializeField] Pool _Pool;
    float _Min;
    float _Max;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    private void Start()
    {
        _Min = 2.0f;
        _Max = 4.0f;
        StartCoroutine(crear());
    }

    IEnumerator crear()
    {
        while (true)
        {
            float num = Random.Range(_Min, _Max);
            print("Espera: " + num);
            yield return new WaitForSeconds(num);
            int enemicNum = Random.Range(0, 5);
            GameObject enemicNau = _Pool.GetElement();

            if (enemicNau == null)
                continue;

            enemicNau.SetActive(true);
            enemicNau.GetComponent<IPoolable>().OnDestroyed += ReturnEnemicToPool;

            float posX = Random.Range(-2.6f, 2.6f);
            enemicNau.transform.position = new Vector2(posX, 5.6f);
            enemicNau.transform.tag = "Enemic";
            /*
            enemicNau.GetComponent<Rigidbody2D>().velocity = _Enemic[enemicNum].velocitat * enemicNau.transform.up;
            enemicNau.GetComponent<Enemic>().mal = _Enemic[enemicNum].mal;
            enemicNau.GetComponent<Enemic>().vides = _Enemic[enemicNum].vides;
            enemicNau.GetComponent<Enemic>().punts = _Enemic[enemicNum].punts;
            enemicNau.GetComponent<SpriteRenderer>().sprite = _Enemic[enemicNum].sprite;
            enemicNau.GetComponentInChildren<HealthBar>().IniciarBarra(_Enemic[enemicNum].vides);
            count++;

            if (count % ronda == 0 && max > 0.5f)
            {
                if (min > 0f)
                    min -= 0.5f;
                max -= 0.5f;
                ronda += 15 * numRonda;
                rondaEnemics++;
                numRonda++;
            }

            switch (rondaEnemics)
            {
                case 0:
                    enemicNau.GetComponent<SpriteRenderer>().color = Color.white;
                    break;
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
                default:
                    enemicNau.GetComponent<SpriteRenderer>().color = new Color(Random.Range(0, 1f), Random.Range(0, 1f), Random.Range(0, 1f));
                    break;
            }*/
        }
    }
    private void ReturnEnemicToPool(GameObject enemic)
    {
        enemic.GetComponent<IPoolable>().OnDestroyed -= ReturnEnemicToPool;
        enemic.SetActive(false);
        _Pool.ReturnElement(enemic);
    }
}
