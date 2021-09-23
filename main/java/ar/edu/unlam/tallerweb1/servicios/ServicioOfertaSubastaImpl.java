package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.OfertaSubasta;
import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioOfertaSubasta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSubasta;

@Service("servicioOfertaSubasta")
@Transactional
public class ServicioOfertaSubastaImpl implements ServicioOfertaSubasta {

	private RepositorioOfertaSubasta servicioOfertaSubastaDao;
	private RepositorioSubasta servicioSubastaDao;
	private ServicioUsuario servicioUsuario;
	private ServicioSubasta servicioSubasta;

	@Autowired
	public ServicioOfertaSubastaImpl(RepositorioOfertaSubasta servicioOfertaSubastaDao,
			RepositorioSubasta servicioSubastaDao, ServicioUsuario servicioUsuario, ServicioSubasta servicioSubasta) {
		this.servicioOfertaSubastaDao = servicioOfertaSubastaDao;
		this.servicioSubastaDao = servicioSubastaDao;
		this.servicioUsuario = servicioUsuario;
		this.servicioSubasta = servicioSubasta;
	}

	@Override
	public void crearUnaOfertaSubasta(OfertaSubasta oferta, int idUsuario, int idSubasta) {
		Usuario usuario = servicioUsuario.consultarUsuario(idUsuario);
		Subasta subasta = servicioSubasta.buscarSubastaPorId(idSubasta);
		LocalDate fechaDelDia = LocalDate.now();
		oferta.setUsuario(usuario);
		oferta.setSubasta(subasta);
		oferta.setFechaOfertaSubasta(fechaDelDia);
		servicioOfertaSubastaDao.crearOfertaSubasta(oferta);
	}

	@Override
	public List<OfertaSubasta> buscarOfertaPorSubasta(int idSubasta) {
		Subasta subBuscada = servicioSubastaDao.buscarSubastaPorId(idSubasta);
		return servicioOfertaSubastaDao.buscarOfertaPorSubasta(subBuscada);
	}

	@Override
	public double buscarMaximaOfertaPorSubasta(int idSubasta) {
		Subasta subBuscada = servicioSubastaDao.buscarSubastaPorId(idSubasta);
		if (servicioOfertaSubastaDao.buscarMaximaOfertaPorSubasta(subBuscada) == 0) {
			return subBuscada.getPujaInicial();
		} else {
			return servicioOfertaSubastaDao.buscarMaximaOfertaPorSubasta(subBuscada);
		}

	}

	@Override
	public double buscarPujaInicial(int idSubasta) {
		Subasta subBuscada = servicioSubastaDao.buscarSubastaPorId(idSubasta);
		return subBuscada.getPujaInicial();
	}

	@Override
	public List<OfertaSubasta> consultarHistorialDeOfertasPorIdDeUsuario(int idDeUsuario) {
		Usuario usuario = servicioUsuario.consultarUsuario(idDeUsuario);
		return servicioOfertaSubastaDao.buscarOfertaPorIdDeUsuario(usuario);
	}



}
