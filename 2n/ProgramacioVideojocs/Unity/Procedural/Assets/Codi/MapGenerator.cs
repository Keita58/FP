using System;
using UnityEngine;
using UnityEngine.Tilemaps;
using UnityEngine.UIElements;
using static Unity.Burst.Intrinsics.X86;


public class MapGenerator : MonoBehaviour
{
    [SerializeField]
    private bool _Verbose = false;

    [SerializeField]
    private Tilemap _Tilemap;

    [SerializeField]
    private Tile[] _TileBosc;

    [SerializeField]
    private Tile[] _TileTerra;

    [SerializeField]
    private Tile[] _TileAigua;

    [SerializeField]
    private Tile[] _TileSorra;

    [SerializeField]
    private Tile[] _TileNeu;

    [SerializeField]
    private Tile[] _TileRoca;

    [SerializeField]
    private Tile _TileLimit;

    //GUI
    public event Action OnToggleHelp;
    public event Action OnToggleQuad;
    public event Action<Texture2D, int, int> OnTextureChanged;

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
    [SerializeField]
    private float _FrequencySoil = 1f;
    [SerializeField]
    private float _FrequencyTemp = 1f;

    //octaves
    private const int MAX_OCTAVES = 8;
    [SerializeField]
    [Range(0, MAX_OCTAVES)]
    private int _Octaves = 0;
    [Range(2, 3)]
    [SerializeField]
    private int _Lacunarity = 2;
    [SerializeField]
    [Range(0.1f, 0.9f)]
    private float _Persistence = 0.5f;

    void Start()
    {
        GeneratePerlinMap();
    }

    private void GeneratePerlinMap()
    {
        //recorrem el mapa
        for (int y = 0; y < _Width; y++)
        {
            float perlin1D = PerlinNoiseUtilities.CalculatePerlinNoise(y, 1, _FrequencySoil, _Width, 1, _OffsetX, _OffsetY, _Octaves, _Lacunarity, _Persistence, false, _Verbose, true);

            Tile aux = Instantiate(_TileLimit);
            aux.color = Color.black;
            _Tilemap.SetTile(new Vector3Int(y, (int)(_Height * perlin1D), 0), aux);

            for (int x = (int)(_Height * perlin1D) - 1; x >= 0; x--)
            {
                //Perlin per les coves
                float perlinNoise = PerlinNoiseUtilities.CalculatePerlinNoise(x, y, _Frequency, _Width, _Height, _OffsetX, _OffsetY, _Octaves, _Lacunarity, _Persistence, false, _Verbose, true);

                //Perlin per les temps
                float tempNoise = PerlinNoiseUtilities.CalculatePerlinNoise(x, y, _FrequencyTemp, _Width, _Height, _OffsetX + 1000, _OffsetY + 1000, _Octaves, _Lacunarity, _Persistence, false, _Verbose, true);

                if (perlinNoise <= 0.25f)
                {
                    _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileAigua[0]);
                }
                else if (perlinNoise > 0.25f && perlinNoise <= 0.4f)
                {
                    _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileSorra[0]);
                }
                else if (perlinNoise > 0.4f && perlinNoise <= 0.75f)
                {
                    if (perlinNoise >= 0.7f && perlinNoise < 0.75f)
                    {
                        _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileBosc[0]);
                    }
                    else
                    {
                        int s = UnityEngine.Random.Range(0, 10);
                        if(s <= 5)
                            _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileTerra[0]);
                        else
                            _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileTerra[1]);
                    }
                        
                }
                else if (perlinNoise > 0.75f && perlinNoise <= 0.9)
                {
                    _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileRoca[0]);
                }
                else
                {
                    int s = UnityEngine.Random.Range(0, 10);
                    if (s <= 3.3f)
                        _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileNeu[0]);
                    else if(s <= 6.6f)
                        _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileNeu[1]);
                    else
                        _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileNeu[2]);
                }
            }
        }
    }

    void Update()
    {
        //regenerar perlins
        if (Input.GetKeyDown(KeyCode.Space))
        {
            _Tilemap.ClearAllTiles();
            GeneratePerlinMap();
        }
    }
}
