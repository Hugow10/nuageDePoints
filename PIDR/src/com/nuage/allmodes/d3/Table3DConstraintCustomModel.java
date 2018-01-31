package com.nuage.allmodes.d3;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.nuage.allmodes.MainWindow;

@SuppressWarnings("serial")
public class Table3DConstraintCustomModel extends AbstractTableModel {

	private final LinkedList<Constraint3D> constraints = new LinkedList<Constraint3D>();
	private final String[] entetes;
	private LinkedList<Double> xw;
	private LinkedList<Double> yw;
	private LinkedList<Double> constraintValue;
	private LinkedList<Double> derivationOrder;
	private LinkedList<Double> ux;
	private LinkedList<Double> uy;

	public Table3DConstraintCustomModel(MainWindow mainWindow, String fileName, String[] entetes) {
		super();
		this.entetes = entetes;
		fillConstraints(fileName);
	}

	public Table3DConstraintCustomModel(String[] entetes) {
		super();
		this.entetes = entetes;
		/*
		 * LinkedList<Double> constraint = new LinkedList<Double>();
		 * constraint.add(1.0); constraint.add(0.0); constraint.add(0.0);
		 * constraint.add(0.0); constraint.add(0.0); constraint.add(0.0);
		 * constraint.add(0.0); constraints.add(new Constraint3D(constraint));
		 */
	}

	public void setTable(Table3DConstraintCustomModel table) {
		xw = table.getXw();
		yw = table.getYw();
		constraintValue = table.getConstraintValue();
		derivationOrder = table.getDerivationOrder();
		ux = table.getUx();
		uy = table.getUy();
		constraints.clear();
		for (int i = 0; i < xw.size(); i++) {
			constraints.add(new Constraint3D(i + 0.0, xw.get(i), yw.get(i), constraintValue.get(i),
					derivationOrder.get(i), ux.get(i), uy.get(i)));
		}
	}

	public void warnView(MainWindow mainWindow, File file) {
		setTable(mainWindow, file.getPath());
	}

	// Weird ça non?
	public void setTable(MainWindow mainWindow, String fileName) {
		setTable(new Table3DConstraintCustomModel(mainWindow, fileName, entetes));
		fireTableChanged(new TableModelEvent(this));
	}

	public void fillConstraints(String fileName) {
		LinkedList<Double> constraint = new LinkedList<Double>();
		Constraints3DFactory fact = null;
		try {
			fact = new Constraints3DFactory(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		xw = fact.getXw();
		yw = fact.getYw();
		constraintValue = fact.getConstraintValue();
		derivationOrder = fact.getDerivationOrder();
		ux = fact.getUx();
		uy = fact.getUy();
		for (int i = 0; i < xw.size(); i++) {
			constraint.add(i + 1.0);
			constraint.add(xw.get(i));
			constraint.add(yw.get(i));
			constraint.add(constraintValue.get(i));
			constraint.add(derivationOrder.get(i));
			constraint.add(ux.get(i));
			constraint.add(uy.get(i));
			constraints.add(new Constraint3D(constraint));
			constraint.clear();
		}
	}

	public void fillConstraints(LinkedList<Double> xw, LinkedList<Double> yw, LinkedList<Double> constraintValue,
			LinkedList<Integer> derivationOrderList, LinkedList<Double> ux, LinkedList<Double> uy) {
		eraseTable();
		LinkedList<Double> newPoint = new LinkedList<Double>();
		this.xw = new LinkedList<>();
		this.yw = new LinkedList<>();
		this.constraintValue = new LinkedList<>();
		this.derivationOrder = new LinkedList<>();
		this.ux = new LinkedList<>();
		this.uy = new LinkedList<>();
		for (int i = 0; i < xw.size(); i++) {
			newPoint.add(i + 1.0);
			newPoint.add(xw.get(i));
			this.xw.add(xw.get(i));
			newPoint.add(yw.get(i));
			this.yw.add(yw.get(i));
			newPoint.add(constraintValue.get(i));
			this.constraintValue.add(constraintValue.get(i));
			newPoint.add(derivationOrderList.get(i) + 0.0);
			this.derivationOrder.add(derivationOrderList.get(i) + 0.0);
			newPoint.add(ux.get(i));
			this.ux.add(ux.get(i) + 0.0);
			newPoint.add(uy.get(i));
			this.uy.add(uy.get(i) + 0.0);
			addConstraint(new Constraint3D(newPoint));
			newPoint = new LinkedList<Double>();
		}
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
		return entetes.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public void addConstraint(Constraint3D constraint) {
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
			return constraints.get(rowIndex).getYw();
		case 3:
			return constraints.get(rowIndex).getConstraintValue();
		case 4:
			return constraints.get(rowIndex).getDerivationOrder();
		case 5:
			return constraints.get(rowIndex).getUx();
		case 6:
			return constraints.get(rowIndex).getUy();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public LinkedList<Double> getColumn(int i) {
		switch (i) {
		case 1:
			return xw;
		case 2:
			return yw;
		case 3:
			return constraintValue;
		case 4:
			return derivationOrder;
		case 5:
			return ux;
		case 6:
			return uy;
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
			Constraint3D constraint = constraints.get(rowIndex);
			switch (columnIndex) {
			case 1:
				constraint.setXw((Double) val);
			case 2:
				constraint.setYw((Double) val);
			case 3:
				constraint.setConstraintValue((Double) val);
			case 4:
				constraint.setDerivationOrder((Double) val);
			case 5:
				constraint.setUx((Double) val);
			case 6:
				constraint.setUy((Double) val);
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

	public LinkedList<Constraint3D> getConstraints() {
		return constraints;
	}

	public String[] getEntetes() {
		return entetes;
	}

	public boolean isEmpty() {
		return yw.isEmpty() && ux.isEmpty() && uy.isEmpty() && xw.isEmpty() && constraintValue.isEmpty()
				&& derivationOrder.isEmpty();
	}

	/**
	 * @return the ux
	 */
	public LinkedList<Double> getUx() {
		return ux;
	}

	/**
	 * @param ux
	 *            the ux to set
	 */
	public void setUx(LinkedList<Double> ux) {
		this.ux = ux;
	}

	/**
	 * @return the uy
	 */
	public LinkedList<Double> getUy() {
		return uy;
	}

	/**
	 * @param uy
	 *            the uy to set
	 */
	public void setUy(LinkedList<Double> uy) {
		this.uy = uy;
	}

	/**
	 * @return the yw
	 */
	public LinkedList<Double> getYw() {
		return yw;
	}

}
