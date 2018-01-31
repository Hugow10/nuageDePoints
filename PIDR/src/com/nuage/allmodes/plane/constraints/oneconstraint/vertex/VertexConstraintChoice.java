package com.nuage.allmodes.plane.constraints.oneconstraint.vertex;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VertexConstraintChoice {

	private JRadioButton vertexIJ;
	private JRadioButton vertexKI;
	private JRadioButton vertexJK;

	private JPanel vertexVij;
	private JPanel vertexVki;
	private JPanel vertexVjk;

	private JTextField vertexVXIJ;
	private JTextField vertexVYIJ;
	private JTextField vertexIJSlope;

	private JTextField vertexVXKI;
	private JTextField vertexVYKI;
	private JTextField vertexKISlope;

	private JTextField vertexVXJK;
	private JTextField vertexVYJK;
	private JTextField vertexJKSlope;

	public VertexConstraintChoice() {

		vertexIJ = new JRadioButton("Vecteur Vij (xoy)");
		vertexKI = new JRadioButton("Vecteur Vki (zox)");
		vertexJK = new JRadioButton("Vecteur Vjk (yoz)");

		initPanelDatas();

	}

	public void initPanelDatas() {
		// vertexVij = new JPanel(new GridLayout(3,2));
		vertexVij = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		vertexVXIJ = new JTextField(8);
		vertexVYIJ = new JTextField(8);
		vertexIJSlope = new JTextField(8);

		vertexVij.add(new JLabel("Vxij = "), gbc);
		gbc.gridx += gbc.gridwidth;
		vertexVij.add(vertexVXIJ, gbc);

		gbc.gridx = 0;
		gbc.gridy += gbc.gridheight;
		vertexVij.add(new JLabel("Vyij = "), gbc);
		gbc.gridx += gbc.gridwidth;
		vertexVij.add(vertexVYIJ, gbc);

		gbc.gridx = 0;
		gbc.gridy += gbc.gridheight;
		vertexVij.add(new JLabel("Pente = "), gbc);
		gbc.gridx += gbc.gridwidth;
		vertexVij.add(vertexIJSlope, gbc);

		vertexVki = new JPanel(new GridBagLayout());

		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		vertexVXKI = new JTextField(8);
		vertexVYKI = new JTextField(8);
		vertexKISlope = new JTextField(8);

		vertexVki.add(new JLabel("Vxki = "), gbc);
		gbc.gridx += gbc.gridwidth;
		vertexVki.add(vertexVXKI, gbc);

		gbc.gridx = 0;
		gbc.gridy += gbc.gridheight;
		vertexVki.add(new JLabel("Vyki = "), gbc);
		gbc.gridx += gbc.gridwidth;
		vertexVki.add(vertexVYKI, gbc);

		gbc.gridx = 0;
		gbc.gridy += gbc.gridheight;
		vertexVki.add(new JLabel("Pente = "), gbc);
		gbc.gridx += gbc.gridwidth;
		vertexVki.add(vertexKISlope, gbc);

		vertexVjk = new JPanel(new GridBagLayout());

		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		vertexVXJK = new JTextField(8);
		vertexVYJK = new JTextField(8);
		vertexJKSlope = new JTextField(8);

		vertexVjk.add(new JLabel("Vxjk = "), gbc);
		gbc.gridx += gbc.gridwidth;
		vertexVjk.add(vertexVXJK, gbc);

		gbc.gridx = 0;
		gbc.gridy += gbc.gridheight;
		vertexVjk.add(new JLabel("Vyjk = "), gbc);
		gbc.gridx += 1;
		vertexVjk.add(vertexVYJK, gbc);

		gbc.gridy += gbc.gridheight;
		gbc.gridx = 0;
		vertexVjk.add(new JLabel("Pente = "), gbc);
		gbc.gridx += 1;
		vertexVjk.add(vertexJKSlope, gbc);
	}

	public JPanel getVertexVij() {
		return vertexVij;
	}

	public JPanel getVertexVki() {
		return vertexVki;
	}

	public JPanel getVertexVjk() {
		return vertexVjk;
	}

	public JTextField getVertexVXIJ() {
		return vertexVXIJ;
	}

	public JTextField getVertexVYIJ() {
		return vertexVYIJ;
	}

	public JTextField getVertexIJSlope() {
		return vertexIJSlope;
	}

	public JTextField getVertexVXKI() {
		return vertexVXKI;
	}

	public JTextField getVertexVYKI() {
		return vertexVYKI;
	}

	public JTextField getVertexKISlope() {
		return vertexKISlope;
	}

	public JTextField getVertexVXJK() {
		return vertexVXJK;
	}

	public JTextField getVertexVYJK() {
		return vertexVYJK;
	}

	public JTextField getVertexJKSlope() {
		return vertexJKSlope;
	}

	public JRadioButton getVertexIJ() {
		return vertexIJ;
	}

	public JRadioButton getVertexKI() {
		return vertexKI;
	}

	public JRadioButton getVertexJK() {
		return vertexJK;
	}

}
