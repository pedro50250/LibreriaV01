package beans;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="proveedor")
public class Proveedor {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_proveedor;
	String nombre_proveedor;
	Timestamp fecha_alta;
	String rfc_proveedor;
	String telefono_proveedor;
	
	public Proveedor() {
		
	}

	public Proveedor(String nombre_proveedor, String rfc_proveedor,
			String telefono_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
		this.rfc_proveedor = rfc_proveedor;
		this.telefono_proveedor = telefono_proveedor;
	}

	public int getid_proveedor() {
		return id_proveedor;
	}

	public void setid_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public String getnombre_proveedor() {
		return nombre_proveedor;
	}

	public void setnombre_proveedor(String nombre_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
	}

	public Timestamp getfecha_alta() {
		return fecha_alta;
	}

	public void setfecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public String getrfc_proveedor() {
		return rfc_proveedor;
	}

	public void setrfc_proveedor(String rfc_proveedor) {
		this.rfc_proveedor = rfc_proveedor;
	}

	public String gettelefono_proveedor() {
		return telefono_proveedor;
	}

	public void settelefono_proveedor(String telefono_proveedor) {
		this.telefono_proveedor = telefono_proveedor;
	}
	

}
