package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LibroDAO;
import JavaEEJDBC.DataBaseException;
import beans.Libro;

public class BorrarLibroAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		LibroDAO libDao = new LibroDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Libro lib = libDao.consultaLibroPorId(id);
			libDao.BorrarLibro(lib);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarLibro.do";
	}

}
