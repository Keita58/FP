package Projecte;

public class RecursFactory implements JocFactory<Recurs> {
	/**
	 * Factory que ens crea diferents recursos segons el nom que li facilitem.
	 *
	 * @param tipus El tipus de l'objecte, que en aquest cas sempre serà Recurs
	 * @param nom El nom del recurs.
	 * @return Si el tipus o el nom és incorrecte retorna null, és a dir, no fa res.
	 */
	@Override
	public Recurs create(String tipus, String nom) {
		if ("Recurs".equalsIgnoreCase(tipus)&& "Cristall d'energia".equalsIgnoreCase(nom)) {
			return new CristallEnergia();
		}else if ("Recurs".equalsIgnoreCase(tipus)&& "Kebab".equalsIgnoreCase(nom)) {
			return new Kebab();
		}else if ("Recurs".equalsIgnoreCase(tipus)&& "Or".equalsIgnoreCase(nom)) {
			return new Or();
		}
		return null;
	}

}
