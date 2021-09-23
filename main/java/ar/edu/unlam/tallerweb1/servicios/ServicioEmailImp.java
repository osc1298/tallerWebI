package ar.edu.unlam.tallerweb1.servicios;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.OfertaSubasta;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Component
public class ServicioEmailImp implements ServicioEmail {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	// Mensaje sencillo sin plantilla html
	@Async("taskExecutor")
	public void sendSimpleMessage(String to, String subject, String text) {

		SimpleMailMessage message = new SimpleMailMessage();

		try {
			message.setFrom("noreply@google.com");
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

	// Template para utilizar en cada metodo con los valores respectivos al caso
	public void emailTemplate(String to, String subject, String templateName, Map<String,String> values) {

		MimeMessage mimeMessage = emailSender.createMimeMessage();

		try {
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setTo(to);
			Template template = velocityEngine.getTemplate("/emailTemplates/"+templateName+".html");
			VelocityContext velocityContext = new VelocityContext();
			for(String key: values.keySet()) {
				velocityContext.put(key, values.get(key));				
			}

			StringWriter stringWriter = new StringWriter();
			template.merge(velocityContext, stringWriter);

			mimeMessageHelper.setText(stringWriter.toString(), true);
			emailSender.send(mimeMessageHelper.getMimeMessage());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// Metodo para enviar mail en caso de permutacion creada
	@Async("taskExecutor")
	public void sendEmailPermutacionCreada(String to, String subject, Permutacion perm) {
		
		Map<String,String> values = new HashMap<>();
		values.put("titulo", perm.getNombrePermutacion());
		values.put("fechaFinalizacion", perm.getFechaFinalizacion().toString());
		emailTemplate(to, subject, "permutacion-creada", values);

	}
	
	// Metodo para enviar mail en caso de subasta creada
	@Async("taskExecutor")
	public void sendEmailSubastaCreada(String to, String subject, Subasta sub) {
		
		Map<String,String> values = new HashMap<>();
		values.put("titulo", sub.getNombreSubasta());
		values.put("fechaFinalizacion", sub.getFechaFinalizacion().toString());
		values.put("pujaInicial", sub.getPujaInicial().toString());
		emailTemplate(to, subject, "subasta-creada", values);
		
	}
	
	// Metodo para enviar mail en caso de Oferta de subasta
	@Async("taskExecutor")
	public void sendEmailOfertaSubasta(String to, String subject, Subasta sub, Usuario usuario, OfertaSubasta oferta) {
		
		Map<String,String> values = new HashMap<>();
		values.put("titulo", sub.getNombreSubasta());
		values.put("usuario", usuario.getNombre());
		values.put("oferta", oferta.getPrecioOferta().toString());
		emailTemplate(to, subject, "oferta-subasta", values);

	}
	
	// Metodo para enviar mail en caso de Oferta de permutacion
	@Async("taskExecutor")
	public void sendEmailOfertaPermutacion(String to, String subject, Permutacion perm, Usuario usuario, OfertaPermutacion oferta) {
		
		Map<String,String> values = new HashMap<>();
		values.put("titulo", perm.getNombrePermutacion());
		values.put("usuario", usuario.getNombre());
		values.put("oferta", oferta.getDescripcion());
		emailTemplate(to, subject, "oferta-permutacion", values);

	}
	
	// Metodo para enviar mail en caso de subasta finalizada
	@Async("taskExecutor")
	public void emailSubastaFinalizada(String to, String subject, Subasta sub, Usuario usuario) {
		
		Map<String,String> values = new HashMap<>();
		values.put("titulo", sub.getNombreSubasta());
		values.put("fechaCreacion", sub.getFechaCreacion().toString());
		values.put("oferta", sub.getOfertaGanadora().getPrecioOferta().toString());
		values.put("usuarioGanador", sub.getOfertaGanadora().getUsuario().getUserName());
		emailTemplate(to, subject, "subasta-finalizada", values);
	
	}

	//Metodo para enviar mail en caso de permutacion finalizada
	@Async("taskExecutor")
	public void emailPermutacionFinalizada(String to, String subject, Permutacion perm, Usuario usuario) {
		
		Map<String,String> values = new HashMap<>();
		values.put("titulo", perm.getNombrePermutacion());
		values.put("fechaCreacion", perm.getFechaCreacion().toString());
		values.put("oferta", perm.getOfertaGanadora().getDescripcion());
		emailTemplate(to, subject, "permutacion-finalizada", values);
		
	}
	
	// Metodo para enviar mail en caso de subasta caducada
	@Async("taskExecutor")
	public void emailSubastaCaducada(String to, String subject, Subasta sub) {
		
		Map<String,String> values = new HashMap<>();
		values.put("titulo", sub.getNombreSubasta());
		values.put("fechaCreacion", sub.getFechaCreacion().toString());
		emailTemplate(to, subject, "subasta-caducada", values);
	
	}

	//Metodo para enviar mail en caso de permutacion caducada
	@Async("taskExecutor")
	public void emailPermutacionCaducada(String to, String subject, Permutacion perm) {
		
		Map<String,String> values = new HashMap<>();
		values.put("titulo", perm.getNombrePermutacion());
		values.put("fechaCreacion", perm.getFechaCreacion().toString());
		emailTemplate(to, subject, "permutacion-caducada", values);
		
	}

}
