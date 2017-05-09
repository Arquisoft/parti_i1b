package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel extends RList {

	@Override
	public List<CitizenInfo> read(String path) {
		List<CitizenInfo> info = new ArrayList<CitizenInfo>();

		try {
			FileInputStream file = new FileInputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// if there is any cell missing created with null values
				CitizenInfo citizen = new CitizenInfo(row.getCell(0) != null ? row.getCell(0).toString() : null, // name
						row.getCell(1) != null ? row.getCell(1).toString() : null, // lastname
						row.getCell(2) != null ? row.getCell(2).toString() : null, // birthday
						row.getCell(3) != null ? row.getCell(3).toString() : null, // email
						row.getCell(4) != null ? row.getCell(4).toString() : null, // NIF
						row.getCell(5) != null ? row.getCell(5).toString() : null, // address
						row.getCell(6) != null ? row.getCell(6).toString() : null, // nationality
						row.getCell(7) != null ? row.getCell(7).toString() : null);// pollingstationcode
				info.add(citizen);
			}
			workbook.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("The excel file was not found.");
		} catch (IOException e) {
			System.out.println("There was a problem reading the excel file.");
		}
		return info;
	}

}
