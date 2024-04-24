using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Parallax : MonoBehaviour
{
    [SerializeField] private Vector2 speed;

    private Vector2 offset;
    private Material material;
    public Rigidbody2D player;

    void Start()
    {
        material = GetComponent<SpriteRenderer>().material;
    }

    // Update is called once per frame
    void Update()
    {
        offset = (player.velocity.x * 0.1f) * speed * Time.deltaTime;
        material.mainTextureOffset += offset;
    }
}
