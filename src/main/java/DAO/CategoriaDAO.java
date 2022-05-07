package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.JPAHelper;
import beans.Categoria;

public class CategoriaDAO {
	
	public  List<Categoria> getCategorias() throws DataBaseException
	{
		List<Categoria> listaCategorias = null;
		EntityManager entityManager = JPAHelper.getEntityManager();
		try{
			TypedQuery<Categoria> consulta = entityManager.createQuery("SELECT C FROM Categoria C", Categoria.class);
			listaCategorias = consulta.getResultList();
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
		return listaCategorias;
	}

	public String getNombreCategoriaById(int idCat) throws DataBaseException
	{
		String consultaSQL = "from Categoria where id_cat="+idCat;
		List<Categoria> listaCategorias = null;
		EntityManager entityManager = JPAHelper.getEntityManager();
		try{
			TypedQuery<Categoria> consulta = entityManager.createQuery(consultaSQL, Categoria.class);
			listaCategorias = consulta.getResultList();
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
		return listaCategorias.get(0).getnom_cat();
	}

}
