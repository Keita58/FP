package Projecte;

public class EquipamentFactory implements JocFactory<Equipament> {

	/**
	 * Factory que ens crea diferents equipaments segons el nom que li facilitem.
	 *
	 * @param tipus "El tipus de l'objecte, que en aquest cas sempre serà Equipament."
	 * @param nom "El nom de l'equipament / objecte".
	 * @return "Si el nom o el tipsus és incorrecte retorna null, és a dir no fa res."
	 */
	@Override
	public Equipament create(String tipus, String nom) {
		if ("Equipament".equalsIgnoreCase(tipus) && "Escut protector".equalsIgnoreCase(nom)) {
			return new Escut();
		}
		else if ("Equipament".equalsIgnoreCase(tipus) && "Cano".equalsIgnoreCase(nom)) {
			return new Cano();
		}
		else if ("Equipament".equalsIgnoreCase(tipus) && "Imant magnetic".equalsIgnoreCase(nom)) {
			return new ImantMangetic();
		}
		return null;
	}
}
