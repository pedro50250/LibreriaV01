package JavaEEJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categoria {

	private int idCategoria;
	private String nomCategoria;
	
	public Categoria() {
		
	}

	public Categoria(int idCategoria, String nomCategoria) {
		this.idCategoria = idCategoria;
		this.nomCategoria = nomCategoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public String getNomCategoria() {
		return nomCategoria;
	}

	public void setNomCategoria(String nomCategoria) {
		this.nomCategoria = nomCategoria;
	}
	
	public ArrayList<Integer> buscarLasCategorias() {
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
	}
	
	public ArrayList<Categoria> getCategorias()
	{
		String consultaSQL = "SELECT * FROM categoria";
		DataBaseHelper dbh = new DataBaseHelper();
		ResultSet rs = dbh.seleccionarRegistros(consultaSQL);
		ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
		try {
			while(rs.next())
			{
				listaCategorias.add(new Categoria(rs.getInt("id_cat"), rs.getString("nom_cat")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategorias;
	}
	
	public String getNombreCategoriaById(int idCat)
	{
		String consultaSQL = "SELECT nom_cat FROM categoria WHERE id_cat="+idCat;
		DataBaseHelper dbh = new DataBaseHelper();
		ResultSet rs = dbh.seleccionarRegistros(consultaSQL);
		String nombreCategoria ="";
		try {
			while(rs.next())
			{
				nombreCategoria = rs.getString("nom_cat");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombreCategoria;
	}
	
	
}
