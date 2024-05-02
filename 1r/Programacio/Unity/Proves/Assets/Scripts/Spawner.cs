using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawner : MonoBehaviour
{
    public GameObject blueTank;
    public GameObject redTank;
    public GameObject blackTank;
    public GameObject whiteTank;
    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(spawner());
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public IEnumerator spawner()
    {
        int num = Random.Range(0, 4); // De 0 a 3
        switch(num)
        {
            case 0: 
                GameObject bluetank = Instantiate(blueTank, new Vector2(this.transform.position.x, this.transform.position.y), transform.rotation); 
                break;
            case 1:
                GameObject redtank = Instantiate(redTank, new Vector2(this.transform.position.x, this.transform.position.y), transform.rotation);
                break;
            case 2:
                GameObject blacktank = Instantiate(blackTank, new Vector2(this.transform.position.x, this.transform.position.y), transform.rotation);
                break;
            case 3:
                GameObject whitetank = Instantiate(whiteTank, new Vector2(this.transform.position.x, this.transform.position.y), transform.rotation);
                break;
        }
        yield return new WaitForSeconds(0.64333141592f);
    }
}
