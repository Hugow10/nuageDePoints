package com.nuage.allmodes.plane.transformations;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TransformZPanel extends JPanel {

	public TransformZPanel(TransformZ transformZ) {

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		this.setLayout(gbl);
		this.add(transformZ.getTransformZLabel(), gbc);

		gbc.gridx = 1;
		this.add(transformZ.getTransformZ(), gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("xTransform");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TransformZ transformZ = new TransformZ();
		TransformZPanel newContentPane = new TransformZPanel(transformZ);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
