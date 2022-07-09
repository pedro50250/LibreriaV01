package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CategoriaDAO;
import JavaEEJDBC.DataBaseException;
import beans.Categoria;
import servicios.ServicioCategorias;
import servicios.ServicioCategoriasImpl;

public class EditarCategoriaAction extends Action {

	public EditarCategoriaAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

		String Nombre = request.getParameter("Nombre");
		int id = Integer.parseInt(request.getParameter("Id"));
		ServicioCategorias servicioCategorias = (ServicioCategorias) getBean("ServicioCategorias", request);

		Categoria cat;
		try {
			cat = servicioCategorias.buscarPorClave(id);
			cat.setid_cat(id);
			cat.setnom_cat(Nombre);
			servicioCategorias.guardarCambios(cat);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}

		return "MostrarCategoria.do";
	}

}
