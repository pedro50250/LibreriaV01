<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="JavaEEJDBC.DataBaseHelper"%>
<%@ page import="JavaEEJDBC.Libro"%>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<h1>Selecciona por Categoria: </h1>
	<div>
		<select name="categoria" class="form-select form-select-lg mb-3">
			<option value="0">Seleccionar</option>
			<%
					ArrayList<Integer> listaCategoria = new  Libro().buscarLasCategorias();
					for(Integer c: listaCategoria ) {%>
						<option value="<% c.intValue(); %>"> <%= c.intValue()%> </option>
					<%}
			%>
		</select>
	</div>
	<div>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">ISBN</th>
					<th scope="col">Titulo</th>
					<th scope="col">Categoria</th>
					<th scope="col">Precio</th>
				</tr>
			</thead>
			<tbody>
				<%
					ArrayList<Libro> libros = new Libro().consultarLibros();
					for(Libro lib: libros){
					%>
				<tr>
					<th scope="row"><%=lib.getNumLib() %></th>
					<td><%=lib.getISBN()%></td>
					<td><%=lib.getTitulo()%></td>
					<td><%=lib.getCategoria()%></td>
					<td><%=lib.getPrecio() %></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</div>
	<br />
	<a href="FormularioInsertarLibro.jsp">Inserta un Libro</a>
</body>
</html>