package acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Categoria;
import servicios.ServicioCategorias;

public class MostrarCategoriaAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioCategorias servicioCategorias = (ServicioCategorias) getBean("ServicioCategorias", request);
		List<Categoria> listaCategorias = null;
		try {
			listaCategorias = servicioCategorias.buscarTodos();
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		request.setAttribute("listaCategorias", listaCategorias);
		return "MostrarCategoria.jsp";
	}

}
