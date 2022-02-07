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
	
		RequestDispatcher despachador = null;
		if(request.getServletPath().equals("/MostrarLibro.do") )
		{
			response.getWriter().append("Served at: ").append(request.getContextPath());
			try {
				List<Libro> listDeLibros = Libro.consultarLibros();
				List<Categoria> listaCategoria =  Categoria.getCategorias();
				request.setAttribute("listaDeLibros", listDeLibros);
				request.setAttribute("listDeCategoria", listaCategoria);
			} catch (DataBaseException e) {
				e.printStackTrace();
			}
			despachador = request.getRequestDispatcher("MostrarLibro.jsp");
			despachador.forward (request, response);
		}
		else if( request.getServletPath().equals("/FormularioInsertarLibro.do"))
		{
			List<Categoria> listaDeCategorias = null;
			try {
				listaDeCategorias = Categoria.getCategorias();
				request.setAttribute("listaCategorias", listaDeCategorias);
				despachador = request.getRequestDispatcher("FormularioInsertarLibro.jsp");
				despachador.forward (request, response);
			} catch (DataBaseException e) {
				e.printStackTrace();
			}
			
		}
		else if(request.getServletPath().equals("/ControladorLibros.do"))
		{
			List<Libro> listDeLibrosFiltro = null;
			if(request.getParameter("categoria") != null)
			{
				if(!request.getParameter("categoria").equals("Seleccionar"))
				{
					try {
						listDeLibrosFiltro = Libro.buscarPorCategoria(Integer.parseInt(request.getParameter("categoria")));
						List<Categoria> listaCategoria =  Categoria.getCategorias();
						request.setAttribute("listDeCategoria", listaCategoria);
						request.setAttribute("listaDeLibros", listDeLibrosFiltro);
					} catch (NumberFormatException | DataBaseException e) {
						e.printStackTrace();
					}
				}
				else
				{
					try {
						List<Libro> listDeLibros = Libro.consultarLibros();
						List<Categoria> listaCategoria =  Categoria.getCategorias();
						request.setAttribute("listaDeLibros", listDeLibros);
						request.setAttribute("listDeCategoria", listaCategoria);
					} catch (DataBaseException e) {
						e.printStackTrace();
					}
				}
				
				despachador = request.getRequestDispatcher("MostrarLibro.jsp");
				despachador.forward (request, response);
			}
		}
		else if(request.getServletPath().equals("/BorrarLibro.do"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				new Libro().BorrarLibro(id);
			} catch (DataBaseException e) {
				e.printStackTrace();
			}
			despachador = request.getRequestDispatcher("MostrarLibro.do");
			despachador.forward (request, response);
		}
		else if(request.getServletPath().equals("/EditarLibro.do"))
		{
			String StrISBN = request.getParameter("ISBN"); 
			String StrTitulo = request.getParameter("Titulo"); 
			int Cat = Integer.parseInt(request.getParameter("Categoria"));
			float Pre = Float.parseFloat(request.getParameter("Precio")); 
			int id = Integer.parseInt(request.getParameter("Id"));
			try {
				int fil= new Libro(StrISBN, StrTitulo, Cat, Pre).actualizarLibro(id);
				System.out.println("Filas modificadas: " + fil);
			} catch (DataBaseException e) {
				e.printStackTrace();
			}
			despachador = request.getRequestDispatcher("MostrarLibro.do");
			despachador.forward (request, response);
		}
		else
		{
			String StrISBN = request.getParameter("ISBNLibro"); 
			String StrTitulo = request.getParameter("NomLibro"); 
			int Cat = Integer.parseInt(request.getParameter("CatLibro"));
			float Pre = Float.parseFloat(request.getParameter("PreLibro")); 
			try {
				new Libro(StrISBN, StrTitulo, Cat, Pre).insertar();
			} catch (DataBaseException e) {
				e.printStackTrace();
			}
			despachador = request.getRequestDispatcher("MostrarLibro.do");
			despachador.forward (request, response);
		}
	}


}
