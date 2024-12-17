package factory;

import DAO.IGenericDAO;
import DAO.JugadorDAO;
import DAO.PersonatgeDAO;

public class MySQLFactory implements DAOFactory{
    public IGenericDAO create(String daoType){
        if ("jugador".equalsIgnoreCase(daoType)) {
            return new JugadorDAO();
        } else if ("personatge".equalsIgnoreCase(daoType))
            return new PersonatgeDAO();
        return null;
    }
}
