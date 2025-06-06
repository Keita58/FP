namespace m17;

[Serializable]
public class ObjecteRectangle : GameObject
{
    public override List<Component> _Components { get; set; }
    public override string _Nom { get => "Rectangle"; }

    public ObjecteRectangle() 
    {
        _Components = new List<Component>();
        
        Transform t = new Transform(70, 70);
        AddComponent(t);

        Render_Objecte r = new Render_Objecte(50, 50, Raylib_cs.Color.Blue);
        AddComponent(r);

        Comportament_Moviment c_m = new Comportament_Moviment();
        AddComponent(c_m);
    }
}