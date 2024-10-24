using UnityEditor.Animations;
using UnityEngine;

[CreateAssetMenu(fileName = "EnemicSO", menuName = "Scriptable Objects/EnemicSO")]
public class EnemicSO : ScriptableObject
{
    public int vides;
    public int mal;
    public AnimatorController animacio;
    public Color color;
}
