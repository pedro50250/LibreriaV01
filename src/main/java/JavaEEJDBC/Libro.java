package JavaEEJDBC;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="libros")
public class Libro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num_lib;
	private String isbn_lib;
	private String tit_lib;
	private int cat_lib;
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
	

	@SuppressWarnings("rawtypes")
	public int insertar() throws DataBaseException{
		HibernateHelper hh = new HibernateHelper();
		hh.modificarRegistro(this,"insertar");
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Libro> consultarLibros() throws DataBaseException{
		String SQL = "from Libro libros";
		HibernateHelper hh = new HibernateHelper();
		List <Libro> ListaDeLibros = hh.leerRegistros(SQL);
		return ListaDeLibros;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Libro consultaLibroPorId(int id) throws DataBaseException
	{
		String SQL = "from Libro WHERE num_lib=" + id + "";
		HibernateHelper hh = new HibernateHelper();
		List<Libro> ListaDeLibros= hh.leerRegistros(SQL);
		return ListaDeLibros.get(0);
	}
	
	@SuppressWarnings("rawtypes")
	public int actualizarLibro(int id) throws DataBaseException
	{
		this.num_lib = id;
		HibernateHelper hh = new HibernateHelper();
		hh.modificarRegistro(this,"actualizar");
		return 0;
	}
	
	@SuppressWarnings("rawtypes")
	public void BorrarLibro(int id) throws DataBaseException
	{
		this.num_lib = id;
		HibernateHelper hh = new HibernateHelper();
		hh.modificarRegistro(this, "eliminar");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Libro> buscarPorCategoria(int Cat) throws DataBaseException
	{
		String SQL = "from Libro WHERE cat_lib ="+ Cat;
		HibernateHelper hh = new HibernateHelper();
		List<Libro> ListaDeLibros = hh.leerRegistros(SQL);
		return ListaDeLibros;
	}
	
}
