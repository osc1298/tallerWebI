package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPermutacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPermutacionImp;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioImpl;



public class TestsRepositoriosMock {

	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session sessionRepositorio;
	
	@Mock
	private RepositorioPermutacion permutacionRepo = new RepositorioPermutacionImp(sessionFactory);
	
	@InjectMocks
	private RepositorioUsuario userRepo = new RepositorioUsuarioImpl(sessionFactory);
	
	
	private Usuario user = new Usuario();
	
	@Before
	public void init() {
		user.setId(1);
		user.setNombre("Leandro");
		user.setApellido("Test");
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void guardarUser() {
		when(this.sessionFactory.getCurrentSession()).thenReturn(this.sessionRepositorio);
		this.userRepo.crearUsuario(user);;
		verify(this.sessionRepositorio,times(1)).save(any(Usuario.class));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void buscarTodasLasPermutacionesActivas() {		
		when(this.sessionFactory.getCurrentSession()).thenReturn(this.sessionRepositorio);
		List<Permutacion> list = this.permutacionRepo.todasPermutacionesActivas();
		assertThat(list.size()).isEqualByComparingTo(0);
	}
	
	
}
