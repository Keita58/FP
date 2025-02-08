using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SanchezLopezMarc_ExamenModelA
{
    public class Alumne : IComparable
    {

        public int AlumneID { get; set; }

        [StringLength(9)]
        public string DNI { get; set; }

        [StringLength(150)]
        public string NomComplet { get; set; }
        public Curs Curs { get; set; }

        [DefaultValue(0)]
        public int Import { get; set; }

        [DefaultValue(false)]
        public bool Repetidor { get; set; }
        public ICollection<Alumne> IDMentor { get; set; }
        public ICollection<Alumne> IDMentorat { get; set; }

        public Alumne()
        {
            IDMentor = new List<Alumne>();
            IDMentorat = new List<Alumne>();
        }

        public Alumne(string dNI, string nomComplet, Curs curs, int import, bool repetidor)
        {
            DNI = dNI;
            NomComplet = nomComplet;
            Curs = curs;
            Import = import;
            Repetidor = repetidor;
            IDMentor = new List<Alumne>();
            IDMentorat = new List<Alumne>();
        }

        public int CompareTo(object obj)
        {
            return this.AlumneID.CompareTo(((Alumne) obj).AlumneID);
        }

        public override string ToString()
        {
            return "Nom alumne: " + NomComplet + ", Id: " + AlumneID;
        }
    }

    public enum Curs
    {
        DAMvi,
        DAM,
        SMX,
        ASIX
    }
}
