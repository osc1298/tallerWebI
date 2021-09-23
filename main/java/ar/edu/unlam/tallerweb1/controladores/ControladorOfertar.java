package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.OfertaSubasta;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.servicios.ImageUtility;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioOfertaPermutacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioOfertaSubasta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPermutacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSubasta;

@Controller
public class ControladorOfertar{

	private ServicioOfertaPermutacion servicioOfertaPermutacion;
	private ServicioOfertaSubasta servicioOfertaSubasta;
	private ServicioPermutacion servicioPermutacion;
	private ServicioEmail servicioEmail;
	private ImageUtility utilityImagenes;
	private ServicioSubasta servicioSubasta;

	@Autowired
	public ControladorOfertar(ServicioOfertaPermutacion servicioOfertaPermutacion,
			ServicioOfertaSubasta servicioOfertaSubasta, ServicioPermutacion servicioPermutacion,
			ServicioEmail servicioEmail, ImageUtility utilityImagenes, ServicioSubasta servicioSubasta) {
		this.servicioOfertaPermutacion = servicioOfertaPermutacion;
		this.servicioOfertaSubasta = servicioOfertaSubasta;
		this.servicioPermutacion = servicioPermutacion;
		this.servicioEmail = servicioEmail;
		this.utilityImagenes = utilityImagenes;
		this.servicioSubasta = servicioSubasta;
	}

	@RequestMapping(path="/ofertar-permutacion/{idPermutacion}", method= RequestMethod.GET)
	public ModelAndView ofertarPermutacion(@PathVariable("idPermutacion") int idPermutacion, HttpServletRequest request)
	{
		OfertaPermutacion ofertaPermutacion = new OfertaPermutacion();
		request.getSession().setAttribute("idPermutacion", idPermutacion);
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("ofertaPermutacion", ofertaPermutacion);
		modelo.addAttribute("idPermutacion", idPermutacion);
		request.setAttribute("idPermutacion", idPermutacion );
		return new ModelAndView("ofertar-permutacion", modelo);
	}
	
	@RequestMapping(path="/guardar-oferta-permutacion", method= RequestMethod.POST)
	public ModelAndView guardarOfertaPermutacion(@ModelAttribute("ofertaPermutacion") OfertaPermutacion ofertaPermutacion,
			HttpServletRequest request)
	{
		servicioOfertaPermutacion.crearUnaOfertaPermutacion(ofertaPermutacion,
				(int)request.getSession().getAttribute("idUsuarioLogueado"),
				(int)request.getSession().getAttribute("idPermutacion"),
				utilityImagenes.guardarImagen(ofertaPermutacion.getFotoOfertaPermutacion()));
		servicioEmail.sendEmailOfertaPermutacion(ofertaPermutacion.getPermutacion().getUsuario().getEmail(),
				"AVISO - Nueva oferta", ofertaPermutacion.getPermutacion(), ofertaPermutacion.getUsuario(), ofertaPermutacion);
		request.getSession().removeAttribute("idPermutacion");
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("descripcionOferta", ofertaPermutacion.getDescripcion());
		modelo.addAttribute("imageNombre", ofertaPermutacion.getImagenOfertaPermutacion());
		return new ModelAndView("exito-oferta-permutacion" , modelo);
	}
	
	@RequestMapping(path="/aceptar-oferta/{id}/{idOfertaPermutacion}", method= RequestMethod.GET)
	public ModelAndView aceptarOferta( @PathVariable("id") int idPermutacion, 
			@PathVariable("idOfertaPermutacion") int idOfertaPermutacion)
	{
		servicioOfertaPermutacion.aceptarUnaOfertaDePermutacion(idOfertaPermutacion, idPermutacion);
		Permutacion permutacion = servicioPermutacion.buscarPermutacionPorId(idPermutacion);
		OfertaPermutacion oferta = servicioOfertaPermutacion.buscarOfertaPorId(idOfertaPermutacion);
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("nombre",permutacion.getNombrePermutacion());
		modelo.addAttribute("descripcion",permutacion.getDescripcionPermutacion());
		modelo.addAttribute("fecha",permutacion.getFechaCreacion());
		modelo.addAttribute("id", permutacion.getIdPermutacion());
		modelo.addAttribute("imageNombre", permutacion.getPathImagenPermutacion());
		modelo.addAttribute("ofertaDescripcion", oferta.getDescripcion());
		modelo.addAttribute("ofertaImageNombre", oferta.getImagenOfertaPermutacion());
		modelo.addAttribute("fechaCreacion", oferta.getFechaOferta());
		modelo.addAttribute("nombre", oferta.getUsuario().getNombre());
		modelo.addAttribute("apellido", oferta.getUsuario().getApellido());
		return new ModelAndView("exito-aceptar-oferta", modelo);
		
	}
	
	@RequestMapping(path="/ofertar-subasta/{idSubasta}", method= RequestMethod.GET)
	public ModelAndView ofertarSubasta(@PathVariable("idSubasta") int idSubasta, HttpServletRequest request) 
	{
		OfertaSubasta ofertaSubasta = new OfertaSubasta();

		double maximaOferta = servicioOfertaSubasta.buscarMaximaOfertaPorSubasta(idSubasta);
		request.getSession().setAttribute("idSubasta", idSubasta);
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("ofertaSubasta", ofertaSubasta);
		modelo.addAttribute("maximaOferta", maximaOferta);
		modelo.addAttribute("pujaInicial", servicioSubasta.buscarSubastaPorId(idSubasta).getPujaInicial());
		return new ModelAndView("ofertar-subasta", modelo);
	}
	
	@RequestMapping(path="/guardar-oferta-subasta", method= RequestMethod.POST)
	public ModelAndView guardarOfertaSubasta(@ModelAttribute("ofertaSubasta") OfertaSubasta ofertaSubasta, HttpServletRequest request) 
	{
		servicioOfertaSubasta.crearUnaOfertaSubasta(ofertaSubasta, (int)request.getSession().getAttribute("idUsuarioLogueado"),
				(int)request.getSession().getAttribute("idSubasta"));
		servicioEmail.sendEmailOfertaSubasta(ofertaSubasta.getSubasta().getUsuario().getEmail(),"AVISO - Nueva oferta",
				ofertaSubasta.getSubasta(), ofertaSubasta.getUsuario(), ofertaSubasta);
		request.getSession().removeAttribute("idSubasta");
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("titulo", ofertaSubasta.getSubasta().getNombreSubasta());
		modelo.addAttribute("precio", ofertaSubasta.getPrecioOferta());
		return new ModelAndView("exito-oferta-subasta" , modelo);
	}
	
}
	
	

