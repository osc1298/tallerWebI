package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface ServicioUsuario {
	
	void crearUsuario(Usuario usuario, String imagen);
	Usuario verificarUsuario(Usuario usuario);
	void crearUsuarioAlfa();
	void crearUsuarioBeta();
	void crearUsuarioGama();
	Usuario consultarUsuario(int idUsuario);
	int consultarCantidadDeSubastasRealizadaPorElUsuario(Usuario usuario);
	int consultarCantidadDePermutacionesRealizadasPorElUsuario(Usuario usuario);
}
