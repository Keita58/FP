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
    float _Count;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    private void Start()
    {
        _Min = 2.0f;
        _Max = 6.0f;
        _Count = 0;
        StartCoroutine(crear());
    }

    IEnumerator crear()
    {
        while (true)
        {
            float num = Random.Range(_Min, _Max);
            yield return new WaitForSeconds(num);
            int enemicNum = Random.Range(0, 2);
            GameObject enemicGO = _Pool.GetElement();

            if (enemicGO == null)
                continue;

            enemicGO.SetActive(true);
            enemicGO.GetComponent<IPoolable>().OnDestroyed += ReturnEnemicToPool;

            float posX = Random.Range(-2.6f, 2.6f);
            enemicGO.transform.position = new Vector2(posX, 5.6f);
            enemicGO.transform.tag = "Enemic";

            enemicGO.GetComponent<Enemic>().setVides(_Enemics[enemicNum].vides);
            enemicGO.GetComponent<Enemic>().setMal(_Enemics[enemicNum].mal);
            enemicGO.GetComponent<Enemic>().setPunts(_Enemics[enemicNum].punts);
            enemicGO.GetComponent<Enemic>().setVelocitat(_Enemics[enemicNum].velocitat);
            enemicGO.GetComponent<Enemic>().setAnimacio(_Enemics[enemicNum].animacio);
            enemicGO.GetComponent<Enemic>().setRadiDeteccio(_Enemics[enemicNum].radiDeteccio);
            enemicGO.GetComponent<Enemic>().setRadiAtac(_Enemics[enemicNum].radiAtac);

            //enemicGO.GetComponent<SpriteRenderer>().sprite = _Enemics[enemicNum].sprite;
            //enemicGO.GetComponentInChildren<HealthBar>().IniciarBarra(_Enemics[enemicNum].vides);
            //_Count++;

            // Això és el comptador de les rondes, per a que vagi més ràpid
            /*
            if (_Count % ronda == 0 && _Max > 0.5f)
            {
                if (_Min > 0f)
                    _Min -= 0.5f;
                _Max -= 0.5f;
                ronda += 15 * numRonda;
                rondaEnemics++;
                numRonda++;
            }

            switch (rondaEnemics)
            {
                case 0:
                    enemicGO.GetComponent<SpriteRenderer>().color = Color.white;
                    break;
                case 1:
                    enemicGO.GetComponent<SpriteRenderer>().color = Color.green;
                    break;
                case 2:
                    enemicGO.GetComponent<SpriteRenderer>().color = Color.blue;
                    break;
                case 3:
                    enemicGO.GetComponent<SpriteRenderer>().color = Color.yellow;
                    break;
                case 4:
                    enemicGO.GetComponent<SpriteRenderer>().color = Color.red;
                    break;
                case 5:
                    enemicGO.GetComponent<SpriteRenderer>().color = Color.gray;
                    break;
                case 6:
                    enemicGO.GetComponent<SpriteRenderer>().color = Color.black;
                    break;
                default:
                    enemicGO.GetComponent<SpriteRenderer>().color = new Color(Random.Range(0, 1f), Random.Range(0, 1f), Random.Range(0, 1f));
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
