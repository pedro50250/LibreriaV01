package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.JPAHelper;
import beans.Libro;

public class LibroDAO {

	public int insertar(Libro lib) throws DataBaseException{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.persist(lib);
			entityTransaction.commit();
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
        
        
		return 0;
	}

	@SuppressWarnings({ })
	public List<Libro> consultarLibros() throws DataBaseException{
		List <Libro> ListaDeLibros = null;
		EntityManager entityManager = JPAHelper.getEntityManager();
		try{
			TypedQuery<Libro> consulta = entityManager.createQuery("SELECT L FROM Libro L", Libro.class);
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

	public Libro consultaLibroPorId(int id) throws DataBaseException
	{
		List<Libro> ListaDeLibros = null;
		EntityManager entityManager = JPAHelper.getEntityManager();
		try{
			TypedQuery<Libro> consulta = entityManager.createQuery("SELECT L FROM Libro L WHERE num_lib=" + id + "", Libro.class);
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
		return ListaDeLibros.get(0);
	}
	
	public int actualizarLibro(Libro lib) throws DataBaseException
	{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.merge(lib);
			entityTransaction.commit();
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
		return 0;
	}
	

	public void BorrarLibro(Libro libro) throws DataBaseException
	{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.remove(entityManager.merge(libro));      
			entityTransaction.commit();
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
	}
	
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
