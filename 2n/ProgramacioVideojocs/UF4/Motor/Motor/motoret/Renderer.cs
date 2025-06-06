using System.Numerics;
using Raylib_cs;

namespace motoret;

/*
* Classe senzilla per a fer el render i la gestió interna de textures pel motoret de classe.
* S'utilitza Raylib de'n Ray: https://www.raylib.com/
* a través dels bindings per a c# disponibles a: https://github.com/raylib-cs/raylib-cs
*/
public static class Renderer
{
    private static Dictionary<string, Texture2D> _Textures = new();
    
    private static Color _BackgroundColor;

    public static void CreateWindow(int amplada = 800, int alcada = 480, string nom = "Motoret Project", Color colorFons = default)
    {
        Raylib.InitWindow(amplada, alcada, nom);

        _BackgroundColor = colorFons;
    }

    public static void InitRender()
    {
        Raylib.BeginDrawing();
        Raylib.ClearBackground(_BackgroundColor);
    }

    public static void EndRender()
    {
        Raylib.EndDrawing();
    }

    public static void CloseWindow()
    {
        foreach(var (path, textura) in _Textures)
            Raylib.UnloadTexture(textura);
        _Textures.Clear();

        Raylib.CloseWindow();
    }

    #region dibuixar
    //Funcions per a dibuixar coses

    //Dibuixar un text
    public static void DibuixarText(string text, int posX=0, int posY=0, int fontSize=12, Color color = default)
    => Raylib.DrawText(text, posX, posY, fontSize, color);

    //Dibuixar un rectangle
    public static void DibuixarRectangle(int posX, int posY, int width, int height, Color color = default)
    => Raylib.DrawRectangle(posX, posY, width, height, color);

    public static void DibuixarRectangle(Vector2 position, Vector2 size, Color color = default)
    => Raylib.DrawRectangleV(position, size, color);

    public static void DibuixarRectangle(Rectangle rec, Color color = default)
    => Raylib.DrawRectangleRec(rec, color);

    public static void DibuixarRectangle(Rectangle rec, Vector2 origin, float rotation, Color color = default)
    => Raylib.DrawRectanglePro(rec, origin, rotation, color);

    //Dibuixar un cercle
    public static void DibuixarCercle(int centerX, int centerY, float radius, Color color = default)
    => Raylib.DrawCircle(centerX, centerY, radius, color);

    public static void DibuixarCercle(Vector2 center, float radius, Color color = default)
    => Raylib.DrawCircleV(center, radius, color);

    //Gestió interna de textures
    private static void ObtenirTextura(string pathTextura, out Texture2D textura)
    {
        if(!_Textures.ContainsKey(pathTextura))
            _Textures.Add(pathTextura, Raylib.LoadTexture(pathTextura));
        
        textura = _Textures[pathTextura];
    }

    //Dibuixar un sprite a partir d'una textura
    public static void DibuixarSprite(string pathTextura, float posicioX, float posicioY, float amplada, float alcada, float pivotX = 0, float pivotY = 0)
    {
        Vector2 posicio = new Vector2(posicioX, posicioY);
        Vector2 mida = new Vector2(amplada, alcada);
        Vector2 pivot = new Vector2(pivotX, pivotY);
        DibuixarSprite(pathTextura, posicio, mida, pivot);
    }

    public static void DibuixarSprite(string pathTextura, Vector2 posicio, Vector2 mida, Vector2 pivot)
    {
        Rectangle desti = new Rectangle(posicio, mida);
        DibuixarSprite(pathTextura, desti, pivot, 0, Color.White);
    }

    public static void DibuixarSprite(string pathTextura, Rectangle desti, Vector2 pivot, float rotacio, Color tint)
    {
        ObtenirTextura(pathTextura, out Texture2D textura);
        Rectangle texturaSencera = new Rectangle()
        {
            X = 0,
            Y = 0,
            Width = textura.Width,
            Height = textura.Height
        };
        Raylib.DrawTexturePro(textura, texturaSencera, desti, pivot, rotacio, tint);
    }

    //Dibuixar un sprite a partir d'una textura amb diferents tiles
    public static void DibuixarTiledSprite(string pathTextura, float posicioXTile, float posicioYTile, float ampladaTile, float alcadaTile, float posicioX, float posicioY, float amplada, float alcada, float pivotX = 0, float pivotY = 0)
    {
        Vector2 posicioTile = new Vector2(posicioXTile, posicioYTile);
        Vector2 midaTile = new Vector2(ampladaTile, alcadaTile);
        Vector2 posicio = new Vector2(posicioX, posicioY);
        Vector2 mida = new Vector2(amplada, alcada);
        Vector2 pivot = new Vector2(pivotX, pivotY);
        DibuixarTiledSprite(pathTextura, posicioTile, midaTile, posicio, mida, pivot);
    }

    public static void DibuixarTiledSprite(string pathTextura, Vector2 posicioTile, Vector2 midaTile, Vector2 posicio, Vector2 mida, Vector2 pivot)
    {
        Rectangle tile  = new Rectangle(posicioTile, midaTile);
        Rectangle desti = new Rectangle(posicio, mida);
        DibuixarTiledSprite(pathTextura, tile, desti, pivot, 0, Color.White);
    }

    public static void DibuixarTiledSprite(string pathTextura, Rectangle tile, Rectangle desti, Vector2 pivot, float rotacio, Color tint)
    {
        ObtenirTextura(pathTextura, out Texture2D textura);
        Raylib.DrawTexturePro(textura, tile, desti, pivot, rotacio, tint);
    }
    #endregion dibuixar
}