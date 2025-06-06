using System.Text.Json;
using System.Text.Json.Serialization;
using m17;

public class Conversor : JsonConverter<GameObject>
{
    public override GameObject Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
    {
        using (var doc = JsonDocument.ParseValue(ref reader))
        {
            
            var type = doc.RootElement.GetProperty(@"_Nom").GetString();
            switch(type)
            {
                case "Rectangle":
                    return JsonSerializer.Deserialize<ObjecteRectangle>(doc.RootElement.GetRawText());
                default:    
                    return JsonSerializer.Deserialize<GameObject>(doc.RootElement.GetRawText());
            }
        }
    }

    public override void Write(Utf8JsonWriter writer, GameObject value, JsonSerializerOptions options) {}
}