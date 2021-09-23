package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioPermutacion {

	void crearPermutacion(Permutacion permutacion);
	Permutacion buscarPermutacionPorId(int id);
	List<Permutacion> todasPermutacionesActivas();
	List<Permutacion> permutacionesActivasParaElHome();
	int cantidadPermutacionesActivasPorUser(Usuario usuario);
	void actualizarPermutacion(Permutacion permutacion);
	List<Permutacion> buscarTodasLasPermutacionporIdDeUsuario(Usuario usuario);
	
}
