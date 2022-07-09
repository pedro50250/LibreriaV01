package DAO;

import java.util.List;
import JavaEEJDBC.DataBaseException;
import beans.Categoria;

public interface CategoriaDAO {

	public abstract List<Categoria> buscarTodos() throws DataBaseException;

	public abstract String getNombreCategoriaById(int idCat) throws DataBaseException;
	
	public abstract Categoria buscarPorClave(Integer idCat) throws DataBaseException;
	
	public abstract void insertar(Categoria cat) throws DataBaseException;
	
	public abstract void guardarCambios(Categoria cat) throws DataBaseException;
	
	public abstract void borrar(Categoria cat) throws DataBaseException;
}
