package factory;

import dao.AnimeDAO;
import dao.ComandesDAO;
import dao.IGenericDAO;
import dao.PersonatgesDAO;
import dao.PlataformaDAO;

public class AnimeFactory implements DAOFactory<IGenericDAO>{

    @Override
    public IGenericDAO create(String type) {
        if("Anime".equalsIgnoreCase(type))
            return new AnimeDAO();
        else if("Comandes".equalsIgnoreCase(type))
            return new ComandesDAO();
        else if("Personatges".equalsIgnoreCase(type))
            return new PersonatgesDAO();
        else if("Plataforma".equalsIgnoreCase(type))
            return new PlataformaDAO();

        return null;
    }

}
