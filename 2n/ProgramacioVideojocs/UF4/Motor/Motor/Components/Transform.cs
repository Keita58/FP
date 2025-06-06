
[Serializable]
public class Transform : Component
{
    private float x;
    public float _X { get => x; set => x = value; }
    private float y;
    public float _Y { get => y; set => y = value; }

    public override string _NomComponent => "Transform";
    
    public Transform() {}

    public Transform(float x, float y) {
        _X = x;
        _Y = y;
    }
}