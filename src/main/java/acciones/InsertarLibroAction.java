package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LibroDAO;
import JavaEEJDBC.DataBaseException;
import beans.Libro;

public class InsertarLibroAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		LibroDAO libDao = new LibroDAO();
		String StrISBN = request.getParameter("ISBNLibro"); 
		String StrTitulo = request.getParameter("NomLibro"); 
		int Cat = Integer.parseInt(request.getParameter("CatLibro"));
		float Pre = Float.parseFloat(request.getParameter("PreLibro")); 
		try {
			Libro lib = new Libro(StrISBN, StrTitulo, Cat, Pre);
			libDao.insertar(lib);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		
		return "MostrarLibro.do";
	}

}
