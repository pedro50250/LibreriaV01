<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="JavaEEJDBC.*"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	Libro lib = new Libro();
	boolean error=false;
	try{
		lib.BorrarLibro(id);
	}
	catch(DataBaseException e) 
	{%>
	  <% out.println(e.getMessage());
	   error=true;%>	
	<%}
	if(!error)
	{
		response.sendRedirect("MostrarLibro.jsp");
	}
	
%>