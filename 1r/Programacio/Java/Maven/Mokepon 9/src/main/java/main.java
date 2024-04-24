import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class main {

	public static void main(String[] args) {
		
//		Evolucio ma = new Evolucio("-", "Prinplup");
//		String l2 = "Amagatalls subaquàtics";
//		ArrayList<String> lo2 = new ArrayList<>();
//		lo2.add(l2);
//		Entrada entrada1 = new Entrada(3, "Piplup", "Aigua", 20.0, ma, lo2);
//		afegirMokedex("resources/exemple.xml", entrada1);
		//---------------------------
//		modificarPes("resources/exemple.xml", 2, -5.0);
		//---------------------------
//		afegirLocalitzacio("resources/exemple.xml", 3, "Lab. Professor Oak");
		//---------------------------
//		postEvolucio("resources/exemple.xml", 3);
	}
	
	public static void postEvolucio(String nomFitxer, int idEntrada) {
		File fitxer = new File(nomFitxer);
		boolean trobat = false;
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mokedex.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Mokedex m = (Mokedex) jaxbUnmarshaller.unmarshal(fitxer);
		    for(int i = 0; i < m.getEntrades().size(); i++) {
		    	if(m.getEntrades().get(i).getId() == idEntrada) {
		    		if(m.getEntrades().get(i).getEvolucions().post.equals("-"))
		    			System.out.println("No té evolució");
		    		else
		    			System.out.println(m.getEntrades().get(i).getEvolucions().post);
		    		trobat = true;
		    	}
		    }
		    //Fins aquí llegeix
		    
		    if(!trobat)
		    	System.out.println("No s'ha trobat l'entrada especificada");
		    
		    //A partir d'aquí escriu
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la
		    // instruccio que segueix)
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    //entrada es un objecte de tipus Entrada. fitxerDesti es una String amb el path al fitxer
		    jaxbMarshaller.marshal(m, fitxer);
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
	}
	
	public static void afegirLocalitzacio(String nomFitxer, int idEntrada, String novaLoc) {
		File fitxer = new File(nomFitxer);
		boolean trobat = false;
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mokedex.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Mokedex m = (Mokedex) jaxbUnmarshaller.unmarshal(fitxer);
		    for(int i = 0; i < m.getEntrades().size(); i++) {
		    	if(m.getEntrades().get(i).getId() == idEntrada) {
		    		m.getEntrades().get(i).getLocalitzacio().add(novaLoc);
		    		trobat = true;
		    	}
		    }
		    //Fins aquí llegeix
		    
		    if(!trobat)
		    	System.out.println("No s'ha trobat l'entrada especificada");
		    
		    //A partir d'aquí escriu
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la
		    // instruccio que segueix)
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    //entrada es un objecte de tipus Entrada. fitxerDesti es una String amb el path al fitxer
		    jaxbMarshaller.marshal(m, fitxer);
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
	}
	
	public static void modificarPes(String nomFitxer, int idEntrada, double pesExtra) {
		File fitxer = new File(nomFitxer);
		boolean trobat = false;
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mokedex.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Mokedex m = (Mokedex) jaxbUnmarshaller.unmarshal(fitxer);
		    for(int i = 0; i < m.getEntrades().size(); i++) {
		    	if(m.getEntrades().get(i).getId() == idEntrada) {
		    		m.getEntrades().get(i).setPes(m.getEntrades().get(i).getPes() + pesExtra);
		    		trobat = true;
		    	}
		    }
		    //Fins aquí llegeix
		    
		    if(!trobat)
		    	System.out.println("No s'ha trobat l'entrada especificada");
		    
		    //A partir d'aquí escriu
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la
		    // instruccio que segueix)
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    //entrada es un objecte de tipus Entrada. fitxerDesti es una String amb el path al fitxer
		    jaxbMarshaller.marshal(m, fitxer);
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
	}
	
	public static void afegirMokedex(String nomFitxer, Entrada novaEntrada) {
		File fitxer = new File(nomFitxer);
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mokedex.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Mokedex m = (Mokedex) jaxbUnmarshaller.unmarshal(fitxer);
		    m.getEntrades().add(novaEntrada);
		    //Fins aquí llegeix
		    
		    //A partir d'aquí escriu
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la
		    // instruccio que segueix)
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    //entrada es un objecte de tipus Entrada. fitxerDesti es una String amb el path al fitxer
		    jaxbMarshaller.marshal(m, fitxer);
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}
	}
}
