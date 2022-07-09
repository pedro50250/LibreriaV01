package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class Action {

	public abstract String ejecutar(HttpServletRequest request, HttpServletResponse response);
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static Action getAccion(String tipo)
	{
		Action action =  null;
		
		try {
			String text = Action.class.getPackage().toString()+"."+tipo+"Action";
			Class c = Class.forName(text.substring(8, text.length()));
			action = (Action) c.newInstance() ;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return action;
	}
	
	public Object getBean(String nombre, HttpServletRequest request)
	{
		WebApplicationContext factoria = WebApplicationContextUtils.
				getRequiredWebApplicationContext(request.getSession().getServletContext());
		return factoria.getBean(nombre);
	}
	
	

}
