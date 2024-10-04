using UnityEngine;

public class PoolBales : MonoBehaviour
{
    [SerializeField] private GameObject _Bala;
    [SerializeField] private int _NumBales;

    private GameObject[] _Pool;
    private bool[] _Disponible;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Awake()
    {
        _Pool = new GameObject[_NumBales];
        _Disponible = new bool[_NumBales];

        for(int i = 0; i < _NumBales; i++)
        {
            GameObject aux = Instantiate(_Bala, transform); // Posem transform perquè així creem les noves bales dins de l'objecte buit
            aux.SetActive(false);

            _Pool[i] = aux;
            _Disponible[i] = true;
        }
    }

    public GameObject GetBala()
    {
        for (int i = 0; i < _NumBales; i++)
        {
            if (_Disponible[i])
            {
                _Disponible[i] = false;
                return _Pool[i];
            }
        }
        return null;
    }

    public void ReturnBala(GameObject element)
    {
        for (int i = 0; i < _NumBales; i++)
        {
            if (_Pool[i] == element)
            {
                element.SetActive(false);
                _Disponible[i] = true;
                return;
            }
        }
    }
}
