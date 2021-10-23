<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="JavaEEJDBC.*"%>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
  <script src="js/EditarLibro.js" ></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<h1>Selecciona por Categoria: </h1>
	<div>
		<form action="MostrarLibro.jsp"  method="GET">
		<select name="categoria" class="form-select form-select-lg mb-3">
			<option value="Seleccionar">Seleccionar</option>
			<%
					ArrayList<Categoria> listaCategoria = new  Categoria().getCategorias();
					for(Categoria c: listaCategoria ) {%>
						<option value="<%= c.getIdCategoria() %>"> <%= c.getNomCategoria()%> </option>
					<%}
			%>
		</select>
		<input type="submit" value="Filtrar"/>
		</form>
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
					<th scope="col">      </th>
					<th scope="col">      </th>
				</tr>
			</thead>
			<tbody>
				<%
					ArrayList<Libro> libros = null;
					if(request.getParameter("categoria") == null || request.getParameter("categoria").equals("Seleccionar"))
					{
						libros = new Libro().consultarLibros();
					}
					else
					{
						int c = Integer.parseInt(request.getParameter("categoria"));
						libros = new Libro().buscarPorCategoria(c);
					}
					for(Libro lib: libros){
					%>
				<tr>
					<th scope="row"><%=lib.getNumLib() %></th>
					<td><%=lib.getISBN()%></td>
					<td><%=lib.getTitulo()%></td>
					<td><%=new Categoria().getNombreCategoriaById(lib.getCategoria()) %></td>
					<td><%=lib.getPrecio() %></td>
					<td>
						<a href="FormularioEditarLibro.jsp?ID=<%=lib.getNumLib() %>"><i class="fas fa-edit"></i></a>
					</td>	
					<td> <a href="BorrarLibro.jsp?id=<%=lib.getNumLib() %>"><i class="fas fa-trash-alt"></i></a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</div>
	<br />
	<a class="btn btn-primary" href="FormularioInsertarLibro.jsp" role="button">Inserta un Libro <i class="fas fa-book"></i></a>
	
</body>
</html>