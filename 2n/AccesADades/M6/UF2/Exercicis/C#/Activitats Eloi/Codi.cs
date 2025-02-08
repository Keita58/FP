using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Activitats
{
    internal class Codi
    {
        static void Main(string[] args)
        {
            //Activitat1();
            //Activitat2();
            //Activitat3();
            //Activitat4();
            //Activitat5();
            //Activitat6();
            //Activitat7();
            //Activitat8();
            //Activitat9();
            Activitat10();
        }

        static void Activitat1()
        {
            int num = int.Parse(Console.ReadLine());

            if (num % 10 == 0 || (num >= 100 && num <= 200))
                Console.WriteLine("true");
            else
                Console.WriteLine("false");
        }

        static void Activitat2()
        {
            char[] cadena = Console.ReadLine().ToCharArray();
            char aux = cadena[0];
            cadena[0] = cadena[cadena.Length - 1];
            cadena[cadena.Length - 1] = aux;

            for(int i = 0; i < cadena.Length; i++)
            {
                Console.Write(cadena[i]);
            }
        }

        static void Activitat3()
        {
            int files = int.Parse(Console.ReadLine());

            for(int i = 0; i < files; i++)
            {
                for (int j = 0; j < (i + 1); j++)
                    Console.Write("*");
                Console.WriteLine();
            }
        }

        static void Activitat4()
        {
            int files = int.Parse(Console.ReadLine());

            for(int i = 1; i < files + 1; i++)
            {
                for (int k = files; k > i; k--)
                {   
                    Console.Write(" ");
                }

                for (int j = 0; j < i; j++)
                {
                    Console.Write("* ");
                }
                Console.WriteLine();
            }
        }

        static void Activitat5()
        {
            int files = int.Parse(Console.ReadLine());

            for (int i = 1; i < files + 1; i++)
            {
                for (int k = files; k > i; k--)
                {
                    Console.Write(" ");
                }

                for (int j = 0; j < (i + (i-1)); j++)
                {
                    Console.Write("*");
                }
                Console.WriteLine();
            }

            for (int i = files - 1; i > 0; i--)
            {
                for (int k = files; k > i; k--)
                {
                    Console.Write(" ");
                }

                for (int j = 0; j < (i + (i - 1)); j++)
                {
                    Console.Write("*");
                }
                Console.WriteLine();
            }
        }

        static void Activitat6()
        {
            int[] num = new int[10];

            for (int i = 0; i < num.Length; i++)
                num[i] = 0;
            Random rnd = new Random();

            for (int i = 0; i < 10; i++)
            {
                int nRandom = rnd.Next(1, 11);
                num[nRandom - 1]++;
                Console.WriteLine(nRandom);
            }
            for (int i = 0; i < num.Length; i++)
            {
                if(num[i] > 0)
                Console.WriteLine(i+1 + ": " + num[i] + " vegades");
            }
        }

        static void Activitat7()
        {
            Random rnd = new Random();
            List<int> numsGenerats = new List<int>();

            for(int i = 0; i < 10; i++)
            {
                int num = rnd.Next(1, 101);
                numsGenerats.Add(num);
            }

            List<int> res = numsGenerats.OrderByDescending(x => x).ToList();

            Console.WriteLine("Sense ordenar:");

            for (int i = 0; i < numsGenerats.Count; i++)
                Console.WriteLine(numsGenerats[i]);

            Console.WriteLine();
            Console.WriteLine("Ordenats: ");

            for (int i = 0; i < res.Count; i++)
                Console.WriteLine(res[i]);
        }

        static void Activitat8() 
        {
            char[] cadenaText = Console.ReadLine().ToLower().ToCharArray();
            int[] nums = new int[2]; // 0 -> vocals | 1 -> consonants

            for (int i = 0; i < cadenaText.Length; i++)
            {
                if (cadenaText[i] == 'a' || cadenaText[i] == 'e' || cadenaText[i] == 'i' || cadenaText[i] == 'o' || cadenaText[i] == 'u')
                    nums[0]++;
                else
                    nums[1]++;
            }

            Console.WriteLine("Vocals: " + nums[0]);
            Console.WriteLine("Consonants: " + nums[1]);
        }

        static void Activitat9()
        {
            int num = int.Parse(Console.ReadLine());

            Console.WriteLine(Recursiu(0, 1, num));

            //Funció dins de funció :0
            int Recursiu(int numAnt, int numAct, int vegades)
            {
                if (vegades > 1)
                {
                    numAnt = numAnt + numAct;
                    vegades--;
                    return Recursiu(numAct, numAnt, vegades);
                }
                else if (vegades == 0)
                    return 0;
                return
                    numAct;
            }
        }

        static void Activitat10()
        {
            Random rnd = new Random();
            List<KeyValuePair<int, int>> num = new List<KeyValuePair<int, int>>();


        }
    }
}
