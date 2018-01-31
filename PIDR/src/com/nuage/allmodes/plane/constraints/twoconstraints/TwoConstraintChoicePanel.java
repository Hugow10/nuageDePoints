package com.nuage.allmodes.plane.constraints.twoconstraints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TwoConstraintChoicePanel extends JPanel {

	public TwoConstraintChoicePanel(TwoConstraintChoice twoConstraintChoice) {
		super(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		add(twoConstraintChoice.getConstraintChoice(), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		add(twoConstraintChoice.getConstraintData(), gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("ConstraintsPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TwoConstraintChoice twoConstraintChoice = new TwoConstraintChoice();
		TwoConstraintChoicePanel newContentPane = new TwoConstraintChoicePanel(twoConstraintChoice);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

}
