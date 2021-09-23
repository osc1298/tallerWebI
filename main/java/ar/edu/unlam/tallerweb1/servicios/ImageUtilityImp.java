package ar.edu.unlam.tallerweb1.servicios;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service("servicioImagenes")
@Transactional
public class ImageUtilityImp implements ImageUtility {

	@Inject
	private ServletContext context;

	public String guardarImagen(CommonsMultipartFile imagen) {

		CommonsMultipartFile foto = imagen;
		String uniqueName = RandomStringUtils.randomAlphanumeric(10) + "."
				+ FilenameUtils.getExtension(foto.getOriginalFilename());
		String contextPath = context.getRealPath(
				"/img" + File.separator + foto.getOriginalFilename().replace(foto.getOriginalFilename(), uniqueName));

		try {
			Files.createDirectories(Paths.get(context.getRealPath("/img").toString()));
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		try {
			byte barr[] = foto.getBytes();
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(contextPath));

			bout.write(barr);
			bout.flush();
			bout.close();
			return uniqueName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String obtenerExtension(String nombreArchivo) {

		return FilenameUtils.getExtension(nombreArchivo);
	}

}
