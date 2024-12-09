using TMPro;
using UnityEngine;

public class Comprar : MonoBehaviour
{
    [SerializeField] public GameObject Clint;
    [SerializeField] public TextMeshProUGUI Diners;
    private int DinersNum;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Awake()
    {
        DinersNum = 50;
        Diners.text = DinersNum.ToString() + " Chonks";
    }

    public void CompraAl()
    {
        if (DinersNum >= 10)
        {
            DinersNum -= 10;
            Clint.GetComponent<Personatge>().Al++;
            Diners.text = DinersNum.ToString() + " Chonks";
        }
    }

    public void CompraMa()
    {
        if (DinersNum >= 20)
        {
            DinersNum -= 20;
            Clint.GetComponent<Personatge>().Ma++;
            Diners.text = DinersNum.ToString() + " Chonks";
        }
    }

    public void CompraGi()
    {
        if (DinersNum >= 5)
        {
            DinersNum -= 5;
            Clint.GetComponent<Personatge>().Gi++;
            Diners.text = DinersNum.ToString() + " Chonks";
        }
    }

    public void Venta()
    {
        if(Clint.GetComponent<Personatge>().Al > 0)
        {
            for(int i = 0; i < Clint.GetComponent<Personatge>().Al; i++)
            {
                DinersNum += 10;
            }
            Clint.GetComponent<Personatge>().Al = 0;
        }
        if (Clint.GetComponent<Personatge>().Ma > 0)
        {
            for (int i = 0; i < Clint.GetComponent<Personatge>().Ma; i++)
            {
                DinersNum += 20;
            }
            Clint.GetComponent<Personatge>().Ma = 0;
        }
        if (Clint.GetComponent<Personatge>().Gi > 0)
        {
            for (int i = 0; i < Clint.GetComponent<Personatge>().Gi; i++)
            {
                DinersNum += 5;
                Clint.GetComponent<Personatge>().Gi = 0;
            }
        }
        Diners.text = DinersNum.ToString() + " Chonks";
    }
}
