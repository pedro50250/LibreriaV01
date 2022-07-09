package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CategoriaDAO;
import JavaEEJDBC.DataBaseException;
import beans.Categoria;
import servicios.ServicioCategorias;
import servicios.ServicioCategoriasImpl;


public class BorrarCategoriaAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioCategorias servicioCategorias = (ServicioCategorias) getBean("ServicioCategorias", request);
		int id = Integer.parseInt(request.getParameter("id"));
		Categoria cat;
		try {
			cat = servicioCategorias.buscarPorClave(id);
			servicioCategorias.borrar(cat);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MostrarCategoria.do";
	}

}
