import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class main {

	public static void main(String[] args) throws IOException {
				
//		JsonObject mokepon = new JsonObject();
//		mokepon.addProperty("id", 2);
//		mokepon.addProperty("nom", "Zekrom");
//		mokepon.addProperty("tipus", "Elèctric/Drac");
//		mokepon.addProperty("pes", 120.5);
//		JsonObject evo = new JsonObject();
//		evo.addProperty("pre-evolucio", "-");
//		evo.addProperty("post-evolucio", "-");
//		mokepon.add("evolucions", evo);
//		JsonArray lloc = new JsonArray();
//		lloc.add("Cueva Incierta");
//		mokepon.add("localitzacions", lloc);
//		afegirMokedex("resources/mokedex.json", mokepon);
//--------------------------------------------------------------
//		modificarPes("resources/mokedex.json", 2, 224.5);
//--------------------------------------------------------------
//		afegirLocalitzacio("resources/mokedex.json", 2, "Alto Mando");
//--------------------------------------------------------------
//		postEvolucio("resources/mokedex.json", 1);
//--------------------------------------------------------------
//		Evolucio evo2 = new Evolucio("Magmar", "-");
//		ArrayList<String> a = new ArrayList<>();
//		a.add("Ladera Corona");
//		Entrada magmortar = new Entrada(3, "Magmortar", "Foc", 68.0, evo2, a);
//		afegirMokedex("resources/mokedex2.json", magmortar);
//--------------------------------------------------------------
//		Evolucio evo3 = new Evolucio("Magby", "Magmortar");
//		ArrayList<String> a2 = new ArrayList<>();
//		a2.add("Monte Ascuas");
//		Entrada magmortar = new Entrada(4, "Magmar", "Foc", 44.5, evo3, a2);
//		afegirMokedex("resources/mokedex2.json", magmortar);
//		GigantamaxPostEvolució("resources/mokedex2.json", 4);
//--------------------------------------------------------------
//		esborrarLocalitzacio("resources/mokedex2.json", 2, "Alto Mando");
//--------------------------------------------------------------
		Evolucio evo4 = new Evolucio("-", "Prinplup");
		ArrayList<String> a3 = new ArrayList<>();
		a3.add("Monte Ascuas");
		Entrada miplup = new Entrada(5, "Piplup", "Aigua", 5.2, evo4, a3);
		afegirMokedex("resources/mokedex2.json", miplup);
	}
	
	public static void esborrarLocalitzacio(String nomFitxer, int idEntrada, String Loc) throws IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Mokedex mok = gson.fromJson(new FileReader(nomFitxer), Mokedex.class);
		boolean trobat = false;
		
		for(Entrada e : mok.getEntrades()) {
			if(e.getId() == idEntrada) {
				for(int i = 0; i < e.getLocalitzacio().size(); i++) {
					if(e.getLocalitzacio().get(i).equals(Loc)) {
						e.getLocalitzacio().remove(i);
						trobat = true;
					}
				}
			}
		}
		
		if(!trobat) {
			System.out.println("Error, no s'ha trobat la localització especificada");
		}
		
		FileWriter fw = new FileWriter(nomFitxer);
		fw.append(gson.toJson(mok));
		fw.flush();fw.close();
	}
	
	public static void GigantamaxPostEvolució(String nomFitxer, int idEntrada) throws IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Mokedex mok = gson.fromJson(new FileReader(nomFitxer), Mokedex.class);
		boolean postevo, id;
		postevo = id = false;
		
		for (Entrada e : mok.getEntrades()) {
			if(e.getId() == idEntrada) {
				System.out.println(e.getEvolucions().post);
				id = true;
				
				for (Entrada e2 : mok.getEntrades()) {
					if(e2.getNom().equals(e.getEvolucions().post)) {
						e2.setPes(e2.getPes()*10);
						postevo = true;
					}
				}
			}
		}
		
		if(!id || !postevo) {
			System.out.println("Error, no s'ha trobat el Moképon especificat");
		}
		
		FileWriter fw = new FileWriter(nomFitxer);
		fw.append(gson.toJson(mok));
		fw.flush();fw.close();
	}
	
	public static void afegirMokedex(String nomFitxer, Entrada novaEntrada) throws IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Mokedex mok = gson.fromJson(new FileReader(nomFitxer), Mokedex.class);
		
		mok.getEntrades().add(novaEntrada);
		
		FileWriter fw = new FileWriter(nomFitxer);
		fw.append(gson.toJson(mok));
		fw.flush();fw.close();
	}
	
	public static void postEvolucio(String nomFitxer, int idEntrada) {
		
		try {
			//castejes a l'arrel mitjançant un casteig estàtic de JsonParser L'arrel pot ser un objecte o un array json, depèn
			JsonElement arrel = JsonParser.parseReader(new FileReader(nomFitxer));
			boolean trobat = false;
			
			if(arrel instanceof JsonObject) {
				JsonObject mokedex = arrel.getAsJsonObject();
				System.out.println(mokedex);
				JsonArray entrades = (JsonArray) mokedex.get("entrades");
				
				for(JsonElement e : entrades) {
					JsonObject obj = e.getAsJsonObject();
					if(obj.get("id").getAsInt() == idEntrada) {
						trobat = true;
						System.out.println(obj.get("evolucions").getAsJsonObject().get("post-evolucio"));
					}
				}
				
				Gson escriure = new GsonBuilder().setPrettyPrinting().create();
				FileWriter fw = new FileWriter(nomFitxer);
				fw.append(escriure.toJson(arrel));
				fw.flush();fw.close();
			}
			
			if(!trobat) {
				System.out.println("Error, entrada no trobada a la Mokédex");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void afegirLocalitzacio(String nomFitxer, int idEntrada, String novaLoc) {
		
		try {
			//castejes a l'arrel mitjançant un casteig estàtic de JsonParser L'arrel pot ser un objecte o un array json, depèn
			JsonElement arrel = JsonParser.parseReader(new FileReader(nomFitxer));
			boolean trobat = false;
			
			if(arrel instanceof JsonObject) {
				JsonObject mokedex = arrel.getAsJsonObject();
				JsonArray entrades = (JsonArray) mokedex.get("entrades");
				
				for(JsonElement e : entrades) {
					JsonObject obj = e.getAsJsonObject();
					if(obj.get("id").getAsInt() == idEntrada) {
						trobat = true;
						JsonArray loc = (JsonArray) obj.get("localitzacions");
						loc.add(novaLoc);
					}
				}
				
				Gson escriure = new GsonBuilder().setPrettyPrinting().create();
				FileWriter fw = new FileWriter(nomFitxer);
				fw.append(escriure.toJson(arrel));
				fw.flush();fw.close();
			}
			
			if(!trobat) {
				System.out.println("Error, entrada no trobada a la Mokédex");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void modificarPes(String nomFitxer, int idEntrada, double pesExtra) {
		 
		try {
			//castejes a l'arrel mitjançant un casteig estàtic de JsonParser L'arrel pot ser un objecte o un array json, depèn
			JsonElement arrel = JsonParser.parseReader(new FileReader(nomFitxer));
			boolean trobat = false;

			if(arrel instanceof JsonObject) {
				JsonObject mokedex = arrel.getAsJsonObject();
				JsonArray entrades = (JsonArray) mokedex.get("entrades");
				
				for(JsonElement e : entrades) {
					JsonObject obj = e.getAsJsonObject();
					if(obj.get("id").getAsInt() == idEntrada) {
						double pes = obj.get("pes").getAsDouble();
						pes += pesExtra;
						obj.addProperty("pes", pes);
						trobat = true;
					}
				}
				
				Gson escriure = new GsonBuilder().setPrettyPrinting().create();
				FileWriter fw = new FileWriter(nomFitxer);
				fw.append(escriure.toJson(arrel));
				fw.flush();fw.close();
			}
			
			if(!trobat) {
				System.out.println("Error, entrada no trobada a la Mokédex");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void afegirMokedex(String nomFitxer, JsonObject novaEntrada) {
		
		try {
			//castejes a l'arrel mitjançant un casteig estàtic de JsonParser L'arrel pot ser un objecte o un array json, depèn
			JsonElement arrel = JsonParser.parseReader(new FileReader(nomFitxer));
			
			if(arrel instanceof JsonObject) {
				JsonObject mokedex = arrel.getAsJsonObject();
				JsonArray entrades = (JsonArray) mokedex.get("entrades");
				entrades.add(novaEntrada);
				
				Gson escriure = new GsonBuilder().setPrettyPrinting().create();
				FileWriter fw = new FileWriter(nomFitxer);
				fw.append(escriure.toJson(arrel));
				fw.flush();fw.close();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
