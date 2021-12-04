package JavaEEJDBC;

import java.util.List;

public class Categoria {

	private int id_cat;
	private String nom_cat;
	
	public Categoria() {
		
	}

	public Categoria(int id_cat, String nom_cat) {
		this.id_cat = id_cat;
		this.nom_cat = nom_cat;
	}

	public void setid_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public int getid_cat() {
		return id_cat;
	}

	public String getnom_cat() {
		return nom_cat;
	}

	public void setnom_cat(String nom_cat) {
		this.nom_cat = nom_cat;
	}
	
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Integer> buscarLasCategorias() {
		String consultaSQL = "SELECT DISTINCT(cat_lib) FROM libros";
		DataBaseHelper dbh = new DataBaseHelper();
		List<Integer> listaCategorias= dbh.seleccionarRegistros(consultaSQL, Categoria.class);
		dbh.cerrarObjetos();
		return listaCategorias;
	}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Categoria> getCategorias() throws DataBaseException
	{
		String consultaSQL = "SELECT * FROM categoria";
		DataBaseHelper dbh = new DataBaseHelper();
		List<Categoria> listaCategorias =  dbh.seleccionarRegistros(consultaSQL, Categoria.class);
		dbh.cerrarObjetos();
		return listaCategorias;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getNombreCategoriaById(int idCat) throws DataBaseException
	{
		String consultaSQL = "SELECT * FROM categoria WHERE id_cat="+idCat;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Categoria> listaCategorias = dbh.seleccionarRegistros(consultaSQL, Categoria.class);
		dbh.cerrarObjetos();
		return listaCategorias.get(0).getnom_cat();
	}
	
	
}
