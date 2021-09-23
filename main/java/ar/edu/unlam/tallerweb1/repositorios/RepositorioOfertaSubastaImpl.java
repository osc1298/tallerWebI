package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.OfertaSubasta;
import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioOfertaSubasta")
@Transactional
public class RepositorioOfertaSubastaImpl implements RepositorioOfertaSubasta{
	
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public void crearOfertaSubasta(OfertaSubasta oferta) {
		sessionFactory.getCurrentSession().save(oferta);	
	}

	@Override
	public List<OfertaSubasta> buscarOfertaPorSubasta(Subasta subasta) {
		final Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(OfertaSubasta.class)
				.add(Restrictions.eq("subasta", subasta))
				.addOrder(Property.forName("precioOferta").desc())
				.list();
	}

	@Override
	public double buscarMaximaOfertaPorSubasta(Subasta subasta) {
		final Session session = this.sessionFactory.getCurrentSession();
		OfertaSubasta ofertaSubasta = (OfertaSubasta) session.createCriteria(OfertaSubasta.class)
		.add(Restrictions.eq("subasta", subasta))
		.addOrder(Property.forName("precioOferta").desc())
		.setMaxResults(1)
	    .uniqueResult();
		if(ofertaSubasta == null) {
			return 0;
		}else {
			return ofertaSubasta.getPrecioOferta();
		}
	}
	
	@Override
	public List<OfertaSubasta> buscarOfertaPorIdDeUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(OfertaSubasta.class)
				.add(Restrictions.eq("usuario", usuario)).list();
				
	}

}
