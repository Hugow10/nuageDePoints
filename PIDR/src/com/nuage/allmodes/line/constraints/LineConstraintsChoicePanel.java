package com.nuage.allmodes.line.constraints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

//Ajouter un bouton pour ajouter des contraintes :)
@SuppressWarnings("serial")
public class LineConstraintsChoicePanel extends JPanel implements Observer {

	public LineConstraintsChoicePanel(LineConstraintsChoice lineConstraintsChoice) {
		super(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		this.add(lineConstraintsChoice.getSelectionPanel(), gbc);

		gbc.gridx += gbc.gridwidth;
		this.add(lineConstraintsChoice.getChosenPanel(), gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("ConstraintsPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LineConstraintsChoicePanel newContentPane = new LineConstraintsChoicePanel(new LineConstraintsChoice());
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}
