package com.nuage.allmodes.line.transformations;

import javax.swing.JButton;

import com.nuage.allmodes.line.Line;
import com.nuage.allmodes.lineandplane.TransformX;
import com.nuage.allmodes.lineandplane.TransformY;
import com.nuage.allmodes.lineandplane.ValidationButtonListener;

public class TransformationsLine {

	private TransformX transformX;
	private TransformY transformY;
	private JButton validation;

	public TransformationsLine(Line line) {
		transformX = new TransformX();
		transformY = new TransformY();
		validation = new JButton("Validation des données");
		validation.addActionListener(new ValidationButtonListener(line));
	}

	public TransformX getTransformX() {
		return transformX;
	}

	public TransformY getTransformY() {
		return transformY;
	}

	public JButton getValidation() {
		return validation;
	}
}
