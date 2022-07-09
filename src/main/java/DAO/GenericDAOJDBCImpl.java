package DAO;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import JavaEEJDBC.DataBaseException;

public abstract class GenericDAOJDBCImpl<T, Id extends Serializable> implements GenericDAO<T, Id> {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/libreria";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";
	private Logger log = LogManager.getLogger("DataBaseHelper");

	Connection con = null;
	Statement stm = null;

	public GenericDAOJDBCImpl() throws DataBaseException {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USUARIO, CLAVE);
			log.setLevel(Level.DEBUG);
		} catch (SQLException e) {
			System.out.println("Error de SQL" + e.getMessage());
			throw new DataBaseException("Error de SQL");

		} catch (ClassNotFoundException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Clase no encontrada");
		}
		log.setLevel(Level.DEBUG);
	}

	@SuppressWarnings("unchecked")
	public T buscarPorClave(String query, T objeto) {

		ResultSet filas = null;
		List<T> listaDeObjetos = new ArrayList<T>();
		try {
			this.stm = this.con.createStatement();
			filas = stm.executeQuery(query);
			while (filas.next()) {
				T object = (T) Class.forName(objeto.getClass().getName()).getDeclaredConstructor().newInstance();
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

		} catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			IOException ioe = new IOException();  
			ioe.initCause(e);
			log.fatal("Ocurrio un error fatal");
		} 
		return listaDeObjetos.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(T objeto) {
		String query = "SELECT * FROM " + objeto.getClass().getName();
		ResultSet filas = null;
		List<T> listaDeObjetos = new ArrayList<T>();
		try {
			this.stm = this.con.createStatement();
			filas = stm.executeQuery(query);
			while (filas.next()) {
				T object = (T) Class.forName(objeto.getClass().getName()).getDeclaredConstructor().newInstance();
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

		} catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			IOException ioe = new IOException();  
			ioe.initCause(e);
			log.fatal("Ocurrio un error fatal");
		} 
		return listaDeObjetos;
	}

	public void borrar(String query) {
		try {
			stm = con.createStatement();
			stm.executeUpdate(query);
		} catch (SQLException e) {
			IOException ioe = new IOException();
			ioe.initCause(e);
			log.fatal("Ocurrio un error fatal");
		}
	}

	public void insertar(String query) {
		try {
			stm = con.createStatement();
			stm.executeUpdate(query);
		} catch (SQLException e) {
			IOException ioe = new IOException();
			ioe.initCause(e);
			log.fatal("Ocurrio un error fatal");
		}

		this.cerrarObjetos();
	}

	public void guardarCambios(String query) {
		try {
			stm = con.createStatement();
			stm.executeUpdate(query);
		} catch (SQLException e) {
			IOException ioe = new IOException();
			ioe.initCause(e);
			log.fatal("Ocurrio un error fatal");
		}

		this.cerrarObjetos();
	}

	public void cerrarObjetos() {
		try {
			if (stm != null)
				stm.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
