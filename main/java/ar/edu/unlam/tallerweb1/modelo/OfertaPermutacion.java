package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
public class OfertaPermutacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOfertaPermutacion;
	
	private String descripcion;
	private String imagenOfertaPermutacion;
	
	@Transient
	private CommonsMultipartFile fotoOfertaPermutacion;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaOferta;
	
	@ManyToOne
	@JoinColumn(name = "idPermutacion", referencedColumnName = "idPermutacion")
	private Permutacion permutacion;

	@ManyToOne
	@JoinColumn(name = "idUsuario",referencedColumnName = "idUsuario")
	private Usuario usuario;
	
	//Getters y setters 
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Integer getIdOfertaPermutacion() {
		return idOfertaPermutacion;
	}
	public void setIdOfertaPermutacion(Integer idOfertaPermutacion) {
		this.idOfertaPermutacion = idOfertaPermutacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFechaOferta() {
		return fechaOferta;
	}
	public void setFechaOferta(LocalDate fechaOferta) {
		this.fechaOferta = fechaOferta;
	}
	public String getImagenOfertaPermutacion() {
		return imagenOfertaPermutacion;
	}
	public void setImagenOfertaPermutacion(String imagenOfertaPermutacion) {
		this.imagenOfertaPermutacion = imagenOfertaPermutacion;
	}
	public Permutacion getPermutacion() {
		return permutacion;
	}
	public void setPermutacion(Permutacion permutacion) {
		this.permutacion = permutacion;
	}
	
	public CommonsMultipartFile getFotoOfertaPermutacion() {
		return fotoOfertaPermutacion;
	}
	public void setFotoOfertaPermutacion(CommonsMultipartFile fotoOfertaPermutacion) {
		this.fotoOfertaPermutacion = fotoOfertaPermutacion;
	}
}
