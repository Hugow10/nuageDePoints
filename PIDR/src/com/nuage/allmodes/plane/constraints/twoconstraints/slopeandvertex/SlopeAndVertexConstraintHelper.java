package com.nuage.allmodes.plane.constraints.twoconstraints.slopeandvertex;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class SlopeAndVertexConstraintHelper {

	private JTextField v1x;
	private JTextField v1y;
	private JTextField v2x;
	private JTextField v2y;

	private JLabel v1xLab;
	private JLabel v1yLab;
	private JLabel v2xLab;
	private JLabel v2yLab;

	public SlopeAndVertexConstraintHelper(String axev1, String axev2) {
		v1x = new JTextField(8);
		v2x = new JTextField(8);
		v1y = new JTextField(8);
		v2y = new JTextField(8);

		v1xLab = new JLabel("V1x" + axev1 + " = ");
		v1yLab = new JLabel("V1y" + axev1 + " = ");
		v2xLab = new JLabel("V2x" + axev2 + " = ");
		v2yLab = new JLabel("V2y" + axev2 + " = ");
	}

	public JTextField getV1x() {
		return v1x;
	}

	public JTextField getV1y() {
		return v1y;
	}

	public JTextField getV2x() {
		return v2x;
	}

	public JTextField getV2y() {
		return v2y;
	}

	public JLabel getV1xLab() {
		return v1xLab;
	}

	public JLabel getV1yLab() {
		return v1yLab;
	}

	public JLabel getV2xLab() {
		return v2xLab;
	}

	public JLabel getV2yLab() {
		return v2yLab;
	}

}
