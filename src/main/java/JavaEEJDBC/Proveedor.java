package JavaEEJDBC;

import java.sql.Timestamp;
import java.util.List;

public class Proveedor {

	int id_proveedor;
	String nombre_proveedor;
	Timestamp fecha_alta;
	String rfc_proveedor;
	String telefono_proveedor;
	
	public Proveedor() {
		
	}

	public Proveedor(int id_proveedor, String nombre_proveedor, Timestamp fecha_alta, String rfc_proveedor,
			String telefono_proveedor) {
		this.id_proveedor = id_proveedor;
		this.nombre_proveedor = nombre_proveedor;
		this.fecha_alta = fecha_alta;
		this.rfc_proveedor = rfc_proveedor;
		this.telefono_proveedor = telefono_proveedor;
	}

	public int getid_proveedor() {
		return id_proveedor;
	}

	public void setid_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public String getnombre_proveedor() {
		return nombre_proveedor;
	}

	public void setnombre_proveedor(String nombre_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
	}

	public Timestamp getfecha_alta() {
		return fecha_alta;
	}

	public void setfecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public String getrfc_proveedor() {
		return rfc_proveedor;
	}

	public void setrfc_proveedor(String rfc_proveedor) {
		this.rfc_proveedor = rfc_proveedor;
	}

	public String gettelefono_proveedor() {
		return telefono_proveedor;
	}

	public void settelefono_proveedor(String telefono_proveedor) {
		this.telefono_proveedor = telefono_proveedor;
	}
	
	@SuppressWarnings("rawtypes")
	public int insertar() throws DataBaseException
	{
		String consultaSQL = "INSERT INTO proveedor(nombre_proveedor,fecha_alta,rfc_proveedor,telefono_proveedor) VALUES";
		consultaSQL += "('" + nombre_proveedor + "','" + fecha_alta + "','" + rfc_proveedor + "','" + telefono_proveedor + "')";
		DataBaseHelper dbh = new DataBaseHelper();
		int filas = dbh.modificarRegistro(consultaSQL);
		dbh.cerrarObjetos();
		return filas;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Proveedor> consultarProveedores() throws DataBaseException{
		String SQL = "SELECT * FROM proveedor";
		DataBaseHelper dbh = new DataBaseHelper();
		List<Proveedor> ListaDeProveedores = dbh.seleccionarRegistros(SQL, Proveedor.class);
		dbh.cerrarObjetos();
		return ListaDeProveedores;
	}
	
	@SuppressWarnings("rawtypes")
	public void BorrarProveedor(int id) throws DataBaseException
	{
		String consultaSQL = "DELETE FROM proveedor WHERE id_proveedor =" +id + ";";
		DataBaseHelper dbh = new DataBaseHelper();
		int filas = dbh.modificarRegistro(consultaSQL);
		System.out.println(filas);
	}
	
	@SuppressWarnings("rawtypes")
	public int actualizarProveedor(int id) throws DataBaseException
	{
		this.id_proveedor = id;
		DataBaseHelper dbh = new DataBaseHelper();
		int filas = dbh.actualizarRegistroProveedor(this);
		dbh.cerrarObjetos();
		return filas;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Proveedor buscarPorId(int id) throws DataBaseException
	{
		String SQL = "SELECT * FROM proveedor WHERE id_proveedor ="+ id;
		DataBaseHelper dbh = new DataBaseHelper();
		List<Proveedor> ListaDeProveedores = dbh.seleccionarRegistros(SQL, Proveedor.class);
		dbh.cerrarObjetos();
		return ListaDeProveedores.get(0);
	}

}
