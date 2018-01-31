package com.nuage.allmodes.d3;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.nuage.allmodes.UpdatablePanel;

@SuppressWarnings("serial")
public class D3Panel extends JPanel implements Observer, UpdatablePanel {

	private D3 d3;

	public D3Panel(D3 d3) {
		this.setD3(d3);

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
		add(d3.getThreeDTestFunctionPane(), gbc);

		// CalculatedFunction
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridy += gbc.gridheight;
		gbc.gridheight = 2;
		gbc.gridx = 0;
		add(d3.getThreeDCalculatedFunctionPane(), gbc);

		// Calculette
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(d3.getThreeDMathShortcutsPane(), gbc);

		// Contraintes Différentielles
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		gbc.gridx = 5;
		gbc.gridy = 0;
		add(d3.getThreeDConstraintsPane(), gbc);

		// Datas
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 4;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(d3.getThreeDDatasPane(), gbc);

		// Display
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 4;
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(d3.getThreeDDisplaySettingsPane(), gbc);

		// Panel de visualisation du graphe
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 7;
		gbc.weightx = 15;
		gbc.weighty = 1;
		add(d3.getD3Graph().getCanvas(), gbc);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public D3 getOnglet() {
		return d3;
	}

	public void setD3(D3 d3) {
		this.d3 = d3;
	}

}
