package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Permutacion;

public interface ServicioPermutacion {

	void crearPermutacion(Permutacion permutacion, int idUsuario);

	boolean cantidadMaximoDePermutacion(int idDeUSuario);

	List<Permutacion> permutacionesEnHome();

	List<Permutacion> todasPermutacionesActivas();

	void crearPermutacionAlfa();

	void crearPermutacionBeta();

	void crearPermutacionGama();

	Permutacion buscarPermutacionPorId(int id);

	void finalizar(Permutacion permutacion);

	List<Permutacion> consultarHistorialDePermutacionesPorIdDeUsuario(int idDeUsuario);

	void cronfinalizarPermutaciones();
	
	Boolean esValidaLaFechaDeFinalizacion(LocalDate fechaAValidar);

	
}
