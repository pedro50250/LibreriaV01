package PaquetePruebas;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.Libro;

public class CRUDHIbernate <T>{

	public CRUDHIbernate() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		CRUDHIbernate hib = new CRUDHIbernate();
		try {
			hib.leerRegistros("from Libro libros");
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void leerLibros()
	{
		Session session = null;
		try {
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			Query consulta = session.createQuery("from Libro libros");
			List<Libro> listaLibros = consulta.list();
			for(Libro L:listaLibros)
			{
				System.out.println(L.getnum_lib()+"\t\t"+L.getisbn_lib()+"\t\t"+L.gettit_lib()+"\t\t"+L.getcat_lib()+"\t\t"+L.getpre_lib());
			}
			
		}
		catch(HibernateException e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			if(session!=null) session.close();
		}
	}
	
	public void altaLibro()
	{
		Session session = null;
		Transaction transaction = null;
		try {
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			transaction = session.beginTransaction();
			Libro lib = new Libro("478","Hibernate",2,34.5f);
			session.save(lib);
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
	
	public List<T> leerRegistros( String query) throws DataBaseException
	{
		Session session = null;
		List<T> listaDeObjetos = new ArrayList<T>();
		try {
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
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

}
