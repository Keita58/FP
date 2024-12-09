using UnityEngine;

namespace m17.examen
{
    public class UIBotoMenu : MonoBehaviour
    {
        [SerializeField] private GameObject _Menu;
        
        public void ToggleMenu()
        {
            _Menu.SetActive(!_Menu.activeSelf);
        }
    }
}
