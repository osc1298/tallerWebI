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

import ar.edu.unlam.tallerweb1.modelo.OfertaSubasta;
import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.servicios.ServicioOfertaSubasta;
import ar.edu.unlam.tallerweb1.servicios.ServicioSubasta;

@Controller
public class ControladorSubasta {

	private ServicioSubasta servicioSubasta;
	private ServicioOfertaSubasta servicioOfertaSubasta;

	@Autowired
	public ControladorSubasta(ServicioSubasta servicioSubasta, ServicioOfertaSubasta servicioOfertaSubasta) {
		this.servicioSubasta = servicioSubasta;
		this.servicioOfertaSubasta = servicioOfertaSubasta;
	}

	@RequestMapping(path = "/listar-subastas", method = RequestMethod.GET)
	public ModelAndView subastas() {
		ModelMap listadoSubastas = new ModelMap();
		List<Subasta> subastasActivas = new ArrayList<Subasta>();
		subastasActivas = servicioSubasta.todasSubastasActivas();
		listadoSubastas.put("listadoSubastas", subastasActivas);
		return new ModelAndView("listar-subastas", listadoSubastas);
	}



	@RequestMapping(path = "/crear-subasta", method = RequestMethod.GET)
	public ModelAndView crearSubasta(HttpServletRequest request) {
		if (request.getSession().getAttribute("idUsuarioLogueado") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			ModelMap modelo = new ModelMap();
			Subasta subasta = new Subasta();
			modelo.put("subasta", subasta);
			return new ModelAndView("crear-subasta", modelo);
		}
	}

	@RequestMapping(path = "/guardar-subasta", method = RequestMethod.POST)
	public ModelAndView guardarSubasta(@Valid @ModelAttribute("subasta") Subasta subasta, BindingResult bindingResult,
			HttpServletRequest request) {
				
		if (bindingResult.hasErrors()) {

			if (subasta.getFechaFinalizacion() != null) {
				if (servicioSubasta.esValidaLaFechaDeFinalizacion(subasta.getFechaFinalizacion())) {
					return new ModelAndView("/crear-subasta", new ModelMap().addAttribute("falloFechaFin", true));
				}
				
			}
			
			
			return new ModelAndView("/crear-subasta");
		}
		servicioSubasta.crearSubasta(subasta, (int)request.getSession().getAttribute("idUsuarioLogueado"));

		ModelMap modelo = new ModelMap();
		modelo.addAttribute("nombre", subasta.getNombreSubasta());
		modelo.addAttribute("imageNombre", subasta.getPathImagenSubasta());
		return new ModelAndView("exito-subasta", modelo);
	}

	@RequestMapping(path = "/detalle-subasta/{id}", method = RequestMethod.GET)
	public ModelAndView detalleSubasta(@PathVariable("id") int id) {

		Subasta subBuscada = servicioSubasta.buscarSubastaPorId(id);

		List<OfertaSubasta> ofertaSubasta = servicioOfertaSubasta.buscarOfertaPorSubasta(id);
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("nombreSubasta", subBuscada.getNombreSubasta());
		modelo.addAttribute("descripcionSubasta", subBuscada.getDescripcionSubasta());
		modelo.addAttribute("fechafinalizacionSubasta", subBuscada.getFechaFinalizacion());
		modelo.addAttribute("idCreadorSub", subBuscada.getUsuario().getId());
		modelo.addAttribute("imageNombreSubasta", subBuscada.getPathImagenSubasta());
		modelo.addAttribute("estado", subBuscada.getEstaActiva());
		if (subBuscada.getOfertaGanadora() != null) {
			modelo.addAttribute("ofertaGanadora", subBuscada.getOfertaGanadora());
		}
		modelo.addAttribute("ofertasSubasta", ofertaSubasta);
		return new ModelAndView("detalle-subasta", modelo);

	}

	// Version funcional
	@ResponseBody
	@RequestMapping(value = "/getImageSubasta/{imageNombreSubasta:.+}", produces = { MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public byte[] getImageSubasta(@PathVariable String imageNombreSubasta, HttpServletRequest request)
			throws IOException {
		String rpath = request.getRealPath("/");
		rpath = rpath + "/img/" + imageNombreSubasta;
		Path path = Paths.get(rpath);
		byte[] data = Files.readAllBytes(path);
		return data;
	}

}
