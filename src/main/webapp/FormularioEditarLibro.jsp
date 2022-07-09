<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="JavaEEJDBC.*"%>
<%@ page import="beans.*"%>
<%@ page import="servicios.*"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>Formulario Editar Libro</title>
		<link rel="stylesheet" type="text/css" href="CSS/AltaLibro.css" media="screen"/> 
</head>
<body>
	<div>
		<%

				int id = Integer.parseInt(request.getParameter("ID"));
				ServicioLibros servicioLibros = new ServicioLibrosImpl();
				Libro lib = servicioLibros.buscarPorClave(id);
		%>
		<h1>Editar Libro</h1>
			<form name="form" action="EditarLibro.do" method="GET">
				<input type="hidden" name="Id" id="ISBN" value="<%=lib.getnum_lib()%>"></input><br></br> 
				ISBN: <input type="text" name="ISBN" id="ISBN" value="<%=lib.getisbn_lib()%>"></input><br></br> 
				Titulo:<input type="text" name="Titulo" id="Titulo" value="<%=lib.gettit_lib()%>"></input><br> </br> 
				Categoria: <input type="number" name="Categoria" id="Categoria" value="<%=lib.getcat_lib()%>" pattern="\d*" title="Numbers only, please."></input><br></br> 
				Precio: <input type="text" name="Precio" id="Precio" value="<%=lib.getpre_lib()%>"></input><br></br> 
				<input type="submit" value="Editar"/>
			</form>
		</div>
	</body>
</html>