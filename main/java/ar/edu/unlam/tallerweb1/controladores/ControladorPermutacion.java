package ar.edu.unlam.tallerweb1.controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioOfertaPermutacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPermutacion;

@Controller
public class ControladorPermutacion {

	private ServicioPermutacion servicioPermutacion;
	private ServicioOfertaPermutacion servicioOfertaPermutacion;

	@Autowired
	public ControladorPermutacion(ServicioPermutacion servicioPermutacion,
			ServicioOfertaPermutacion servicioOfertaPermutacion) {
		this.servicioPermutacion = servicioPermutacion;
		this.servicioOfertaPermutacion = servicioOfertaPermutacion;
	}

	@RequestMapping(path = "/listar-permutaciones", method = RequestMethod.GET)
	public ModelAndView permutaciones() {
		ModelMap listadoPermutaciones = new ModelMap();
		List<Permutacion> permutacionesActivas = new ArrayList<Permutacion>();
		permutacionesActivas = servicioPermutacion.todasPermutacionesActivas();
		listadoPermutaciones.put("listadoPermutaciones", permutacionesActivas);
		return new ModelAndView("listar-permutaciones", listadoPermutaciones);
	}

	@RequestMapping(path = "/crear-permutacion", method = RequestMethod.GET)
	public ModelAndView crearPermutacion(HttpServletRequest request) {
		if (request.getSession().getAttribute("idUsuarioLogueado") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			ModelMap modelo = new ModelMap();
			Permutacion permutacion = new Permutacion();
			modelo.put("permutacion", permutacion);
			int id = (int) request.getSession().getAttribute("idUsuarioLogueado");
			if (servicioPermutacion.cantidadMaximoDePermutacion(id) == true) {
				modelo.addAttribute("limite", true);
			}
			
			return new ModelAndView("/crear-permutacion", modelo);
		}
	}

	@RequestMapping(path = "/guardar-permutacion", method = RequestMethod.POST)
	public ModelAndView guardarPermutacion(@Valid @ModelAttribute("permutacion") Permutacion permutacion,
			BindingResult bindingResult, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			
			if (permutacion.getFechaFinalizacion() != null) {
				if (servicioPermutacion.esValidaLaFechaDeFinalizacion(permutacion.getFechaFinalizacion())) {
					ModelMap modelo = new ModelMap();
					modelo.addAttribute("falloFechaFin", true);
					modelo.addAttribute("limite",true);
					return new ModelAndView("/crear-permutacion", modelo);
				}
				
			}
			
			
			return new ModelAndView("/crear-permutacion", new ModelMap().addAttribute("limite",true));
		}
		
		servicioPermutacion.crearPermutacion(permutacion, (int) request.getSession().getAttribute("idUsuarioLogueado"));
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("nombre", permutacion.getNombrePermutacion());
		modelo.addAttribute("imageNombre", permutacion.getPathImagenPermutacion());

		return new ModelAndView("exito-permutacion", modelo);
	}

	@RequestMapping(path = "/detalle-permutacion/{id}", method = RequestMethod.GET)
	public ModelAndView detallePermutacion(@PathVariable("id") int id, HttpServletRequest request) {

		Permutacion permBuscada = servicioPermutacion.buscarPermutacionPorId(id);
		List<OfertaPermutacion> ofertaPermutacion = servicioOfertaPermutacion.buscarOfertaPorIdPermutacion(id);
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("nombre", permBuscada.getNombrePermutacion());
		modelo.addAttribute("descripcion", permBuscada.getDescripcionPermutacion());
		modelo.addAttribute("fechafinalizacion", permBuscada.getFechaFinalizacion());
		modelo.addAttribute("idCreadorPerm", permBuscada.getUsuario().getId());
		modelo.addAttribute("imageNombre", permBuscada.getPathImagenPermutacion());
		modelo.addAttribute("ofertas", ofertaPermutacion);
		modelo.addAttribute("estado", permBuscada.getEstadoPublicacion());
		return new ModelAndView("detalle-permutacion", modelo);
	}
	
	@RequestMapping(path = "/detalle-oferta-permutacion/{id}", method = RequestMethod.GET)
	public ModelAndView detalleOfertaPermutacion(@PathVariable("id") int id, HttpServletRequest request) {

		
		OfertaPermutacion ofertaPermutacion = servicioOfertaPermutacion.buscarOfertaPorId(id);
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("descripcion", ofertaPermutacion.getDescripcion());
		modelo.addAttribute("idOfertaPermutacion", ofertaPermutacion.getIdOfertaPermutacion());
		modelo.addAttribute("username", ofertaPermutacion.getUsuario().getUserName());
		modelo.addAttribute("idPermutacion", ofertaPermutacion.getPermutacion().getIdPermutacion());
		modelo.addAttribute("idCreadorPerm", ofertaPermutacion.getPermutacion().getUsuario().getId());
		modelo.addAttribute("imageNombre", ofertaPermutacion.getImagenOfertaPermutacion());
		return new ModelAndView("detalle-oferta-permutacion", modelo);
	}
	

	// Version funcional
	@ResponseBody
	@RequestMapping(value = "/getImage/{imageNombre:.+}", produces = { MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public byte[] getImage(@PathVariable String imageNombre, HttpServletRequest request) throws IOException {
		String rpath = request.getRealPath("/");
		rpath = rpath + "/img/" + imageNombre;
		Path path = Paths.get(rpath);
		byte[] data = Files.readAllBytes(path);
		return data;
	}

}
