/**
 * 
 */
function validaDatos()
{
	var n1, n2, n3;
	n1 = document.getElementById("ISBN").value;
	n2 = document.getElementById("Titulo").value;
	n3 = document.getElementById("Categoria").value;
	n4 = document.getElementById("Precio").value;
	if(n1 =="" || n2=="" || n3=="")
	{
		alert("Tiene que llenar todos los campos");
	}
	else
	{
		alert("Datos enviados");
		document.forms[0].action = "InsertarLibro.jsp?ISBN="+n1+"&Titulo="+n2+"&Categoria="+n3+"&Precio="+n4;
		document.forms[0].method = "post";
		document.forms[0].submit();
	}
}