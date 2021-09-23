package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.configuration.CronConfiguration;
import ar.edu.unlam.tallerweb1.servicios.ServicioPermutacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSubasta;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorInicializador {

	private ServicioUsuario servicioUsuario;
	private ServicioSubasta servicioSubasta;
	private ServicioPermutacion servicioPermutacion;
	private CronConfiguration cronConfigurations;

	@Autowired
	public ControladorInicializador(ServicioUsuario servicioUsuario, ServicioSubasta servicioSubasta,
			ServicioPermutacion servicioPermutacion, CronConfiguration cronConfigurations) {
		this.servicioUsuario = servicioUsuario;
		this.servicioSubasta = servicioSubasta;
		this.servicioPermutacion = servicioPermutacion;
		this.cronConfigurations = cronConfigurations;
	}

	@RequestMapping(path = "/auto-registrar", method = RequestMethod.GET)
	public ModelAndView autoRegistrar() {
		
		//Creo tres usuarios basicos
		servicioUsuario.crearUsuarioAlfa();
		servicioUsuario.crearUsuarioBeta();
		servicioUsuario.crearUsuarioGama();
		
		
		//Creo tres permutaciones basicas
		servicioPermutacion.crearPermutacionAlfa();
		servicioPermutacion.crearPermutacionBeta();
		servicioPermutacion.crearPermutacionGama();
		
		//Creo tres subastas basicas
		servicioSubasta.crearSubastaAlfa();
		servicioSubasta.crearSubastaBeta();
		servicioSubasta.crearSubastaGama();
		servicioSubasta.crearSubastaDelta();
		return new ModelAndView("redirect:/home");
	}
	
	
	@RequestMapping(path = "/cron", method = RequestMethod.GET)
	public ModelAndView cron()
	{
		
		//ejecuto los cron de manera manual para verificar su funcionamiento
		cronConfigurations.cronFinalizarPermutaciones();
		cronConfigurations.cronFinalizarSubastas();
		return new ModelAndView("redirect:/home");
	}
}
