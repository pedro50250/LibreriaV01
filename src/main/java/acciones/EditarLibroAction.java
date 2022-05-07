package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Libro;

public class EditarLibroAction extends Action{

	@SuppressWarnings("unused")
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		String StrISBN = request.getParameter("ISBN"); 
		String StrTitulo = request.getParameter("Titulo"); 
		int Cat = Integer.parseInt(request.getParameter("Categoria"));
		float Pre = Float.parseFloat(request.getParameter("Precio")); 
		int id = Integer.parseInt(request.getParameter("Id"));
		try {
			int fil= new Libro(StrISBN, StrTitulo, Cat, Pre).actualizarLibro(id);
			//System.out.println("Filas modificadas: " + fil);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarLibro.do";
	}

}
