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
public class Subasta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSubasta;
	
	@NotEmpty(message = "Ingrese un nombre a la Subasta")
	private String nombreSubasta;
	
	@NotEmpty(message = "Ingrese una descripcion")
	private String descripcionSubasta;
	private String pathImagenSubasta;
	
	//@NotNull(message = "La imagen es obligatoria")
	@Transient
	private CommonsMultipartFile fotoSubasta;
	
	@NotNull(message = "Debe tener un monto inicial")
	private Double pujaInicial;
	private Boolean estaActiva;
	private Date fechaCreacion;
	

	@NotNull(message = "La fecha de finalizacion es obligatoria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFinalizacion;
	
	@OneToOne
	@JoinColumn(name = "idOperacionSubasta", referencedColumnName = "idOperacionSubasta")
	private OfertaSubasta ofertaGanadora;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
	private Usuario usuario;

	//Getters y setters 
	
	
	public int getIdSubasta() {
		return idSubasta;
	}


	public void setIdSubasta(int idSubasta) {
		this.idSubasta = idSubasta;
	}


	public String getNombreSubasta() {
		return nombreSubasta;
	}


	public void setNombreSubasta(String nombreSubasta) {
		this.nombreSubasta = nombreSubasta;
	}


	public String getPathImagenSubasta() {
		return pathImagenSubasta;
	}


	public void setPathImagenSubasta(String pathImagenSubasta) {
		this.pathImagenSubasta = pathImagenSubasta;
	}

	public CommonsMultipartFile getFotoSubasta() {
		return fotoSubasta;
	}

	public void setFotoSubasta(CommonsMultipartFile fotoSubasta) {
		this.fotoSubasta = fotoSubasta;
	}

	public String getDescripcionSubasta() {
		return descripcionSubasta;
	}


	public void setDescripcionSubasta(String descripcionSubasta) {
		this.descripcionSubasta = descripcionSubasta;
	}


	public Double getPujaInicial() {
		return pujaInicial;
	}


	public void setPujaInicial(Double pujaInicial) {
		this.pujaInicial = pujaInicial;
	}


	public Boolean getEstaActiva() {
		return estaActiva;
	}


	public void setEstaActiva(Boolean estaActiva) {
		this.estaActiva = estaActiva;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public LocalDate getFechaFinalizacion() {
		return fechaFinalizacion;
	}


	public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public OfertaSubasta getOfertaGanadora() {
		return ofertaGanadora;
	}
	
	public void setOfertaGanadora(OfertaSubasta ofertaGanadora) {
		this.ofertaGanadora = ofertaGanadora;
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean estaCumplida() {
		

		if (LocalDate.now().isEqual(this.fechaFinalizacion) || LocalDate.now().isAfter(this.fechaFinalizacion)) 
		{
			return true;
		}
		return false;
	}
	
}
