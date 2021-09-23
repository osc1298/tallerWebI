package ar.edu.unlam.tallerweb1.controler;

import static org.assertj.core.api.Assertions.assertThat;
import org.apache.struts.mock.MockHttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorOfertar;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class TestMockControladorOferta{

	@InjectMocks
	private ControladorOfertar controladorOferta;
	private Permutacion permutacion = new Permutacion();
	private Usuario user = new Usuario();
	private MockHttpServletRequest request = new MockHttpServletRequest();
	
	@Before
	public void init() {
		permutacion.setIdPermutacion(1);
		user.setIdUsuario(1);
		request.setAttribute("idUsuarioLogueado", user.getIdUsuario());
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@Transactional
	public void verificarIngresoAOfertarPermutacion() {
		ModelAndView model = controladorOferta.ofertarPermutacion(permutacion.getIdPermutacion(), request);
		assertThat(model.getViewName()).isEqualTo("ofertar-permutacion");
	}
	
}
