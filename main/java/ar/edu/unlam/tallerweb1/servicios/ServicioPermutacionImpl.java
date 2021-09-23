package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPermutacion;

@Service("servicioPermutacion")
@Transactional
public class ServicioPermutacionImpl implements ServicioPermutacion {

	private RepositorioPermutacion servicioPermutacionDao;
	private ServicioUsuario servicioUsuario;
	private ServicioEmail servicioEmail;
	private ImageUtility utilityImagenes;

	@Autowired
	public ServicioPermutacionImpl(RepositorioPermutacion servicioPermutacionDao, ServicioUsuario servicioUsuario,
			ServicioEmail servicioEmail, ImageUtility utilityImagenes) {
		this.servicioPermutacionDao = servicioPermutacionDao;
		this.servicioUsuario = servicioUsuario;
		this.servicioEmail = servicioEmail;
		this.utilityImagenes = utilityImagenes;
	}

	/*
	 * en este metodo intente setear la fecha de creacion usando objeto date y
	 * setearlo en la fecha de creacion, luego llamo al metodo del repositorio y se
	 * deberia crear la permutacion.
	 */
	@Override
	public void crearPermutacion(Permutacion permutacion, int idUsuario) {
		Usuario usuario = servicioUsuario.consultarUsuario(idUsuario);
		Date fechaDeCreacion = new Date();
		permutacion.setUsuario(usuario);
		permutacion.setPathImagenPermutacion(utilityImagenes.guardarImagen(permutacion.getFotoPermutacion()));
		permutacion.setFechaCreacion(fechaDeCreacion);
		permutacion.setEstadoPublicacion(true);
		servicioEmail.sendEmailPermutacionCreada(usuario.getEmail(), "AVISO - Permutacion Registrada", permutacion);
		servicioPermutacionDao.crearPermutacion(permutacion);
	}

	public void crearPermutacionAlfa() {
		Usuario usuario = servicioUsuario.consultarUsuario(1);
		Date fechaDeCreacion = new Date();
		LocalDate fechaFinalizacion = LocalDate.now().plusDays(10);
		Permutacion perm1 = new Permutacion();
		perm1.setNombrePermutacion("Bicicleta Azul");
		perm1.setDescripcionPermutacion("Una bicicleta azul de 10 años de antiguedad con 5 velocidades");
		perm1.setUsuario(usuario);
		perm1.setPathImagenPermutacion("bicicleta.jpg");
		perm1.setFechaCreacion(fechaDeCreacion);
		perm1.setEstadoPublicacion(true);
		perm1.setFechaFinalizacion(fechaFinalizacion);
		servicioPermutacionDao.crearPermutacion(perm1);
	}

	public void crearPermutacionBeta() {
		Usuario usuario = servicioUsuario.consultarUsuario(2);
		Date fechaDeCreacion = new Date();
		LocalDate fechaFinalizacion = LocalDate.now().plusDays(5);
		Permutacion perm2 = new Permutacion();
		perm2.setNombrePermutacion("Computadora AMD");
		perm2.setDescripcionPermutacion("Una computadora modelo 2008 con 16 gb de RAM DDR3");
		perm2.setUsuario(usuario);
		perm2.setPathImagenPermutacion("computadora.jpg");
		perm2.setFechaCreacion(fechaDeCreacion);
		perm2.setEstadoPublicacion(true);
		perm2.setFechaFinalizacion(fechaFinalizacion);
		servicioPermutacionDao.crearPermutacion(perm2);
	}

	public void crearPermutacionGama() {
		Usuario usuario = servicioUsuario.consultarUsuario(3);
		Date fechaDeCreacion = new Date();
		LocalDate fechaFinalizacion = LocalDate.now().plusDays(3);
		Permutacion perm3 = new Permutacion();
		perm3.setNombrePermutacion("Monitor HD 19'");
		perm3.setDescripcionPermutacion("Un Monitor HD 19 Pulgadas con salidas VGA y HDMI");
		perm3.setUsuario(usuario);
		perm3.setPathImagenPermutacion("monitor.jpg");
		perm3.setFechaCreacion(fechaDeCreacion);
		perm3.setEstadoPublicacion(true);
		perm3.setFechaFinalizacion(fechaFinalizacion);
		servicioPermutacionDao.crearPermutacion(perm3);
	}

	/*
	 * en este metodo se hace una busqueda por el id, recorrerlo y despues ver el
	 * estado que posee la publicacion de usuario, si el valor es true entonces que
	 * incremente 1 hasta que el maximo sea tres y terminan retornando un true pero
	 * si supera ese valor retorna un false
	 */
	@Override
	public boolean cantidadMaximoDePermutacion(int idDeUsuario) {
		Usuario usuario = new Usuario();
		usuario.setId(idDeUsuario);
		int cantidadPermutacion = servicioPermutacionDao.cantidadPermutacionesActivasPorUser(usuario);
		if (cantidadPermutacion <= 5) {
			return true;
		} else {
			return false;
		}
	}

	public List<Permutacion> todasPermutacionesActivas() {
		return servicioPermutacionDao.todasPermutacionesActivas();
	}

	public List<Permutacion> permutacionesEnHome() {
		return servicioPermutacionDao.permutacionesActivasParaElHome();
	}

	public Permutacion buscarPermutacionPorId(int id) {
		return servicioPermutacionDao.buscarPermutacionPorId(id);
	}

	public void finalizar(Permutacion permutacion) {
		permutacion.setEstadoPublicacion(false);
		servicioPermutacionDao.actualizarPermutacion(permutacion);
	}

	@Override
	public List<Permutacion> consultarHistorialDePermutacionesPorIdDeUsuario(int idDeUsuario) {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(idDeUsuario);
		return servicioPermutacionDao.buscarTodasLasPermutacionporIdDeUsuario(usuario);

	}
	
	public Boolean esValidaLaFechaDeFinalizacion(LocalDate fechaAValidar) {
		if (fechaAValidar.isBefore(LocalDate.now())) {		
			return true;
		}
		return false;
	}
	
	

	// finaliza las permutaciones al llegar a la fecha de finalizacion
	@Override
	public void cronfinalizarPermutaciones() {
		List<Permutacion> permutaciones = todasPermutacionesActivas();
		permutaciones.forEach((permutacion) -> {
			if (permutacion.estaCumplida()) {
				finalizar(permutacion);
			servicioEmail.emailPermutacionCaducada(permutacion.getUsuario().getEmail(),
					"IMPORTANTE - Permutacion caducada", permutacion);
			}
		});
	}

}
