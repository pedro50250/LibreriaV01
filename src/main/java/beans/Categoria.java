package beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cat;
	private String nom_cat;
	@OneToMany
	@JoinColumn(name ="id_cat")
	private List<Libro> listaDeLibros;
	
	
	public Categoria() {
		
	}

	public Categoria(int id_cat, String nom_cat) {
		this.id_cat = id_cat;
		this.nom_cat = nom_cat;
	}

	public void setid_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public int getid_cat() {
		return id_cat;
	}

	public String getnom_cat() {
		return nom_cat;
	}

	public void setnom_cat(String nom_cat) {
		this.nom_cat = nom_cat;
	}
	
	public List<Libro> getListaDeLibros() {
		return listaDeLibros;
	}

	public void setListaDeLibros(List<Libro> listaDeLibros) {
		this.listaDeLibros = listaDeLibros;
	}

}
