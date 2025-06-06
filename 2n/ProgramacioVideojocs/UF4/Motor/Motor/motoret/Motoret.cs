using Raylib_cs;
namespace motoret;

public class Motoret
{
    private List<GameObject> _GameObjects = new();
    public bool Finalitzar {get; set;}
    private float _FPS;
    private float _FPSDesitjats;
    private static Motoret _Instance;
    public static Motoret Instance
    {
        get{
            _Instance ??= new Motoret();
            return _Instance;
        }
    }
    private List<GameObject> eliminarGO = [];
    private List<GameObject> afegirGO = [];

    private Motoret() {
        _Instance = this;
    }
    public void Inicialitzar(int amplada = 800, int alcada = 480, string nom = "Motoret Project", Color colorFons = default, int fps = 60)
    {
        _GameObjects = new List<GameObject>();
        Renderer.CreateWindow(amplada, alcada, nom);
        _FPS = 0;
        _FPSDesitjats = fps;
    }

    //TODO:
    // Aquesta funció l'heu de refer vosaltres. tempsIniciFrame mateix afegeix objectes directament a la llista.
    // Per tant, si afegiu objectes durant l'execució, estareu afegint objectes dins una llista a la vegada
    //que recorreu aquesta llista.
    public void AfegirGameObject(GameObject go)
    {
        afegirGO.Add(go);
    }

    public void RemoveGameObject(GameObject go)
    {
        eliminarGO.Add(go);
        Console.WriteLine($"Elimino {go}");
    }

    public void Correr()
    {
        Finalitzar = false;
        float deltaTime = 0;
        long tempsAnterior = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds();
        long tempsActualFrame = 0;
        float milisegonsPerFrame = (1/_FPSDesitjats) * 1000f; // Per calcular el que ha de durar el temps de cada frame 

        foreach(GameObject go in _GameObjects)
            go.Start();
        
        while(!Finalitzar && !Raylib.WindowShouldClose())
        {
            //TODO: calcular deltaTime segons frame anterior
            long tempsIniciFrame = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds();
            deltaTime = (tempsIniciFrame - tempsAnterior) / 1000f;
            tempsAnterior = tempsIniciFrame;

            //recórrer la llista de GO per a Update
            foreach(GameObject go in _GameObjects)
                go.Update(deltaTime);

            //recórrer la llista de GO per a Render
            Render();            
            
            //TODO: Treure elements de la llista

            foreach(GameObject go in eliminarGO) 
            {
                go.Dispose();
                _GameObjects.Remove(go);
            }

            eliminarGO.Clear();

            //TODO: Afegir elements a la llista de GO

            foreach(GameObject go in afegirGO) 
            {
                go.Start();
                _GameObjects.Add(go);
            }

            afegirGO.Clear();

            //TODO: Fer una petita espera o esperar a una interrupció per tal d'acabar el frame actual
            do{
                Thread.Sleep(1);
                tempsActualFrame = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds();
            } while(milisegonsPerFrame > (tempsActualFrame - tempsIniciFrame));
            
            _FPS = 1 / ((tempsActualFrame - tempsIniciFrame) / 1000f);
        }
    }

    private void Render()
    {
        Renderer.InitRender();

        foreach(GameObject go in _GameObjects)
            go.Render();

        Renderer.DibuixarText($"FPS: {(int)_FPS}" , 0, 0, 24, Raylib_cs.Color.Green);
        Renderer.EndRender();
    }

    public void Aturar()
    {
        foreach(GameObject go in _GameObjects)
            go.Dispose();
        _GameObjects.Clear();

        Renderer.CloseWindow();
    }
}