package com.nuage.allmodes;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.nuage.allmodes.d3.Point3D;
import com.nuage.allmodes.lineandd2.Datas2DFactory;
import com.nuage.allmodes.lineandd2.Point2D;
import com.nuage.allmodes.lineandd2.TableCustom2DModel;
import com.nuage.allmodes.planeandd3.Datas3DFactory;
import com.nuage.allmodes.planeandd3.TableCustom3DModel;

public class Data extends Observable implements TableModelListener {

	private Object[][] data;
	private JTable table;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private LinkedList<Double> x = new LinkedList<>();
	private LinkedList<Double> y = new LinkedList<>();
	private LinkedList<Double> z = new LinkedList<>();
	private String path;
	private int length1;
	private int length2;
	private int nbDim;
	private DefaultTableModel tableModel;
	private TableCustom3DModel table3DModel;
	private TableCustom2DModel table2DModel;

	public Data(String col1, String col2, String col3, String col4) {
		columnNames = new String[] { col1, col2, col3, col4 };
		data = new Object[Math.max(1, Math.max(x.size(), Math.max(y.size(), z.size())))][4];
		nbDim = 3;
		tableModel = new DefaultTableModel(columnNames, columnNames.length);
		table3DModel = new TableCustom3DModel(columnNames);
		initTable();
	}

	public Data(String col1, String col2, String col3) {
		columnNames = new String[] { col1, col2, col3 };
		data = new Object[Math.max(1, Math.max(x.size(), y.size()))][3];
		nbDim = 2;
		tableModel = new DefaultTableModel(columnNames, columnNames.length);
		table2DModel = new TableCustom2DModel(columnNames);
		initTable();
	}

	public void updateTableContent(String path) {
		this.path = path;
		// TODO : Choisir le type d'initialisation suivant le panneau utilisé
		if (nbDim == 2) {
			init2DData();
			updateTable();
			update2DModel();

		} else {
			init3DData();
			updateTable();
			update3DModel();
		}

		setChanged();
		notifyObservers();
	}

	private void update3DModel() {
		for (int row = 0; row < this.data.length; row++) {
			table3DModel.addPoint(new Point3D(row + 0.0, (Double) data[row][1] + 0.0, (Double) data[row][2] + 0.0,
					(Double) data[row][3]));
		}
	}

	private void update2DModel() {
		for (int row = 0; row < this.data.length; row++) {
			table2DModel.addPoint(new Point2D(row + 0.0, (Double) data[row][1] + 0.0, (Double) data[row][2] + 0.0));
		}
	}

	public void initTable() {
		tableModel.addTableModelListener(this);
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		// Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
		// A voir a quoi ca sert, sinon creer un MouseListener particulier
		scrollPane.addMouseListener(new MouseAdapter() {

		});
		scrollPane.setColumnHeader(new JViewport());
		scrollPane.getColumnHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {

				}
			}
		});
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		DefaultTableModel model = (DefaultTableModel) e.getSource();
		Object newValue = new Object();
		try {
			newValue = model.getValueAt(row, column);
		} catch (ClassCastException exc) {
			newValue = model.getValueAt(row, column);
		}
		System.out.println(column + " colonne");
		System.out.println(row + " ligne");
		for (int i = 0; i < model.getColumnCount(); i++) {
			for (int j = 0; j < model.getRowCount(); j++) {
				System.out.println(model.getValueAt(i, j) + " en" + i + "," + j);
				data[i][j] = model.getValueAt(i, j);
			}
		}
		if (data.length >= column) {
			data[column][row] = newValue;
		}
		updateTable();
	}

	/*
	 * public void sort(ColumnHeader cH){ data.sort(); }
	 */

	/**
	 * Remplir un objet data[][] avec des données dans des linkedlist x et y
	 */
	public void init2DData() {
		fillDatas();
		data = new Object[Math.max(x.size(), y.size())][3];
		for (int i = 0; i < x.size(); i++) {
			data[i][0] = i;
			data[i][1] = x.get(i);
		}
		for (int j = 0; j < y.size(); j++) {
			data[j][2] = y.get(j);
		}
	}

	public void addLine(Data datas) {
		int initialColumn = datas.getData().length;
		int initialRow = datas.getData()[0].length;
		Object[][] dataTemp = new Object[initialColumn][initialRow + 1];
		for (int i = 0; i < initialRow; i++) {
			for (int j = 0; j < initialColumn; j++) {
				dataTemp[j][i] = datas.getData()[j][i];
			}
		}
		data = dataTemp;
		tableModel = new DefaultTableModel(data, initialRow + 1);
		table = new JTable(tableModel);
	}

	/**
	 * Remplir un objet data[][] avec des données dans des linkedlist x,y et z
	 */
	public void init3DData() {
		fillDatas();
		data = new Object[Math.max(z.size(), Math.max(x.size(), y.size()))][4];
		for (int i = 0; i < x.size(); i++) {
			data[i][0] = i;
			data[i][1] = x.get(i);
		}
		for (int j = 0; j < y.size(); j++) {
			data[j][2] = y.get(j);
		}
		for (int k = 0; k < z.size(); k++) {
			data[k][3] = z.get(k);
		}
	}

	public void updateTable() {
		DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);
		defaultTableModel.addTableModelListener(this);
		table.setModel(defaultTableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		printData(data);
	}

	public void setData(Object[][] datas) {
		length2 = datas[0].length;
		length1 = datas.length;
		this.data = new Object[length1][length2];
		for (int i = 0; i < length1; i++) {
			for (int j = 0; j < length2; i++) {
				data[i][j] = datas[i][j];
			}
		}
	}

	public Data getDataObject() {
		return this;
	}

	public void fillDatas() {
		Datas2DFactory d2DF = null;
		Datas3DFactory d3DF = null;
		try {
			if (nbDim == 2) {
				d2DF = new Datas2DFactory(path);
			} else {
				d3DF = new Datas3DFactory(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (nbDim == 2) {
			x = d2DF.getX();
			y = d2DF.getY();
		} else {
			x = d3DF.getX();
			y = d3DF.getY();
			z = d3DF.getZ();
		}
	}

	public void printData(Object[][] o) {
		StringBuilder sb = new StringBuilder();
		Double res = 0.0;
		for (int i = 0; i < o.length; i++) {
			sb.append("\n");
			for (int j = 0; j < o[i].length; j++) {
				sb.append(o[i][j] + "");
				try {
					if (o[i][j] != null) {
						res += (Double) o[i][j];
					}
				} catch (ClassCastException exc) {

				}
			}
		}
		System.out.println(sb);
		System.out.println(res);
	}

	public void setData(Object dat, int i, int j) {
		data[i][j] = dat;
	}

	public Object[][] getData() {
		return data;
	}

	public JTable getTable() {
		return table;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	public static void main(String[] args) {
		Data test = new Data("n°", "xi", "yi");
		System.out.println(test.getData().toString());
	}

	public void warnView(File file) {
		updateTableContent(file.getPath());
		setChanged();
		notifyObservers();
	}

	/**
	 * @return the x
	 */
	public LinkedList<Double> getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public LinkedList<Double> getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public LinkedList<Double> getZ() {
		return z;
	}

	public boolean isEmpty() {
		for (int i = 0; i < length1; i++) { // col
			for (int j = 0; j < length1; j++) { // ligne
				if (("" + (double) data[j][i]).equals("")) {
					return true;
				}
			}
		}
		return false;
	}

}
