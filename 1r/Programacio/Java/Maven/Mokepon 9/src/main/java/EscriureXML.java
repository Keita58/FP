import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class EscriureXML {

	public static void main(String[] args) {
		
		File fitxerDesti = new File("resources/exemple.xml");
		Evolucio patata = new Evolucio("-", "-");
		String l = "Alto Mando";
		ArrayList<String> lo = new ArrayList<>();
		lo.add(l);
		
		Evolucio ma = new Evolucio("-", "Gyarados");
		String l2 = "Ruta 12";
		String l3 = "Ruta 13";
		String l4 = "Ruta 17";
		String l5 = "Ruta 18";
		ArrayList<String> lo2 = new ArrayList<>();
		lo2.add(l2);lo2.add(l3);lo2.add(l4);lo2.add(l5);
		Entrada entrada1 = new Entrada(1, "Mekrom", "Elèctric/Drac", 120.5, patata, lo);
		Entrada entrada2 = new Entrada(2, "Magikarp", "Aigua", 15.0, ma, lo2);
//		Entrada entrada3 = new Entrada(3, "Mcizor", "Insecte/Acer");
//		Entrada entrada4 = new Entrada(4, "Mogepi", "Fada");
		ArrayList<Entrada> entrades = new ArrayList<>();
		entrades.add(entrada1); entrades.add(entrada2);//entrades.add(entrada3); entrades.add(entrada4);
		Mokedex mokedex = new Mokedex("Marc", entrades);
		try{
		    //si ja has creat el jaxbContext abans per llegir no cal aquesta línea, clar.
		    JAXBContext jaxbContext = JAXBContext.newInstance(Mokedex.class);
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la
		    // instruccio que segueix)
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    //entrada es un objecte de tipus Entrada. fitxerDesti es una String amb el path al fitxer
		    jaxbMarshaller.marshal(mokedex, fitxerDesti);


		} catch (JAXBException je) {
		            je.printStackTrace();
		}

	}
}
