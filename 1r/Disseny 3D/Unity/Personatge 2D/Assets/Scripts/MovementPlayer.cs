using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MovementPlayer : MonoBehaviour
{
    private Rigidbody2D rigidbody;
    private CapsuleCollider2D capsule;
    private Animator anim;
    private float movementX = 0f; //Horizontal
    [Header ("Movement")]
    public float speedX = 0f; //velocidad
    public float speedSmooth = 0f;
    private bool derecha = true;
    private Vector3 speed = Vector3.zero;

    [Header("Jump")]
    public float jumpPower;
    private bool inGround = true;
    private bool jump = false;

    private bool crouched = false;

    void Start()
    {
        rigidbody = GetComponent<Rigidbody2D>();
        capsule = GetComponent<CapsuleCollider2D>();
        anim = GetComponent<Animator>();
    }

    void Update()
    {
        if (Input.GetKey(KeyCode.LeftControl))
        {
            //Modificar valors per els vostres
            crouched = true;
            anim.SetBool("crouch", true);
            capsule.offset = new Vector2(0.02451974f, -0.0436008f);
            capsule.size = new Vector2(1.626895f, 3.95f);
            movementX = 0f;
            return;
        }
        else if (crouched) {
            //Modificar valors per els vostres
            anim.SetBool("crouch", false);
            capsule.offset = new Vector2(0.02451974f, 0.02572942f);
            capsule.size = new Vector2(1.626895f, 4.093686f);
            crouched = false;
        }

        movementX = Input.GetAxisRaw("Horizontal") * speedX;
        if (movementX != 0)
        {
            anim.SetBool("run", true);
        }
        else {
            anim.SetBool("run", false);
        }
        if (Input.GetKeyDown(KeyCode.Space) || Input.GetKeyDown(KeyCode.W))
        {
            jump = true;
        }
    }

    void FixedUpdate()
    {
        Move(movementX * Time.fixedDeltaTime, jump);
        jump = false;
    }

    private void Move(float move, bool jump)
    {
        Vector3 newSpeed = new Vector2(move, rigidbody.velocity.y);
        rigidbody.velocity = Vector3.SmoothDamp(rigidbody.velocity, newSpeed, ref speed, speedSmooth);

        if (move > 0 && !derecha) {
            Rotate();
        } else if (move < 0 && derecha) {
            Rotate();
        }
        if(jump && inGround) {
            anim.SetBool("jump", true);
            rigidbody.AddForce(new Vector2(0f, jumpPower));
            inGround = false;
        }
    }
    private void Rotate()
    {
        derecha = !derecha;
        Vector3 scale = transform.localScale;
        scale.x *= -1;
        transform.localScale = scale;
    }
    private void OnTriggerEnter2D(Collider2D col)
    {
        if (col.gameObject.tag == "Ground") {
            inGround = true;
            anim.SetBool("jump", false);
        }
       
    }
}
