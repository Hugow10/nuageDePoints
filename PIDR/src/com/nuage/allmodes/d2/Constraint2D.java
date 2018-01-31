package com.nuage.allmodes.d2;

import java.util.LinkedList;

public class Constraint2D {

	private LinkedList<Double> constraint = new LinkedList<Double>();

	public Constraint2D(LinkedList<Double> list) {
		constraint = list;
	}

	public Constraint2D(Double d, Double e, Double f, Double g) {
		constraint = new LinkedList<Double>();
		constraint.add(d);
		constraint.add(e);
		constraint.add(f);
		constraint.add(g);
	}

	public Double getNum() {
		return constraint.get(0);
	}

	public void setNum(Double num) {
		constraint.set(0, num);
	}

	public Double getXw() {
		return constraint.get(1);
	}

	public Double getConstraintValue() {
		return constraint.get(2);
	}

	public Double getDerivationOrder() {
		return constraint.get(3);
	}

	public void setXw(Double xw) {
		constraint.set(1, xw);
	}

	public void setConstraintValue(Double constraintValue) {
		constraint.set(2, constraintValue);
	}

	public void setDerivationOrder(Double derivationOrder) {
		constraint.set(3, derivationOrder);
	}

}
