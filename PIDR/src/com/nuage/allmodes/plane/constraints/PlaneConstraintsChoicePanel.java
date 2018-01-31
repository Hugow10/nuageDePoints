package com.nuage.allmodes.plane.constraints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlaneConstraintsChoicePanel extends JPanel implements Observer {

	public PlaneConstraintsChoicePanel(PlaneConstraintsChoice planeConstraintsChoice) {
		super(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		add(planeConstraintsChoice.getConstraintChoice(), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		add(planeConstraintsChoice.getConstraintData(), gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("ConstraintsPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlaneConstraintsChoicePanel newContentPane = new PlaneConstraintsChoicePanel(new PlaneConstraintsChoice());
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
