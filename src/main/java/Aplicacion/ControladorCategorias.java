package Aplicacion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acciones.Action;

@WebServlet("/ControladorCategoria")
public class ControladorCategorias extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public ControladorCategorias() {
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PAsa por categoria");
		RequestDispatcher despachador = null;
		Action action = null;
		String url = request.getServletPath();
		action = Action.getAccion(url.substring(1, url.length()-3));
		if(action != null)
		{
			despachador = request.getRequestDispatcher(action.ejecutar(request, response));
			despachador.forward (request, response);
		}
	}

}
