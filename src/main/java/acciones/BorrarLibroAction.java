package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Libro;

public class BorrarLibroAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			new Libro().BorrarLibro(id);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarLibro.do";
	}

}
