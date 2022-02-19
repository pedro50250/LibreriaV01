package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.Categoria;
import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.Libro;

public class MostrarLibroAction extends Action {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Libro> listDeLibros = Libro.consultarLibros();
			List<Categoria> listaCategoria = Categoria.getCategorias();
			request.setAttribute("listaDeLibros", listDeLibros);
			request.setAttribute("listDeCategoria", listaCategoria);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarLibro.jsp";
	}

}
