package ar.edu.unlam.tallerweb1.controler;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.apache.struts.mock.MockHttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorPermutacion;
import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ImageUtilityImp;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmailImp;
import ar.edu.unlam.tallerweb1.servicios.ServicioOfertaPermutacionImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioPermutacionImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarioImpl;

public class TestMockConrtoladorPermutacion  {

	@InjectMocks
	private ControladorPermutacion controlador;

	// se generan los Mocks necesarios para los servicios //
	private MockHttpServletRequest request = new MockHttpServletRequest();

	@Mock
	private ServicioUsuarioImpl servicioUsuario;

	@Mock
	private BindingResult bindingResult;

	@Mock
	private ImageUtilityImp servicioImagen;

	@Mock
	private ServicioPermutacionImpl servicioPermutacion;

	@Mock
	private ServicioOfertaPermutacionImpl servicioOferta;
	
	@Mock
	private ServicioEmailImp servicioMail;

	// se crean los objetos para utilizarlos en los test//
	private Usuario user = new Usuario();
	private Usuario usarioInicioDeSession = new Usuario();
	private Permutacion permutacion = new Permutacion();
	private Permutacion permutacionBuscada = new Permutacion();
	private List<OfertaPermutacion> ofertaPermutacion;
	LocalDate fechaFin = LocalDate.parse("2020-11-25");
	
	@Before
	public void init() {
		user.setId(1);
		user.setNombre("Juan");
		user.setApellido("Perez");
		user.setEmail("osc.eck@gmail.com");
		permutacion.setNombrePermutacion("algo");
		permutacion.setIdPermutacion(1);
		permutacion.setDescripcionPermutacion("algo");
		permutacion.setPathImagenPermutacion("nombre.jpg");
		permutacionBuscada.setIdPermutacion(1);
		permutacionBuscada.setFechaFinalizacion(fechaFin);
		permutacionBuscada.setUsuario(user);

		request.getSession().setAttribute("idUsuarioLogueado", user.getId());
		
		MockitoAnnotations.initMocks(this);

		Mockito.when(bindingResult.hasErrors()).thenReturn(false);

		Mockito.when(servicioUsuario.consultarUsuario(user.getId())).thenReturn(usarioInicioDeSession);

		Mockito.when(servicioImagen.obtenerExtension("foto.jpg")).thenReturn("foto");

		servicioPermutacion.crearPermutacion(permutacion, (int) request.getSession().getAttribute("idUsuarioLogueado"));

		Mockito.when(servicioPermutacion.buscarPermutacionPorId(permutacion.getIdPermutacion()))
				.thenReturn(permutacionBuscada);

		Mockito.when(servicioOferta.buscarOfertaPorIdPermutacion(permutacion.getIdPermutacion()))
				.thenReturn(ofertaPermutacion);
		 
		servicioMail.sendEmailPermutacionCreada(user.getEmail(), "AVISO - Permutacion registrada", permutacion);
	}

	// Testeo que si el user esta logueado, ingresa a la vista crear permutacion
	@Test
	@Transactional
	public void pruebaDequeIngresaAlControladorCrearPermutaciones() {

		ModelAndView model = controlador.crearPermutacion(request);
		assertThat(model.getViewName()).isEqualTo("/crear-permutacion");

	}

	@Test
	@Transactional
	public void pruebaDeQueGuardaLaPermutacion() {
		ModelAndView modelo = controlador.guardarPermutacion(permutacion, bindingResult, request);
		assertThat(modelo.getViewName()).isEqualTo("exito-permutacion");

	}

	@Test
	@Transactional
	public void pruebaDequeIngresaAlDetalleDeLaPermutacion() {

		ModelAndView modelo = controlador.detallePermutacion(permutacionBuscada.getIdPermutacion(), request);
		assertThat(modelo.getViewName()).isEqualTo("detalle-permutacion");
	}

}
