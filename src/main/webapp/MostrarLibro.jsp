<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page import="java.sql.ResultSet"%>
<%@ page import="JavaEEJDBC.DataBaseHelper"%>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	String SQL = "SELECT * FROM libros";
	DataBaseHelper dbh = new DataBaseHelper();
	ResultSet rs = dbh.seleccionarRegistros(SQL);
	while (rs.next()) {
	%>
	<%=rs.getInt("num_lib")%>
	<%=rs.getString("isbn_lib")%>
	<%=rs.getString("tit_lib")%>
	<%=rs.getInt("cat_lib")%>
	<%=rs.getFloat("pre_lib")%>
	<br />
	<%} %>
	<a href="FormularioInsertarLibro.jsp">Inserta un Libro</a>
</body>
</html>