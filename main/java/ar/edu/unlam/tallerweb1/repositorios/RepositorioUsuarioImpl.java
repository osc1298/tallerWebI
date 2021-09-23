package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.inject.Inject;


@Repository("repositorioUsuario")
@Transactional
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
    
    @Override
    public void crearUsuario(Usuario usuario) {
    	sessionFactory.getCurrentSession().save(usuario);
    }

	@Override
	public Usuario verificarUsuario(Usuario usuario) {

		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}

	@Override
	public Usuario consultarUsuario(int idUsuario) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("idUsuario", idUsuario))
				.uniqueResult();
	}

	@Override
	public int consultarCantidadDePermutacionesPorIdDeUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		 List<Permutacion> permutaciones =  session.createCriteria(Permutacion.class).add(Restrictions.eq("usuario", usuario)).list();
		 return permutaciones.size();
	}

	@Override
	public int consultarCantidadDeSubastasPorIdDeUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		 List<Subasta> subastas =  session.createCriteria(Subasta.class).add(Restrictions.eq("usuario", usuario)).list();
		 return subastas.size();
		
	}

}
