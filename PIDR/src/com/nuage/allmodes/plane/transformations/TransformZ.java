package com.nuage.allmodes.plane.transformations;

import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TransformZ extends Observable {

	private JTextField transformZ;
	private JLabel transformZLabel;

	public TransformZ() {
		transformZ = new JTextField(8);
		transformZLabel = new JLabel("Z = tz(z)");
	}

	public JTextField getTransformZ() {
		return transformZ;
	}

	public JLabel getTransformZLabel() {
		return transformZLabel;
	}

	public void setTransformZ(String newFunction) {
		transformZ.setText(newFunction);
	}

}