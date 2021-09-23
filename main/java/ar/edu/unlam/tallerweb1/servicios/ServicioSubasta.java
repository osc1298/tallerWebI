package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Subasta;

public interface ServicioSubasta {
	
	void crearSubasta(Subasta subasta, int idUsuario);
	void crearSubastaAlfa();
	void crearSubastaBeta();
	void crearSubastaGama();
	void crearSubastaDelta();
	List<Subasta> subastasActivasHome();
	List<Subasta> todasSubastasActivas();
	Subasta buscarSubastaPorId(int id);
	List<Subasta> consultarHistorialDeSubastasPorIdDeUsuario(int id);
	void finalizarSubasta(Subasta subasta);
	void cronfinalizarSubastas();
	Boolean esValidaLaFechaDeFinalizacion(LocalDate fechaAValidar);
	
}
