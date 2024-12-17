package factory;

import dao.IGenericDAO;
import dao.*;

public class ExamenFactory implements DAOFactory {

	@Override
	public IGenericDAO create(String type) {
		if ("joc".equalsIgnoreCase(type)) {
            return new JocDAO();
        } else if ("plataforma".equalsIgnoreCase(type)) {
            return new PlataformaDAO();
        }
        return null;
	}

}
