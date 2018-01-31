package com.nuage.allmodes.d3;

import java.util.LinkedList;

public class Constraint3D {

	private LinkedList<Double> constraint = new LinkedList<Double>();

	public Constraint3D(LinkedList<Double> list) {
		constraint = list;
	}

	public Constraint3D(Double num, Double xw, Double yw, Double constraintedValue, Double derivationOrder, Double ux,
			Double uy) {
		constraint = new LinkedList<Double>();
		constraint.add(num);
		constraint.add(xw);
		constraint.add(yw);
		constraint.add(constraintedValue);
		constraint.add(derivationOrder);
		constraint.add(ux);
		constraint.add(uy);
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

	public Double getYw() {
		return constraint.get(2);
	}

	public Double getConstraintValue() {
		return constraint.get(3);
	}

	public Double getDerivationOrder() {
		return constraint.get(4);
	}

	public Double getUx() {
		return constraint.get(5);
	}

	public Double getUy() {
		return constraint.get(6);
	}

	public void setXw(Double xw) {
		constraint.set(1, xw);
	}

	public void setYw(Double yw) {
		constraint.set(2, yw);
	}

	public void setConstraintValue(Double constraintValue) {
		constraint.set(3, constraintValue);
	}

	public void setDerivationOrder(Double derivationOrder) {
		constraint.set(4, derivationOrder);
	}

	public void setUx(Double ux) {
		constraint.set(5, ux);
	}

	public void setUy(Double uy) {
		constraint.set(6, uy);
	}

}
