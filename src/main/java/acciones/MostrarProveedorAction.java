package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Proveedor;
import servicios.ServicioProveedor;
import servicios.ServicioProveedorImpl;

public class MostrarProveedorAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioProveedor servicioProveedor = (ServicioProveedor) getBean("ServicioProveedores", request);
		List<Proveedor> ListaProveedor = null;
		try {
			ListaProveedor = servicioProveedor.buscarTodos();
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		request.setAttribute("listaProveedor", ListaProveedor);
		return "MostrarProveedor.jsp";
	}

}
