package com.nuage.allmodes.d3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Constraints3DFactory {

	private LinkedList<Double> xw = new LinkedList<Double>();
	private LinkedList<Double> yw = new LinkedList<Double>();
	private LinkedList<Double> constraintValue = new LinkedList<Double>();
	private LinkedList<Double> derivationOrder = new LinkedList<Double>();
	private LinkedList<Double> ux = new LinkedList<Double>();
	private LinkedList<Double> uy = new LinkedList<Double>();
	private HashMap<Integer, Boolean> dic = new HashMap<Integer, Boolean>();
	private Object[][] datas;

	public Constraints3DFactory(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		try {
			XSSFWorkbook wb = readFile(fileName);
			XSSFSheet sheet = wb.getSheetAt(0);
			setXw(retrieveDatas(sheet, 0));
			setYw(retrieveDatas(sheet, 1));
			setConstraintValue(retrieveDatas(sheet, 2));
			setDerivationOrder(retrieveDatas(sheet, 3));
			setUx(retrieveDatas(sheet, 4));
			setUy(retrieveDatas(sheet, 5));
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
		datas = new Object[Math.max(getUx().size(), Math.max(getUy().size(), Math.max(getYw().size(),
				Math.max(getConstraintValue().size(), Math.max(getDerivationOrder().size(), getXw().size())))))][7];
		for (int i = 0; i < getConstraintValue().size(); i++) {
			datas[i][3] = getConstraintValue().get(i);
		}
		for (int j = 0; j < getXw().size(); j++) {
			datas[j][1] = getXw().get(j);
		}
		for (int j = 0; j < getYw().size(); j++) {
			datas[j][2] = getYw().get(j);
		}
		for (int i = 0; i < getDerivationOrder().size(); i++) {
			datas[i][4] = getDerivationOrder().get(i);
		}
		for (int j = 0; j < getUx().size(); j++) {
			datas[j][5] = getUx().get(j);
		}
		for (int j = 0; j < getUy().size(); j++) {
			datas[j][6] = getUy().get(j);
		}
		for (int i = 0; i < datas.length; i++) {
			datas[i][0] = i;
		}
	}

	public LinkedList<Double> getConstraintValue() {
		return constraintValue;
	}

	public void setConstraintValue(LinkedList<Double> cstrtValue) {
		this.constraintValue = cstrtValue;
	}

	public LinkedList<Double> getXw() {
		return xw;
	}

	public void setXw(LinkedList<Double> xw) {
		this.xw = xw;
	}

	public void setYw(LinkedList<Double> yw) {
		this.yw = yw;
	}

	public void setUx(LinkedList<Double> ux) {
		this.ux = ux;
	}

	public void setUy(LinkedList<Double> uy) {
		this.uy = uy;
	}

	public LinkedList<Double> getYw() {
		return yw;
	}

	public LinkedList<Double> getUx() {
		return ux;
	}

	public LinkedList<Double> getUy() {
		return uy;
	}

	public LinkedList<Double> getDerivationOrder() {
		return derivationOrder;
	}

	public void setDerivationOrder(LinkedList<Double> derivOrder) {
		this.derivationOrder = derivOrder;
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
