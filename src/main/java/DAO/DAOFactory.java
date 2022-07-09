package DAO;

public interface DAOFactory {

	public CategoriaDAO getCategoriaDAO();
	public LibroDAO getLibroDAO();
	public ProveedorDAO getProveedorDAO();
	
}
