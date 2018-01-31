package com.nuage.allmodes.plane.constraints.oneconstraint;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.nuage.allmodes.plane.constraints.oneconstraint.vertex.VertexConstraintChoice;
import com.nuage.allmodes.plane.constraints.oneconstraint.vertex.VertexConstraintChoicePanel;

public class OneConstraintChoice extends Observable {

	private ButtonGroup bG;
	private JRadioButton pointChoice;
	private JRadioButton slopeChoice;

	private JPanel pointChosen;
	private VertexConstraintChoice vertexConstraintChoice;
	private VertexConstraintChoicePanel vertexChosenChoicePanel;

	private JTextField x;
	private JTextField y;
	private JTextField z;

	public OneConstraintChoice() {
		initPanelDatas();
	}

	public void initPanelDatas() {
		pointChosen = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;

		x = new JTextField(8);
		y = new JTextField(8);
		z = new JTextField(8);

		bG = new ButtonGroup();
		pointChoice = new JRadioButton("Contraintes sur les coordonnées d'un point");
		slopeChoice = new JRadioButton("Contraintes sur pente selon un vecteur d'un plan");

		bG.add(pointChoice);
		bG.add(slopeChoice);

		pointChoice.setSelected(true);

		// pas très MVC ça...
		pointChosen.add(new JLabel("Xw1 brut = "), gbc);
		gbc.gridx += gbc.gridwidth;
		pointChosen.add(x, gbc);
		gbc.gridy += gbc.gridwidth;
		gbc.gridx = 0;
		pointChosen.add(new JLabel("Yw1 brut = "), gbc);
		gbc.gridx += gbc.gridwidth;
		pointChosen.add(y, gbc);
		gbc.gridy += gbc.gridheight;
		gbc.gridx = 0;
		pointChosen.add(new JLabel("Zw1 brut = "), gbc);
		gbc.gridx += gbc.gridwidth;
		pointChosen.add(z, gbc);

		vertexConstraintChoice = new VertexConstraintChoice();
		vertexChosenChoicePanel = new VertexConstraintChoicePanel(vertexConstraintChoice);
	}

	public VertexConstraintChoice getVertexConstraintChoice() {
		return vertexConstraintChoice;
	}

	public VertexConstraintChoicePanel getVertexChosenChoicePanel() {
		return vertexChosenChoicePanel;
	}

	public ButtonGroup getbG() {
		return bG;
	}

	public JRadioButton getPointChoice() {
		return pointChoice;
	}

	public JRadioButton getSlopeChoice() {
		return slopeChoice;
	}

	public JPanel getPointChosen() {
		return pointChosen;
	}

	public VertexConstraintChoicePanel getVertexChosen() {
		return vertexChosenChoicePanel;
	}

	public JTextField getX() {
		return x;
	}

	public JTextField getY() {
		return y;
	}

	public JTextField getZ() {
		return z;
	}

}
