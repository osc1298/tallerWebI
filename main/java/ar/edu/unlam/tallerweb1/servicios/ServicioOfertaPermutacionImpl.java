package ar.edu.unlam.tallerweb1.servicios;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioOfertaPermutacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPermutacion;

@Service("servicioOfertaPermutacion")
@Transactional
public class ServicioOfertaPermutacionImpl implements ServicioOfertaPermutacion{

	
	private RepositorioOfertaPermutacion servicioOfertaPermutacionDao;
	private RepositorioPermutacion servicioPermutacionDao;
	private ServicioUsuario servicioUsuario;
	private ServicioPermutacion servicioPermutacion;
	private ServicioEmail servicioEmail;
	
	
	@Autowired
	public ServicioOfertaPermutacionImpl(RepositorioOfertaPermutacion servicioOfertaPermutacionDao,
			RepositorioPermutacion servicioPermutacionDao, ServicioUsuario servicioUsuario,
			ServicioPermutacion servicioPermutacion, ServicioEmail servicioEmail) {
		this.servicioOfertaPermutacionDao = servicioOfertaPermutacionDao;
		this.servicioPermutacionDao = servicioPermutacionDao;
		this.servicioUsuario = servicioUsuario;
		this.servicioPermutacion = servicioPermutacion;
		this.servicioEmail = servicioEmail;
	}
	
	@Override
	public void crearUnaOfertaPermutacion(OfertaPermutacion oferta, int idUsuario, int idPermutacion, String imagen) {
		Usuario usuario = servicioUsuario.consultarUsuario(idUsuario);
		Permutacion permutacion = servicioPermutacion.buscarPermutacionPorId(idPermutacion);
		LocalDate fechaDelDia = LocalDate.now();
		oferta.setUsuario(usuario);
		oferta.setPermutacion(permutacion);
		oferta.setImagenOfertaPermutacion(imagen);
		oferta.setFechaOferta(fechaDelDia);
		servicioOfertaPermutacionDao.crearOferta(oferta);
	}





	@Override
	public void aceptarUnaOfertaDePermutacion(int idOferta, int idPermutacion) {
		
		Permutacion permutacionPublicada = new Permutacion();
		OfertaPermutacion ofertaGanadora = new OfertaPermutacion();
		ofertaGanadora=servicioOfertaPermutacionDao.buscarOfertaPorId(idOferta);
		permutacionPublicada= servicioPermutacionDao.buscarPermutacionPorId(idPermutacion);
		permutacionPublicada.setEstadoPublicacion(false);
		permutacionPublicada.setOfertaGanadora(ofertaGanadora);
		servicioPermutacionDao.actualizarPermutacion(permutacionPublicada);
		servicioEmail.emailPermutacionFinalizada(permutacionPublicada.getUsuario().getEmail(), "IMPORTANTE - Permutacion finalizada con exito",
				permutacionPublicada, permutacionPublicada.getUsuario());
		servicioEmail.emailPermutacionFinalizada(permutacionPublicada.getOfertaGanadora().getUsuario().getEmail(), "IMPORTANTE - Han Aceptado su oferta en la Permutacion",
				permutacionPublicada, permutacionPublicada.getOfertaGanadora().getUsuario());
		
	}

	@Override
	public List<OfertaPermutacion> buscarOfertaPorIdPermutacion(int idPermutacion) {
		Permutacion permBuscada = servicioPermutacionDao.buscarPermutacionPorId(idPermutacion);
		return servicioOfertaPermutacionDao.buscarOfertaPorPermutacion(permBuscada);
	}

	@Override
	public OfertaPermutacion buscarOfertaPorId(int id) {
		return servicioOfertaPermutacionDao.buscarOfertaPorId(id);
	}

	@Override
	public OfertaPermutacion buscarUnaOfertaPorIdPermutacion(int id) {
		Permutacion permutacion = servicioPermutacion.buscarPermutacionPorId(id);
		OfertaPermutacion oferta = servicioOfertaPermutacionDao.buscarUnaoferta(permutacion);
		return oferta;
	}

	@Override
	public List<OfertaPermutacion> consultarHistorialDeOfertasPorIdDeUsuario(int idDeUsuario) {
		Usuario usuario = servicioUsuario.consultarUsuario(idDeUsuario);
		return  servicioOfertaPermutacionDao.buscarOfertaPorIdDeUsuario(usuario);
		
	}
	
}
