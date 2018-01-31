package com.nuage.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApacheTest {

	private static XSSFWorkbook readFile(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		try {
			return new XSSFWorkbook(fis); // NOSONAR - should not be closed here
		} finally {
			fis.close();
		}
	}

	public static void main(String args[]) throws IOException {
		String path = "C:\\Users\\Miron\\Desktop\\TestApachePoi.xlsx";
		FileInputStream fis = new FileInputStream(path);
		try {
			XSSFWorkbook wb = readFile(path);
			XSSFSheet sheet = wb.getSheetAt(0);
			retrieveDatas(sheet);
		} finally {
			fis.close();
		}
		System.out.println();
		return;
	}

	public static ArrayList<Double> retrieveDatas(XSSFSheet sheet) {
		ArrayList<Double> res = new ArrayList<Double>();
		int rows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i);
			int cells = row.getPhysicalNumberOfCells();
			for (int j = 0; j < cells; j++) {
				XSSFCell cell = row.getCell(j);
				if (isNumericCell(cell)) {
					res.add(cell.getNumericCellValue());
					System.out.println("On a trouvé une cellule de type double en cellule :" + j + " de la colonne :"
							+ i + " dont la valeur est :" + cell.getNumericCellValue());
				}
			}
		}
		return res;
	}

	@SuppressWarnings("deprecation")
	public static boolean isNumericCell(XSSFCell cell) {
		boolean value = false;
		if (cell != null) {
			switch (cell.getCellTypeEnum()) {

			case FORMULA:
				value = false;
				break;

			case NUMERIC:
				value = true;
				break;

			case STRING:
				value = false;
				break;

			case BLANK:
				value = false;
				break;

			case BOOLEAN:
				value = false;
				break;

			case ERROR:
				value = false;
				break;

			default:
				value = false;
			}
		}
		return value;
	}
}
