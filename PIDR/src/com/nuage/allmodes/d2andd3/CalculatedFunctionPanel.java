package com.nuage.allmodes.d2andd3;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Contient la fonction calculée. Ce panel devra donc se mettre à jour ou se
 * remplacer de lui-même lorsqu'on cliquera sur le bouton Executer
 * 
 * @author Miron
 *
 */
@SuppressWarnings("serial")
public class CalculatedFunctionPanel extends JPanel { // frederic.neurohr@cpc57.fr

	// TODO : Mettre toutes les dimensions en adéquationavec le reste de la
	// fenêtre.
	public CalculatedFunctionPanel(CalculatedFunction cF) {

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(2, 2, 2, 2);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(cF.getFunctionName(), gbc);

		gbc.gridx += gbc.gridwidth;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 4;
		gbc.weighty = 1;
		gbc.weightx = 1;

		JScrollPane panelFunctionCalculated = new JScrollPane(cF.getFunctionCalculated());
		panelFunctionCalculated.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelFunctionCalculated.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelFunctionCalculated.setMinimumSize(new Dimension(10, 100));
		add(panelFunctionCalculated, gbc);

		gbc.gridx = 0;
		gbc.gridy += gbc.gridheight;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weighty = 1;
		gbc.weightx = 1;
		add(cF.getPointName(), gbc);

		gbc.gridx += gbc.gridwidth;
		add(cF.getPoint(), gbc);

		gbc.gridx += gbc.gridwidth;
		add(cF.getCalcul(), gbc);

		gbc.gridx += gbc.gridwidth;
		add(cF.getCalculatedPointName(), gbc);

		gbc.gridx += gbc.gridwidth;
		add(cF.getCalculatedPoint(), gbc);

	}

	// main pour test
	public static void main(String[] args) {
		JFrame test = new JFrame();
		CalculatedFunction cF = new CalculatedFunction("f(x) = ", "(x0)");
		test.setSize(1500, 1000);
		CalculatedFunctionPanel paneltest = new CalculatedFunctionPanel(cF);
		GridBagLayout gbl = new GridBagLayout();
		test.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		paneltest.setSize(test.getWidth(), test.getHeight());
		test.add(paneltest, gbc);
		test.setVisible(true);
	}

}
