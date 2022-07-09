package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Proveedor;
import servicios.ServicioProveedor;

public class BorrarProveedorAction extends Action {

	public BorrarProveedorAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioProveedor servicioProveedor = (ServicioProveedor) getBean("ServicioProveedores", request);
		int id = Integer.parseInt(request.getParameter("id"));
		Proveedor prov;
		try {
			prov = servicioProveedor.buscarPorClave(id);
			servicioProveedor.borrar(prov);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarProveedor.do";
	}

}
