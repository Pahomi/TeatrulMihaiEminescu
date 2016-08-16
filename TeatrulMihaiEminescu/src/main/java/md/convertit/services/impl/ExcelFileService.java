package md.convertit.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import md.convertit.spectacol.domain.Spectacole;
import md.convertit.spectacol.services.FileService;

public class ExcelFileService implements FileService {

	private static final Logger log = Logger.getLogger(ExcelFileService.class.getName());

	@Override
	public void saveAll(List<Spectacole> spectacole, String path) throws Exception {
		File file = new File(path);
		FileOutputStream fos = new FileOutputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Name");
		cell = row.createCell(1);
		cell.setCellValue("Seats Available");
		cell = row.createCell(2);
		cell.setCellValue("Premiere");
		cell = row.createCell(3);
		cell.setCellValue("Date");

		for (int i = 1; i < spectacole.size(); i++) {
			row = sheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(spectacole.get(i).getName());
			cell = row.createCell(1);
			cell.setCellValue(spectacole.get(i).getSeatsAvailable());
			cell = row.createCell(2);
			cell.setCellValue(spectacole.get(1).isPremiere());
			cell = row.createCell(3);
			cell.setCellValue(spectacole.get(i).getData());
		}
		autoSizeColumns(sheet);

		workbook.write(fos);
		fos.close();
		log.log(Level.INFO, "objects saved to: " + file.getAbsolutePath());
	}

	private void autoSizeColumns(HSSFSheet sheet) {
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);

	}

	@Override
	public List<Spectacole> readAll(String path) throws Exception {
		 List<Spectacole> listSpectacole = new ArrayList<>();

		 
		 
		 
		 return listSpectacole;
	}

	


}
