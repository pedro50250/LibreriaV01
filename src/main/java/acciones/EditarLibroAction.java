package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LibroDAO;
import JavaEEJDBC.DataBaseException;
import beans.Libro;

public class EditarLibroAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		LibroDAO libDao = new LibroDAO();
		String StrISBN = request.getParameter("ISBN"); 
		String StrTitulo = request.getParameter("Titulo"); 
		int Cat = Integer.parseInt(request.getParameter("Categoria"));
		float Pre = Float.parseFloat(request.getParameter("Precio")); 
		int id = Integer.parseInt(request.getParameter("Id"));
		try {
			Libro lib = libDao.consultaLibroPorId(id);
			lib.setcat_lib(Cat);
			lib.setisbn_lib(StrISBN);
			lib.settit_lib(StrTitulo);
			lib.setpre_lib(Pre);
			libDao.actualizarLibro(lib);
			//System.out.println("Filas modificadas: " + fil);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarLibro.do";
	}

}
