package com.nuage.allmodes.line;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.nuage.allmodes.UpdatablePanel;

@SuppressWarnings("serial")
public class LinePanel extends JPanel implements Observer, UpdatablePanel {

	private Line line;

	public LinePanel(Line line) {
		this.setLine(line);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		// Fonctions de transformation
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(line.getTransformationsLinePanel(), gbc);

		// Calculette
		gbc.gridy += gbc.gridheight;
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(line.getMathShortcutsPanel(), gbc);

		// Panneau des données brutes
		gbc.gridwidth = 1;
		gbc.weighty = 1;
		gbc.weightx = 0;
		gbc.gridy += gbc.gridheight;
		gbc.gridheight = 5;
		add(line.getDataLinePanel(), gbc);

		// Panneau des contraintes selon choix
		gbc.gridx += gbc.gridwidth;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.gridwidth = 8;
		gbc.weightx = 1;
		gbc.weighty = 0;
		add(line.getChosenPanel(), gbc);

		// Panel de visualisation des params du graphe
		gbc.gridx = 1;
		gbc.gridy += gbc.gridheight;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridheight = 7;
		add(line.getDisplaySettingsPanel(), gbc);

		// Panel de visualisation du graphe
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 7;
		gbc.weightx = 15;
		gbc.weighty = 1;
		add(line.getLineGraph().getCanvas(), gbc);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public Line getOnglet() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

}
