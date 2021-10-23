<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="JavaEEJDBC.*"%>
<%
	String StrISBN = request.getParameter("ISBN"); 
	String StrTitulo = request.getParameter("Titulo"); 
	int Cat = Integer.parseInt(request.getParameter("Categoria"));
	float Pre = Float.parseFloat(request.getParameter("Precio")); 
	int id = Integer.parseInt(request.getParameter("Id"));
	Libro lib = new Libro(StrISBN, StrTitulo, Cat, Pre);
	int filas = lib.actualizarLibro(id);
	System.out.println("Filas modificadas "+filas);
	response.sendRedirect("MostrarLibro.jsp");
%>