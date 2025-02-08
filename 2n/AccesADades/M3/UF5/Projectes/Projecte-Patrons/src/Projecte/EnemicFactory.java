package Projecte;

public class EnemicFactory implements JocFactory<Enemic>{

	/**
	 * Factory que ens crea diferents enemics segons el nom que li facilitem.
	 *
	 * @param tipus el tipus, que en aquest cas sempre será Enemic.
	 * @param nom el nom de l'Enemic
	 * @return si el nom o el tipus és incorrecte retorna null, és a dir, no fa res.
	 */
	@Override
	public Enemic create(String tipus, String nom) {
		if ("Enemic".equalsIgnoreCase(tipus) && "Alien".equalsIgnoreCase(nom)) {
			return new Alien();
		}
		else if ("Enemic".equalsIgnoreCase(tipus)&& "Meteorit".equalsIgnoreCase(nom)) {
			return new Meteorit();
		}
		else if ("Enemic".equalsIgnoreCase(tipus)&& "Nau de combat".equalsIgnoreCase(nom)) {
			return new NauCombat();
		}
		else if ("Enemic".equalsIgnoreCase(tipus)&& "Supernova".equalsIgnoreCase(nom)) {
			return new Supernova();
		}
		return null;
	}
}
