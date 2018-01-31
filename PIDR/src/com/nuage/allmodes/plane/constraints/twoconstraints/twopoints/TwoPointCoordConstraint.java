package com.nuage.allmodes.plane.constraints.twoconstraints.twopoints;

import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TwoPointCoordConstraint extends Observable {

	private JTextField xw1;
	private JTextField yw1;
	private JTextField zw1;
	private JTextField xw2;
	private JTextField yw2;
	private JTextField zw2;

	private JLabel xw1Lab;
	private JLabel yw1Lab;
	private JLabel zw1Lab;
	private JLabel xw2Lab;
	private JLabel yw2Lab;
	private JLabel zw2Lab;

	public TwoPointCoordConstraint() {
		xw1 = new JTextField(8);
		yw1 = new JTextField(8);
		zw1 = new JTextField(8);
		xw2 = new JTextField(8);
		yw2 = new JTextField(8);
		zw2 = new JTextField(8);
		xw1Lab = new JLabel("Xw1 brut = ");
		yw1Lab = new JLabel("Yw1 brut = ");
		zw1Lab = new JLabel("Zw1 brut = ");
		xw2Lab = new JLabel("Xw2 brut = ");
		yw2Lab = new JLabel("Yw2 brut = ");
		zw2Lab = new JLabel("Zw2 brut = ");
	}

	public JTextField getXw1() {
		return xw1;
	}

	public JTextField getYw1() {
		return yw1;
	}

	public JTextField getZw1() {
		return zw1;
	}

	public JTextField getXw2() {
		return xw2;
	}

	public JTextField getYw2() {
		return yw2;
	}

	public JTextField getZw2() {
		return zw2;
	}

	public JLabel getXw1Lab() {
		return xw1Lab;
	}

	public JLabel getYw1Lab() {
		return yw1Lab;
	}

	public JLabel getZw1Lab() {
		return zw1Lab;
	}

	public JLabel getXw2Lab() {
		return xw2Lab;
	}

	public JLabel getYw2Lab() {
		return yw2Lab;
	}

	public JLabel getZw2Lab() {
		return zw2Lab;
	}

}
