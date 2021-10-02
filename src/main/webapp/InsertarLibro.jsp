<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="JavaEEJDBC.*"%>
<%
	String StrISBN = request.getParameter("ISBN"); 
	String StrTitulo = request.getParameter("Titulo"); 
	int Cat = Integer.parseInt(request.getParameter("Categoria"));
	float Pre = Float.parseFloat(request.getParameter("Precio")); 
	String consultaSQL = "INSERT INTO libros(isbn_lib, tit_lib, cat_lib, pre_lib) VALUES";
	consultaSQL += "('" + StrISBN+"','" + StrTitulo +"'," + Cat + "," + Pre +")";
	DataBaseHelper dbh = new DataBaseHelper();
	int filas = dbh.modificarRegistro(consultaSQL);
	dbh.cerrarObjetos();
	System.out.println("Filas modificadas "+filas);
%>