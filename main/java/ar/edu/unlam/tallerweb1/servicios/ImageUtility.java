package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface ImageUtility {

	String guardarImagen(CommonsMultipartFile imagen);
	String obtenerExtension(String nombreArchivo);
}
