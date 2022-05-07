package PaquetePruebas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import beans.Libro;

public class PruebaJPA {

	public static void main(String [] args)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cursodia");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Libro> consulta = em.createQuery("SELECT L FROM Libro L", Libro.class);
		List <Libro> libros = consulta.getResultList();
		for(Libro l : libros)
		{
			System.out.println(l.gettit_lib());
		}
	}

}
