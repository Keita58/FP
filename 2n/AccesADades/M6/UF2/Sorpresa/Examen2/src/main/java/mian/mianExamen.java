package mian;

import dao.IJocDAO;
import dao.IPlataformaDAO;
import entitat.Jocs;
import entitat.plataforma;
import factory.DAOFactory;
import factory.DAOFactoryImp;

public class mianExamen {

	public static void main(String[] args) {
		
		plataforma p1 = new plataforma("a", false);
		plataforma p2 = new plataforma("b", false);
		plataforma p3 = new plataforma("c", false);
		plataforma p4 = new plataforma("d", false);
		plataforma p5 = new plataforma("e", false);
		
		Jocs j1 = new Jocs("a", 10);
		Jocs j2 = new Jocs("b", 10);
		Jocs j3 = new Jocs("c", 10);
		Jocs j4 = new Jocs("d", 10);
		Jocs j5 = new Jocs("e", 10);
		
		
		DAOFactoryImp dao = new DAOFactoryImp();
        DAOFactory daoFactory = dao.getFactory("MySQL");

		IJocDAO jDAO = (IJocDAO)daoFactory.create("joc");
		IPlataformaDAO pDAO = (IPlataformaDAO)daoFactory.create("plataforma");
		
		jDAO.create(j1);
		jDAO.create(j2);
		jDAO.create(j3);
		jDAO.create(j4);
		jDAO.create(j5);
		
		pDAO.create(p1);
		pDAO.create(p2);
		pDAO.create(p3);
		pDAO.create(p4);
		pDAO.create(p5);
		
		j1.setPlataforma(p1);
    }
}
