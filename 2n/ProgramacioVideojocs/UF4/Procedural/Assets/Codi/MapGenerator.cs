using System;
using Unity.Burst.CompilerServices;
using UnityEngine;
using UnityEngine.Tilemaps;
using UnityEngine.UIElements;
using static Unity.Burst.Intrinsics.X86;


public class MapGenerator : MonoBehaviour
{
    private const int MAX_OCTAVES = 8;

    [SerializeField]
    private bool _Verbose = false;

    [SerializeField]
    private Tilemap _Tilemap;

    [SerializeField]
    private Tilemap _TilemapMinerals;

    [SerializeField]
    private Tile[] _TileGespa;

    [SerializeField]
    private Tile[] _TileRoca;

    [SerializeField]
    private Tile[] _TileRocaLimit;

    [SerializeField]
    private Tile[] _TileRocaSubsol;

    [SerializeField]
    private Tile[] _TileTerra;

    [SerializeField]
    private Tile[] _TileLiana;

    [SerializeField]
    private Tile[] _TileMinerals;

    [SerializeField]
    private Tile[] _TileArbres;

    [SerializeField]
    private Tile _TileLimit;

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
    [Range(2, 3)]
    [SerializeField]
    private int _Lacunarity = 2;

    [Header("Mines")]
    [SerializeField]
    private float _Frequency = 10f;
    [SerializeField]
    [Range(0, MAX_OCTAVES)]
    private int _Octaves = 2;
    [SerializeField]
    [Range(0.1f, 0.9f)]
    private float _Persistence = 0.4f;

    [Header("Sòl")]
    [SerializeField]
    private float _FrequenciaSol = 1f;
    [SerializeField]
    [Range(0, MAX_OCTAVES)]
    private int _OctavesSol = 0;
    [SerializeField]
    [Range(0.1f, 0.9f)]
    private float _PersistenciaSol = 0.4f;

    [Header("Minerals")]
    [SerializeField]
    [Min(5f)]
    private float _FrequencyMinerals = 10f;
    [SerializeField]
    [Range(0, MAX_OCTAVES)]
    private int _OctavesMinerals = 0;
    [SerializeField]
    [Range(0.1f, 0.9f)]
    private float _PersistenciaMinerals = 0.4f;

    void Start()
    {
        GeneratePerlinMap();
    }

    private void GeneratePerlinMap()
    {
        //recorrem el mapa
        for (int y = 0; y < _Width; y++)
        {
            //Perlin del límit del tilemap, a partir d'on pintem aquest
            float perlin1D = PerlinNoiseUtilities.CalculatePerlinNoise(y, 1, _FrequenciaSol, _Width, 1, _OffsetX, _OffsetY, _OctavesSol, 2, _PersistenciaSol, true, false);

            int posemArbre = UnityEngine.Random.Range(0, 7);

            if(posemArbre == 2)
            {
                int arbre = UnityEngine.Random.Range(0, 8);

                if(arbre < 7)
                {
                    //Cada arbre està format per dues tiles
                    _Tilemap.SetTile(new Vector3Int(y, (int)(_Height * perlin1D) + 2, 0), _TileArbres[arbre*2]);
                    _Tilemap.SetTile(new Vector3Int(y, (int)(_Height * perlin1D) + 1, 0), _TileArbres[(arbre*2)+1]);
                }
                else
                    //Menys l'últim, que és només una
                    _Tilemap.SetTile(new Vector3Int(y, (int)(_Height * perlin1D) + 1, 0), _TileArbres[arbre * 2]);
                
            }

            _Tilemap.SetTile(new Vector3Int(y, (int)(_Height * perlin1D), 0), _TileLimit);
            int gespaCaselles = UnityEngine.Random.Range(2, 6); //Quantes caselles extres pintem el tilemap de gespa
            int terraCaselles = UnityEngine.Random.Range(6, 8); //El mateix amb tiles de terra, a partir de les anteriors
            int lianaCaselles = UnityEngine.Random.Range(0, 3); //Quantes caselles de lianes pintem a dalt de la cova

            //Pintem el tilemap a partir de la següent posició on hem pintat el límit que ens ha donat el perlin1D
            for (int x = (int)(_Height * perlin1D) - 1; x >= 0; x--)
            {
                //Perlin per les coves
                float perlinNoise = PerlinNoiseUtilities.CalculatePerlinNoise(x, y, _Frequency, _Width, _Height, _OffsetX, _OffsetY, _Octaves, _Lacunarity, _Persistence, true, _Verbose);

                if (gespaCaselles > 0)
                {
                    int s = UnityEngine.Random.Range(0, 10);
                    if (s < 5)
                        _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileGespa[0]);
                    else
                        _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileGespa[1]);
                    gespaCaselles--;
                }
                else if (perlinNoise > 0.3f && perlinNoise <= 0.7f)
                {
                    lianaCaselles = UnityEngine.Random.Range(0, 3);
                    if (gespaCaselles <= 0 && terraCaselles > 0)
                    {
                        _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileTerra[0]);
                        terraCaselles--;
                    }
                    else
                    {
                        if(x > (_Height * perlin1D)*2/10)
                        {
                            //Un perlin per cada mineral que pot aparèixer en aquesta capa, per evitar que apareguin cercles de minerals amb un buit al centre
                            float mineral1 = PerlinNoiseUtilities.CalculatePerlinNoise(x, y, _FrequencyMinerals, _Width, _Height, _OffsetX + 1000, _OffsetY + 1000, _OctavesMinerals, 2, _PersistenciaMinerals, true, false);
                            float mineral2 = PerlinNoiseUtilities.CalculatePerlinNoise(x, y, _FrequencyMinerals, _Width, _Height, _OffsetX + 5000, _OffsetY + 5000, _OctavesMinerals, 2, _PersistenciaMinerals, true, false);
                            float mineral3 = PerlinNoiseUtilities.CalculatePerlinNoise(x, y, _FrequencyMinerals, _Width, _Height, _OffsetX + 2500, _OffsetY + 2500, _OctavesMinerals, 2, _PersistenciaMinerals, true, false);

                            int rand = UnityEngine.Random.Range(0, 3);

                            switch(rand)
                            {
                                case 0:
                                    if (mineral1 >= 0.8f && mineral1 < 1f)
                                    {
                                        _TilemapMinerals.SetTile(new Vector3Int(y, x, 0), _TileMinerals[0]);
                                    }
                                    break;
                                case 1:
                                    if (mineral2 >= 0.85f && mineral2 < 1f)
                                    {
                                        _TilemapMinerals.SetTile(new Vector3Int(y, x, 0), _TileMinerals[1]);
                                    }
                                    break;
                                case 2:
                                    if (mineral3 >= 0.9f && mineral3 < 1f)
                                    {
                                        _TilemapMinerals.SetTile(new Vector3Int(y, x, 0), _TileMinerals[2]);
                                    }
                                    break;
                            }

                            int s = UnityEngine.Random.Range(0, 10);
                            if (s < 5)
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileRoca[0]);
                            else
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileRoca[1]);
                        }
                        else if (x > (_Height * perlin1D) * 0.5 / 10)
                        {
                            //Perlin per lúnic mineral d'aquesat capa
                            float mid = PerlinNoiseUtilities.CalculatePerlinNoise(x, y, _FrequencyMinerals, _Width, _Height, _OffsetX + 2000, _OffsetY + 2000, _OctavesMinerals, 2, _PersistenciaMinerals, true, false);

                            if (mid >= 0.8f && mid < 0.9f)
                            {
                                _TilemapMinerals.SetTile(new Vector3Int(y, x, 0), _TileMinerals[3]);
                            }

                            int s = UnityEngine.Random.Range(0, 10);
                            if (s < 5)
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileRocaLimit[0]);
                            else
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileRocaLimit[1]);
                        }
                        else
                        {
                            //Perlin per lúnic mineral d'aquesat capa
                            float bot = PerlinNoiseUtilities.CalculatePerlinNoise(x, y, _FrequencyMinerals, _Width, _Height, _OffsetX + 1000, _OffsetY + 1000, _OctavesMinerals, 2, _PersistenciaMinerals, true, false);

                            if (bot >= 0.8f && bot < 0.9f)
                            {
                                _TilemapMinerals.SetTile(new Vector3Int(y, x, 0), _TileMinerals[4]);
                            }

                            int s = UnityEngine.Random.Range(0, 10);
                            if (s < 5)
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileRocaSubsol[0]);
                            else
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileRocaSubsol[1]);
                        }
                    }
                }
                else
                {
                    if (lianaCaselles > 0 && x > (_Height * perlin1D)/10)
                    {
                        int s = UnityEngine.Random.Range(0, 6);
                        switch (s)
                        {
                            case 0:
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileLiana[0]);
                                break;
                            case 1:
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileLiana[1]);
                                break;
                            case 2:
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileLiana[2]);
                                break;
                            case 3:
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileLiana[3]);
                                break;
                            case 4:
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileLiana[4]);
                                break;
                            case 5:
                                _Tilemap.SetTile(new Vector3Int(y, x, 0), _TileLiana[5]);
                                break;
                        }
                        lianaCaselles--;
                    }
                    if (terraCaselles > 0)
                        terraCaselles--;
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
            _TilemapMinerals.ClearAllTiles();
            GeneratePerlinMap();
        }
    }
}
