<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="JavaEEJDBC.*"%>
<%@ page import="beans.*"%>
<%@ page import="DAO.*"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>Formulario Editar Proveedor</title>
		<link rel="stylesheet" type="text/css" href="CSS/AltaLibro.css" media="screen"/> 
</head>
<body>
	<div>
		<%
		int id = Integer.parseInt(request.getParameter("ID"));
		ProveedorDAO provDao = new ProveedorDAO();
		Proveedor prov = provDao.buscarPorId(id);
		%>
		<h1>Editar Proveedor</h1>
			<form name="form" action="EditarProveedor.do" method="GET">
				<input type="hidden" name="Id" id="ID" value="<%= prov.getid_proveedor()%>"></input><br></br> 
				<input type="hidden" name="Fecha" id="Fecha" value="<%= prov.getfecha_alta()%>"></input><br> </br> 
				Nombre: <input type="text" name="Nombre" id="Nombre" value="<%= prov.getnombre_proveedor()%>"></input><br></br> 
				RFC: <input type="text" name="RFC" id="RFC" value="<%= prov.getrfc_proveedor()%>"></input><br></br> 
				Telefono: <input type="text" name="Telefono" id="Telefono" value="<%= prov.gettelefono_proveedor()%>"></input><br></br> 
				<input type="submit" value="Editar"/>
			</form>
		</div>
	</body>
</html>