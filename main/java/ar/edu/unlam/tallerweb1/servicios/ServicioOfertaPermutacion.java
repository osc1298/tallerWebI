package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;

public interface ServicioOfertaPermutacion {
	
	List<OfertaPermutacion> buscarOfertaPorIdPermutacion(int idPermutacion);
	void crearUnaOfertaPermutacion(OfertaPermutacion oferta, int idUsuario, int idPermutacion, String imagen);
	void aceptarUnaOfertaDePermutacion(int idOferta, int idPermutacion);
	OfertaPermutacion buscarOfertaPorId(int id);
	OfertaPermutacion buscarUnaOfertaPorIdPermutacion(int id);
	List<OfertaPermutacion> consultarHistorialDeOfertasPorIdDeUsuario(int idDeUsuario);

}
