import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LlegirXML {
	
	public static void main(String[] args) {
		
		File fitxerOrigen = new File("resources/exemple.xml");
		try {
		    // Es crea el context indicant la classe arrel
		    //hauras de posar la classe adient, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mokedex.class);
		    
		    // Es crea un Unmarshaller amb el context de la classe Entrada
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    
		    // Es fa servir el mètode unmarshal per a obtenir les dades. Sempre s'ha de fer un casteig. fitxerOrigen es una String amb el path al fitxer
		    Mokedex m = (Mokedex) jaxbUnmarshaller.unmarshal(fitxerOrigen);
		    
		    System.out.println(m);
		    
		} catch (JAXBException je) {
		    je.printStackTrace();
		}

	}
}
