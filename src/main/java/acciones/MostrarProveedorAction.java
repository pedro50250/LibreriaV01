package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProveedorDAO;
import JavaEEJDBC.DataBaseException;
import beans.Proveedor;

public class MostrarProveedorAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ProveedorDAO provDao = new ProveedorDAO();
		try {
			List<Proveedor> ListaProveedor = provDao.consultarProveedores();
			request.setAttribute("listaProveedor", ListaProveedor);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarProveedor.jsp";
	}

}
