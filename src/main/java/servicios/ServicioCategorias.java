package servicios;

import java.util.List;

import DAO.CategoriaDAO;
import JavaEEJDBC.DataBaseException;
import beans.Categoria;

public interface ServicioCategorias {
	
	public abstract List<Categoria> buscarTodos() throws DataBaseException;

	public abstract String getNombreCategoriaById(int idCat) throws DataBaseException;
	
	public abstract Categoria buscarPorClave(Integer idCat) throws DataBaseException;
	
	public abstract void insertar(Categoria cat) throws DataBaseException;
	
	public abstract void guardarCambios(Categoria cat) throws DataBaseException;
	
	public abstract void borrar(Categoria cat) throws DataBaseException;
	
	public CategoriaDAO getCategoriaDAO();
	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

}
