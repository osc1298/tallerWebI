package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class OfertaSubasta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOperacionSubasta;
	
	private Double precioOferta;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaOfertaSubasta;
	

	@ManyToOne
	@JoinColumn(name = "idSubasta", referencedColumnName = "idSubasta")
	private Subasta subasta;

	@ManyToOne
	@JoinColumn(name = "idUsuario",referencedColumnName = "idUsuario")
	private Usuario usuario;
	
	public Integer getIdOperacionSubasta() {
		return idOperacionSubasta;
	}
	
	public void setIdOperacionSubasta(Integer idOperacionSubasta) {
		this.idOperacionSubasta = idOperacionSubasta;
	}
	
	public Double getPrecioOferta() {
		return precioOferta;
	}
	
	public void setPrecioOferta(Double precioOferta) {
		this.precioOferta = precioOferta;
	}
	
	public LocalDate getFechaOfertaSubasta() {
		return fechaOfertaSubasta;
	}
	
	public void setFechaOfertaSubasta(LocalDate fechaOfertaSubasta) {
		this.fechaOfertaSubasta = fechaOfertaSubasta;
	}
	
	public Subasta getSubasta() {
		return subasta;
	}
	
	public void setSubasta(Subasta subasta) {
		this.subasta = subasta;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



}
