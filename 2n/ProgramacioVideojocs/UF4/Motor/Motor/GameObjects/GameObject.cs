[Serializable]
public abstract class GameObject 
{
    public abstract string _Nom { get; }
    public abstract List<Component> _Components { get; set; }
    protected GameObject() {}
    public void Dispose() 
    {
        foreach(Component c in _Components) 
        {
            c.Dispose();
        }
    }

    public void Start() 
    {
        foreach(Component c in _Components) 
        {
            c.Start();
        }
    }

    public void Update(float deltaTime)
    {
        foreach(Component c in _Components) 
        {
            c.Update(deltaTime);
        }
    }

    public void Render()
    {
        foreach(Component c in _Components) 
        {
            c.Render();
        }
    }
    
    public void AddComponent(Component component) 
    {
        _Components.Add(component);
        component.SetGameObject(this);
    }
    
    public void RemoveComponent(Component component)
    {
        _Components.Remove(component);
    }
    
    //El where és un extends de la classe Component
    //https://stackoverflow.com/questions/4732494/cs-equivalent-of-javas-extends-base-in-generics
    public bool HasComponent<T>()  where T : Component
    {
        foreach(Component component in _Components) {
            if(component is T)
                return true;
        }
        return false;
    }

    //El where és un extends de la classe Component
    //https://stackoverflow.com/questions/4732494/cs-equivalent-of-javas-extends-base-in-generics
    public T? GetComponent<T>() where T : Component
    {
        foreach(Component component in _Components) {
            if(component is T)
                return component as T;
        }
        return null;

    }
}