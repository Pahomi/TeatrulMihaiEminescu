package md.convertit.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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

		for (int i = 0; i < spectacole.size(); i++) {
			row = sheet.createRow(i+1);
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

		FileInputStream inputStream = new FileInputStream(new File(path));
		Workbook workbook = new HSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if (nextRow.getRowNum() != 0) {
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Spectacole spect = new Spectacole();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					 case 0:
					 spect.setName((String) getCellValue(nextCell));
					 break;
					 case 1:
						 Double d = new Double((double) getCellValue(nextCell));
						 int i = d.intValue();
					 spect.setSeatsAvailable(i);
					 break;
					 case 2:
					 spect.setPremiere((boolean) getCellValue(nextCell));
					 break;
					case 3:
						System.out.println(getCellValue(nextCell));
						spect.setData(new Date(Math.round((double) getCellValue(nextCell))));
						break;

					}
				}
				listSpectacole.add(spect);
			}
		}

		inputStream.close();
		return listSpectacole;
	}

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}
}