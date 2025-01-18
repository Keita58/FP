package Projecte;

public class FactoryProvider {

	/**
	 * @param choice String que ens permet seleccionat el tipus de factory que volem utilitzar.
	 * @return Retorna la factory demanada per l'string
	 */
	public static JocFactory getFactory(String choice){
		if ("Enemic".equalsIgnoreCase(choice)) {
			return new EnemicFactory();
		}
		else if ("Recurs".equalsIgnoreCase(choice)) {
			return new RecursFactory();
		}
		else if ("Bonus".equalsIgnoreCase(choice)) {
			return new BonusFactory();
		}
		else if ("Equipament".equalsIgnoreCase(choice)) {
			return new EquipamentFactory();
		}
		else if ("Galàxia".equalsIgnoreCase(choice)) {
			return new GalaxiaFacotry();
		}
		
		return null;
	}
}
