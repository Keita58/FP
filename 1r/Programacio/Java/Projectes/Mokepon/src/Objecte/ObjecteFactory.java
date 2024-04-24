package Objecte;

public class ObjecteFactory {
	
    public Objecte crearObjecte(String tipus)
    {
        if (tipus == null || tipus.isEmpty())
            return null;
        switch (tipus) {
        case "POCIO":
            return new Pocio("Pocio", 50);  //les pocions per defecte curen 50 de vida
        case "SUPERPOCIO":
            return new Pocio("Superpocio", 100);
        case "HIPERPOCIO":
            return new Pocio("Hiperpocio", 200);
        case "REVIURE":
        	return new Reviure();
            // seguir
        //fer la resta de casos
        default:
            throw new IllegalArgumentException("Tipus d'objecte desconegut "+tipus);
        }
    }
}

