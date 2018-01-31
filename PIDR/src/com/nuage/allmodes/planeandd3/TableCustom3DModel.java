package com.nuage.allmodes.planeandd3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;

import com.nuage.allmodes.Data;
import com.nuage.allmodes.MainWindow;
import com.nuage.allmodes.TableCustomModel;
import com.nuage.allmodes.d3.Point3D;

@SuppressWarnings("serial")
public class TableCustom3DModel extends TableCustomModel {
	private final ArrayList<Point3D> points = new ArrayList<Point3D>();
	private final String[] entetes;
	private LinkedList<Double> x;
	private LinkedList<Double> y;
	private LinkedList<Double> z;

	public TableCustom3DModel(MainWindow mainWindow, String fileName, String[] entetes) {
		super();
		this.entetes = entetes;
		fillPoints(fileName);
		// "C:\\Users\\Romain\\git\\nuageGogdemirMenou\\Excel tests\\test
		// droite.xlsx"
	}

	public TableCustom3DModel(String[] entetes) {
		super();
		this.entetes = entetes;
		/*
		 * LinkedList<Double> point = new LinkedList<Double>(); point.add(1.0);
		 * point.add(0.0); point.add(0.0); point.add(0.0);
		 *
		 * points.add(new Point3D(point));
		 */
	}

	public void setTable(TableCustom3DModel table) {
		x = table.getX();
		y = table.getY();
		z = table.getZ();
		points.clear();
		for (int i = 0; i < x.size(); i++) {
			points.add(new Point3D(i + 0.0, x.get(i), y.get(i), z.get(i)));
		}
	}

	@Override
	public void warnView(MainWindow mainWindow, File file) {
		setTable(mainWindow, file.getPath());
		fireTableChanged(new TableModelEvent(this));
	}

	public void setTable(MainWindow mainWindow, String fileName) {
		setTable(new TableCustom3DModel(mainWindow, fileName, entetes));
		fireTableChanged(new TableModelEvent(this));
	}

	public void fillPoints(String fileName) {
		LinkedList<Double> point = new LinkedList<Double>();
		Datas3DFactory fact = null;
		try {
			fact = new Datas3DFactory(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = fact.getX();
		y = fact.getY();
		z = fact.getZ();
		for (int i = 0; i < x.size(); i++) {
			point.add(i + 1.0);
			point.add(x.get(i));
			point.add(y.get(i));
			point.add(z.get(i));
			points.add(new Point3D(point));
			point = new LinkedList<Double>();
		}

	}

	@Override
	public void fillPoints(LinkedList<Double> x, LinkedList<Double> y, LinkedList<Double> z) {
		eraseTable();
		LinkedList<Double> newPoint = new LinkedList<Double>();
		this.x = new LinkedList<Double>();
		this.y = new LinkedList<Double>();
		this.z = new LinkedList<Double>();
		for (int i = 0; i < x.size(); i++) {
			newPoint.add(i + 1.0);
			this.x.add(x.get(i));
			this.y.add(y.get(i));
			this.z.add(z.get(i));
			newPoint.add(i + 1.0);
			newPoint.add(x.get(i));
			newPoint.add(y.get(i));
			newPoint.add(z.get(i));
			addPoint(new Point3D(newPoint));
			newPoint = new LinkedList<Double>();
		}
	}

	public void eraseTable() {
		points.clear();
	}

	public void removePoint(int begin, int end, int ratio) {
		int nbPointsToRemove = (end - begin) / ratio;
		int firstToRemove = begin + ratio * nbPointsToRemove;
		for (int i = 0; i <= nbPointsToRemove; i++) {
			removePoint(firstToRemove - (i * ratio));
		}
	}

	@Override
	public int getRowCount() {
		return points.size();
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public LinkedList<Double> getX() {
		return x;
	}

	@Override
	public LinkedList<Double> getY() {
		return y;
	}

	@Override
	public LinkedList<Double> getZ() {
		return z;
	}

	public ArrayList<Point3D> getPoints() {
		return points;
	}

	public String[] getEntetes() {
		return entetes;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return points.get(rowIndex).getX();
		case 2:
			return points.get(rowIndex).getY();
		case 3:
			return points.get(rowIndex).getZ();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addPoint(Point3D point) {
		points.add(point);
		fireTableRowsInserted(points.size() - 1, points.size() - 1);
	}

	public void removePoint(int rowIndex) {
		points.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void setValueAt(Object val, int rowIndex, int columnIndex) {
		if (!isCellEditable(rowIndex, columnIndex)) {
			return;
		}
		if (val != null) {
			Point3D point = points.get(rowIndex);
			switch (columnIndex) {
			case 1:
				point.setX((Double) val);
				break;
			case 2:
				point.setY((Double) val);
			case 3:
				point.setZ((Double) val);
			default:
				break;
			}
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int columnIndex) {
		return Double.class;
	}

	public Data toData() {
		Data d = new Data("n°", "x", "y", "z");
		for (Point3D p : points) {
			d.getX().add(p.getX());
			d.getY().add(p.getY());
			d.getZ().add(p.getZ());
		}
		return d;
	}

	@Override
	public boolean isEmpty() {
		if (x == null || y == null || z == null) {
			x = new LinkedList<Double>();
			y = new LinkedList<Double>();
			z = new LinkedList<Double>();
			for (int i = 0; i < points.size(); i++) {
				x.add(points.get(i).getX());
				y.add(points.get(i).getY());
				z.add(points.get(i).getZ());
			}
		}
		return x.isEmpty() && y.isEmpty() && z.isEmpty();
	}

}
