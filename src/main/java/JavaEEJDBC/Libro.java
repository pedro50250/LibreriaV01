package JavaEEJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Libro {

	private int NumLib;
	private String ISBN;
	private String Titulo;
	private int Categoria;
	private float Precio;

	public Libro() {

	}

	public Libro(String iSBN, String titulo, int categoria, float precio) {
		ISBN = iSBN;
		Titulo = titulo;
		Categoria = categoria;
		Precio = precio;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public int getCategoria() {
		return Categoria;
	}

	public void setCategoria(int categoria) {
		Categoria = categoria;
	}

	public float getPrecio() {
		return Precio;
	}

	public void setPrecio(float precio) {
		Precio = precio;
	}

	public int getNumLib() {
		return NumLib;
	}
	
	/*public ArrayList<Integer> buscarLasCategorias() {
		String consultaSQL = "SELECT DISTINCT(cat_lib) FROM libros";
		DataBaseHelper dbh = new DataBaseHelper();
		ResultSet rs = dbh.seleccionarRegistros(consultaSQL);
		ArrayList<Integer> listaCategorias = new ArrayList<Integer>();
		try {
			while(rs.next())
			{
				listaCategorias.add(rs.getInt("cat_lib"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategorias;
	}*/

	public int insertar() {
		String consultaSQL = "INSERT INTO libros(isbn_lib, tit_lib, cat_lib, pre_lib) VALUES";
		consultaSQL += "('" + ISBN + "','" + Titulo + "'," + Categoria + "," + Precio + ")";
		DataBaseHelper dbh = new DataBaseHelper();
		int filas = dbh.modificarRegistro(consultaSQL);
		dbh.cerrarObjetos();
		return filas;
	}

	public ArrayList<Libro> consultarLibros() {
		String SQL = "SELECT * FROM libros";
		DataBaseHelper dbh = new DataBaseHelper();
		ResultSet rs = dbh.seleccionarRegistros(SQL);
		ArrayList<Libro> ListaDeLibros = new ArrayList<Libro>();
		try {
			while(rs.next())
			{
				Libro lib = new Libro(rs.getString("isbn_lib"), rs.getString("tit_lib"), rs.getInt("cat_lib"),
						rs.getFloat("pre_lib"));
				lib.NumLib = rs.getInt("num_lib");
				ListaDeLibros.add(lib);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbh.cerrarObjetos();
		return ListaDeLibros;
	}
	
	public Libro consultaLibroPorId(int id)
	{
		String SQL = "SELECT * FROM libros WHERE num_lib=" + id + "";
		DataBaseHelper dbh = new DataBaseHelper();
		ResultSet rs = dbh.seleccionarRegistros(SQL);
		Libro lib = null;
		try {
			while(rs.next())
			{
				lib = new Libro(rs.getString("isbn_lib"), rs.getString("tit_lib"), rs.getInt("cat_lib"),
						rs.getFloat("pre_lib"));
				lib.NumLib = rs.getInt("num_lib");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbh.cerrarObjetos();
		return lib;
	}
	
	public int actualizarLibro(int id)
	{
		this.NumLib = id;
		DataBaseHelper dbh = new DataBaseHelper();
		int filas = dbh.actualizarRegistro(this);
		dbh.cerrarObjetos();
		return filas;
	}
	
	public void BorrarLibro(int id)
	{
		String consultaSQL = "DELETE FROM libros WHERE num_lib =" +id + ";";
		DataBaseHelper dbh = new DataBaseHelper();
		int filas = dbh.modificarRegistro(consultaSQL);
		System.out.println(filas);
	}
	
	public ArrayList<Libro> buscarPorCategoria(int Cat)
	{
		String SQL = "SELECT * FROM libros WHERE cat_lib ="+ Cat;
		DataBaseHelper dbh = new DataBaseHelper();
		ResultSet rs = dbh.seleccionarRegistros(SQL);
		ArrayList<Libro> ListaDeLibros = new ArrayList<Libro>();
		try {
			while(rs.next())
			{
				Libro lib = new Libro(rs.getString("isbn_lib"), rs.getString("tit_lib"), rs.getInt("cat_lib"),
						rs.getFloat("pre_lib"));
				lib.NumLib = rs.getInt("num_lib");
				ListaDeLibros.add(lib);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbh.cerrarObjetos();
		return ListaDeLibros;
	}
	
}
