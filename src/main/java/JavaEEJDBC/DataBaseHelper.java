package JavaEEJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseHelper {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc;mysql://localhost/libreria";
	private static final String USUARIO = "root";
	private static final String CLAVE ="";
	
	Connection con = null;
	Statement stm = null;
	
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
		int filasAfectadas = 0;
		try
		{
			stm = con.createStatement();
			filasAfectadas = stm.executeUpdate(querySQL);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
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
	
}
