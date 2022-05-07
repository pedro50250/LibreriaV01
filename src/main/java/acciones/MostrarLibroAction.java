package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CategoriaDAO;
import DAO.LibroDAO;
import JavaEEJDBC.DataBaseException;
import beans.Categoria;
import beans.Libro;

public class MostrarLibroAction extends Action {

	@SuppressWarnings("static-access")
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			LibroDAO libDao = new LibroDAO();
			CategoriaDAO catDao = new CategoriaDAO();
			List<Libro> listDeLibros = libDao.consultarLibros();
			List<Categoria> listaCategoria = catDao.getCategorias();
			request.setAttribute("listaDeLibros", listDeLibros);
			request.setAttribute("listDeCategoria", listaCategoria);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarLibro.jsp";
	}

}
