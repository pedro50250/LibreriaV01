/**
 * 
 */
function validaDatos()
{
	if (document.form.CveLibro.value.length==0){
      		alert("Tiene que escribir el ISBN")
      		document.form.CveLibro.focus()
      		return 0;
   	}
	if (document.form.NomLibro.value.length==0){
      		alert("Tiene que escribir el titulo")
      		document.form.CveLibro.focus()
      		return 0;
   	}
	if (document.form.CatLibro.value.length==0){
      		alert("Tiene que escribir la categoria")
      		document.form.CveLibro.focus()
      		return 0;
   	}
}