package acciones;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import JavaEEJDBC.Libro;

public class EditarProveedorAction extends Action{

	public EditarProveedorAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		
		return "MostrarProveedor.do";
	}

}


