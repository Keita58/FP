[Serializable]
public class SpawnerComponent : Component
{
    private int time;
    private float _TempsPassat;
    private Pool<GameObject> pool;
    public override string _NomComponent => "SpawnerComponent";
    
    public SpawnerComponent(int n, Pool<GameObject> pool) 
    {
        time = n;
        _TempsPassat = 0;
        this.pool = pool;
    }

    public override void Update(float deltaTime)
    {
        Spawnejar(deltaTime);
    }   

    private void Spawnejar(float deltaTime)
    {
        _TempsPassat += deltaTime;
       
        if(_TempsPassat >= time)
        {
            this.pool.GetElement();
            _TempsPassat -= time;
        }
    }

    public void Despawn(GameObject go)
    {
        this.pool.TryReturnElement(go);
    }
}