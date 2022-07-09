package paquetePruebas;

import java.io.IOException;
import java.util.Properties;

public class MensajeFactory {

	
	public static Mensaje getMensaje() {
		Properties propiedad = new Properties();
		Mensaje mensaje = null;
		
		try {
			propiedad.load(MensajeFactory.class.getResourceAsStream("mensaje.properties"));
			String tipo = propiedad.getProperty("tipo");
			if(tipo.equals("html"))
			{
				mensaje = new MensajeHTML();
			}
			else
			{
				mensaje = new MensajePlano();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mensaje;
	}

}
