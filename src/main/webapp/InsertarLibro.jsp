<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="JavaEEJDBC.*"%>
<%
	String StrISBN = request.getParameter("ISBN"); 
	String StrTitulo = request.getParameter("Titulo"); 
	int Cat = Integer.parseInt(request.getParameter("Categoria"));
	float Pre = Float.parseFloat(request.getParameter("Precio")); 
	int filas =0;
	
	try{
		filas = new Libro(StrISBN, StrTitulo, Cat, Pre).insertar();
	}
	catch(DataBaseException e) 
	{%>
		
	  <%
	  	out.println(e.getMessage());
	  	out.println(e.getCause());
	  	System.out.println(e.getCause());
	  %>	
	<%}
	System.out.println("Filas modificadas "+filas);
	if(filas!=0)
	{
		response.sendRedirect("MostrarLibro.jsp");
	}
	
%>