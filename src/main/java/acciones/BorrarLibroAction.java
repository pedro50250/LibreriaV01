package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Libro;
import servicios.ServicioLibros;


public class BorrarLibroAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioLibros servicioLibros = (ServicioLibros) getBean("ServicioLibros", request);
		int id = Integer.parseInt(request.getParameter("id"));
		Libro lib;
		try {
			lib = servicioLibros.buscarPorClave(id);
			servicioLibros.borrar(lib);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarLibro.do";
	}

}
