package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.Proveedor;

public class MostrarProveedorAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Proveedor> ListaProveedor = Proveedor.consultarProveedores();
			request.setAttribute("listaProveedor", ListaProveedor);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarProveedor.jsp";
	}

}
