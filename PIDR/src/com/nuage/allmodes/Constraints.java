package com.nuage.allmodes;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Constraints extends Observable {

	private Object[][] data;
	private String[] columnNames;
	private final JTable table;
	private JScrollPane scrollPane;

	public Constraints(String[] string) {
		data = new Object[5][string.length];

		columnNames = string;

		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		// Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
	}

	@SuppressWarnings("unchecked")
	public <E> LinkedList<E> getColumn(int col) {
		LinkedList<E> column = new LinkedList<>();
		for (int i = 0; i < data.length; i++) {
			column.add((E) data[i][col]);
		}
		return column;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public JTable getTable() {
		return table;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] datas) {
		int length2 = datas[0].length;
		int length1 = datas.length;
		this.data = new Object[length1][length2];
		for (int i = 0; i < length1; i++) {
			for (int j = 0; j < length2; i++) {
				data[i][j] = datas[i][j];
			}
		}
	}

	public void setData(Object dat, int i, int j) {
		data[i][j] = dat;
	}
}
