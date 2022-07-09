package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Categoria;
import servicios.ServicioCategorias;

public class InsertarCategoriaAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioCategorias servicioCategorias = (ServicioCategorias) getBean("ServicioCategorias", request);
		String StrNombre = request.getParameter("Nombre"); 
		Categoria cat = new Categoria();
		cat.setnom_cat(StrNombre);
		try {
			servicioCategorias.insertar(cat);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		
		return "MostrarCategoria.do";
	}

}
