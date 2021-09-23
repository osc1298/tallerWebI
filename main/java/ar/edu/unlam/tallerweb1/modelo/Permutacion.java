package ar.edu.unlam.tallerweb1.modelo;



import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
public class Permutacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPermutacion;
	
	@NotEmpty(message = "Ingrese un nombre a la Permutacion")
	private String nombrePermutacion;
	
	@NotEmpty(message = "Ingrese una descripcion")
	private String descripcionPermutacion;
	private String pathImagenPermutacion;
	private Date fechaCreacion;
	

	@NotNull(message = "La fecha de finalizacion es obligatoria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFinalizacion;
	
	private Boolean estadoPublicacion;
	
	//@NotNull(message = "La imagen es obligatoria")
	@Transient
	private CommonsMultipartFile fotoPermutacion;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name = "idOfertaPermutacion", referencedColumnName = "idOfertaPermutacion")
	private OfertaPermutacion ofertaGanadora;
	
	//Getters y setters 
	
	public int getIdPermutacion() {
		return idPermutacion;
	}
	public void setIdPermutacion(int idPermutacion) {
		this.idPermutacion = idPermutacion;
	}
	public String getNombrePermutacion() {
		return nombrePermutacion;
	}
	public void setNombrePermutacion(String nombrePermutacion) {
		this.nombrePermutacion = nombrePermutacion;
	}
	public String getDescripcionPermutacion() {
		return descripcionPermutacion;
	}
	public void setDescripcionPermutacion(String descripcionPermutacion) {
		this.descripcionPermutacion = descripcionPermutacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Boolean getEstadoPublicacion() {
		return estadoPublicacion;
	}
	public void setEstadoPublicacion(Boolean estadoPublicacion) {
		this.estadoPublicacion = estadoPublicacion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	public LocalDate getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	
	public String getPathImagenPermutacion() {
		return pathImagenPermutacion;
	}
	public void setPathImagenPermutacion(String pathImagenPermutacion) {
		this.pathImagenPermutacion = pathImagenPermutacion;
	}
	public CommonsMultipartFile getFotoPermutacion() {
		return fotoPermutacion;
	}
	public void setFotoPermutacion(CommonsMultipartFile fotoPermutacion) {
		this.fotoPermutacion = fotoPermutacion;
	}
	
	public OfertaPermutacion getOfertaGanadora() {
		return ofertaGanadora;
	}
	public void setOfertaGanadora(OfertaPermutacion ofertaGanadora) {
		this.ofertaGanadora = ofertaGanadora;
	}
	
	public boolean estaCumplida() {
		if (LocalDate.now().isEqual(this.fechaFinalizacion) || LocalDate.now().isAfter(this.fechaFinalizacion)) 
		{
			return true;
		}
		return false;
	}

}
