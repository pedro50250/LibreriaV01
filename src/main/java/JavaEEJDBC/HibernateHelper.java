package JavaEEJDBC;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateHelper <T> {

	private Logger log =  LogManager.getLogger("HibernateHelper");
	private static final SessionFactory sessionFactory =  buildSessionFactory();

	public HibernateHelper() {
		log.setLevel(Level.DEBUG);
	}
	
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.err.println("Error al crear la session: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> leerRegistros( String query) 
	{
		Session session = null;
		List<T> listaDeObjetos = new ArrayList<T>();
		try {
			SessionFactory factoria = HibernateHelper.getSessionFactory();
			session = factoria.openSession();
			Query consulta = session.createQuery(query);
			listaDeObjetos = consulta.list();
		}
		catch(HibernateException e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			if(session!=null) session.close();
		}
		return listaDeObjetos;
	}
	
	public void modificarRegistro(Object objeto, String accion)
	{
		Session session = null;
		Transaction transaction = null;
		try {
			SessionFactory factoria = HibernateHelper.getSessionFactory();
			session = factoria.openSession();
			transaction = session.beginTransaction();
			if(accion=="insertar")
			{
				session.save(objeto);
			}
			else if(accion=="actualizar")
			{
				session.update(objeto);
			}
			else if(accion=="eliminar")
			{
				session.delete(objeto);
			}
			transaction.commit();
		}
		catch(HibernateException e)
		{
			System.out.println(e.getMessage());
			try {
				transaction.rollback();
			}catch(IllegalStateException e1)
			{
				System.out.println("No se pudo realizar el rollback");
			}
		}
		catch(SecurityException e)
		{
			e.printStackTrace();
		}
		finally {
			if(session!=null) session.close();
			if(transaction!=null) session.close();
		}
	}
}
