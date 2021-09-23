package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSubasta;

@Service("servicioSubasta")
@Transactional
public class ServicioSubastaImp implements ServicioSubasta {

	private RepositorioSubasta servicioSubastaDao;
	private ServicioUsuario servicioUsuario;

	private ImageUtility utilityImagenes;
	private ServicioEmail servicioEmail;

	@Autowired
	public ServicioSubastaImp(RepositorioSubasta servicioSubastaDao, ServicioUsuario servicioUsuario,
			ImageUtility utilityImagenes, ServicioEmail servicioEmail) {
		this.servicioSubastaDao = servicioSubastaDao;
		this.servicioUsuario = servicioUsuario;
		this.utilityImagenes = utilityImagenes;
		this.servicioEmail = servicioEmail;
	}

	public void crearSubasta(Subasta subasta, int idUsuario) {
		Usuario usuario = servicioUsuario.consultarUsuario(idUsuario);
		Date fechaDeCreacion = new Date();
		subasta.setUsuario(usuario);
		subasta.setPathImagenSubasta(utilityImagenes.guardarImagen(subasta.getFotoSubasta()));
		subasta.setFechaCreacion(fechaDeCreacion);
		subasta.setEstaActiva(true);
		servicioEmail.sendEmailSubastaCreada(subasta.getUsuario().getEmail(), "AVISO - Subasta registrada", subasta);
		servicioSubastaDao.crearSubasta(subasta);
	}

	public void crearSubastaAlfa() {

		Usuario usuario = servicioUsuario.consultarUsuario(1);
		Date fechaDeCreacion = new Date();
		LocalDate fechaFinalizacion = LocalDate.now().plusDays(4);
		Subasta sub1 = new Subasta();
		sub1.setNombreSubasta("Moneda 1810");
		sub1.setDescripcionSubasta("Moneda de coleción datada del 1810");
		sub1.setPujaInicial(2500.0);
		sub1.setUsuario(usuario);
		sub1.setPathImagenSubasta("moneda.jpg");
		sub1.setFechaCreacion(fechaDeCreacion);
		sub1.setFechaFinalizacion(fechaFinalizacion);
		sub1.setEstaActiva(false);
		servicioSubastaDao.crearSubasta(sub1);
	}

	public void crearSubastaBeta() {

		Usuario usuario = servicioUsuario.consultarUsuario(2);
		Date fechaDeCreacion = new Date();
		LocalDate fechaFinalizacion = LocalDate.now().plusDays(6);
		Subasta sub2 = new Subasta();
		sub2.setNombreSubasta("Figura de Acción");
		sub2.setDescripcionSubasta("Figura de Acción antigua o vintage");
		sub2.setPujaInicial(585.0);
		sub2.setUsuario(usuario);
		sub2.setPathImagenSubasta("figura.jpg");
		sub2.setFechaCreacion(fechaDeCreacion);
		sub2.setFechaFinalizacion(fechaFinalizacion);
		sub2.setEstaActiva(true);
		servicioSubastaDao.crearSubasta(sub2);

	}

	public void crearSubastaGama() {

		Usuario usuario = servicioUsuario.consultarUsuario(3);
		Date fechaDeCreacion = new Date();
		LocalDate fechaFinalizacion = LocalDate.now().plusDays(5);
		Subasta sub3 = new Subasta();
		sub3.setNombreSubasta("Lentes de sol RayBan");
		sub3.setDescripcionSubasta("Lentes de sol RayBan usados colección 1999");
		sub3.setPujaInicial(1500.0);
		sub3.setUsuario(usuario);
		sub3.setPathImagenSubasta("lentes.jpg");
		sub3.setFechaCreacion(fechaDeCreacion);
		sub3.setFechaFinalizacion(fechaFinalizacion);
		sub3.setEstaActiva(true);
		servicioSubastaDao.crearSubasta(sub3);

	}
	
	public void crearSubastaDelta() {

		Usuario usuario = servicioUsuario.consultarUsuario(1);
		Date fechaDeCreacion = new Date();
		LocalDate fechaFinalizacion = LocalDate.now().plusDays(3);
		Subasta sub4 = new Subasta();
		sub4.setNombreSubasta("Gameboy Color");
		sub4.setDescripcionSubasta("Gameboy color con 3 juegos y baterias");
		sub4.setPujaInicial(4500.0);
		sub4.setUsuario(usuario);
		sub4.setPathImagenSubasta("gameboy.jpg");
		sub4.setFechaCreacion(fechaDeCreacion);
		sub4.setFechaFinalizacion(fechaFinalizacion);
		sub4.setEstaActiva(true);
		servicioSubastaDao.crearSubasta(sub4);

	}

	public List<Subasta> todasSubastasActivas() {
		return servicioSubastaDao.todasSubastasActivas();
	}

	public List<Subasta> subastasActivasHome() {
		return servicioSubastaDao.subastasActivasParaElHome();
	}

	public Subasta buscarSubastaPorId(int id) {
		return servicioSubastaDao.buscarSubastaPorId(id);
	}

	@Override
	public List<Subasta> consultarHistorialDeSubastasPorIdDeUsuario(int id) {
		Usuario usuario = servicioUsuario.consultarUsuario(id);
		return servicioSubastaDao.buscarSubastasTodasPorIdDeUsuario(usuario);
	}

	@Override
	public void finalizarSubasta(Subasta subasta) {
		subasta.setEstaActiva(false);
		servicioSubastaDao.actualizarSubasta(subasta);
	}
	
	
	public Boolean esValidaLaFechaDeFinalizacion(LocalDate fechaAValidar) {
		if (fechaAValidar.isBefore(LocalDate.now())) {		
			return true;
		}
		return false;
	}
	
	

	// finaliza las subastas al llegar a la fecha de finalizacion verificando si hay
	// ofertas ganadora
	@Override
	public void cronfinalizarSubastas() {
		List<Subasta> subastas = todasSubastasActivas();
		subastas.forEach((subasta) -> {
			if (subasta.estaCumplida()) {
				finalizarSubasta(subasta);
			if (subasta.getOfertaGanadora() != null) {
				;
				servicioEmail.emailSubastaFinalizada(subasta.getUsuario().getEmail(),
						"IMPORTANTE - Subasta finalizada con exito", subasta, subasta.getUsuario());
				servicioEmail.emailSubastaFinalizada(subasta.getOfertaGanadora().getUsuario().getEmail(),
						"IMPORTANTE - Felicidades su oferta ha ganado", subasta, subasta.getOfertaGanadora().getUsuario());
			} else {

				servicioEmail.emailSubastaCaducada(subasta.getUsuario().getEmail(), "IMPORTANTE - Subasta caducada",
						subasta);
			}
			}
		});
	}

}
