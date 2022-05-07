package acciones;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaEEJDBC.DataBaseException;
import beans.Proveedor;

public class EditarProveedorAction extends Action{

	public EditarProveedorAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		
		String Nombre = request.getParameter("Nombre");
		int id =  Integer.parseInt(request.getParameter("Id"));
		String RFC = request.getParameter("RFC");
		String Telefono = request.getParameter("Telefono");
		String fecha = request.getParameter("Fecha");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date;
		try {
			date = dateFormat.parse(fecha);
			long time = date.getTime();
			Timestamp fec = new Timestamp(time);
			Proveedor prov = new Proveedor(Nombre,RFC,Telefono);
			prov.setid_proveedor(id);
			prov.setfecha_alta(fec);
			prov.actualizarProveedor(id);
		} catch (ParseException | DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MostrarProveedor.do";
	}

}


