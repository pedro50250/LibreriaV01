package servicios;

import java.util.List;

import DAO.ProveedorDAO;
import JavaEEJDBC.DataBaseException;
import beans.Proveedor;

public interface ServicioProveedor {

	public void insertar(Proveedor proveedor) throws DataBaseException;

	public  List<Proveedor> buscarTodos() throws DataBaseException;
	
	public void borrar(Proveedor prov) throws DataBaseException;
	
	public void guardarCambios(Proveedor prov) throws DataBaseException;
	
	public Proveedor buscarPorClave(Integer id) throws DataBaseException;
	
	public ProveedorDAO getProveedorDAO();
	public void setProveedorDAO(ProveedorDAO proveedorDAO);
}
