using UnityEngine;

public class PoolBales : MonoBehaviour
{
    [SerializeField] private GameObject _Element;
    [SerializeField] private int _NumElements;

    private GameObject[] _Pool;
    private bool[] _Disponible;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Awake()
    {
        _Pool = new GameObject[_NumElements];
        _Disponible = new bool[_NumElements];

        for(int i = 0; i < _NumElements; i++)
        {
            GameObject aux = Instantiate(_Element, transform); // Posem transform perquè així creem les noves bales dins de l'objecte buit
            aux.SetActive(false);

            _Pool[i] = aux;
            _Disponible[i] = true;
        }
    }

    public GameObject GetElement()
    {
        for (int i = 0; i < _NumElements; i++)
        {
            if (_Disponible[i])
            {
                _Disponible[i] = false;
                return _Pool[i];
            }
        }
        return null;
    }

    public void ReturnElement(GameObject element)
    {
        for (int i = 0; i < _NumElements; i++)
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
