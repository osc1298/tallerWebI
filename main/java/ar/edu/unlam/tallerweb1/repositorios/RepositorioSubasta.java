package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioSubasta {

	void crearSubasta(Subasta subasta);
	Subasta buscarSubastaPorId(int id);
	List<Subasta> todasSubastasActivas();
	List<Subasta> subastasActivasParaElHome();
	List<Subasta> buscarSubastasTodasPorIdDeUsuario(Usuario usuario);
	void actualizarSubasta(Subasta subasta);
}
