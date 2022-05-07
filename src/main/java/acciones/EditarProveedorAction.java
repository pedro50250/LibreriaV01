package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProveedorDAO;
import JavaEEJDBC.DataBaseException;
import beans.Proveedor;

public class EditarProveedorAction extends Action{

	public EditarProveedorAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		
		String Nombre = request.getParameter("Nombre");
		int id =  Integer.parseInt(request.getParameter("Id"));
		String RFC = request.getParameter("RFC");
		String Telefono = request.getParameter("Telefono");
		//String fecha = request.getParameter("Fecha");
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		//Date date;
		ProveedorDAO provDao = new ProveedorDAO();
		
		try {
			Proveedor prov = provDao.buscarPorId(id);
			prov.setid_proveedor(id);
			//prov.setfecha_alta(fec);
			prov.setnombre_proveedor(Nombre);
			prov.setrfc_proveedor(RFC);
			prov.settelefono_proveedor(Telefono);
			provDao.actualizarProveedor(prov);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MostrarProveedor.do";
	}

}


