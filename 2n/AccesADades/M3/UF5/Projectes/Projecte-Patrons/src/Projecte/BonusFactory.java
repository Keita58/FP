package Projecte;

public class BonusFactory implements JocFactory<Bonus> {

	/**
	 * Una Factory que ens crea diferents Bonus depenent del nom que li passem.
	 *
	 * @param tipus rep el tipus d'Objecte
	 * @param nom rep el nom de l'Objecte
	 * @return null si el nom o el tipus no és correcte.
	 */
	@Override
	public Bonus create(String tipus, String nom) {
		if ("Bonus".equalsIgnoreCase(tipus) && "Accelerador de velocitat".equalsIgnoreCase(nom)) {
			return new AcceleradorVelocitat();
		}
		else if ("Bonus".equalsIgnoreCase(tipus) && "Robot reparador".equalsIgnoreCase(nom)) {
			return new RobotReparador();
		}
		else if ("Bonus".equalsIgnoreCase(tipus) && "Porta espacial".equalsIgnoreCase(nom)) {
			return new PortaEspacial();
		}
		return null;
	}
}
