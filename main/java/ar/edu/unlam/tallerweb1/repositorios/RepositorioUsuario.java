package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioUsuario {
	
	void crearUsuario(Usuario usuario);
	Usuario verificarUsuario (Usuario usuario);
	Usuario consultarUsuario(int idUsuario);
	int consultarCantidadDePermutacionesPorIdDeUsuario(Usuario usuario);
	int consultarCantidadDeSubastasPorIdDeUsuario(Usuario usuario);
	

}
