package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProveedorDAO;
import JavaEEJDBC.DataBaseException;
import beans.Proveedor;

public class BorrarProveedorAction extends Action {

	public BorrarProveedorAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ProveedorDAO provDao = new ProveedorDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Proveedor prov = provDao.buscarPorId(id);
			provDao.BorrarProveedor(prov);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarProveedor.do";
	}

}
