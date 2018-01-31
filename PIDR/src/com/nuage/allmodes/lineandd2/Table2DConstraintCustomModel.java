package com.nuage.allmodes.lineandd2;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.nuage.allmodes.MainWindow;
import com.nuage.allmodes.d2.Constraint2D;
import com.nuage.allmodes.d2.Constraints2DFactory;

@SuppressWarnings("serial")
public class Table2DConstraintCustomModel extends AbstractTableModel {

	private final LinkedList<Constraint2D> constraints = new LinkedList<Constraint2D>();
	private final String[] entetes;
	private LinkedList<Double> xw;
	private LinkedList<Double> constraintValue;
	private LinkedList<Double> derivationOrder;

	public Table2DConstraintCustomModel(MainWindow mainWindow, String fileName, String[] entetes) {
		super();
		this.entetes = entetes;
		fillConstraints(fileName);
	}

	public Table2DConstraintCustomModel(String[] entetes) {
		super();
		this.entetes = entetes;
		/*
		 * LinkedList<Double> constraint = new LinkedList<Double>();
		 * constraint.add(1.0); constraint.add(0.0); constraint.add(0.0);
		 * constraint.add(0.0); constraints.add(new Constraint2D(constraint));
		 */
	}

	public void setTable(Table2DConstraintCustomModel table) {
		xw = table.getXw();
		constraintValue = table.getConstraintValue();
		derivationOrder = table.getDerivationOrder();
		constraints.clear();
		for (int i = 0; i < xw.size(); i++) {
			constraints.add(new Constraint2D(i + 0.0, xw.get(i), constraintValue.get(i), derivationOrder.get(i)));
		}
	}

	public void warnView(MainWindow mainWindow, File file) {
		setTable(mainWindow, file.getPath());
	}

	// Weird ça non?
	public void setTable(MainWindow mainWindow, String fileName) {
		setTable(new Table2DConstraintCustomModel(mainWindow, fileName, entetes));
		fireTableChanged(new TableModelEvent(this));
	}

	public void fillConstraints(String fileName) {
		LinkedList<Double> constraint = new LinkedList<Double>();
		Constraints2DFactory fact = null;
		try {
			fact = new Constraints2DFactory(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		xw = fact.getXw();
		constraintValue = fact.getConstraintValue();
		derivationOrder = fact.getDerivationOrder();
		for (int i = 0; i < xw.size(); i++) {
			constraint.add(i + 1.0);
			constraint.add(xw.get(i));
			constraint.add(constraintValue.get(i));
			constraint.add(derivationOrder.get(i));
			constraints.add(new Constraint2D(constraint));
			constraint = new LinkedList<Double>();
		}
	}

	public void fillConstraints(LinkedList<Double> xw, LinkedList<Double> constraintValue,
			LinkedList<Integer> derivationOrderList) {
		eraseTable();
		this.xw = new LinkedList<Double>();
		this.constraintValue = new LinkedList<Double>();
		derivationOrder = new LinkedList<Double>();
		LinkedList<Double> newPoint = new LinkedList<Double>();
		for (int i = 0; i < xw.size(); i++) {
			newPoint.add(i + 1.0);
			newPoint.add(xw.get(i));
			this.xw.add(xw.get(i));
			newPoint.add(constraintValue.get(i));
			this.constraintValue.add(constraintValue.get(i));
			newPoint.add(derivationOrderList.get(i) + 0.0);
			derivationOrder.add(derivationOrderList.get(i) + 0.0);
			addConstraint(new Constraint2D(newPoint));
			newPoint = new LinkedList<Double>();
		}
		System.out.println(derivationOrderList);
	}

	public void eraseTable() {
		constraints.clear();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return constraints.size();
	}

	public LinkedList<Double> getConstraintValue() {
		return constraintValue;
	}

	public LinkedList<Double> getDerivationOrder() {
		return derivationOrder;
	}

	public LinkedList<Double> getXw() {
		return xw;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public void addConstraint(Constraint2D constraint) {
		constraints.add(constraint);
		fireTableRowsInserted(constraints.size() - 1, constraints.size() - 1);
	}

	public void removeConstraint(int rowIndex) {
		constraints.remove(rowIndex);
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return constraints.get(rowIndex).getXw();
		case 2:
			return constraints.get(rowIndex).getConstraintValue();
		case 3:
			return constraints.get(rowIndex).getDerivationOrder();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public LinkedList<Double> getColumn(int i) {
		switch (i) {
		case 1:
			return xw;
		case 2:
			return constraintValue;
		case 3:
			return derivationOrder;
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object val, int rowIndex, int columnIndex) {
		if (!isCellEditable(rowIndex, columnIndex)) {
			return;
		}
		if (val != null) {
			Constraint2D constraint = constraints.get(rowIndex);
			switch (columnIndex) {
			case 1:
				constraint.setXw((Double) val);
			case 2:
				constraint.setConstraintValue((Double) val);
			case 3:
				constraint.setDerivationOrder((Double) val);
			default:
				break;
			}
		}
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex) {
		return Double.class;
	}

	public LinkedList<Constraint2D> getConstraints() {
		return constraints;
	}

	public String[] getEntetes() {
		return entetes;
	}

	public boolean isEmpty() {
		return xw.isEmpty() && constraintValue.isEmpty() && derivationOrder.isEmpty();
	}

}
