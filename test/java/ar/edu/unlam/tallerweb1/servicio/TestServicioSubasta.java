package ar.edu.unlam.tallerweb1.servicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSubasta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSubastaImp;
import ar.edu.unlam.tallerweb1.servicios.ImageUtility;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioSubasta;
import ar.edu.unlam.tallerweb1.servicios.ServicioSubastaImp;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class TestServicioSubasta{

	
	@Mock
	private RepositorioSubasta servicioSubastaDao;
	
	@Mock
	private ServicioUsuario servicioUsuario;
	
	@Mock 
	private ImageUtility utilityImagenes;
	
	@Mock
	private ServicioEmail servicioEmail;
	
	@InjectMocks
	private ServicioSubasta servicioSubasta = new ServicioSubastaImp(servicioSubastaDao, servicioUsuario, utilityImagenes, servicioEmail);
	
	@Mock
	private Subasta subasta = new Subasta();

	// Antes de iniciar los tests mockeo lo necesario.
	@Before
	public void init() {
		subasta.setEstaActiva(true);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFinalizarSubasta() {
		servicioSubasta.finalizarSubasta(subasta);
		assertThat(subasta.getEstaActiva()).isEqualTo(false);
	}
	
	@Test
	public void testTodasLasSubastasActivas() {
		when((List<Subasta>) servicioSubasta.todasSubastasActivas()).thenReturn(new ArrayList<Subasta>(Arrays.asList(subasta))) ;
		List<Subasta> list = servicioSubasta.todasSubastasActivas();
		assertThat(list.size()).isEqualTo(1);
	}
	
}
