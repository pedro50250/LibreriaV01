<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="JavaEEJDBC.*"%>
<%@ page import="beans.*"%>
<%@ page import="servicios.*"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>Formulario Editar Categoria</title>
		<link rel="stylesheet" type="text/css" href="CSS/AltaLibro.css" media="screen"/> 
</head>
<body>
	<div>
		<%
		int id = Integer.parseInt(request.getParameter("ID"));
				ServicioCategorias servicioCategorias = new ServicioCategoriasImpl();
				Categoria cat = servicioCategorias.buscarPorClave(id);
		%>
		<h1>Editar Proveedor</h1>
			<form name="form" action="EditarCategoria.do" method="GET">
				<input type="hidden" name="Id" id="ID" value="<%= cat.getid_cat()%>"></input><br></br> 
				Nombre: <input type="text" name="Nombre" id="Nombre" value="<%= cat.getnom_cat()%>"></input><br></br> 
				<input type="submit" value="Editar"/>
			</form>
		</div>
	</body>
</html>