package Projecte;

/**
 * Interfície de la qual depenen les factories d'Enemic, Galàxia, Equipament, Bonus i Recursos
 * @param <T> Genèric per permetre la creació de diferents factories
 */
public interface JocFactory <T>{
	T create(String tipus, String nom);
}
