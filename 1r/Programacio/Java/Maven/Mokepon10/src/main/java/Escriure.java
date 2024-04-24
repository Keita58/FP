import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Escriure {

	public static void main(String[] args) {
		
		//JSONObject, funciona igual que un HashMap
		JsonObject obj = new JsonObject();
		//posem entrades a l'objecte JSON, com si fos un HashMap
		//si son propietats primitives (String Boolean o Numeric) es fa  addProperty en comptes d’add
		obj.addProperty("nom", "marc");
		obj.addProperty("edad", 21);


		//JSONArray, funciona igual que una ArrayList
		JsonArray list = new JsonArray();
		list.add("jugar al WoW");
		list.add("programar");
		list.add("riure'm dels alumnes");
		//ara hem de posar la llista dintre de l'objecte, que ho fem amb un put normal
		obj.add("aficions", list);
		//creem un objecte GSON per a escriure

		try {
			Gson escribir = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(escribir.toJson(obj));
       
			FileWriter fw = new FileWriter("resources/prova.json");
			fw.append(escribir.toJson(obj));
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
