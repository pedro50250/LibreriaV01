package JavaEEJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	public int actualizarRegistro(Libro lib)
	{
		String SQL = "UPDATE libros SET isbn_lib = ?, tit_lib =?, cat_lib=?, pre_lib=? "
				+ "WHERE num_lib=?";
		int filas= -1;
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, lib.getISBN());
			ps.setString(2, lib.getTitulo());
			ps.setInt(3, lib.getCategoria());
			ps.setFloat(4, lib.getPrecio());
			ps.setInt(5, lib.getNumLib());
			filas = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filas;
		
	}
	
	
	
}
