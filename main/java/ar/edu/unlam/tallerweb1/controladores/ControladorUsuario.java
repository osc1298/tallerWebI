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

import ar.edu.unla.tallerweb1.utils.GenerarExcelPermutacion;
import ar.edu.unla.tallerweb1.utils.GenerarExcelSubasta;
import ar.edu.unlam.tallerweb1.modelo.OfertaPermutacion;
import ar.edu.unlam.tallerweb1.modelo.OfertaSubasta;
import ar.edu.unlam.tallerweb1.modelo.Permutacion;
import ar.edu.unlam.tallerweb1.modelo.Subasta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ImageUtility;
import ar.edu.unlam.tallerweb1.servicios.ServicioOfertaPermutacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioOfertaSubasta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPermutacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSubasta;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario {


	private ServicioUsuario servicioUsuario;
	private ImageUtility utilityImagenes;
	private ServicioSubasta servicioSubasta;
	private ServicioPermutacion servicioPermutacion;
	private ServicioOfertaPermutacion servicioOfertaPermutacion;
	private ServicioOfertaSubasta servicioOfertaSubasta;
	
	@Autowired
	public ControladorUsuario(ServicioUsuario servicioUsuario, ImageUtility utilityImagenes,
			ServicioSubasta servicioSubasta, ServicioPermutacion servicioPermutacion,
			ServicioOfertaPermutacion servicioOfertaPermutacion, ServicioOfertaSubasta servicioOfertaSubasta)
	{
		this.servicioUsuario = servicioUsuario;
		this.utilityImagenes = utilityImagenes;
		this.servicioSubasta= servicioSubasta;
		this.servicioPermutacion= servicioPermutacion;
		this.servicioOfertaPermutacion = servicioOfertaPermutacion;
		this.servicioOfertaSubasta = servicioOfertaSubasta;
	}

	@RequestMapping(path = "/registro", method = RequestMethod.GET)
	public ModelAndView irARegistro()
	{
		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("registrar", modelo);
	}
	

	
	@RequestMapping(path = "/crear-usuario", method = RequestMethod.POST)
	public ModelAndView crearUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, 
			HttpServletRequest request)
	{
		if (bindingResult.hasErrors())
		{
			return new ModelAndView("/crear-usuario");
		}
		servicioUsuario.crearUsuario(usuario, utilityImagenes.guardarImagen(usuario.getFotoImagen()));
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public ModelAndView irALogin(HttpServletRequest request)
	{
		if(request.getSession().getAttribute("idUsuarioLogueado") == null)
		{
			ModelMap modelo = new ModelMap();
			Usuario usuario = new Usuario();
			modelo.put("usuario", usuario);
			return new ModelAndView("login", modelo);
		}
		else
		{
			return new ModelAndView("redirect:/home");
		}
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request)
	{
		
		ModelMap model = new ModelMap();
		Usuario usuarioBuscado = servicioUsuario.verificarUsuario(usuario);
		if (usuarioBuscado != null)
		{
			request.getSession().setAttribute("idUsuarioLogueado", usuarioBuscado.getId());
			request.getSession().setAttribute("user", usuarioBuscado.getUserName());
			return new ModelAndView("redirect:/home");
		}
		else
		{
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}
	
	@RequestMapping(path = "/cerrar-sesion", method = RequestMethod.GET)
	public ModelAndView cerrarSesion(HttpServletRequest request)
	{
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("idUsuarioLogueado");
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path ="/perfil", method = RequestMethod.GET)
	public ModelAndView perfilDeUser(HttpServletRequest request)
	{
		
		//verifico que el usuario este logeado para ver su perfil
		
		if(request.getSession().getAttribute("idUsuarioLogueado").equals(null))
		{
			return new ModelAndView("redirect:/login");
		}
		else
		{
			int idUsuarioCreador = (int) request.getSession().getAttribute("idUsuarioLogueado");
			ModelMap modelo = new ModelMap();
			Usuario usuario = new Usuario();
			usuario = servicioUsuario.consultarUsuario(idUsuarioCreador);
			modelo.addAttribute("nick", usuario.getUserName());
			modelo.addAttribute("nombre", usuario.getNombre());
			modelo.addAttribute("apellido", usuario.getApellido());
			modelo.addAttribute("imageNombre", usuario.getPathImagenDeUsuario());
			modelo.put("numeroDePermutaciones", servicioUsuario.consultarCantidadDePermutacionesRealizadasPorElUsuario(usuario));
			modelo.put("numeroDeSubastas", servicioUsuario.consultarCantidadDeSubastasRealizadaPorElUsuario(usuario));
			return new ModelAndView("perfil-usuario", modelo);
		}
	}
	
	@RequestMapping(value= "/subastas-realizadas", method=RequestMethod.GET )
	public ModelAndView verHistorialDeSubastas(HttpServletRequest request)
	{
		if(request.getSession().getAttribute("idUsuarioLogueado").equals(null))
		{
			return new ModelAndView("redirect:/login");
		}else
		{
			ModelMap listadoSubastas = new ModelMap();
			List<Subasta> subastas = new ArrayList<Subasta>(); 
			int idDeUsuario = (int) request.getSession().getAttribute("idUsuarioLogueado"); 
			subastas= servicioSubasta.consultarHistorialDeSubastasPorIdDeUsuario(idDeUsuario);
			listadoSubastas.put("listadoSubastas",subastas);
			return new ModelAndView("subastas-realizadas", listadoSubastas);
		}
			
	}
	@RequestMapping(value= "/permutaciones-realizadas", method=RequestMethod.GET )
	public ModelAndView verHistorialDePermutacion(HttpServletRequest request)
	{
		if(request.getSession().getAttribute("idUsuarioLogueado").equals(null))
		{
			return new ModelAndView("redirect:/login");
		}else
		{
			ModelMap listadoPermutaciones = new ModelMap();
			List<Permutacion> permutacion = new ArrayList<Permutacion>(); 
			int idDeUsuario = (int) request.getSession().getAttribute("idUsuarioLogueado"); 
			permutacion= servicioPermutacion.consultarHistorialDePermutacionesPorIdDeUsuario(idDeUsuario);
			listadoPermutaciones.put("listadoPermutacion",permutacion);
			return new ModelAndView("permutaciones-realizadas", listadoPermutaciones);
		}
			
	}
	@RequestMapping(value= "/ofertas-realizadas", method=RequestMethod.GET )
	public ModelAndView verHistorialDeOfertas(HttpServletRequest request)
	{
		if(request.getSession().getAttribute("idUsuarioLogueado").equals(null))
		{
			return new ModelAndView("redirect:/login");
		}else
		{
			ModelMap listadoOfertas = new ModelMap();
			List<OfertaPermutacion> ofertasPermutacion = new ArrayList<OfertaPermutacion>();
			List<OfertaSubasta> ofertasSubasta = new ArrayList<OfertaSubasta>();
			int idDeUsuario = (int) request.getSession().getAttribute("idUsuarioLogueado"); 
			ofertasPermutacion = servicioOfertaPermutacion.consultarHistorialDeOfertasPorIdDeUsuario(idDeUsuario);
			ofertasSubasta = servicioOfertaSubasta.consultarHistorialDeOfertasPorIdDeUsuario(idDeUsuario);
			listadoOfertas.put("listadoOfertasPermutacion", ofertasPermutacion);
			listadoOfertas.put("listadoOfertasSubasta", ofertasSubasta);
			return new ModelAndView("ofertas-realizadas", listadoOfertas);
		}
			
	}
	
	
	       @RequestMapping(value="/excel-permutaciones" ,method=RequestMethod.GET)
	       public ModelAndView getExcel(HttpServletRequest request){
	    	   int idDeUsuario = (int) request.getSession().getAttribute("idUsuarioLogueado");
	              List<Permutacion> permutaciones = new ArrayList<Permutacion>(); 
	              permutaciones = servicioPermutacion.consultarHistorialDePermutacionesPorIdDeUsuario(idDeUsuario);
	              return new ModelAndView(new GenerarExcelPermutacion(), "permutaciones", permutaciones);
	       }
	       
	       @RequestMapping(value="/excel-subastas" ,method=RequestMethod.GET)
	       public ModelAndView getExcelSubastas(HttpServletRequest request){
	    	   int idDeUsuario = (int) request.getSession().getAttribute("idUsuarioLogueado");
	              List<Subasta> subastas = new ArrayList<Subasta>(); 
	              subastas = servicioSubasta.consultarHistorialDeSubastasPorIdDeUsuario(idDeUsuario);
	              return new ModelAndView(new GenerarExcelSubasta(), "subastas", subastas);
	       }
	
	
	@ResponseBody
	@RequestMapping(value = "/getImageUsuario/{imageNombreUsuario}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable String imageNombre, HttpServletRequest request) throws IOException
	{
		String rpath = request.getRealPath("/");
		rpath = rpath + "/img/" + imageNombre + ".jpg";
		Path path = Paths.get(rpath);
		byte[] data = Files.readAllBytes(path);
		return data;
	}
}
