package com.nuage.allmodes.d3;

import java.util.LinkedList;

public class Point3D {

	private LinkedList<Double> point = new LinkedList<Double>();

	public Point3D(LinkedList<Double> list) {
		point = list;
	}

	public Point3D(Double d, Double e, Double f, Double g) {
		point = new LinkedList<Double>();
		point.add(d);
		point.add(e);
		point.add(f);
		point.add(g);
	}

	public Double getNum() {
		return point.get(0);
	}

	public void setNum(Double num) {
		point.set(0, num);
	}

	public Double getX() {
		return point.get(1);
	}

	public Double getY() {
		return point.get(2);
	}

	public Double getZ() {
		return point.get(3);
	}

	public void setX(Double x) {
		point.set(1, x);
	}

	public void setY(Double y) {
		point.set(2, y);
	}

	public void setZ(Double z) {
		point.set(3, z);
	}

}
