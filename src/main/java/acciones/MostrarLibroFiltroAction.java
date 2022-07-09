package acciones;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import JavaEEJDBC.DataBaseException;
import servicios.ServicioCategorias;
import servicios.ServicioLibros;

public class MostrarLibroFiltroAction extends Action {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

		ServicioLibros servicioLibros = (ServicioLibros) getBean("ServicioLibros", request);
		ServicioCategorias servicioCategorias = (ServicioCategorias) getBean("ServicioCategorias", request);
		try {
			request.setAttribute("listDeCategoria", servicioCategorias.buscarTodos());
			request.setAttribute("listaDeLibros", servicioLibros.buscarPorCategoria(Integer.parseInt(request.getParameter("categoria"))));
		} catch (NumberFormatException | DataBaseException e) {
			e.printStackTrace();
		}
		return "MostrarLibro.jsp";

	}
}
