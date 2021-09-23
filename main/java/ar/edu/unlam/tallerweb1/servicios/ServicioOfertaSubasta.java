package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.OfertaSubasta;

public interface ServicioOfertaSubasta {
	
	void crearUnaOfertaSubasta(OfertaSubasta oferta, int idUsuario, int idSubasta);
	List<OfertaSubasta> buscarOfertaPorSubasta(int idSubasta);
	double buscarMaximaOfertaPorSubasta(int idSubasta);
	double buscarPujaInicial(int idSubasta);
	List<OfertaSubasta> consultarHistorialDeOfertasPorIdDeUsuario(int idDeUsuario);
}
