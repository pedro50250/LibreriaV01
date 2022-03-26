package acciones;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.Proveedor;

public class InsertarProveedorAction extends Action{

	public InsertarProveedorAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		String Nombre = request.getParameter("Nombre");
		String RFC = request.getParameter("RFC");
		String Telefono = request.getParameter("Telefono");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Proveedor prov = new Proveedor(Nombre,RFC,Telefono);
		prov.setfecha_alta(timestamp);
		try {
			prov.insertar();
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarProveedor.do";
	}

}
