package ar.edu.unlam.tallerweb1.servicios;

import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.OfertaSubasta;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioEmail {

	void sendSimpleMessage(String to, String subject, String text);
	void emailTemplate(String to, String subject, String templateName, Map<String,String> values);
	void sendEmailPermutacionCreada(String to, String subject, Permutacion perm);
	void sendEmailSubastaCreada(String to, String subject, Subasta sub);
	void sendEmailOfertaSubasta(String to, String subject, Subasta sub, Usuario usuario, OfertaSubasta oferta);
	void sendEmailOfertaPermutacion(String to, String subject, Permutacion perm, Usuario usuario, OfertaPermutacion oferta);
	void emailSubastaFinalizada(String to, String subject, Subasta sub, Usuario usuario);
	void emailPermutacionFinalizada(String to, String subject, Permutacion perm, Usuario usuario);
	void emailSubastaCaducada(String to, String subject, Subasta sub);
	void emailPermutacionCaducada(String to, String subject, Permutacion perm);

}
