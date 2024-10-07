using NUnit.Framework;
using System;
using UnityEngine;
using System.Collections.Generic;

public class Classificacio : MonoBehaviour
{
    [Serializable]
    public class Info
    {
        public String nom;
        public int punts;
        public int temps;
    }

    public List<Info> list = new List<Info>();
}
