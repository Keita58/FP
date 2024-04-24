import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Llegir {

	public static void main(String[] args) throws ParseException {
		
		try {
			//castejes a l'arrel mitjançant un casteig estàtic de JsonParser L'arrel pot ser un objecte o un array json, depèn
			JsonElement arrel = JsonParser.parseReader(new FileReader("resources/prova.json"));
			//en aquest cas sabem que és un objecte així que ho castegem directament a JSONObject
			JsonObject jsonObject = arrel.getAsJsonObject();
			System.out.println(jsonObject);


			//keyset et torna totes les claus del diccionari. En aquest cas, un set ["nom","edad","aficions"]
			System.out.println(jsonObject.keySet());
			String name = jsonObject.get("nom").getAsString();
			System.out.println(name);
			//Recordem: pots ferho parsejant o fent servir la funció getAs
			long age = jsonObject.get("edad").getAsLong();
			System.out.println(age);


			// el JSONArray funciona com una ArrayList, per tant es pot recorrer amb un for o un foreach
			JsonArray aficions = jsonObject.get("aficions").getAsJsonArray();

			for(Object s : aficions) {
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
