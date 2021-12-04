package Aplicacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.Categoria;
import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.Libro;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher despachador = null;
		List<Libro> listDeLibrosFiltro = null;
		if(request.getParameter("categoria") != null)
		{
			if(request.getParameter("categoria").equals("Seleccionar"))
			{
				System.out.println("Es seleccionar");
			}
			else
			{
				try {
					listDeLibrosFiltro = Libro.buscarPorCategoria(Integer.parseInt(request.getParameter("categoria")));
					request.setAttribute("listDeLibrosFiltro", listDeLibrosFiltro);
				} catch (NumberFormatException | DataBaseException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			List<Libro> listDeLibros = Libro.consultarLibros();
			List<Categoria> listaCategoria =  Categoria.getCategorias();
			request.setAttribute("listaDeLibros", listDeLibros);
			request.setAttribute("listDeCategoria", listaCategoria);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		despachador = request.getRequestDispatcher("MostrarLibro.jsp");
		despachador.forward (request, response);
	}


}
