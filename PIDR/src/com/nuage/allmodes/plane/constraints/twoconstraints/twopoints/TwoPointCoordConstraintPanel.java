package com.nuage.allmodes.plane.constraints.twoconstraints.twopoints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TwoPointCoordConstraintPanel extends JPanel {

	public TwoPointCoordConstraintPanel(TwoPointCoordConstraint twoPointCoordConstraint) {
		super(new GridBagLayout());
		fillWithComp(twoPointCoordConstraint);
	}

	public void fillWithComp(TwoPointCoordConstraint twoPointCoordConstraint) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(twoPointCoordConstraint.getXw1Lab(), gbc);

		gbc.gridx = 1;
		add(twoPointCoordConstraint.getXw1(), gbc);

		gbc.gridx = 2;
		add(twoPointCoordConstraint.getXw2Lab(), gbc);

		gbc.gridx = 3;
		add(twoPointCoordConstraint.getXw2(), gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		add(twoPointCoordConstraint.getYw1Lab(), gbc);

		gbc.gridx = 1;
		add(twoPointCoordConstraint.getYw1(), gbc);

		gbc.gridx = 2;
		add(twoPointCoordConstraint.getYw2Lab(), gbc);

		gbc.gridx = 3;
		add(twoPointCoordConstraint.getYw2(), gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		add(twoPointCoordConstraint.getZw1Lab(), gbc);

		gbc.gridx = 1;
		add(twoPointCoordConstraint.getZw1(), gbc);

		gbc.gridx = 2;
		add(twoPointCoordConstraint.getZw2Lab(), gbc);

		gbc.gridx = 3;
		add(twoPointCoordConstraint.getZw2(), gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("ConstraintsPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TwoPointCoordConstraint twoPointCoordConstraint = new TwoPointCoordConstraint();
		TwoPointCoordConstraintPanel newContentPane = new TwoPointCoordConstraintPanel(twoPointCoordConstraint);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
