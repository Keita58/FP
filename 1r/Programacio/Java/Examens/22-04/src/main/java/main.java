import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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
import com.google.gson.reflect.TypeToken;

public class main {

	public static void main(String[] args) throws IOException {
		
//		llegirEscriureXML();
//		afegirIngredients("Bombay Blue Saphire", "aigua", 0.0);
//		saborPrincipal("Puerto de Indias");
//		alcoholisme();
//		System.out.println(volumPerAssignar("Bombay Blue Saphire"));
//		mapejaGson();
		saborejar("Bombay Blue Saphire");
	}
	
	public static void saborejar(String nomGin) throws JsonSyntaxException, JsonIOException, IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type arrayGins = new TypeToken<ArrayList<Gin>>() {}.getType();
		ArrayList<Gin> gins = gson.fromJson(new FileReader("resource/gins.json"), arrayGins);
		
		for(Gin g : gins) {
			if(g.getNom().equals(nomGin))
				if(g.getSabor() == null)
					g.setSabor(g.getIngredients().get(1).getNom());
		}
		
		FileWriter fw = new FileWriter("resource/gins_sabor.json");
		fw.append(gson.toJson(gins));
		fw.flush();fw.close();
	}
	
	public static void mapejaGson() throws IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type arrayGins = new TypeToken<ArrayList<Gin>>() {}.getType();
		ArrayList<Gin> gins = gson.fromJson(new FileReader("resource/gins.json"), arrayGins);
		
		
		FileWriter fw = new FileWriter("resource/gins_copy.json");
		fw.append(gson.toJson(gins));
		fw.flush();fw.close();
	}
	
	public static double volumPerAssignar(String nomgin) {
		
		double result = 0.0;
		try {
			//castejes a l'arrel mitjançant un casteig estàtic de JsonParser L'arrel pot ser un objecte o un array json, depèn
			JsonElement arrel = JsonParser.parseReader(new FileReader("resource/gins.json"));
			if(arrel instanceof JsonArray) {
				JsonArray gins = arrel.getAsJsonArray();
				
				for(JsonElement e : gins) {
					JsonObject gin = e.getAsJsonObject();
					if(gin.get("nom").getAsString().equals(nomgin)) {
						JsonArray ingredients = gin.get("ingredients").getAsJsonArray();
						for(JsonElement e2 : ingredients) {
							JsonObject o = e2.getAsJsonObject();
							result += o.get("vol").getAsDouble();
						}
					}
				}
				
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 100-result;
	}
	
	public static void alcoholisme() {
		
		try {
			//castejes a l'arrel mitjançant un casteig estàtic de JsonParser L'arrel pot ser un objecte o un array json, depèn
			JsonElement arrel = JsonParser.parseReader(new FileReader("resource/gins.json"));
			if(arrel instanceof JsonArray) {
				JsonArray gins = arrel.getAsJsonArray();
				
				for(JsonElement e : gins) {
					JsonObject gin = e.getAsJsonObject();
					int graduacio = gin.get("graduacio").getAsInt();
					graduacio++;
					gin.addProperty("graduacio", graduacio);
				}
				
				Gson escriure = new GsonBuilder().setPrettyPrinting().create();
				FileWriter fw = new FileWriter("resource/gins_moreAlcohol.json");
				fw.append(escriure.toJson(arrel));
				fw.flush();fw.close();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saborPrincipal(String nomGin) {
		
		File fitxer = new File("resource/gins.xml");
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Celler.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Celler c = (Celler) jaxbUnmarshaller.unmarshal(fitxer);
		    
		    boolean trobat = false;
		    
		    for(int i = 0; i < c.getGins().size(); i++) {
		    	if(c.getGins().get(i).getNom().equals(nomGin)) {
		    		if(c.getGins().get(i).getSabor() != null) {
		    			for(Ingredient in : c.getGins().get(i).getIngredients()) {
			    			if(in.getNom().equals(c.getGins().get(i).getSabor())) {
								System.out.println(in.getVol());
								trobat = true;
			    			}
		    			}
		    		}
		    		else {
		    			trobat = true;
		    			System.out.println("ORIGINAL");
		    		}
		    	}
		    }
		    
		    if(!trobat)
		    	System.out.println("NO");
		    //Fins aquí llegeix
		    
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
	}
	
	public static void afegirIngredients(String nomGin, String nomIngredient, double volum) {
		
		File fitxer = new File("resource/gins.xml");
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Celler.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Celler c = (Celler) jaxbUnmarshaller.unmarshal(fitxer);
		    
		    for(int i = 0; i < c.getGins().size(); i++) {
		    	if(c.getGins().get(i).getNom().equals(nomGin))
		    		c.getGins().get(i).getIngredients().add(new Ingredient(nomIngredient, volum));
		    }
		    //Fins aquí llegeix
		    
		    //A partir d'aquí escriu
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la
		    // instruccio que segueix)
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    //entrada es un objecte de tipus Entrada. fitxerDesti es una String amb el path al fitxer
		    jaxbMarshaller.marshal(c, new File("resource/gins_added.xml"));
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
	}
	
	public static void llegirEscriureXML() {
		
		File fitxerOrigen = new File("resource/gins.xml");
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Celler.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Celler c = (Celler) jaxbUnmarshaller.unmarshal(fitxerOrigen);
		    
		    System.out.println(c);
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la
		    // instruccio que segueix)
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    //entrada es un objecte de tipus Entrada. fitxerDesti es una String amb el path al fitxer
		    jaxbMarshaller.marshal(c, new File("resource/gins_copia.xml"));
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
	}
}
