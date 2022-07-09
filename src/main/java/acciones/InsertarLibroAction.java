package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Libro;
import servicios.ServicioLibros;
import servicios.ServicioLibrosImpl;

public class InsertarLibroAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioLibros servicioLibros = new ServicioLibrosImpl();
		String StrISBN = request.getParameter("ISBNLibro"); 
		String StrTitulo = request.getParameter("NomLibro"); 
		int Cat = Integer.parseInt(request.getParameter("CatLibro"));
		float Pre = Float.parseFloat(request.getParameter("PreLibro")); 
		try {
			servicioLibros.insertar(new Libro(StrISBN, StrTitulo, Cat, Pre));
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		
		return "MostrarLibro.do";
	}

}
