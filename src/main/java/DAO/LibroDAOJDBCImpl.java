package DAO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import JavaEEJDBC.DataBaseException;
import beans.Libro;

public class LibroDAOJDBCImpl<T> extends GenericDAOHIBERNATEImpl<Libro, Integer> implements LibroDAO {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/libreria";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public List<Libro> buscarPorCategoria(int Cat) throws DataBaseException {
		String SQL = "SELECT * FROM Libro WHERE cat_lib =" + Cat;
		ResultSet filas = null;
		List<T> listaDeObjetos = null;
		Connection con = null;
		Statement stm = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USUARIO, CLAVE);
			stm = con.createStatement();
			filas = stm.executeQuery(SQL);
			while (filas.next()) {
				T object = (T) Class.forName(Libro.class.getName()).getDeclaredConstructor().newInstance();
				Method[] metodos = object.getClass().getDeclaredMethods();
				for (int i = 0; i < metodos.length; i++) {
					if (metodos[i].getName().startsWith("set")) {
						if (metodos[i].getName().substring(3).equals("num_lib")
								|| metodos[i].getName().substring(3).equals("cat_lib")
								|| metodos[i].getName().substring(3).equals("id_cat")
								|| metodos[i].getName().substring(3).equals("id_proveedor")) {
							metodos[i].invoke(object, filas.getInt(metodos[i].getName().substring(3)));
						} else if (metodos[i].getName().substring(3).equals("pre_lib")) {
							metodos[i].invoke(object, filas.getFloat(metodos[i].getName().substring(3)));
						} else if (metodos[i].getName().substring(3).equals("fecha_alta")) {
							metodos[i].invoke(object, filas.getTimestamp(metodos[i].getName().substring(3)));
						} else {
							metodos[i].invoke(object, filas.getString(metodos[i].getName().substring(3)));
						}

					}

				}
				listaDeObjetos.add(object);
			}

		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("Error de SQL" + e.getMessage());
			throw new DataBaseException("Error de SQL");
		} finally {
			try {
				if (stm != null)
					stm.close();
				if (con != null)
					con.close();
			} catch (Exception e) {

			}

		}
		return null;
	}

}
