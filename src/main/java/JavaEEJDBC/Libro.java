package JavaEEJDBC;

import java.util.List;

public class Libro {

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
	
	
	
	/*public ArrayList<Integer> buscarLascat_libs() {
		String consultaSQL = "SELECT DISTINCT(cat_lib) FROM libros";
		DataBaseHelper dbh = new DataBaseHelper();
		ResultSet rs = dbh.seleccionarRegistros(consultaSQL);
		ArrayList<Integer> listacat_libs = new ArrayList<Integer>();
		try {
			while(rs.next())
			{
				listacat_libs.add(rs.getInt("cat_lib"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listacat_libs;
	}*/

	@SuppressWarnings("rawtypes")
	public int insertar() {
		String consultaSQL = "INSERT INTO libros(isbn_lib, tit_lib, cat_lib, pre_lib) VALUES";
		consultaSQL += "('" + isbn_lib + "','" + tit_lib + "'," + cat_lib + "," + pre_lib + ")";
		DataBaseHelper dbh = new DataBaseHelper();
		int filas = dbh.modificarRegistro(consultaSQL);
		dbh.cerrarObjetos();
		return filas;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Libro> consultarLibros() {
		String SQL = "SELECT * FROM libros";
		DataBaseHelper dbh = new DataBaseHelper();
		List<Libro> ListaDeLibros = dbh.seleccionarRegistros(SQL, Libro.class);
		dbh.cerrarObjetos();
		return ListaDeLibros;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Libro consultaLibroPorId(int id)
	{
		String SQL = "SELECT * FROM libros WHERE num_lib=" + id + "";
		DataBaseHelper dbh = new DataBaseHelper();
		List<Libro> ListaDeLibros= dbh.seleccionarRegistros(SQL, Libro.class);
		dbh.cerrarObjetos();
		return ListaDeLibros.get(0);
	}
	
	@SuppressWarnings("rawtypes")
	public int actualizarLibro(int id)
	{
		this.num_lib = id;
		DataBaseHelper dbh = new DataBaseHelper();
		int filas = dbh.actualizarRegistro(this);
		dbh.cerrarObjetos();
		return filas;
	}
	
	@SuppressWarnings("rawtypes")
	public void BorrarLibro(int id)
	{
		String consultaSQL = "DELETE FROM libros WHERE num_lib =" +id + ";";
		DataBaseHelper dbh = new DataBaseHelper();
		int filas = dbh.modificarRegistro(consultaSQL);
		System.out.println(filas);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Libro> buscarPorCategoria(int Cat)
	{
		String SQL = "SELECT * FROM libros WHERE cat_lib ="+ Cat;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Libro> ListaDeLibros = dbh.seleccionarRegistros(SQL, Libro.class);
		dbh.cerrarObjetos();
		return ListaDeLibros;
	}
	
}
