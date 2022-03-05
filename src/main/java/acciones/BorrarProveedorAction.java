package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.Proveedor;

public class BorrarProveedorAction extends Action {

	public BorrarProveedorAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			new Proveedor().BorrarProveedor(id);;
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarProveedor.do";
	}

}
