package com.nuage.allmodes.lineandplane;

import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TransformX extends Observable {

	private JTextField transformX;
	private JLabel transformXLabel;

	public TransformX() {
		transformX = new JTextField(8);
		transformXLabel = new JLabel("X = tx(x)");
	}

	public JTextField getTransformX() {
		return transformX;
	}

	public JLabel getTransformXLabel() {
		return transformXLabel;
	}

	public void setTransformX(String newFunction) {
		transformX.setText(newFunction);
	}

}
