package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Categoria;

public class FormularioInsertarLibroAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		List<Categoria> listaDeCategorias = null;
		try {
			listaDeCategorias = Categoria.getCategorias();
			request.setAttribute("listaCategorias", listaDeCategorias);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "FormularioInsertarLibro.jsp";
	}

}
