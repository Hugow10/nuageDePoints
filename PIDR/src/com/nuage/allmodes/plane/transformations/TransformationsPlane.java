package com.nuage.allmodes.plane.transformations;

import javax.swing.JButton;

import com.nuage.allmodes.lineandplane.TransformX;
import com.nuage.allmodes.lineandplane.TransformY;

public class TransformationsPlane {

	private TransformX transformX;
	private TransformY transformY;
	private TransformZ transformZ;
	private JButton validation;

	public TransformationsPlane() {
		transformX = new TransformX();
		transformY = new TransformY();
		transformZ = new TransformZ();
		validation = new JButton("Validation des données");
	}

	public TransformX getTransformX() {
		return transformX;
	}

	public TransformY getTransformY() {
		return transformY;
	}

	public TransformZ getTransformZ() {
		return transformZ;
	}

	public JButton getValidation() {
		return validation;
	}

}
