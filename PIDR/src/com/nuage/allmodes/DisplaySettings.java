package com.nuage.allmodes;

import java.util.Observable;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class DisplaySettings extends Observable {

	private JCheckBox pointCloud;
	private JCheckBox constraintPoints;
	private JCheckBox function[];
	private JCheckBox limited;
	private JLabel abs;
	private JLabel ord;
	private JLabel hei;
	private JTextArea absText;
	private JTextArea ordText;
	private JTextArea heiText;

	public DisplaySettings() {

		pointCloud = new JCheckBox("Nuage de points");

		constraintPoints = new JCheckBox("Point(s) contraint(s)");

		function = new JCheckBox[5];
		function[0] = new JCheckBox("f(x,y)");

		limited = new JCheckBox("Limité");

		abs = new JLabel("Abscisses : ");

		absText = new JTextArea("x");
		absText.setColumns(8);

		ord = new JLabel("Ordonnées : ");

		ordText = new JTextArea("y");

		ordText.setColumns(8);

		hei = new JLabel("Cote : ");

		heiText = new JTextArea("z");
		heiText.setColumns(8);

	}

	public JCheckBox getPointCloud() {
		return pointCloud;
	}

	public JCheckBox getConstraintPoints() {
		return constraintPoints;
	}

	public JCheckBox[] getFunction() {
		return function;
	}

	public JCheckBox getLimited() {
		return limited;
	}

	public JLabel getAbs() {
		return abs;
	}

	public JLabel getOrd() {
		return ord;
	}

	public JLabel getHei() {
		return hei;
	}

	public JTextArea getAbsText() {
		return absText;
	}

	public JTextArea getOrdText() {
		return ordText;
	}

	public JTextArea getHeiText() {
		return heiText;
	}

}
