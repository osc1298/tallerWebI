package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.OfertaSubasta;
import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioOfertaSubasta {

	List<OfertaSubasta> buscarOfertaPorSubasta(Subasta subastaBuscada);
	void crearOfertaSubasta(OfertaSubasta oferta);
	double buscarMaximaOfertaPorSubasta(Subasta idSubasta);
	List<OfertaSubasta> buscarOfertaPorIdDeUsuario(Usuario usuario);

}
