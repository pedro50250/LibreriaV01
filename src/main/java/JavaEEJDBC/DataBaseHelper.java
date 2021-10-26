package JavaEEJDBC;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper <T> {

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> seleccionarRegistros(String query, Class clase) {
		ResultSet filas = null;
		List<T> listaDeObjetos = new ArrayList<T>();
		try {
			this.stm = this.con.createStatement();
			filas = stm.executeQuery(query);
			while(filas.next())
			{
				T object = (T) Class.forName(clase.getName()).getDeclaredConstructor().newInstance();
				Method[] metodos = object.getClass().getDeclaredMethods();
				for(int i=0; i< metodos.length; i++)
				{
					if(metodos[i].getName().startsWith("set"))
					{
						if(metodos[i].getName().substring(3).equals("num_lib") || metodos[i].getName().substring(3).equals("cat_lib") ||  metodos[i].getName().substring(3).equals("id_cat"))
						{
							metodos[i].invoke(object, filas.getInt(metodos[i].getName().substring(3)));
						}
						else if(metodos[i].getName().substring(3).equals("pre_lib"))
						{
							metodos[i].invoke(object, filas.getFloat(metodos[i].getName().substring(3)));
						}
						else
						{
							metodos[i].invoke(object, filas.getString(metodos[i].getName().substring(3)));
						}

					}
					
				}
				listaDeObjetos.add(object);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return listaDeObjetos;
	}
	
	public int actualizarRegistro(Libro lib)
	{
		String SQL = "UPDATE libros SET isbn_lib = ?, tit_lib =?, cat_lib=?, pre_lib=? "
				+ "WHERE num_lib=?";
		int filas= -1;
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, lib.getisbn_lib());
			ps.setString(2, lib.gettit_lib());
			ps.setInt(3, lib.getcat_lib());
			ps.setFloat(4, lib.getpre_lib());
			ps.setInt(5, lib.getnum_lib());
			filas = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filas;
		
	}
	
	
	
}
