package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("PermutacionDao")
@Transactional
public class RepositorioPermutacionImp implements RepositorioPermutacion {

	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioPermutacionImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void crearPermutacion(Permutacion permutacion)
	{
		sessionFactory.getCurrentSession().save(permutacion);
	}

	public Permutacion buscarPermutacionPorId(int id) 
	{
		final Session session = sessionFactory.getCurrentSession();
		return (Permutacion) session.createCriteria(Permutacion.class)
				.add(Restrictions.eq("idPermutacion", id))
				.uniqueResult();
	}
	
	public List<Permutacion> todasPermutacionesActivas()
	{
		final Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(Permutacion.class)
				.add(Restrictions.eq("estadoPublicacion", true))
				.list();
	}
	
	public List<Permutacion> permutacionesActivasParaElHome()
	{
		final Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(Permutacion.class)
				.add(Restrictions.eq("estadoPublicacion", true))
				.addOrder(Order.desc("idPermutacion"))
				.setMaxResults(3)
				.list();
	}
	
	
	
	public int cantidadPermutacionesActivasPorUser(Usuario usuario)
	{
		final Session session = this.sessionFactory.getCurrentSession();
		List<Permutacion> listadoPermutacion = session.createCriteria(Permutacion.class)
						.add(Restrictions.eq("usuario", usuario))
						.add(Restrictions.eq("estadoPublicacion", true))
						.list();
		return listadoPermutacion.size();
	}

	@Override
	public void actualizarPermutacion(Permutacion permutacion) {
		sessionFactory.getCurrentSession().update(permutacion);
	}

	@Override
	public List<Permutacion> buscarTodasLasPermutacionporIdDeUsuario(Usuario usuario) {
		final Session session = this.sessionFactory.getCurrentSession();
		List<Permutacion> listadoPermutacion = session.createCriteria(Permutacion.class).add(Restrictions.eq("usuario", usuario)).list();
		return listadoPermutacion;
	}
	
}
