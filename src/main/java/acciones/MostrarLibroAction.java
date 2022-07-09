package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Categoria;
import beans.Libro;
import servicios.ServicioCategorias;
import servicios.ServicioLibros;


public class MostrarLibroAction extends Action {

	
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioLibros servicioLibros = (ServicioLibros) getBean("ServicioLibros", request);
		ServicioCategorias servicioCategorias = (ServicioCategorias) getBean("ServicioCategorias", request);
		List<Libro> listDeLibros = null;
		List<Categoria> listaCategoria = null;
		try {
			listDeLibros = servicioLibros.buscarTodos();
			listaCategoria = servicioCategorias.buscarTodos();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listaDeLibros", listDeLibros);
		request.setAttribute("listDeCategoria", listaCategoria);
		return "MostrarLibro.jsp";
	}

}
