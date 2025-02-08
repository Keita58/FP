package Projecte;

/**
 * Classe que serveix per interaccions de la nau amb altres objectes com enemics, recursos, bonuses...
 * @param <T> Genèric per poder posar qualsevol tipus d'objecte que agafi la nau
 */
public class ObjecteCapturat <T>{

	private Nau nau;
	private T objecte;

	public ObjecteCapturat(Nau nau, T objecte){
		super();
		this.nau = nau;
		this.objecte = objecte;
	}

	public T getObjecte() {
		return objecte;
	}

	/**
	 * @return retorna la nau a la qual està equipada.
	 */
	public Nau getNau() {
		return nau;
	}

	public void setNau(Nau nau) {
		this.nau = nau;
	}

	@Override
	public String toString() {
		return "ObjecteCapturat{" +
				", nau=" + nau +
				", objecte=" + objecte +
				'}';
	}
}
