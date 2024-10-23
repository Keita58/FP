package Examen;

public class GelatFactory {

	public Gelats crearGelat(String tipus, Sabors sabor) {
        if (tipus.equalsIgnoreCase("Tarrina")) {
            Tarrina t = new Tarrina(sabor);
            return t;
        } 
        else if (tipus.equalsIgnoreCase("Cucurutxo")) {
            Cucurutxo c = new Cucurutxo(sabor);
            return c;
        } 
        return null;
    }
}
