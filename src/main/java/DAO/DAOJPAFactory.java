package DAO;

public class DAOJPAFactory implements DAOFactory{

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return new CategoriaDAOJPAImpl();
	}
	
	@Override
	public LibroDAO getLibroDAO()
	{
		return new LibroDAOJPAImpl();
	}

	@Override
	public ProveedorDAO getProveedorDAO() {
		return new ProveedorDAOJPAImpl();
	}
	

}
