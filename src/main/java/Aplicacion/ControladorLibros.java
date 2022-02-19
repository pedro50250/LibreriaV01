package Aplicacion;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import acciones.Action;
import acciones.MostrarLibroAction;
import acciones.MostrarLibroFiltroAction;

/**
 * Servlet implementation class ControladorLibros
 */
//@WebServlet("/ControladorLibros")
public class ControladorLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher despachador = null;
		Action action = null;
		String url = request.getServletPath();
		if(request.getServletPath().equals("/ControladorLibros.do"))
		{
			if (request.getParameter("categoria") != null) {
				if (!request.getParameter("categoria").equals("Seleccionar")) {
					action = new MostrarLibroFiltroAction();
				} else {
					action = new MostrarLibroAction();
				}
			}
		}
		else
		{
			action = Action.getAccion(url.substring(1, url.length()-3));
		}
		if(action != null)
		{
			despachador = request.getRequestDispatcher(action.ejecutar(request, response));
			despachador.forward (request, response);
		}
	}


}
