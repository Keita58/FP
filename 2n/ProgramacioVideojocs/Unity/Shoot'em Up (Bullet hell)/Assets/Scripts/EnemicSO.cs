using UnityEditor.Animations;
using UnityEngine;

[CreateAssetMenu(fileName = "EnemicSO", menuName = "Scriptable Objects/EnemicSO")]
public class EnemicSO : ScriptableObject
{
    public float vides;
    public int mal;
    public int punts;
    public int velocitat;
    public AnimatorController animacio;
    public Color color;
}
