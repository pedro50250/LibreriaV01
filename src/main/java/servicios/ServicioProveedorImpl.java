package servicios;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.ProveedorDAO;
import JavaEEJDBC.DataBaseException;
import beans.Proveedor;

public class ServicioProveedorImpl implements ServicioProveedor{

	private ProveedorDAO proveedorDAO;
	
	public ServicioProveedorImpl()
	{
		ClassPathXmlApplicationContext factoria = new ClassPathXmlApplicationContext("classpath*:/contextoAplicacion.xml");
		proveedorDAO = (ProveedorDAO) factoria.getBean("ProveedorJPA");
	}
	
	@Override
	public void insertar(Proveedor proveedor) throws DataBaseException {
		this.proveedorDAO.insertar(proveedor);
		
	}

	@Override
	public List<Proveedor> buscarTodos() throws DataBaseException {
		return this.proveedorDAO.buscarTodos();
	}

	@Override
	public void borrar(Proveedor prov) throws DataBaseException {
		this.proveedorDAO.borrar(prov);
	}

	@Override
	public void guardarCambios(Proveedor prov) throws DataBaseException {
		this.proveedorDAO.guardarCambios(prov);
	}

	@Override
	public Proveedor buscarPorClave(Integer id) throws DataBaseException {
		return this.proveedorDAO.buscarPorClave(id);
	}

	@Override
	public ProveedorDAO getProveedorDAO() {
		return this.proveedorDAO;
	}

	@Override
	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
		
	}

}
