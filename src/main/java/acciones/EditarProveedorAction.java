package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Proveedor;
import servicios.ServicioProveedor;
public class EditarProveedorAction extends Action{

	public EditarProveedorAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioProveedor servicioProveedor = (ServicioProveedor) getBean("ServicioProveedores", request);
		String Nombre = request.getParameter("Nombre");
		int id =  Integer.parseInt(request.getParameter("Id"));
		String RFC = request.getParameter("RFC");
		String Telefono = request.getParameter("Telefono");


		
		Proveedor prov;
		try {
			prov = servicioProveedor.buscarPorClave(id);
			prov.setid_proveedor(id);
			//prov.setfecha_alta(fec);
			prov.setnombre_proveedor(Nombre);
			prov.setrfc_proveedor(RFC);
			prov.settelefono_proveedor(Telefono);
			servicioProveedor.guardarCambios(prov);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MostrarProveedor.do";
	}

}


