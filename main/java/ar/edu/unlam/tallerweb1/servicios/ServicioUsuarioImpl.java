package ar.edu.unlam.tallerweb1.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;


@Service("servicioLogin")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario servicioUsuarioDao;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario servicioUsuarioDao){
		this.servicioUsuarioDao = servicioUsuarioDao;
	}
	
	public void crearUsuario(Usuario usuario, String imagen){
		usuario.setRol(1);
		usuario.setEstado(false);
		usuario.setPathImagenDeUsuario(imagen);
		servicioUsuarioDao.crearUsuario(usuario);
	}
	
	public Usuario verificarUsuario(Usuario usuario) {
		return servicioUsuarioDao.verificarUsuario(usuario);
	}

	public void crearUsuarioAlfa() {
		Usuario usuarioAlfa = new Usuario();
		usuarioAlfa.setApellido("Rossi");
		usuarioAlfa.setNombre("Leandro");
		usuarioAlfa.setUserName("Alfa");
		usuarioAlfa.setEstado(true);
		usuarioAlfa.setEmail("leandrorossi616@gmail.com");
		usuarioAlfa.setPassword("password");
		usuarioAlfa.setRol(1);
		servicioUsuarioDao.crearUsuario(usuarioAlfa);
	}

	public void crearUsuarioBeta() {
		Usuario usuarioBeta = new Usuario();
		usuarioBeta.setApellido("Curti");
		usuarioBeta.setNombre("Facundo");
		usuarioBeta.setUserName("Beta");
		usuarioBeta.setEstado(true);
		usuarioBeta.setEmail("CurtiFacundoMartin@gmail.com");
		usuarioBeta.setPassword("password");
		usuarioBeta.setRol(1);		
		servicioUsuarioDao.crearUsuario(usuarioBeta);
	}
	
	public void crearUsuarioGama() {
		Usuario usuarioGama = new Usuario();
		usuarioGama.setApellido("Eckerdt");
		usuarioGama.setNombre("Oscar");
		usuarioGama.setUserName("Gama");
		usuarioGama.setEstado(true);
		usuarioGama.setEmail("osc.eck@gmail.com");
		usuarioGama.setPassword("password");
		usuarioGama.setRol(1);		
		servicioUsuarioDao.crearUsuario(usuarioGama);
	}
	

	@Override
	public Usuario consultarUsuario(int idUsuario) {
		return servicioUsuarioDao.consultarUsuario(idUsuario);
	}

	@Override
	public int consultarCantidadDeSubastasRealizadaPorElUsuario(Usuario usuario) {
		return servicioUsuarioDao.consultarCantidadDeSubastasPorIdDeUsuario(usuario);
	}

	@Override
	public int consultarCantidadDePermutacionesRealizadasPorElUsuario(Usuario usuario) {
		return servicioUsuarioDao.consultarCantidadDePermutacionesPorIdDeUsuario(usuario);
	}
	
}
