[Serializable]
public abstract class Component
{
    internal GameObject _GameObject;
    public Component() {}
    //Virtual permet que només els components que necessitin modificar
    //la funció ho facin
    public virtual void Start() {}
    //Virtual permet que només els components que necessitin modificar
    //la funció ho facin
    public virtual void Update(float deltaTime) {}
    //Virtual permet que només els components que necessitin modificar
    //la funció ho facin
    public virtual void Render() {}
    //Virtual permet que només els components que necessitin modificar
    //la funció ho facin
    public virtual void Dispose() {}
    public GameObject GetGameObject() 
    {
        return _GameObject;
    }

    public void SetGameObject(GameObject go) 
    {
        _GameObject = go;
    }
    public abstract string _NomComponent { get; }
}