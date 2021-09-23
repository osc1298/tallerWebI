package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioOfertaPermutacion")
@Transactional
public class RepositorioOfertaPermutacionImpl implements RepositorioOfertaPermutacion{
	
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public void crearOferta(OfertaPermutacion oferta) {
		sessionFactory.getCurrentSession().save(oferta);	
	}

	@Override
	public List<OfertaPermutacion> buscarOfertaPorPermutacion(Permutacion permutacion) {
		final Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(OfertaPermutacion.class)
				.add(Restrictions.eq("permutacion", permutacion))
				.list();
	}
	
	@Override
	public OfertaPermutacion buscarOfertaPorId(int id) {
		final Session session = sessionFactory.getCurrentSession();
		return (OfertaPermutacion) session.createCriteria(OfertaPermutacion.class)
				.add(Restrictions.eq("idOfertaPermutacion", id))
				.uniqueResult();
	}

	@Override
	public void actualizarOFertaPermutacion(OfertaPermutacion oferta) {
		sessionFactory.getCurrentSession().update(oferta);
		
		
	}

	@Override
	public OfertaPermutacion buscarUnaoferta(Permutacion permutacion) {
		final Session session = sessionFactory.getCurrentSession();
		return (OfertaPermutacion) session.createCriteria(OfertaPermutacion.class)
				.add(Restrictions.eq("permutacion", permutacion))
				.uniqueResult();
	}

	@Override
	public List<OfertaPermutacion> buscarOfertaPorIdDeUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(OfertaPermutacion.class)
				.add(Restrictions.eq("usuario", usuario)).list();
				
	}
  

}
