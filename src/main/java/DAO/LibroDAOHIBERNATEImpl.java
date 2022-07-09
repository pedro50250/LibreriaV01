package DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import JavaEEJDBC.DataBaseException;
import beans.Libro;

public class LibroDAOHIBERNATEImpl extends GenericDAOHIBERNATEImpl<Libro, Integer> implements LibroDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Libro> buscarPorCategoria(int Cat) throws DataBaseException {
		String SQL = "from Libro WHERE cat_lib ="+ Cat;
		List<Libro> ListaDeLibros = null;
		Session session = null;
		try {
			session = GenericDAOHIBERNATEImpl.getSessionFactory().openSession();
			Query<Libro> consulta = session.createQuery(SQL);
			ListaDeLibros = consulta.list();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return ListaDeLibros;
	}

}
