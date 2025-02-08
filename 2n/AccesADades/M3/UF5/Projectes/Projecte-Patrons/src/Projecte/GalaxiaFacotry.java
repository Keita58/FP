package Projecte;

public class GalaxiaFacotry implements JocFactory<Galaxia> {

	/**
	 * Factory que ens crea diferents galàxies segons el nom que li facilitem.
	 *
	 * @param tipus El tipus de l'objecte, que en aquest cas sempre serà Galàxia
	 * @param nom El nom de la Galàxia.
	 * @return Si el tipus o el nom és incorrecte retorna null, és a dir, no fa res.
	 */
	@Override
	public Galaxia create(String tipus, String nom) {
		if ("Galàxia".equalsIgnoreCase(tipus)&& "Espiral".equalsIgnoreCase(nom)) {
			return new Espiral();
		}else if ("Galàxia".equalsIgnoreCase(tipus)&& "El·liptica".equalsIgnoreCase(nom)) {
			return new Eliptica();
		}else if ("Galàxia".equalsIgnoreCase(tipus)&& "Irregular".equalsIgnoreCase(nom)) {
			return new Irregular();
		}else if ("Galàxia".equalsIgnoreCase(tipus)&& "Lenticular".equalsIgnoreCase(nom)) {
			return new Lenticular();
		}
		return null;
	}
}
