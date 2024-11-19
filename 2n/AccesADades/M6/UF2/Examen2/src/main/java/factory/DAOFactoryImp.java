package factory;

public class DAOFactoryImp {

	public static DAOFactory getFactory(String choice){
        if("MySQL".equalsIgnoreCase(choice)){
            return new ExamenFactory();
        }
        return null;
    }
}
