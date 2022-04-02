package JavaEEJDBC;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
		String consultaSQL = "from Categoria categoria";
		HibernateHelper hh = new HibernateHelper();
		//List<Categoria> listaCategorias =  dbh.seleccionarRegistros(consultaSQL, Categoria.class);
		//dbh.cerrarObjetos();
		List<Categoria> listaCategorias = hh.leerRegistros(consultaSQL);
		return listaCategorias;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getNombreCategoriaById(int idCat) throws DataBaseException
	{
		String consultaSQL = "from Categoria where id_cat="+idCat;
		HibernateHelper hh = new HibernateHelper();
		//List<Categoria> listaCategorias = dbh.seleccionarRegistros(consultaSQL, Categoria.class);
		//dbh.cerrarObjetos();
		List<Categoria> listaCategorias = hh.leerRegistros(consultaSQL);
		return listaCategorias.get(0).getnom_cat();
	}
	
	
}
