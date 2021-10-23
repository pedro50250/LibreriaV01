<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="JavaEEJDBC.*"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	Libro lib = new Libro();
	lib.BorrarLibro(id);
	response.sendRedirect("MostrarLibro.jsp");
%>