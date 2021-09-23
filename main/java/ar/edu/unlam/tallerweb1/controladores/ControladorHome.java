package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.configuration.CronConfiguration;
import ar.edu.unlam.tallerweb1.servicios.ServicioPermutacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSubasta;


@Controller
public class ControladorHome {

	private ServicioSubasta servicioSubasta;
	private ServicioPermutacion servicioPermutacion;

	
	@Autowired
	public ControladorHome(ServicioSubasta servicioSubasta, ServicioPermutacion servicioPermutacion,
			CronConfiguration cronConfigurations) {
		this.servicioSubasta = servicioSubasta;
		this.servicioPermutacion = servicioPermutacion;
	}



	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView home()
	{
		ModelMap listados = new ModelMap();
		listados.put("listadoPermutaciones", servicioPermutacion.permutacionesEnHome());
		listados.put("listadoSubastas", servicioSubasta.subastasActivasHome());
		return new ModelAndView("home", listados);
	}


	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView start(HttpServletRequest request)
	{
			return new ModelAndView("redirect:/home");
	}
	
	
	@RequestMapping(path = "/contacto", method = RequestMethod.GET)
	public ModelAndView contacto()
	{
		return new ModelAndView("contacto");
	}
	
}
