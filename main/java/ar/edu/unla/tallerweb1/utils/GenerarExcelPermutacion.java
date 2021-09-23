package ar.edu.unla.tallerweb1.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import ar.edu.unlam.tallerweb1.modelo.Permutacion;

public class GenerarExcelPermutacion extends AbstractXlsView  {

		@Override
		protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			
			 response.setHeader("Content-Disposition", "attachment;filename=\"permutacion.xls\"");
			 List<Permutacion> permutaciones = (List<Permutacion>) model.get("permutaciones");
			 Sheet sheet = workbook.createSheet("Student Data");
			 Row header = sheet.createRow(0);
			
			 header.createCell(0).setCellValue("Nombre");
			 header.createCell(1).setCellValue("Descripcion");
			 header.createCell(2).setCellValue("Fecha de Creacion");
			 header.createCell(3).setCellValue("Nombre de Usuario");
			 header.createCell(4).setCellValue("Nombre de Usuario Ganador");
			 header.createCell(5).setCellValue("Permutacion Ganadora");
			 
			 
			  
			 int rowNum = 1;
			 for(Permutacion lista:permutaciones){
			 Row row = sheet.createRow(rowNum++);
			 
			 row.createCell(0).setCellValue(lista.getDescripcionPermutacion());
			 row.createCell(1).setCellValue(lista.getNombrePermutacion());
			 row.createCell(2).setCellValue(lista.getFechaCreacion());
			 row.createCell(3).setCellValue(lista.getUsuario().getNombre());
			 if(lista.getOfertaGanadora() == null) {
				 row.createCell(4).setCellValue("aun no hay usuario ganador");
				 row.createCell(5).setCellValue("aun no hay permutacion ganadora");
			 }else
			 {
			 
			 row.createCell(4).setCellValue(lista.getOfertaGanadora().getUsuario().getNombre());
			 row.createCell(5).setCellValue(lista.getOfertaGanadora().getDescripcion());
		
			 }
			
		}
}
}