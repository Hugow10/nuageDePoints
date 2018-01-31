package com.nuage.allmodes.lineandplane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TransformXPanel extends JPanel {

	public TransformXPanel(TransformX transformX) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		this.setLayout(gbl);
		this.add(transformX.getTransformXLabel(), gbc);

		gbc.gridx = 1;
		this.add(transformX.getTransformX(), gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("xTransform");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TransformX transformX = new TransformX();
		TransformXPanel newContentPane = new TransformXPanel(transformX);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

}
