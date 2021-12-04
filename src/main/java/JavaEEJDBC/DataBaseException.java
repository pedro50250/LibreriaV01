package JavaEEJDBC;


public class DataBaseException extends Exception{

	private static final long serialVersionUID = 1L;

	public String ERROR_400 = "Ocurrio un error local";
	public String ERROR_500 = "Ocurrio un error en el servidor";
	
	public DataBaseException() {
		super();
	}
	
	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataBaseException(String message) {
		super(message);
	}
	
	public DataBaseException( Throwable cause) {
		
		super(cause);
	}
	
	public String getCodigoError()
	{
		if(this.getCause().getClass().equals("java.sql.SQLSyntaxErrorException"))
		{
			return this.ERROR_500;
		}
		else
		{
			return "aun no conocida";
		}
	}
	
}
