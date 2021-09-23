package ar.edu.unlam.tallerweb1.controler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorHome;

import ar.edu.unlam.tallerweb1.servicios.ServicioPermutacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSubasta;

public class TestMockControladorHome{

	@InjectMocks
	private ControladorHome controladorHome;

	@Mock
	private ServicioPermutacion servicioPermutacion;

	@Mock
	private ServicioSubasta servicioSubasta;

	// Antes de iniciar los tests mockeo lo necesario.
	@Before
	public void init() {

		controladorHome = new ControladorHome(servicioSubasta, servicioPermutacion, null);
		MockitoAnnotations.initMocks(this);
	}

	// Chequeo que si invoco el metodo home del ControladorHome, me dirija a la
	// vista home
	@Test
	@Transactional
	public void checkDirectHomeController() {

		ModelAndView model = controladorHome.home();
		assertThat(model.getViewName()).isEqualTo("home");

	}

}
