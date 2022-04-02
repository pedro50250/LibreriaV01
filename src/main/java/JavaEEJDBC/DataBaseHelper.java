package JavaEEJDBC;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class DataBaseHelper <T> {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/libreria";
	private static final String USUARIO = "root";
	private static final String CLAVE ="";
	private Logger log =  LogManager.getLogger("DataBaseHelper");
	
	Connection con = null;
	Statement stm = null;
	int filasAfectadas = 0;
	public DataBaseHelper() throws DataBaseException
	{
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USUARIO, CLAVE);
			log.setLevel(Level.DEBUG);
		}
		catch(SQLException e)
		{
			System.out.println("Error de SQL" + e.getMessage());
			throw new DataBaseException("Error de SQL");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Clase no encontrada");
		}
		log.setLevel(Level.DEBUG);
	}
	
	public int modificarRegistro(String querySQL) throws DataBaseException {
		try {
			stm = con.createStatement();
			filasAfectadas = stm.executeUpdate(querySQL);
		} catch (SQLException e) {
			IOException ioe = new IOException();  
			ioe.initCause(e);
			log.fatal("Ocurrio un error fatal");
			throw new DataBaseException("Error de SQL", ioe.getCause());
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

	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> seleccionarRegistros(String query, Class clase) throws DataBaseException{
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
						if(metodos[i].getName().substring(3).equals("num_lib") || metodos[i].getName().substring(3).equals("cat_lib") ||  metodos[i].getName().substring(3).equals("id_cat")
								|| metodos[i].getName().substring(3).equals("id_proveedor"))
						{
							metodos[i].invoke(object, filas.getInt(metodos[i].getName().substring(3)));
						}
						else if(metodos[i].getName().substring(3).equals("pre_lib"))
						{
							metodos[i].invoke(object, filas.getFloat(metodos[i].getName().substring(3)));
						}
						else if(metodos[i].getName().substring(3).equals("fecha_alta"))
						{
							metodos[i].invoke(object, filas.getTimestamp(metodos[i].getName().substring(3)));
						}
						else
						{
							metodos[i].invoke(object, filas.getString(metodos[i].getName().substring(3)));
						}

					}
					
				}
				listaDeObjetos.add(object);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException |  InvocationTargetException | NoSuchMethodException  e) {
			System.out.println("Clase: "+ e.getClass());
			IOException ioe = new IOException();  
			ioe.initCause(e);
			log.fatal("Ocurrio un error fatal");
			throw new DataBaseException("Error al leer registros", ioe.getCause());
		}

		return listaDeObjetos;
	}*/
	
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
	
	public int actualizarRegistroProveedor(Proveedor prov)
	{
		String SQL = "UPDATE libros SET nombre_proveedor = ?, fecha_alta =?, rfc_proveedor=?, telefono_proveedor=? "
				+ "WHERE id_proveedor=?";
		int filas= -1;
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, prov.getnombre_proveedor());
			ps.setTimestamp(2, prov.getfecha_alta());
			ps.setString(3, prov.getrfc_proveedor());
			ps.setString(4, prov.gettelefono_proveedor());
			ps.setInt(5, prov.getid_proveedor());
			filas = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filas;
		
	}
	
	
	
}
