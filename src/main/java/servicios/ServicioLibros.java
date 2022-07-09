package servicios;

import java.util.List;

import DAO.LibroDAO;
import JavaEEJDBC.DataBaseException;
import beans.Libro;

public interface ServicioLibros {

	public abstract void insertar(Libro libro) throws DataBaseException;

	public abstract List<Libro> buscarTodos() throws DataBaseException;

	public abstract Libro buscarPorClave(Integer id) throws DataBaseException;
	
	public abstract void guardarCambios(Libro lib) throws DataBaseException;

	public abstract void borrar(Libro libro) throws DataBaseException;
	
	public abstract List<Libro> buscarPorCategoria(int Cat) throws DataBaseException;
	
	public LibroDAO getLibroDAO();
	public void setLibroDAO(LibroDAO libroDAO);
}
