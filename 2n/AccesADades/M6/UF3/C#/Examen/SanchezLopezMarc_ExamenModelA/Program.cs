using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SanchezLopezMarc_ExamenModelA
{
    public class Program
    {
        static void Main(string[] args)
        {
            using (Context ctx = new Context())
            {
                Alumne a = new Alumne("123456789", "Patata", Curs.DAMvi, 300, true);
                Alumne b = new Alumne("123456789", "Ceba", Curs.DAM, 300, true);
                Alumne c = new Alumne("123456789", "Enciam", Curs.DAMvi, 180, true);
                Alumne d = new Alumne("123456789", "Bleda", Curs.SMX, 20, true);
                Alumne e = new Alumne("123456789", "Pastanaga", Curs.ASIX, 100, false);
                ctx.Alumnes.Add(a);
                ctx.Alumnes.Add(b);
                ctx.Alumnes.Add(c);
                ctx.Alumnes.Add(d);
                ctx.Alumnes.Add(e);
                ctx.SaveChanges();

                a.IDMentorat.Add(b);
                c.IDMentorat.Add(b);
                ctx.SaveChanges();

                Console.WriteLine(a.ToString());
                Console.WriteLine(b.ToString());
                Console.WriteLine(c.ToString());
                Console.WriteLine(d.ToString());
                Console.WriteLine(e.ToString());

                getAlumne(true);
            }
        }

        public static void getAlumne(bool repetidor)
        {
            using (Context ctx = new Context())
            {
                List<Alumne> a = ctx.Alumnes.ToList();

                foreach(Alumne al in a)
                {
                    if (repetidor && al.Repetidor)
                    {
                        al.ToString();
                        al.IDMentorat.ToString();
                    }
                }
                
            }
        }
    }
}
