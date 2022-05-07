package beans;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.HibernateHelper;

@Entity
@Table(name="proveedor")
public class Proveedor {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_proveedor;
	String nombre_proveedor;
	Timestamp fecha_alta;
	String rfc_proveedor;
	String telefono_proveedor;
	
	public Proveedor() {
		
	}

	public Proveedor(String nombre_proveedor, String rfc_proveedor,
			String telefono_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
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
		//String consultaSQL = "INSERT INTO proveedor(nombre_proveedor,fecha_alta,rfc_proveedor,telefono_proveedor) VALUES";
		//consultaSQL += "('" + nombre_proveedor + "','" + fecha_alta + "','" + rfc_proveedor + "','" + telefono_proveedor + "')";
		HibernateHelper hh = new HibernateHelper();
		//int filas = dbh.modificarRegistro(consultaSQL);
		//dbh.cerrarObjetos();
		hh.modificarRegistro(this, "insertar");
		return 0;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Proveedor> consultarProveedores() throws DataBaseException{
		String SQL = "from Proveedor proveedor";
		HibernateHelper hh = new HibernateHelper();
		//List<Proveedor> ListaDeProveedores = dbh.seleccionarRegistros(SQL, Proveedor.class);
		//dbh.cerrarObjetos();
		List<Proveedor> ListaDeProveedores = hh.leerRegistros(SQL);
		return ListaDeProveedores;
	}
	
	@SuppressWarnings("rawtypes")
	public void BorrarProveedor(int id) throws DataBaseException
	{
		//String consultaSQL = "DELETE FROM proveedor WHERE id_proveedor =" +id + ";";
		this.setid_proveedor(id);
		HibernateHelper hh = new HibernateHelper();
		//int filas = dbh.modificarRegistro(consultaSQL);
		//System.out.println(filas);
		hh.modificarRegistro(this, "eliminar");
	}
	
	@SuppressWarnings("rawtypes")
	public int actualizarProveedor(int id) throws DataBaseException
	{
		this.id_proveedor = id;
		HibernateHelper hh = new HibernateHelper();
		//int filas = dbh.actualizarRegistroProveedor(this);
		//dbh.cerrarObjetos();
		hh.modificarRegistro(this, "actualizar");
		return 0;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Proveedor buscarPorId(int id) throws DataBaseException
	{
		String SQL = "from Proveedor WHERE id_proveedor ="+ id;
		HibernateHelper hh = new HibernateHelper();
		//List<Proveedor> ListaDeProveedores = dbh.seleccionarRegistros(SQL, Proveedor.class);
		//dbh.cerrarObjetos();
		List<Proveedor> ListaDeProveedores = hh.leerRegistros(SQL);
		return ListaDeProveedores.get(0);
	}

}
