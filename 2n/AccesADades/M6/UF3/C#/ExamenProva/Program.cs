using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamenProva
{
    internal class Program
    {
        static void Main(string[] args)
        {
            LinkedList<string> list = new LinkedList<string>();
            list.AddFirst("MOTOGP 25");
            list.AddFirst("MINECRAFT");
            list.AddFirst("SUPERMARIO BROS");
            list.AddFirst("NARUTO ULTIMATE NINJA");
            list.AddFirst("NEVA");
            list.AddFirst("ZELDA");
            list.AddFirst("SONIC");
            list.AddFirst("FIFA 25");
            list.AddFirst("EDELRING");
            

            List<string> aux = list.OrderBy(x => x.Length).ToList();
            Console.WriteLine("Llista ordenada per llargada: ");
            foreach (string x in aux)
            {
                Console.WriteLine(x);
            }

            Console.WriteLine();

            List<string> aux2 = list.OrderByDescending(x => x).ToList();
            Console.WriteLine("Llista ordenada descendent: ");
            foreach (string x in aux2)
            {
                Console.WriteLine(x);
            }
        }
    }
}
