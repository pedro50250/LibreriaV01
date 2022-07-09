package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.JPAHelper;
import beans.Libro;

public class LibroDAOJPAImpl extends GenericDAOJPAImpl<Libro, Integer> implements LibroDAO{

	public List<Libro> buscarPorCategoria(int Cat) throws DataBaseException
	{
		String SQL = "from Libro WHERE cat_lib ="+ Cat;
		List<Libro> ListaDeLibros = null;
		EntityManager entityManager = JPAHelper.getEntityManager();
		try{
			TypedQuery<Libro> consulta = entityManager.createQuery(SQL, Libro.class);
			ListaDeLibros = consulta.getResultList();
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(entityManager != null)
			{
				entityManager.close();
			}
		}
		return ListaDeLibros;
	}

}
