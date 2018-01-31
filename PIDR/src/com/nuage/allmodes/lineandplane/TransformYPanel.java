package com.nuage.allmodes.lineandplane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TransformYPanel extends JPanel {

	public TransformYPanel(TransformY transformY) {

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		this.setLayout(gbl);
		this.add(transformY.getTransformYLabel(), gbc);

		gbc.gridx = 1;
		this.add(transformY.getTransformY(), gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("xTransform");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TransformY transformY = new TransformY();
		TransformYPanel newContentPane = new TransformYPanel(transformY);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

}
