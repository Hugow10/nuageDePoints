package com.nuage.allmodes.line.transformations;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.nuage.allmodes.lineandplane.TransformXPanel;
import com.nuage.allmodes.lineandplane.TransformYPanel;

@SuppressWarnings("serial")
public class TransformationsLinePanel extends JPanel {

	public TransformationsLinePanel(TransformationsLine transformationsLine) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.setLayout(gbl);
		this.add(new TransformXPanel(transformationsLine.getTransformX()), gbc);

		gbc.gridy += gbc.gridheight;
		this.add(new TransformYPanel(transformationsLine.getTransformY()), gbc);

		gbc.gridy = 0;
		gbc.gridx = 1;
		gbc.gridheight = 2;
		this.add(transformationsLine.getValidation(), gbc);

	}

}
