package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity( name = "CategoriaProducto")
@Table( name = "CategoriaProducto")
public class CategoriaProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoria;
	private String DescripcionCategoria;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idCategoria")
	private List<Permutacion> permutacion = new ArrayList<>();
	
	
	// Getters and Setters
	
	public String getDescripcionCategoria() {
		return DescripcionCategoria;
	}
	public void setDescripcionCategoria(String descripcionCategoria) {
		DescripcionCategoria = descripcionCategoria;
	}
	public List<Permutacion> getPermutacion() {
		return permutacion;
	}
	public void setPermutacion(List<Permutacion> permutacion) {
		this.permutacion = permutacion;
	}
	
	
}
