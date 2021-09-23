package ar.edu.unla.tallerweb1.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import ar.edu.unlam.tallerweb1.modelo.Subasta;

public class GenerarExcelSubasta extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		 response.setHeader("Content-Disposition", "attachment;filename=\"subastas.xls\"");
		 List<Subasta> subastas = (List<Subasta>) model.get("subastas");
		 Sheet sheet = workbook.createSheet("listado");
		 Row header = sheet.createRow(0);
		 
		
		 header.createCell(0).setCellValue("Nombre");
		 header.createCell(1).setCellValue("Descripcion");
		 header.createCell(2).setCellValue("Fecha de Creacion");
		 header.createCell(3).setCellValue("Nombre de Usuario");
		 header.createCell(4).setCellValue("Nombre de Usuario Ganador");
		 header.createCell(5).setCellValue("dinero ofertado");
	
		 
		 int rowNum = 1;
		 for(Subasta lista:subastas){
		 Row row = sheet.createRow(rowNum++);
		 
		 row.createCell(0).setCellValue(lista.getNombreSubasta());
		 row.createCell(1).setCellValue(lista.getDescripcionSubasta());
		 row.createCell(2).setCellValue(lista.getFechaCreacion());
		 row.createCell(3).setCellValue(lista.getUsuario().getNombre());
		 if(lista.getOfertaGanadora() == null) {
			 row.createCell(4).setCellValue("No hubo ganadores");
			 row.createCell(5).setCellValue("aun no se oferto");
		 }else
		 {
			 row.createCell(4).setCellValue(lista.getOfertaGanadora().getUsuario().getNombre());
			 row.createCell(5).setCellValue(lista.getOfertaGanadora().getPrecioOferta());
		 }
		 }
	}
}
