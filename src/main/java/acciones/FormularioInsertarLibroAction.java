package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Categoria;
import servicios.ServicioCategorias;

public class FormularioInsertarLibroAction extends Action{


	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		List<Categoria> listaDeCategorias = null;
		ServicioCategorias servicioCategorias = (ServicioCategorias) getBean("ServicioCategorias", request);
		try {
			listaDeCategorias = servicioCategorias.buscarTodos();
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		request.setAttribute("listaCategorias", listaDeCategorias);
		return "FormularioInsertarLibro.jsp";
	}

}
