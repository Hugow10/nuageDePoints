package com.nuage.allmodes.plane.transformations;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.nuage.allmodes.lineandplane.TransformXPanel;
import com.nuage.allmodes.lineandplane.TransformYPanel;

@SuppressWarnings("serial")
public class TransformationsPlanePanel extends JPanel {

	public TransformationsPlanePanel(TransformationsPlane transformationsPlane) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.setLayout(gbl);
		this.add(new TransformXPanel(transformationsPlane.getTransformX()), gbc);

		gbc.gridy += gbc.gridheight;
		this.add(new TransformYPanel(transformationsPlane.getTransformY()), gbc);

		gbc.gridy += gbc.gridheight;
		this.add(new TransformZPanel(transformationsPlane.getTransformZ()), gbc);

		gbc.gridy = 0;
		gbc.gridx = 1;
		gbc.gridheight = 3;
		this.add(transformationsPlane.getValidation(), gbc);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Transforms");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TransformationsPlane transformationsPlane = new TransformationsPlane();
		TransformationsPlanePanel newContentPane = new TransformationsPlanePanel(transformationsPlane);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
