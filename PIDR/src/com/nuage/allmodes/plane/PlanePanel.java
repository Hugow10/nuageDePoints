package com.nuage.allmodes.plane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.nuage.allmodes.UpdatablePanel;

@SuppressWarnings("serial")
public class PlanePanel extends JPanel implements Observer, UpdatablePanel {

	private Plane plane;

	public PlanePanel(Plane plane) {
		this.setPlane(plane);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		// Fonctions de transformation
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(plane.getTransformationPlanePanel(), gbc);

		// Calculette
		gbc.gridy += gbc.gridheight;
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(plane.getMathShortcutsPlanePanel(), gbc);

		// Panneau des données brutes
		gbc.gridwidth = 1;
		gbc.weighty = 1;
		gbc.weightx = 0;
		gbc.gridy += gbc.gridheight;
		gbc.gridheight = 5;
		add(plane.getDataPlanePanel(), gbc);

		// Panneau des contraintes selon choix
		gbc.gridx += gbc.gridwidth;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.gridwidth = 8;
		gbc.weighty = 0;
		gbc.weightx = 1;
		add(plane.getPlaneConstraintsChoicePanel(), gbc);

		// Panel de visualisation des Params du graphe
		gbc.gridx = 1;
		gbc.gridy += gbc.gridheight;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridheight = 7;
		add(plane.getDisplayPlanePanel(), gbc);

		// Panel de visualisation du graphe
		gbc.gridx = 2;
		gbc.gridwidth = 7;
		gbc.weightx = 15;
		gbc.weighty = 1;
		add(plane.getPlaneGraph().getCanvas(), gbc);

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public Plane getOnglet() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

}
