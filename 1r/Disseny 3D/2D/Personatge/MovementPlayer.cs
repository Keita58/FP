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
            anim.SetBool("Agacharse", true);
            capsule.offset = new Vector2(0f, -0.7258259f);
            capsule.size = new Vector2(1f, 1.862011f);
            return;
        }
        else if (crouched) {
            //Modificar valors per els vostres
            anim.SetBool("Agacharse", false);
            capsule.offset = new Vector2(0f, 0.01172209f);
            capsule.size = new Vector2(1f, 3.500869f);
            crouched = false;
        }

        movementX = Input.GetAxisRaw("Horizontal") * speedX;
        if (movementX != 0)
        {
            anim.SetBool("Correr", true);
        }
        else {
            anim.SetBool("Correr", false);
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
            anim.SetBool("Saltar", true);
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
            anim.SetBool("Saltar", false);
        }
       
    }
}
