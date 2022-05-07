package beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceException;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.JPAHelper;

@Entity
@Table(name="libros")
public class Libro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num_lib;
	private String isbn_lib;
	private String tit_lib;
	private int cat_lib;
	@ManyToOne
	@JoinColumn(name = "cat_lib", referencedColumnName ="id_cat", insertable=false, updatable=false, nullable=false )
	private Categoria categoria;
	private float pre_lib;

	public Libro() {

	}

	public Libro(String isbn_lib_lib, String tit_lib, int cat_lib, float pre_lib) {
		this.isbn_lib = isbn_lib_lib;
		this.tit_lib = tit_lib;
		this.cat_lib = cat_lib;
		this.pre_lib = pre_lib;
	}

	public String getisbn_lib() {
		return this.isbn_lib;
	}

	public void setisbn_lib(String isbn_lib) {
		this.isbn_lib = isbn_lib;
	}

	public String gettit_lib() {
		return this.tit_lib;
	}

	public void settit_lib(String tit_lib) {
		this.tit_lib = tit_lib;
	}

	public int getcat_lib() {
		return this.cat_lib;
	}

	public void setcat_lib(int cat_lib) {
		this.cat_lib = cat_lib;
	}

	public float getpre_lib() {
		return this.pre_lib;
	}

	public void setpre_lib(float pre_lib) {
		this.pre_lib = pre_lib;
	}

	public int getnum_lib() {
		return num_lib;
	}
	
	public void setnum_lib(int num_lib) {
		this.num_lib = num_lib;
	}
	
	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int insertar() throws DataBaseException{
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.persist(this);
	        entityManager.getTransaction().commit();
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
	public static List<Libro> consultarLibros() throws DataBaseException{
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
	
	public int actualizarLibro(int id) throws DataBaseException
	{
		this.num_lib = id;
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.merge(this);
	        entityManager.getTransaction().commit();
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
	

	public void BorrarLibro(int id) throws DataBaseException
	{
		this.num_lib = id;
		EntityManager entityManager = JPAHelper.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
        
		try{
			entityTransaction.begin();
			entityManager.remove(entityManager.merge(this));      
			entityManager.getTransaction().commit();
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
	
	public static List<Libro> buscarPorCategoria(int Cat) throws DataBaseException
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
