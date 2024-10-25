using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

public class SpawnerEnemics : MonoBehaviour
{
    [SerializeField] EnemicSO[] _Enemics;
    [SerializeField] GameObject _EnemicGO;
    [SerializeField] Pool _Pool;
    [SerializeField] GameObject _JugadorGO;
    float _Min;
    float _Max;
    float _Count;
    List<int> _Rondes;
    int _RondaActual;
    int _EnemicsVius;
    int _EnemicsRestants;
    bool _CrearEnemicRonda;

    public delegate void AvisarUI(int ronda);
    public AvisarUI augmentarRondaDelegat;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    private void Start()
    {
        _CrearEnemicRonda = true;
        _Min = 2.0f;
        _Max = 6.0f;
        _Count = 0;
        _Rondes = new List<int>{ 5, 10, 17, 30, 37, 50, 62, 75, 88, 100};
        _RondaActual = 0;
        _EnemicsVius = _Rondes[_RondaActual];
        _EnemicsRestants = _EnemicsVius;
        StartCoroutine(crear());
    }

    IEnumerator crear()
    {
        for(int i = 0; i < _EnemicsVius; i++)
        {
            float num = Random.Range(_Min, _Max);
            yield return new WaitForSeconds(num);
            int enemicNum = Random.Range(0, 2);
            GameObject enemicGO = _Pool.GetElement();

            if (enemicGO == null)
                continue;

            enemicGO.SetActive(true);
            enemicGO.GetComponent<IPoolable>().OnDestroyed += ReturnEnemicToPool;

            int posx = 0;
            float pos = Random.Range(0, 2);
            switch(pos)
            {
                case 0:
                    posx = -9;
                    break;
                case 1:
                    posx = 9;
                    break;
            }
            float posy = Random.Range(-2.5f, 2.5f);
            enemicGO.transform.position = new Vector2((_JugadorGO.transform.position.x + posx), (_JugadorGO.transform.position.y + posy));
            enemicGO.transform.tag = "Enemic";

            enemicGO.GetComponent<Enemic>().setVides(_Enemics[enemicNum].vides);
            enemicGO.GetComponent<Enemic>().setMal(_Enemics[enemicNum].mal);
            enemicGO.GetComponent<Enemic>().setPunts(_Enemics[enemicNum].punts);
            enemicGO.GetComponent<Enemic>().setVelocitat(_Enemics[enemicNum].velocitat);
            enemicGO.GetComponent<Enemic>().setAnimacio(_Enemics[enemicNum].animacio);
            enemicGO.GetComponent<Enemic>().setRadiDeteccio(_Enemics[enemicNum].radiDeteccio);
            enemicGO.GetComponent<Enemic>().setRadiAtac(_Enemics[enemicNum].radiAtac);


            switch (_RondaActual)
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
                    enemicGO.GetComponent<SpriteRenderer>().color = Color.cyan;
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
            }
        }
    }

    public void augmentarRonda()
    {
        if (_EnemicsRestants > 1)
        {
            _EnemicsRestants--;
        }
        else
        {
            _RondaActual++;
            _EnemicsVius = _Rondes[_RondaActual];
            _EnemicsRestants = _EnemicsVius;
            augmentarRondaDelegat.Invoke(_RondaActual);
            StartCoroutine(crear());
        }
    }

    private void ReturnEnemicToPool(GameObject enemic)
    {
        enemic.GetComponent<IPoolable>().OnDestroyed -= ReturnEnemicToPool;
        enemic.SetActive(false);
        _Pool.ReturnElement(enemic);
    }
}
