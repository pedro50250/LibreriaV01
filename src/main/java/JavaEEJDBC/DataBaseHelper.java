package JavaEEJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseHelper {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/libreria";
	private static final String USUARIO = "root";
	private static final String CLAVE ="";
	
	Connection con = null;
	Statement stm = null;
	int filasAfectadas = 0;
	public DataBaseHelper()
	{
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USUARIO, CLAVE);
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public int modificarRegistro(String querySQL)
	{
		try
		{
			stm = con.createStatement();
			filasAfectadas = stm.executeUpdate(querySQL);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		this.cerrarObjetos();
		return filasAfectadas;
	}
	
	public void cerrarObjetos()
	{
		try
		{
			if(stm!=null) stm.close();
			if(con!=null) con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public ResultSet seleccionarRegistros(String query) {
		ResultSet filas = null;
		try {
			this.stm = this.con.createStatement();
			filas = stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return filas;
	}
	
}
