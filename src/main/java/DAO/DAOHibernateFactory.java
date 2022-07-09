package DAO;

public class DAOHibernateFactory implements DAOFactory {

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return new CategoriaDAOHIBERNATEImpl();
	}

	@Override
	public LibroDAO getLibroDAO() {
		return new LibroDAOHIBERNATEImpl();
	}

	@Override
	public ProveedorDAO getProveedorDAO() {
		return new ProveedorDAOJPAImpl();
	}

}
