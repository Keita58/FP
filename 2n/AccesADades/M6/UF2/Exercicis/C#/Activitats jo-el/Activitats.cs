using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Activitat_1
{
    internal class Activitats
    {
        static void Main(string[] args)
        {
            //Patata();
            //Capitals();
            //Dreta();
            Bolas();
        }

        static void Patata()
        {
            int casos = int.Parse(Console.ReadLine());
            while (casos > 0)
            {
                int vegades = int.Parse(Console.ReadLine());
                while (vegades > 0)
                {
                    Console.WriteLine("No ofendre al president patata");
                    vegades--;
                }
                casos--;
            }
        }

        static void Capitals()
        {
            int casos = int.Parse(Console.ReadLine());
            
            for (int i = 0; i < casos; i++)
            {
                Dictionary<String, String> ciutats = new Dictionary<String, String>();
                int aux = int.Parse(Console.ReadLine());

                for(int j = 0; j < aux - 1; j++)
                {
                    String[] capitals = Console.ReadLine().Split('-');
                    ciutats.Add(capitals[0], capitals[1]);
                }

                string ciutat = Console.ReadLine();
                if (ciutats.ContainsKey(ciutat))
                {
                    Console.WriteLine(ciutats[ciutat]);
                }
                else
                {
                    Console.WriteLine("NO HO SE");
                }
            }
        }

        static void Dreta()
        {
            int midaArray = int.Parse(Console.ReadLine());

            List<int> numeros = new List<int>();

            string[] aux = Console.ReadLine().Split(' ');

            for(int i = 0; i < midaArray; i++)
            {
                numeros.Add(int.Parse(aux[i]));
            }

            int ultim = numeros[midaArray - 1];
            numeros.RemoveAt(midaArray - 1);
            numeros.Insert(0, ultim);

            for (int i = 0; i < numeros.Count; i++)
            {
                Console.Write(numeros[i]);
                Console.Write(' ');
            }  
        }

        static void Bolas()
        {
            int casos = int.Parse(Console.ReadLine());

            for (int i = 0; i < casos; i++)
            {
                string paraula = Console.ReadLine();
                char[] pSeparada = new char[paraula.Length];
                for (int j = 0; j < paraula.Length; j++) { pSeparada[j] = paraula[j]; }
                string[] posicions = Console.ReadLine().Split(' ');

                char aux = pSeparada[int.Parse(posicions[0])];
                pSeparada[int.Parse(posicions[0])] = pSeparada[int.Parse(posicions[1])];
                pSeparada[int.Parse(posicions[1])] = aux;

                for (int j = 0; j < pSeparada.Length; j++)
                {
                    Console.Write(pSeparada[j]);
                }
                Console.WriteLine();
            }
        }
    }
}
