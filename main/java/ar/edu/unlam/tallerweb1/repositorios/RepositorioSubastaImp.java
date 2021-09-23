package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


@Repository("SubastaDao")
@Transactional
public class RepositorioSubastaImp implements RepositorioSubasta{

private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioSubastaImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void crearSubasta(Subasta subasta)
	{
		sessionFactory.getCurrentSession().save(subasta);
	}

	public Subasta buscarSubastaPorId(int id) 
	{
		final Session session = sessionFactory.getCurrentSession();
		return (Subasta) session.createCriteria(Subasta.class)
				.add(Restrictions.eq("idSubasta", id))
				.uniqueResult();
	}
	
	public List<Subasta> todasSubastasActivas()
	{
		final Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(Subasta.class)
				.add(Restrictions.eq("estaActiva", true))
				.list();
	}
	
	public List<Subasta> subastasActivasParaElHome()
	{
		final Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(Subasta.class)
				.add(Restrictions.eq("estaActiva", true))
				.addOrder(Order.desc("idSubasta"))
				.setMaxResults(3)
				.list();
	}

	@Override
	public List<Subasta> buscarSubastasTodasPorIdDeUsuario(Usuario usuario) {
		final Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(Subasta.class).add(Restrictions.eq("usuario", usuario)).list();
	}

	@Override
	public void actualizarSubasta(Subasta subasta) {
		sessionFactory.getCurrentSession().update(subasta);
		
	}
	
}
