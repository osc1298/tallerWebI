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
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


@Entity
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	@NotEmpty(message = "Ingrese un nombre de Usuario")
	private String userName;
	@NotEmpty(message = "Ingrese un nombre")
	private String nombre;
	@NotEmpty(message = "Ingrese un apellido")
	private String apellido;
	@NotEmpty(message = "Ingrese un mail valido")
	private String email;
	@NotEmpty(message = "Ingrese una contraseña")
	private String password;
	private int rol;
	private Boolean estado;
	private double valoracion;
	private String pathImagenDeUsuario;
	
	@Transient
	private CommonsMultipartFile fotoImagen;
	
	//Getters y setters
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getPathImagenDeUsuario() {
		return pathImagenDeUsuario;
	}
	public void setPathImagenDeUsuario(String pathImagenDeUsuario) {
		this.pathImagenDeUsuario = pathImagenDeUsuario;
	}
	public CommonsMultipartFile getFotoImagen() {
		return fotoImagen;
	}
	public void setFotoImagen(CommonsMultipartFile fotoImagen) {
		this.fotoImagen = fotoImagen;
	}
	public int getId() {
		return idUsuario;
	}
	public void setId(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public double getValoracion() {
		return valoracion;
	}
	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}

}
