package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import JavaEEJDBC.DataBaseException;
import beans.Categoria;

public class CategoriaDAOHIBERNATEImpl extends GenericDAOHIBERNATEImpl<Categoria, Integer> implements CategoriaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public String getNombreCategoriaById(int idCat) throws DataBaseException {
		Session session = null;
		String consultaSQL = "from Categoria where id_cat="+idCat;
		List<Categoria> listaDeObjetos = new ArrayList<Categoria>();
		try {
			session = GenericDAOHIBERNATEImpl.getSessionFactory().openSession();
			Query<Categoria> consulta = session.createQuery(consultaSQL);
			listaDeObjetos = consulta.list();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return listaDeObjetos.get(0).getnom_cat();
	}

}
