package com.example.taller.vistas;

import com.example.taller.variables.Cliente;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("listarExcel")

public class ListadoExel extends AbstractXlsxView {

	private static final String[] header = { "Id", "Nombre", "Apellido", "Email", "Fecha" };

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<Cliente> listadoClientes = (List<Cliente>) model.get("cliente");

		Sheet sheet = workbook.createSheet("ListadoClientes");
		sheet.setFitToPage(true);

// EstiloNegrillaCentrado
		Font font = workbook.createFont();
		font.setBold(true);
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);

		Row rowHeader = sheet.createRow(0);

		for (int i = 0; i < header.length; i++) {
// sheet.setColumnWidth(i, 3000);
			Cell cell = rowHeader.createCell(i);
			cell.setCellValue(header[i]);
			cell.setCellStyle(style);
		}

		int rowCount = 1;

// EstiloFecha
		CellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat((short) 14);

		for (Cliente cliente : listadoClientes) {
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(cliente.getId());
			row.createCell(1).setCellValue(cliente.getNombre());
			row.createCell(2).setCellValue(cliente.getApellido());
			row.createCell(3).setCellValue(cliente.getMail());

			Cell cell = row.createCell(4);
			cell.setCellValue(cliente.getFecha());
			cell.setCellStyle(dateStyle);

		}

		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(4, 3000);

		for (int i = 1; i <= 3; i++) {
			sheet.autoSizeColumn(i);
		}

		response.setHeader("Content-Disposition", "attachment; filename=listadoExcel.xlsx");

	}

}
