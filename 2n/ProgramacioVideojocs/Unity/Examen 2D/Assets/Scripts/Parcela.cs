using UnityEngine;
using UnityEngine.InputSystem;

public class Parcela : MonoBehaviour
{
    enum PlayerStates { SECA, REMOGUDA, REGADA }
    [SerializeField] PlayerStates actualState;
    [SerializeField] float stateTime;
    [SerializeField] private GameEvent _Event;
    [SerializeField] public PlantaPrefab prefab;
    int creixement;
    Plantes plantes;


    private void Start()
    {
        //this.transform.position = Vector3.zero;
        ChangeState(PlayerStates.SECA);
        //_Event += Canvi_Dia();
    }

    private void ChangeState(PlayerStates newstate)
    {
        ExitState(actualState);
        IniState(newstate);
    }

    private void IniState(PlayerStates initState)
    {
        actualState = initState;
        stateTime = 0f;

        switch (actualState)
        {
            case PlayerStates.SECA:
                break;
            case PlayerStates.REMOGUDA:
                break;
            case PlayerStates.REGADA:
                break;
            default:
                break;
        }
    }
  
    private void ExitState(PlayerStates exitState)
    {
        switch (exitState)
        {
            case PlayerStates.SECA:
                break;
            case PlayerStates.REMOGUDA:
                break;
            case PlayerStates.REGADA:
                break;
        }
    }

    public void Seca()
    {
        switch (actualState)
        {
            case PlayerStates.REMOGUDA:
                ChangeState(PlayerStates.SECA);
                break;
        }
    }

    public void Remoguda()
    {
        switch (actualState)
        {
            case PlayerStates.SECA:
                ChangeState(PlayerStates.REMOGUDA);
                break;
            case PlayerStates.REGADA:
                ChangeState(PlayerStates.REMOGUDA);
                break;
        }
    }

    public void Regada()
    {
        switch (actualState)
        {
            case PlayerStates.REMOGUDA:
                ChangeState(PlayerStates.REGADA);
                break;
        }
    }

    public Plantes Podada()
    {
        if(plantes != null)
        {
            Plantes aux = plantes;
            plantes = null;
            return aux;
        }
        return null;
    }

    public bool Plant_Al(Plantes planta)
    {
        if (actualState == PlayerStates.REGADA) 
        {
            plantes = planta;
            prefab.GetComponent<SpriteRenderer>().sprite = plantes.pet;
            return true;
        }
        return false;
    }

    public bool Plant_Ma(Plantes planta)
    {
        if (actualState == PlayerStates.REGADA)
        {
            plantes = planta;
            prefab.GetComponent<SpriteRenderer>().sprite = plantes.pet;
            return true;
        }
        return false;
    }

    public bool Plant_Gi(Plantes planta)
    {
        if (actualState == PlayerStates.REGADA)
        {
            plantes = planta;
            prefab.GetComponent<SpriteRenderer>().sprite = plantes.pet;
            return true;
        }
        return false;
    }
}
