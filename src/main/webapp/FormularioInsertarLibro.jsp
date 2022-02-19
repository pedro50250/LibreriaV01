<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>Formulario Alta Libro</title>
		<script src="js/AltaLibro.js" ></script>
		<link rel="stylesheet" type="text/css" href="CSS/AltaLibro.css" media="screen"/> 
	</head>
	<body>
		<div>
			<h1>Formulario Alta Libro</h1>
			<form action="InsertarLibro.do" method="GET">
				ISBN: <input type="text" name="ISBNLibro" id="ISBN"></input><br></br> 
				Titulo:<input type="text" name="NomLibro" id="Titulo"></input><br> </br> 
				Categoria: <input type="number" name="CatLibro" id="Categoria"></input><br></br> 
				Precio: <input type="text" name="PreLibro" id="Precio"></input><br></br> 
				<input type="submit" value="insertar"/>
			</form>
		</div>
	</body>
</html>