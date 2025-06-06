using System.Text.Json;

public class Guardat
{
    public static void Guardar(object go, string nom) 
    {
        var options = new JsonSerializerOptions() 
        { 
            WriteIndented = true,
        };
        var json = JsonSerializer.Serialize(go, options);
        File.WriteAllText(nom, json);
    }

    public static List<GameObject>? Carregar(string nom) 
    {
        var opcions = new JsonSerializerOptions();
        opcions.Converters.Add(new ConversorComponents());
        opcions.Converters.Add(new Conversor());
        string fitxer = File.ReadAllText(nom);
        return JsonSerializer.Deserialize<List<GameObject>>(fitxer, opcions);
    }
}