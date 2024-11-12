package factory;

public class DAOFactoryImplementation {
    public static DAOFactory geFactory(String choice) {
        if("Anime".equalsIgnoreCase(choice))
            return new AnimeFactory();
        return null;
    }
}
