using motoret;
namespace m17;

class ElVostreMaina
{
    public static void Main()
    {
        Motoret motoret = Motoret.Instance;
        //Inicialització de la finestra i elements interns del motor
        motoret.Inicialitzar(640, 480, "El vostre main", Raylib_cs.Color.White, 300);
        string nom = "dades.json";
        
        //TODO: aqui creeu els vostres objectes que necessitareu a l'escena del joc i aneu-los afegint.
        
        List<GameObject> aux = new List<GameObject>();
        
        if(!File.Exists(nom)) {
            ObjecteSpawner os = new ObjecteSpawner(5, 3);
            motoret.AfegirGameObject(os);
        }
        else {
            aux = Guardat.Carregar(nom)!;
        }
        //Executem el Run del motor i ara funcionarà el joc.
        motoret.Correr();
        //Guardat.Guardar(aux, nom);

        //Quan alguna cosa interna del nostre joc aturi el motor "Motoret.Instancia.Finalitzar = true"
        //caldrà, doncs, que aturem el motor per tal que s'alliberin els recursos reservats
        motoret.Aturar();
    }
}