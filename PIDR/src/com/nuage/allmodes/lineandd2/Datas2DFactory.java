package com.nuage.allmodes.lineandd2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datas2DFactory {

	private LinkedList<Double> x = new LinkedList<Double>();
	private LinkedList<Double> y = new LinkedList<Double>();
	private HashMap<Integer, Boolean> dic = new HashMap<Integer, Boolean>();
	private Object[][] datas;

	public Datas2DFactory(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		try {
			XSSFWorkbook wb = readFile(fileName);
			XSSFSheet sheet = wb.getSheetAt(0);
			setX(retrieveDatas(sheet, 0));
			setY(retrieveDatas(sheet, 1));
		} finally {
			fis.close();
		}
		completeDatas();
	}

	private XSSFWorkbook readFile(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		try {
			return new XSSFWorkbook(fis); // NOSONAR - should not be closed here
		} finally {
			fis.close();
		}
	}

	public LinkedList<Double> retrieveDatas(XSSFSheet sheet, int column) {
		LinkedList<Double> res = new LinkedList<Double>();
		int rows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i);
			isAGoodRow(row);
			int cells = row.getPhysicalNumberOfCells();
			if (column < cells) {
				XSSFCell cell = row.getCell(column);
				if (isNumericCell(cell)) {
					res.add(cell.getNumericCellValue());
				}
			}
		}
		return res;
	}

	@SuppressWarnings("deprecation")
	public boolean isNumericCell(XSSFCell cell) {
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

	public void completeDatas() {
		datas = new Object[Math.max(getY().size(), getX().size())][3];
		for (int i = 0; i < getY().size(); i++) {
			System.out.println(datas.length);
			System.out.println(datas[0].length);
			datas[i][2] = getY().get(i);
			datas[i][0] = i;
		}
		for (int j = 0; j < getX().size(); j++) {
			datas[j][1] = getX().get(j);
		}
	}

	public LinkedList<Double> getY() {
		return y;
	}

	public void setY(LinkedList<Double> y) {
		this.y = y;
	}

	public LinkedList<Double> getX() {
		return x;
	}

	public void setX(LinkedList<Double> x) {
		this.x = x;
	}

	private void isAGoodRow(XSSFRow row) {
		Boolean correct = true;
		for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
			if (!isNumericCell(row.getCell(i))) {
				correct = false;
			}
		}
		Integer numRow = row.getRowNum();
		dic.put(numRow, correct);
	}

	public Object[][] getDatas() {
		return datas;
	}

}
