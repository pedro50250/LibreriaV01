package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.JPAHelper;
import beans.Proveedor;

public class ProveedorDAO {

	public int insertar(Proveedor proveedor) throws DataBaseException
	{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.persist(proveedor);
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

	public  List<Proveedor> consultarProveedores() throws DataBaseException{
		List<Proveedor> ListaDeProveedores = null;
		EntityManager entityManager = JPAHelper.getEntityManager();
		try{
			TypedQuery<Proveedor> consulta = entityManager.createQuery("SELECT P FROM Proveedor P", Proveedor.class);
			ListaDeProveedores = consulta.getResultList();
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
		return ListaDeProveedores;
	}
	

	public void BorrarProveedor(Proveedor prov) throws DataBaseException
	{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.remove(entityManager.merge(prov));      
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
	

	public int actualizarProveedor(Proveedor prov) throws DataBaseException
	{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.merge(prov);
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
	
	public Proveedor buscarPorId(int id) throws DataBaseException
	{
		String SQL = "from Proveedor WHERE id_proveedor ="+ id;
		List<Proveedor> ListaDeProveedores = null;
		EntityManager entityManager = JPAHelper.getEntityManager();
		try{
			TypedQuery<Proveedor> consulta = entityManager.createQuery(SQL, Proveedor.class);
			ListaDeProveedores = consulta.getResultList();
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
		return ListaDeProveedores.get(0);
	}
}
