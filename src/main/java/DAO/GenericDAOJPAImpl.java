package DAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import JavaEEJDBC.JPAHelper;

public abstract class GenericDAOJPAImpl<T, Id extends Serializable> implements GenericDAO<T, Id> {


	private Class<T> claseDePersistencia;

	@SuppressWarnings("unchecked")
	public GenericDAOJPAImpl()
	{
		this.claseDePersistencia = (Class <T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public T buscarPorClave(Id id)
	{
		EntityManager entityManager = JPAHelper.getEntityManager();
		T objeto = null;
		try {
			objeto = (T) entityManager.find(claseDePersistencia, id);
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}finally {
		
			entityManager.close();
		}
		return objeto;
	}
	
	public List<T> buscarTodos(){
		EntityManager entityManager = JPAHelper.getEntityManager();
		List<T> listaDeObjetos = null;
		try {
			TypedQuery<T> consulta = entityManager.createQuery("Select o FROM "+ claseDePersistencia.getSimpleName() + " o", claseDePersistencia);
			listaDeObjetos = consulta.getResultList();
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
		}finally {
		
			entityManager.close();
		}
		return listaDeObjetos;
	}
	
	public void borrar(T objeto)
	{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.remove(entityManager.merge(objeto));      
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
	
	public void insertar(T objeto)
	{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.persist(objeto);
			entityTransaction.commit();
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
			entityTransaction.rollback();
		}
		finally
		{
			if(entityManager != null)
			{
				entityManager.close();
			}
		}
	}
	
	public void guardarCambios(T objeto)
	{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try{
			entityTransaction.begin();
			entityManager.merge(objeto);
			entityTransaction.commit();
		}
		catch(PersistenceException e)
		{
			e.printStackTrace();
			entityTransaction.rollback();
		}
		finally
		{
			if(entityManager != null)
			{
				entityManager.close();
			}
		}
	}
}
