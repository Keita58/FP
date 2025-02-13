using System;
using UnityEngine;
using UnityEngine.Tilemaps;
using UnityEngine.UI;


public class MapGenerator : MonoBehaviour
{
    [SerializeField]
    private bool _Verbose = false;

    //graphic
    [SerializeField]
    private Gradient _Gradient;
    [SerializeField]
    private Tile _Tile;
    private Texture2D _MeshTexture;
    private Texture2D[] _Texture;
    private float[][,] _Heights;

    //GUI
    public event Action OnToggleHelp;
    public event Action OnToggleQuad;
    public event Action<Texture2D, int, int> OnTextureChanged;

    //internal logic
    private int _TextureMode = 0;
    private int _TextureOctave = 0;

    [Header("Size")]
    //size of the area we will paint
    [SerializeField]
    [Min(1)]
    private int _Width = 100;
    [Min(1)]
    [SerializeField]
    private int _Height = 100;
    [SerializeField]
    [Min(0.1f)]
    private float _Amplitude = 10f;

    [Header("Base Parameters")]
    [SerializeField]
    //offset from the perlin map
    private float _OffsetX;
    [SerializeField]
    private float _OffsetY;
    [SerializeField]
    private float _Frequency = 4f;

    //octaves
    private const int MAX_OCTAVES = 8;
    [Header("Octave Parameters")]
    [SerializeField]
    [Range(0, MAX_OCTAVES)]
    private int _Octaves = 0;
    [Range(2, 3)]
    [SerializeField]
    private int _Lacunarity = 2;
    [SerializeField]
    [Range(0.1f, 0.9f)]
    private float _Persistence = 0.5f;
    [Tooltip("Do the octaves carve the terrain?")]
    [SerializeField]
    private bool _Carve = true;

    void Start()
    {
        //Crearem una textura per cada perlin i les seves octaves amb el resultat conjunt
        //i un altre amb el resultat base.
        _Texture = new Texture2D[(MAX_OCTAVES + 1) * 2];
        for (int i = 0; i < (MAX_OCTAVES + 1) * 2; i++)
        {
            _Texture[i] = new Texture2D(_Width, _Height);
            _Texture[i].filterMode = FilterMode.Point;
        }

        _MeshTexture = new Texture2D(_Width, _Height);
        _MeshTexture.filterMode = FilterMode.Bilinear;
        _MeshMaterial.mainTexture = _MeshTexture;

        GeneratePerlinMap();
    }

    private void GeneratePerlinMap()
    {
        GenerateMeshAndTextures();
        _Heights = new float[(MAX_OCTAVES + 1) * 2][,];
        Color[][] colors = new Color[(MAX_OCTAVES + 1) * 2][];
        for (int i = 0; i < (MAX_OCTAVES + 1) * 2; i++)
        {
            _Heights[i] = new float[_Height, _Width];
            colors[i] = new Color[_Height * _Width];
        }

        float elapsedTime = Time.realtimeSinceStartup;
        Debug.Log("Calculant Perlin Noise");
        //recorrem el mapa
        for (int y = 0; y < _Height; y++)
        {
            for (int x = 0; x < _Width; x++)
            {
                float perlinNoise = PerlinNoiseUtilities.CalculatePerlinNoise(x, y, _Frequency, _Width, _Height, _OffsetX, _OffsetY, _Octaves, _Lacunarity, _Persistence, _Carve, _Verbose, true);
                colors[0][x + y * _Width] = _Gradient.Evaluate(perlinNoise);

                //Omplim les textures de les octaves
                for (int octave = 1; octave <= _Octaves; octave++)
                {
                    colors[octave * 2][x + y * _Width] = _Gradient.Evaluate(perlinNoise);
                    colors[octave * 2 + 1][x + y * _Width] = _Gradient.Evaluate(perlinNoise);
                }

                //i utilitzem el soroll com a factor per a determinar l'al�ada final del terreny
                colors[1][x + y * _Width] = _Gradient.Evaluate(perlinNoise);
                for (int i = 0; i < (_Octaves + 1) * 2; i++)
                    _Heights[i][y, x] = perlinNoise;

            }
        }


        elapsedTime = Time.realtimeSinceStartup;
        for (int i = 0; i < (MAX_OCTAVES + 1) * 2; i++)
        {
            _Texture[i].SetPixels(colors[i]);
            _Texture[i].Apply();
        }

        ShowResult(0, 1);

        Debug.Log($"Temps emprat per a la generaci� del terreny: {(Time.realtimeSinceStartup - elapsedTime)}");
        Debug.Log("Recreaci� finalitzada");
    }

    private void ShowResult(int octave, int mode)
    {
        int index = octave * 2 + mode;
        _TextureOctave = octave;
        _TextureMode = mode;
        OnTextureChanged?.Invoke(_Texture[index], _TextureOctave, _TextureMode);
        RecalculateMesh(_Heights[index]);
        _MeshTexture.SetPixels(_Texture[index].GetPixels());
        _MeshTexture.Apply();
        Debug.Log($"Mostrant la textura {(_TextureMode == 0 ? "base" : "combinada")} de l'octava {_TextureOctave}");
    }

    #region MeshGeneration        

    private void GenerateMeshAndTextures()
    {
        for (int i = 0; i < (MAX_OCTAVES + 1) * 2; i++)
            _Texture[i].Reinitialize(_Width, _Height);

        _MeshTexture.Reinitialize(_Width, _Height);

        CreateMesh();
    }
    private void CreateMesh()
    {
        Mesh mesh = GetComponent<MeshFilter>().mesh;
        mesh.Clear();

        Vector3[] vertices = new Vector3[_Height * _Width];
        Vector2[] uv = new Vector2[_Height * _Width];
        for (int x = 0; x < _Height; x++)
            for (int z = 0; z < _Width; z++)
            {
                vertices[x * _Width + z] = new Vector3(x, 0, z);
                uv[x * _Width + z] = new Vector2(z / ((float)_Width - 1), x / ((float)_Height - 1));
            }

        mesh.vertices = vertices;
        mesh.uv = uv;

        int[] triangles = new int[(_Height - 1) * (_Width - 1) * 2 * 3];
        for (int x = 0; x < (_Height - 1); x++)
            for (int z = 0; z < (_Width - 1); z++)
            {
                triangles[x * ((_Width - 1) * 6) + z * 6] = x * _Width + z;
                triangles[x * ((_Width - 1) * 6) + z * 6 + 1] = (x + 1) * _Width + z + 1;
                triangles[x * ((_Width - 1) * 6) + z * 6 + 2] = (x + 1) * _Width + z;

                triangles[x * (_Width - 1) * 6 + z * 6 + 3] = x * _Width + z;
                triangles[x * (_Width - 1) * 6 + z * 6 + 4] = x * _Width + z + 1;
                triangles[x * (_Width - 1) * 6 + z * 6 + 5] = (x + 1) * _Width + z + 1;
            }

        mesh.triangles = triangles;
    }

    private void RecalculateMesh(float[,] heights)
    {
        Mesh mesh = GetComponent<MeshFilter>().mesh;

        float amplitude = _Amplitude;
        if (_TextureMode == 0 && _TextureOctave != 0)
            amplitude *= _Persistence / (float)_TextureOctave;

        Vector3[] vertices = mesh.vertices;
        for (int x = 0; x < _Height; x++)
            for (int z = 0; z < _Width; z++)
                vertices[x * _Width + z] = new Vector3(x, heights[x, z] * amplitude, z);

        mesh.vertices = vertices;
        mesh.RecalculateNormals();
    }

    #endregion

    void Update()
    {
        //regenerar perlins
        if (Input.GetKeyDown(KeyCode.Space))
            GeneratePerlinMap();

        //canviar textura entre base i combinada
        if (Input.GetKeyDown(KeyCode.C))
            ShowResult(_TextureOctave, (_TextureMode + 1) % 2);

        //canviar l'octava de textura a mostrar
        if (Input.GetKeyDown(KeyCode.Alpha0))
            ShowResult(0, _TextureMode);
        if (Input.GetKeyDown(KeyCode.Alpha1))
            ShowResult(1, _TextureMode);
        if (Input.GetKeyDown(KeyCode.Alpha2))
            ShowResult(2, _TextureMode);
        if (Input.GetKeyDown(KeyCode.Alpha3))
            ShowResult(3, _TextureMode);
        if (Input.GetKeyDown(KeyCode.Alpha4))
            ShowResult(4, _TextureMode);
        if (Input.GetKeyDown(KeyCode.Alpha5))
            ShowResult(5, _TextureMode);
        if (Input.GetKeyDown(KeyCode.Alpha6))
            ShowResult(6, _TextureMode);
        if (Input.GetKeyDown(KeyCode.Alpha7))
            ShowResult(7, _TextureMode);
        if (Input.GetKeyDown(KeyCode.Alpha8))
            ShowResult(8, _TextureMode);


        if (Input.GetKeyDown(KeyCode.T))
            OnToggleQuad?.Invoke();

        if (Input.GetKeyDown(KeyCode.H))
            OnToggleHelp?.Invoke();
    }
}
