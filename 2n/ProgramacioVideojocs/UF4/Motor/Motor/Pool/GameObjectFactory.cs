using m17;
using motoret;
public class GameObjectFactory : IPoolableFactory<GameObject>
{
    public void Activate(GameObject element)
    {
        Motoret.Instance.AfegirGameObject(element);
    }

    public GameObject Create()
    {
        ObjecteRectangle rec = new ObjecteRectangle();
        return rec;
    }

    public void Deactivate(GameObject element)
    {
        Motoret.Instance.RemoveGameObject(element);
    }

    public void Delete(GameObject element)
    {
        
    }
}