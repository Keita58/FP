using motoret;

public class Render_Objecte : Component
{
    public int width;
    private int _Width { get => width; set => width = value; }
    public int height;
    private int _Height { get => height; set => height = value; }
    public int radi;
    private int _Radi { get => radi; set => radi = value; }
    public Raylib_cs.Color color;
    private Raylib_cs.Color _Color { get => color; set => color = value; }
    public override string _NomComponent => "Render";
    private string nom;
    private Transform _ComponentTransform;

    public Render_Objecte() {}

    public Render_Objecte(int width, int height, Raylib_cs.Color color) 
    {
        _Width = width;
        _Height = height;
        _Color = color;
        nom = "Rectangle";
    }

    public Render_Objecte(int radi, Raylib_cs.Color color) 
    {
        _Radi = radi;
        _Color = color;
        nom = "Cercle";
    }

    public override void Start()
    {
        base.Start();

        _ComponentTransform = _GameObject.GetComponent<Transform>();
    }

    public override void Render()
    {
        base.Render();

        switch(this.nom) {
            case "Rectangle":
                Renderer.DibuixarRectangle((int)_ComponentTransform._X, (int)_ComponentTransform._Y, _Width, _Height, _Color);
                break;
            case "Cercle":
                Renderer.DibuixarCercle((int)_ComponentTransform._X, (int)_ComponentTransform._Y, _Radi, _Color);
                break;
        }
    }
}