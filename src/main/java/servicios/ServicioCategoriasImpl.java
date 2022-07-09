package servicios;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.CategoriaDAO;
import JavaEEJDBC.DataBaseException;
import beans.Categoria;

public class ServicioCategoriasImpl implements ServicioCategorias{

	private CategoriaDAO categoriaDAO = null;
	
	public ServicioCategoriasImpl()
	{
		/*ClassPathXmlApplicationContext factoria = new ClassPathXmlApplicationContext("classpath*:/contextoAplicacion.xml");
		categoriaDAO = (CategoriaDAO) factoria.getBean("CategoriaJPA");*/
	}
	
	@Override
	public List<Categoria> buscarTodos() throws DataBaseException {
		return categoriaDAO.buscarTodos();
	}

	@Override
	public String getNombreCategoriaById(int idCat) throws DataBaseException {
		return categoriaDAO.getNombreCategoriaById(idCat);
	}

	@Override
	public Categoria buscarPorClave(Integer idCat) throws DataBaseException {
		return categoriaDAO.buscarPorClave(idCat);
	}

	@Override
	public void insertar(Categoria cat) throws DataBaseException {
		categoriaDAO.insertar(cat);
		
	}

	@Override
	public void guardarCambios(Categoria cat) throws DataBaseException {
		categoriaDAO.guardarCambios(cat);
	}

	@Override
	public void borrar(Categoria cat) throws DataBaseException {
		categoriaDAO.borrar(cat);
		
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return this.getCategoriaDAO();
	}

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
		
	}

}
