package com.nuage.allmodes.lineandd2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.nuage.allmodes.UpdatablePanel;
import com.nuage.allmodes.d2.D2;

@SuppressWarnings("serial")
public class D2Panel extends JPanel implements Observer, UpdatablePanel {

	private D2 d2;

	public D2Panel(D2 d2) {
		this.setD2(d2);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		// Fonction de test
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(d2.getTwoDTestFunctionPane(), gbc);

		// CalculatedFunction
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridy += gbc.gridheight;
		gbc.gridheight = 2;
		gbc.gridx = 0;
		add(d2.getTwoDCalculatedFunctionPane(), gbc);

		// Calculette
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(d2.getTwoDMathShortcutsPane(), gbc);

		// Contraintes Différentielles
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		gbc.gridx = 5;
		gbc.gridy = 0;
		add(d2.getTwoDConstraintsPane(), gbc);

		// Datas
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 4;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(d2.getTwoDDatasPane(), gbc);

		// Display
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 4;
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(d2.getTwoDDisplaySettingsPane(), gbc);

		// Panel de visualisation du graphe
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 7;
		gbc.weightx = 15;
		gbc.weighty = 1;
		add(d2.getD2Graph().getCanvas(), gbc);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public D2 getOnglet() {
		return d2;
	}

	public void setD2(D2 d2) {
		this.d2 = d2;
	}

}
