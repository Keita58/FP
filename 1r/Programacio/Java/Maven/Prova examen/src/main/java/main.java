import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
		
//		Mod m = llegir("resources/items.xml");
//		escriure(m, "resources/items_copy.xml");
//		addCategory("Bad", "Neutroamine");
//		System.out.println(getIntricateValue(32));
//--------------------------------------------------------------
//		setGravity(5);
//		System.out.println(countByCategory("Industrial"));
//		copy();
//		addFlamable();
	}
	
	public static void addFlamable() throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Mod2 mok = gson.fromJson(new FileReader("resources/items.json"), Mod2.class);
		
		for(ThingDef2 def : mok.getDef()) {
			if(def.getStatBases().getFlammability() >= 1)
				def.getThingCategories().add("Flammable");
		}
		
		FileWriter fw = new FileWriter("resources/items_added2.json");
		fw.append(gson.toJson(mok));
		fw.flush();fw.close();
	}
	
	public static void copy() throws IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Mod2 mok = gson.fromJson(new FileReader("resources/items.json"), Mod2.class);
		
		FileWriter fw = new FileWriter("resources/items_copy.json");
		fw.append(gson.toJson(mok));
		fw.flush();fw.close();
	}
	
	public static int countByCategory(String categoryName) {
		
		int count = 0;
		try {
			//castejes a l'arrel mitjançant un casteig estàtic de JsonParser L'arrel pot ser un objecte o un array json, depèn
			JsonElement arrel = JsonParser.parseReader(new FileReader("resources/items.json"));
			if(arrel instanceof JsonObject) {
				JsonObject mod = arrel.getAsJsonObject();
				JsonArray thingdef = (JsonArray) mod.get("Defs");
				
				for(JsonElement e : thingdef) {
					JsonObject obj = e.getAsJsonObject();
					JsonArray array = obj.get("thingCategories").getAsJsonArray();
					for(JsonElement s : array) {
						if(s.getAsString().equals(categoryName)) {
							count++;
							break;
						}
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public static void setGravity(double value) {
		
		try {
			//castejes a l'arrel mitjançant un casteig estàtic de JsonParser L'arrel pot ser un objecte o un array json, depèn
			JsonElement arrel = JsonParser.parseReader(new FileReader("resources/items.json"));
			if(arrel instanceof JsonObject) {
				JsonObject mod = arrel.getAsJsonObject();
				JsonArray thingdef = (JsonArray) mod.get("Defs");
				
				for(JsonElement e : thingdef) {
					JsonObject obj = e.getAsJsonObject();
					double pes = obj.get("statBases").getAsJsonObject().get("Mass").getAsDouble();
					pes *= value;
					obj.get("statBases").getAsJsonObject().addProperty("Mass", pes);
				}
				
				Gson escriure = new GsonBuilder().setPrettyPrinting().create();
				FileWriter fw = new FileWriter("resources/itemsGravity.json");
				fw.append(escriure.toJson(arrel));
				fw.flush();fw.close();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getIntricateValue(int minValue) {
		
		File fitxer = new File("resources/items.xml");
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mod.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Mod m = (Mod) jaxbUnmarshaller.unmarshal(fitxer);
		    
		    ArrayList<String> def = new ArrayList<>();
		    for(int i = 0; i < m.getDef().size(); i++) {
		    	if(m.getDef().get(i).isIntricate() != null && m.getDef().get(i).getStatBases().getMarketValue() >= minValue)
		    		def.add(m.getDef().get(i).getDefName());
		    }
		    //Fins aquí llegeix
		    
		    return def;
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
		return null;
	}
	
	public static void addCategory(String categoryName, String defName) {
		
		File fitxer = new File("resources/items.xml");
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mod.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Mod m = (Mod) jaxbUnmarshaller.unmarshal(fitxer);
		    
		    for(int i = 0; i < m.getDef().size(); i++) {
		    	if(m.getDef().get(i).getDefName().equals(defName))
		    		m.getDef().get(i).getThingCategories().add(categoryName);
		    }
		    //Fins aquí llegeix
		    
		    //A partir d'aquí escriu
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la
		    // instruccio que segueix)
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    //entrada es un objecte de tipus Entrada. fitxerDesti es una String amb el path al fitxer
		    jaxbMarshaller.marshal(m, new File("resources/items_added.xml"));
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
	}
	
	public static void escriure(Mod m, String fitxer) {
		
		try{
			File fitxerDesti = new File(fitxer);
		    //si ja has creat el jaxbContext abans per llegir no cal aquesta línea, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mod.class);
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la
		    // instruccio que segueix)
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    //entrada es un objecte de tipus Entrada. fitxerDesti es una String amb el path al fitxer
		    jaxbMarshaller.marshal(m, fitxerDesti);

		} catch (JAXBException je) {
		            je.printStackTrace();
		}
	}

	public static Mod llegir(String fitxer) {
		
		File fitxerOrigen = new File(fitxer);
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mod.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Mod m = (Mod) jaxbUnmarshaller.unmarshal(fitxerOrigen);
		    
		    System.out.println(m);
		    return m;
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
		return null;
	}
}
