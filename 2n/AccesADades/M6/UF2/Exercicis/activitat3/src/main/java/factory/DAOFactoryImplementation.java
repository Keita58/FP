package factory;

public class DAOFactoryImplementation {
    public static DAOFactory getFactory(String choice) {
        if("Anime".equalsIgnoreCase(choice))
            return new AnimeFactory();
        return null;
    }
}
