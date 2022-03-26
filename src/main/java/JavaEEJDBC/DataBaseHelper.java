package JavaEEJDBC;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class DataBaseHelper <T> {

	/*private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/libreria";
	private static final String USUARIO = "root";
	private static final String CLAVE ="";*/
	private Logger log =  LogManager.getLogger("DataBaseHelper");
	
	//Connection con = null;
	//Statement stm = null;
	int filasAfectadas = 0;
	public DataBaseHelper() throws DataBaseException
	{
		/*try {
			//Class.forName(DRIVER);
			//con = DriverManager.getConnection(URL,USUARIO, CLAVE);
			log.setLevel(Level.DEBUG);
		}
		catch(SQLException e)
		{
			System.out.println("Error de SQL" + e.getMessage());
			throw new DataBaseException("Error de SQL");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Clase no encontrada");
		}*/
		log.setLevel(Level.DEBUG);
	}
	
	/*public int modificarRegistro(String querySQL) throws DataBaseException {
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		
	}*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> leerRegistros( String query) throws DataBaseException
	{
		Session session = null;
		List<T> listaDeObjetos = new ArrayList<T>();
		try {
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			Query consulta = session.createQuery(query);
			listaDeObjetos = consulta.list();
		}
		catch(HibernateException e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			if(session!=null) session.close();
		}
		return listaDeObjetos;
	}
	
	public void modificarRegistro(Object objeto, String accion)
	{
		Session session = null;
		Transaction transaction = null;
		try {
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			transaction = session.beginTransaction();
			if(accion=="insertar")
			{
				session.save(objeto);
			}
			else if(accion=="actualizar")
			{
				session.update(objeto);
			}
			else if(accion=="eliminar")
			{
				session.delete(objeto);
			}
			transaction.commit();
		}
		catch(HibernateException e)
		{
			System.out.println(e.getMessage());
			try {
				transaction.rollback();
			}catch(IllegalStateException e1)
			{
				System.out.println("No se pudo realizar el rollback");
			}
		}
		catch(SecurityException e)
		{
			e.printStackTrace();
		}
		finally {
			if(session!=null) session.close();
			if(transaction!=null) session.close();
		}
	}
	
}
