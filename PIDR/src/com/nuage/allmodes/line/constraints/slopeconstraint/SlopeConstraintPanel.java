package com.nuage.allmodes.line.constraints.slopeconstraint;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Quand on a choisi une contrainte de type "pente".
 * 
 * @author Romain
 *
 */
@SuppressWarnings("serial")
public class SlopeConstraintPanel extends JPanel {

	public SlopeConstraintPanel(SlopeConstraints slopeConstraints) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		this.add(slopeConstraints.getSlopeLabel(), gbc);
		gbc.gridx += gbc.gridwidth;
		this.add(slopeConstraints.getSlope(), gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("PointConstraint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SlopeConstraintPanel newContentPane = new SlopeConstraintPanel(new SlopeConstraints());
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

}
