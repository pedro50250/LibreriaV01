package DAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;




public abstract class GenericDAOHIBERNATEImpl<T, Id extends Serializable> implements GenericDAO<T, Id> {

	private Class<T> claseDePersistencia;
	private static final SessionFactory sessionFactory = buildSessionFactory();

	@SuppressWarnings("unchecked")
	public GenericDAOHIBERNATEImpl() {
		this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Error al crear la session: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public T buscarPorClave(Id id) {

		Session session = null;
		T objeto = null;
		try {
			SessionFactory factoria = GenericDAOHIBERNATEImpl.getSessionFactory();
			session = factoria.openSession();
			objeto = session.find(claseDePersistencia, id);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return objeto;
	}

	public List<T> buscarTodos() {
		Session session = null;
		List<T> listaDeObjetos = new ArrayList<T>();
		try {
			SessionFactory factoria = GenericDAOHIBERNATEImpl.getSessionFactory();
			session = factoria.openSession();
			Query<T> consulta = session.createQuery("Select o FROM " + claseDePersistencia.getSimpleName() + " o",
					claseDePersistencia);
			listaDeObjetos = consulta.list();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return listaDeObjetos;
	}

	public void borrar(T objeto) {
		Session session = null;
		Transaction transaction = null;
		try {
			SessionFactory factoria = GenericDAOHIBERNATEImpl.getSessionFactory();
			session = factoria.openSession();
			transaction = session.beginTransaction();
			session.delete(objeto);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			try {
				transaction.rollback();
			} catch (IllegalStateException e1) {
				System.out.println("No se pudo realizar el rollback");
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			if (transaction != null)
				session.close();
		}
	}
	
	public void insertar(T objeto) {
		Session session = null;
		Transaction transaction = null;
		try {
			SessionFactory factoria = GenericDAOHIBERNATEImpl.getSessionFactory();
			session = factoria.openSession();
			transaction = session.beginTransaction();
			session.save(objeto);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			try {
				transaction.rollback();
			} catch (IllegalStateException e1) {
				System.out.println("No se pudo realizar el rollback");
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			if (transaction != null)
				session.close();
		}
	}
	
	public void guardarCambios(T objeto) {
		Session session = null;
		Transaction transaction = null;
		try {
			SessionFactory factoria = GenericDAOHIBERNATEImpl.getSessionFactory();
			session = factoria.openSession();
			transaction = session.beginTransaction();
			session.update(objeto);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			try {
				transaction.rollback();
			} catch (IllegalStateException e1) {
				System.out.println("No se pudo realizar el rollback");
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			if (transaction != null)
				session.close();
		}
	}
}
