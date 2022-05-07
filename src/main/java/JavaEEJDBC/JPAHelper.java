package JavaEEJDBC;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHelper {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cursodia");

	public static EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}
	
	
}
