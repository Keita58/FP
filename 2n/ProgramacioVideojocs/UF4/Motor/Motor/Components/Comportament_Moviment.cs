public class Comportament_Moviment : Component
{
    private Transform _ComponentTransform;
    public override string _NomComponent => "Comportament_Moviment";
    // Fer start i posar getcomponent a una variable!!!!
    public Comportament_Moviment() {}

    public override void Start()
    {
        base.Start();
        _ComponentTransform = _GameObject.GetComponent<Transform>();
    }

    public override void Update(float deltaTime)
    {
        base.Update(deltaTime);

        if(Raylib_cs.Raylib.IsKeyDown(Raylib_cs.KeyboardKey.W))
        {
            _ComponentTransform._Y -= 550*deltaTime;
        }
        if(Raylib_cs.Raylib.IsKeyDown(Raylib_cs.KeyboardKey.A))
        {
            _ComponentTransform._X -= 550*deltaTime;
        }
        if(Raylib_cs.Raylib.IsKeyDown(Raylib_cs.KeyboardKey.S))
        {
            _ComponentTransform._Y += 550*deltaTime;
        }
        if(Raylib_cs.Raylib.IsKeyDown(Raylib_cs.KeyboardKey.D))
        {
            _ComponentTransform._X += 550*deltaTime;
        }
    }
}