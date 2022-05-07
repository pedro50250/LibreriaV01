package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CategoriaDAO;
import DAO.LibroDAO;
import JavaEEJDBC.DataBaseException;
import beans.Categoria;
import beans.Libro;

public class MostrarLibroFiltroAction extends Action {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		List<Libro> listDeLibrosFiltro = null;
		LibroDAO libDao = new LibroDAO();
		CategoriaDAO catDao = new CategoriaDAO();
		try {
			listDeLibrosFiltro = libDao.buscarPorCategoria(Integer.parseInt(request.getParameter("categoria")));
			List<Categoria> listaCategoria = catDao.getCategorias();
			request.setAttribute("listDeCategoria", listaCategoria);
			request.setAttribute("listaDeLibros", listDeLibrosFiltro);
		} catch (NumberFormatException | DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarLibro.jsp";

	}
}
