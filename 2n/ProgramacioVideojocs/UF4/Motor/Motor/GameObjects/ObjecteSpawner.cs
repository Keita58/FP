namespace m17;
public class ObjecteSpawner : GameObject
{

    public override List<Component> _Components { get; set; }
    public override string _Nom { get => "Spawner"; }

    public ObjecteSpawner(int n, int midaPool) 
    {
        _Components = new List<Component>();

        GameObjectFactory factory = new GameObjectFactory();
        Pool<GameObject> pool = new Pool<GameObject>(midaPool, factory);  
        SpawnerComponent sp = new SpawnerComponent(n, pool);
        AddComponent(sp);
    }
}
