using System.Text.Json;
using System.Text.Json.Serialization;

public class ConversorComponents : JsonConverter<Component>
{
    public override Component Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
    {
        using (var doc = JsonDocument.ParseValue(ref reader))
        {
            var type = doc.RootElement.GetProperty(@"_NomComponent").GetString();
            switch(type)
            {
                case "Transform": 
                    return JsonSerializer.Deserialize<Transform>(doc.RootElement.GetRawText());
                case "Render": 
                    return JsonSerializer.Deserialize<Render_Objecte>(doc.RootElement.GetRawText());
                case "Comportament_Moviment": 
                    return JsonSerializer.Deserialize<Comportament_Moviment>(doc.RootElement.GetRawText());
                default: 
                    return JsonSerializer.Deserialize<Component>(doc.RootElement.GetRawText());
            }
        }
    }

    public override void Write(Utf8JsonWriter writer, Component value, JsonSerializerOptions options) {}
}