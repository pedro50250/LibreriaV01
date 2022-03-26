<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="JavaEEJDBC.*"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="es">
<head>
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<title>Lista Proveedores </title>
</head>
<body>
	<body>
	<h1>Proveedores <i class="fas fa-truck"></i></h1>
	<div>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nombre</th>
					<th scope="col">Fecha Alta</th>
					<th scope="col">RFC</th>
					<th scope="col">Telefono</th>
					<th scope="col">      </th>
					<th scope="col">      </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${listaProveedor}">
					<tr>
					<th scope="row">${ list.getid_proveedor()}</th>
					<td>${ list.getnombre_proveedor()}</td>
					<td>${ list.getfecha_alta()}</td>
					<td>${ list.getrfc_proveedor()}</td>
					<td>${ list.gettelefono_proveedor()}</td>
					<td>
						<a href="FormularioEditarProveedor.jsp?ID=${list.getid_proveedor()}"><i class="fas fa-edit"></i></a>
					</td>	
					<td> <a href="BorrarProveedor.do?id=${list.getid_proveedor()}"><i class="fas fa-trash-alt"></i></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br />
	<a class="btn btn-primary" href="FormularioInsertarProveedor.jsp" role="button">Inserta un Proveedor  <i class="fas fa-truck"></i></a>
	<a class="btn btn-primary" href="MostrarLibro.do" role="button">Ir a lista Libros <i class="fas fa-book"></i></a>
</body>
</body>
</html>