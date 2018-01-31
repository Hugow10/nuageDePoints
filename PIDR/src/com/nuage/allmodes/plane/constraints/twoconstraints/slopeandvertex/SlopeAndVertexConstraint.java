package com.nuage.allmodes.plane.constraints.twoconstraints.slopeandvertex;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SlopeAndVertexConstraint extends Observable {

	private SlopeAndVertexConstraintHelper v1XoYV2XoYModel;
	private SlopeAndVertexConstraintHelper v1ZoXV2ZoXModel;
	private SlopeAndVertexConstraintHelper v1YoZV2YoZModel;
	private SlopeAndVertexConstraintHelper v1XoYV2ZoXModel;
	private SlopeAndVertexConstraintHelper v1XoYV2YoZModel;
	private SlopeAndVertexConstraintHelper v1YoZV2ZoXModel;

	private JPanel v1XoYV2XoY;
	private JPanel v1ZoXV2ZoX;
	private JPanel v1YoZV2YoZ;
	private JPanel v1XoYV2ZoX;
	private JPanel v1XoYV2YoZ;
	private JPanel v1YoZV2ZoX;

	private JPanel constraintChoser;

	private JRadioButton v1XoYV2XoYButton;
	private JRadioButton v1ZoXV2ZoXButton;
	private JRadioButton v1YoZV2YoZButton;
	private JRadioButton v1XoYV2YoZButton;
	private JRadioButton v1XoYV2ZoXButton;
	private JRadioButton v1YoZV2ZoXButton;

	private ButtonGroup bG;

	public SlopeAndVertexConstraint() {
		initField();
	}

	private void initField() {

		v1XoYV2XoYModel = new SlopeAndVertexConstraintHelper("ij", "ij");
		v1ZoXV2ZoXModel = new SlopeAndVertexConstraintHelper("ki", "ki");
		v1YoZV2YoZModel = new SlopeAndVertexConstraintHelper("jk", "jk");
		v1XoYV2ZoXModel = new SlopeAndVertexConstraintHelper("ij", "ki");
		v1XoYV2YoZModel = new SlopeAndVertexConstraintHelper("ij", "jk");
		v1YoZV2ZoXModel = new SlopeAndVertexConstraintHelper("jk", "ki");

		v1XoYV2XoY = new SlopeAndVertexConstraintHelperPanel(v1XoYV2XoYModel);
		v1ZoXV2ZoX = new SlopeAndVertexConstraintHelperPanel(v1ZoXV2ZoXModel);
		v1YoZV2YoZ = new SlopeAndVertexConstraintHelperPanel(v1YoZV2YoZModel);
		v1XoYV2ZoX = new SlopeAndVertexConstraintHelperPanel(v1XoYV2ZoXModel);
		v1XoYV2YoZ = new SlopeAndVertexConstraintHelperPanel(v1XoYV2YoZModel);
		v1YoZV2ZoX = new SlopeAndVertexConstraintHelperPanel(v1YoZV2ZoXModel);

		v1XoYV2XoYButton = new JRadioButton("V1 et V2 dans (xoy)");
		v1ZoXV2ZoXButton = new JRadioButton("V1 et V2 dans (zox)");
		v1YoZV2YoZButton = new JRadioButton("V1 et V2 dans (yoz)");
		v1XoYV2YoZButton = new JRadioButton("V1 (xoy) et V2 (yoz)");
		v1XoYV2ZoXButton = new JRadioButton("V1 (xoy) et V2 (zox)");
		v1YoZV2ZoXButton = new JRadioButton("V1 (yoz) et V2 (zox)");

		bG = new ButtonGroup();
		bG.add(v1XoYV2XoYButton);
		bG.add(v1ZoXV2ZoXButton);
		bG.add(v1YoZV2YoZButton);
		bG.add(v1XoYV2YoZButton);
		bG.add(v1XoYV2ZoXButton);
		bG.add(v1YoZV2ZoXButton);

		v1XoYV2XoYButton.setSelected(true);

		constraintChoser = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		constraintChoser.add(v1XoYV2XoYButton, gbc);
		gbc.gridx = 1;
		constraintChoser.add(v1XoYV2YoZButton, gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		constraintChoser.add(v1ZoXV2ZoXButton, gbc);
		gbc.gridx = 1;
		constraintChoser.add(v1XoYV2ZoXButton, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		constraintChoser.add(v1YoZV2YoZButton, gbc);
		gbc.gridx = 1;
		constraintChoser.add(v1YoZV2ZoXButton, gbc);
	}

	public SlopeAndVertexConstraintHelper getV1XoYV2XoYModel() {
		return v1XoYV2XoYModel;
	}

	public SlopeAndVertexConstraintHelper getV1ZoXV2ZoXModel() {
		return v1ZoXV2ZoXModel;
	}

	public SlopeAndVertexConstraintHelper getV1YoZV2YoZModel() {
		return v1YoZV2YoZModel;
	}

	public SlopeAndVertexConstraintHelper getV1XoYV2ZoXModel() {
		return v1XoYV2ZoXModel;
	}

	public SlopeAndVertexConstraintHelper getV1XoYV2YoZModel() {
		return v1XoYV2YoZModel;
	}

	public SlopeAndVertexConstraintHelper getV1YoZV2ZoXModel() {
		return v1YoZV2ZoXModel;
	}

	public JPanel getV1XoYV2XoY() {
		return v1XoYV2XoY;
	}

	public JPanel getV1ZoXV2ZoX() {
		return v1ZoXV2ZoX;
	}

	public JPanel getV1YoZV2YoZ() {
		return v1YoZV2YoZ;
	}

	public JPanel getV1XoYV2ZoX() {
		return v1XoYV2ZoX;
	}

	public JPanel getV1XoYV2YoZ() {
		return v1XoYV2YoZ;
	}

	public JPanel getV1YoZV2ZoX() {
		return v1YoZV2ZoX;
	}

	public JPanel getConstraintChoser() {
		return constraintChoser;
	}

	public JRadioButton getV1XoYV2XoYButton() {
		return v1XoYV2XoYButton;
	}

	public JRadioButton getV1ZoXV2ZoXButton() {
		return v1ZoXV2ZoXButton;
	}

	public JRadioButton getV1YoZV2YoZButton() {
		return v1YoZV2YoZButton;
	}

	public JRadioButton getV1XoYV2YoZButton() {
		return v1XoYV2YoZButton;
	}

	public JRadioButton getV1XoYV2ZoXButton() {
		return v1XoYV2ZoXButton;
	}

	public JRadioButton getV1YoZV2ZoXButton() {
		return v1YoZV2ZoXButton;
	}

	public ButtonGroup getbG() {
		return bG;
	}

}
