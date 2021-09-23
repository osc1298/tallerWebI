package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioOfertaPermutacion {

	List<OfertaPermutacion> buscarOfertaPorPermutacion(Permutacion permutacion);
	void crearOferta(OfertaPermutacion oferta);
	OfertaPermutacion buscarOfertaPorId(int id);
	void actualizarOFertaPermutacion(OfertaPermutacion oferta);
	OfertaPermutacion buscarUnaoferta(Permutacion permutacion);
	List<OfertaPermutacion> buscarOfertaPorIdDeUsuario(Usuario usuario);

}
