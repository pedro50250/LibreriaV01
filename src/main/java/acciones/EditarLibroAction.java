package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Libro;
import servicios.ServicioLibros;

public class EditarLibroAction extends Action{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioLibros servicioLibros = (ServicioLibros) getBean("ServicioLibros", request);
		String StrISBN = request.getParameter("ISBN"); 
		String StrTitulo = request.getParameter("Titulo"); 
		int Cat = Integer.parseInt(request.getParameter("Categoria"));
		float Pre = Float.parseFloat(request.getParameter("Precio")); 
		int id = Integer.parseInt(request.getParameter("Id"));
		Libro lib;
		try {
			lib = servicioLibros.buscarPorClave(id);
			lib.setcat_lib(Cat);
			lib.setisbn_lib(StrISBN);
			lib.settit_lib(StrTitulo);
			lib.setpre_lib(Pre);
			servicioLibros.guardarCambios(lib);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Filas modificadas: " + fil);
		return "MostrarLibro.do";
	}

}
