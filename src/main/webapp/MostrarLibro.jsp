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
  <script src="js/EditarLibro.js" ></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>Lista Libros</title>
</head>
<body>
	<h1>Selecciona por Categoria: </h1>
	<div>
		<form action="ControladorLibros.do"  method="GET">
			<select name="categoria" class="form-select form-select-lg mb-3">
				<option value="Seleccionar">Seleccionar</option>
				<c:forEach var="c" items="${listDeCategoria}">
					<option value="${c.getid_cat()}"> "${c.getnom_cat()}" </option>
				</c:forEach>
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
				<c:forEach var="lib" items="${listaDeLibros}">
					<tr>
					<th scope="row">${lib.getnum_lib()}</th>
					<td>${lib.getisbn_lib()}</td>
					<td>${lib.gettit_lib()}</td>
					<td>${lib.getCategoria().getnom_cat()}</td>
					<td>${lib.getpre_lib()}</td>
					<td>
						<a href="FormularioEditarLibro.jsp?ID=${lib.getnum_lib()}"><i class="fas fa-edit"></i></a>
					</td>	
					<td> <a href="BorrarLibro.do?id=${lib.getnum_lib()}"><i class="fas fa-trash-alt"></i></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br />
	<a class="btn btn-primary" href="FormularioInsertarLibro.jsp" role="button">Inserta un Libro <i class="fas fa-book"></i></a>
	<a class="btn btn-primary" href="MostrarProveedor.do" role="button">Ir a lista proveedores <i class="fas fa-truck"></i></a>
	<a class="btn btn-primary" href="MostrarCategoria.do" role="button">Ir a lista categorias <i class="fas fa-tags"></i></a>
</body>
</html>