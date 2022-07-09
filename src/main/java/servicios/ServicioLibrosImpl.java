package servicios;

import java.util.List;


import DAO.LibroDAO;
import JavaEEJDBC.DataBaseException;
import beans.Libro;

public class ServicioLibrosImpl implements ServicioLibros{

	private LibroDAO libroDAO = null;
	
	public ServicioLibrosImpl()
	{
		
	}
	@Override
	public void insertar(Libro libro) throws DataBaseException {
		libroDAO.insertar(libro);
	}

	@Override
	public List<Libro> buscarTodos() throws DataBaseException {
		return libroDAO.buscarTodos();
	}

	@Override
	public Libro buscarPorClave(Integer id) throws DataBaseException {
		return this.libroDAO.buscarPorClave(id);
	}

	@Override
	public void guardarCambios(Libro lib) throws DataBaseException {
		 this.libroDAO.guardarCambios(lib);
	}

	@Override
	public void borrar(Libro libro) throws DataBaseException {
		this.libroDAO.borrar(libro);
		
	}

	@Override
	public List<Libro> buscarPorCategoria(int Cat) throws DataBaseException {
		return this.libroDAO.buscarPorCategoria(Cat);
	}
	@Override
	public LibroDAO getLibroDAO() {
		
		return this.libroDAO;
	}
	@Override
	public void setLibroDAO(LibroDAO libroDAO) {
		this.libroDAO = libroDAO;
	}
	
}
