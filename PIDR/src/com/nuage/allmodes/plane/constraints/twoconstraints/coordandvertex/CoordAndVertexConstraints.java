package com.nuage.allmodes.plane.constraints.twoconstraints.coordandvertex;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CoordAndVertexConstraints extends Observable {

	private JTextField xij;
	private JTextField yij;
	private JTextField zij;
	private JLabel xijLab;
	private JLabel yijLab;
	private JLabel zijLab;
	private JTextField xki;
	private JTextField yki;
	private JTextField zki;
	private JLabel xkiLab;
	private JLabel ykiLab;
	private JLabel zkiLab;
	private JTextField xjk;
	private JTextField yjk;
	private JTextField zjk;
	private JLabel xjkLab;
	private JLabel yjkLab;
	private JLabel zjkLab;

	private JRadioButton vertexIJ;
	private JRadioButton vertexKI;
	private JRadioButton vertexJK;

	private JPanel vertexVij;
	private JTextField vertexVXIJ;
	private JTextField vertexVYIJ;
	private JPanel vertexVki;
	private JTextField vertexVXKI;
	private JTextField vertexVYKI;
	private JPanel vertexVjk;
	private JTextField vertexVXJK;
	private JTextField vertexVYJK;
	private ButtonGroup bG;

	public CoordAndVertexConstraints() {
		initPanelDatas();
	}

	private void initPanelDatas() {
		vertexIJ = new JRadioButton("Vecteur Vij (xoy)");
		vertexKI = new JRadioButton("Vecteur Vki (zox)");
		vertexJK = new JRadioButton("Vecteur Vjk (yoz)");

		bG = new ButtonGroup();

		bG.add(vertexIJ);
		bG.add(vertexKI);
		bG.add(vertexJK);

		vertexIJ.setSelected(true);

		xij = new JTextField(8);
		yij = new JTextField(8);
		zij = new JTextField(8);
		xijLab = new JLabel("Xw1 brut = ");
		yijLab = new JLabel("Yw1 brut = ");
		zijLab = new JLabel("Zw1 brut = ");

		xki = new JTextField(8);
		yki = new JTextField(8);
		zki = new JTextField(8);
		xkiLab = new JLabel("Xw1 brut = ");
		ykiLab = new JLabel("Yw1 brut = ");
		zkiLab = new JLabel("Zw1 brut = ");

		xjk = new JTextField(8);
		yjk = new JTextField(8);
		zjk = new JTextField(8);
		xjkLab = new JLabel("Xw1 brut = ");
		yjkLab = new JLabel("Yw1 brut = ");
		zjkLab = new JLabel("Zw1 brut = ");

		vertexVij = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		vertexVXIJ = new JTextField(8);
		vertexVYIJ = new JTextField(8);

		vertexVij.add(xijLab, gbc);
		gbc.gridx = 1;
		vertexVij.add(xij, gbc);

		gbc.gridx = 2;
		vertexVij.add(new JLabel("Vxij = "), gbc);
		gbc.gridx = 3;
		vertexVij.add(vertexVXIJ, gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		vertexVij.add(yijLab, gbc);
		gbc.gridx = 1;
		vertexVij.add(yij, gbc);

		gbc.gridx = 2;
		vertexVij.add(new JLabel("Vyij = "), gbc);
		gbc.gridx = 3;
		vertexVij.add(vertexVYIJ, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		vertexVij.add(zijLab, gbc);
		gbc.gridx = 1;
		vertexVij.add(zij, gbc);

		vertexVki = new JPanel(new GridBagLayout());

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		vertexVXKI = new JTextField(8);
		vertexVYKI = new JTextField(8);

		vertexVki.add(xkiLab, gbc);
		gbc.gridx = 1;
		vertexVki.add(xki, gbc);

		gbc.gridx = 2;
		vertexVki.add(new JLabel("Vxki = "), gbc);
		gbc.gridx = 3;
		vertexVki.add(vertexVXKI, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		vertexVki.add(ykiLab, gbc);
		gbc.gridx = 1;
		vertexVki.add(yki, gbc);

		gbc.gridx = 2;
		vertexVki.add(new JLabel("Vyki = "), gbc);
		gbc.gridx = 3;
		vertexVki.add(vertexVYKI, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		vertexVki.add(zkiLab, gbc);
		gbc.gridx = 1;
		vertexVki.add(zki, gbc);

		vertexVjk = new JPanel(new GridBagLayout());

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		vertexVXJK = new JTextField(8);
		vertexVYJK = new JTextField(8);

		vertexVjk.add(xjkLab, gbc);
		gbc.gridx = 1;
		vertexVjk.add(xjk, gbc);

		gbc.gridx = 2;
		vertexVjk.add(new JLabel("Vxjk = "), gbc);
		gbc.gridx = 3;
		vertexVjk.add(vertexVXJK, gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		vertexVjk.add(yjkLab, gbc);
		gbc.gridx = 1;
		vertexVjk.add(yjk, gbc);

		gbc.gridx = 2;
		vertexVjk.add(new JLabel("Vyjk = "), gbc);
		gbc.gridx = 3;
		vertexVjk.add(vertexVYJK, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		vertexVjk.add(zjkLab, gbc);
		gbc.gridx = 1;
		vertexVjk.add(zjk, gbc);

	}

	public JTextField getXij() {
		return xij;
	}

	public JTextField getYij() {
		return yij;
	}

	public JTextField getZij() {
		return zij;
	}

	public JLabel getXijLab() {
		return xijLab;
	}

	public JLabel getYijLab() {
		return yijLab;
	}

	public JLabel getZijLab() {
		return zijLab;
	}

	public JTextField getXki() {
		return xki;
	}

	public JTextField getYki() {
		return yki;
	}

	public JTextField getZki() {
		return zki;
	}

	public JLabel getXkiLab() {
		return xkiLab;
	}

	public JLabel getYkiLab() {
		return ykiLab;
	}

	public JLabel getZkiLab() {
		return zkiLab;
	}

	public JTextField getXjk() {
		return xjk;
	}

	public JTextField getYjk() {
		return yjk;
	}

	public JTextField getZjk() {
		return zjk;
	}

	public JLabel getXjkLab() {
		return xjkLab;
	}

	public JLabel getYjkLab() {
		return yjkLab;
	}

	public JLabel getZjkLab() {
		return zjkLab;
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

	public JPanel getVertexVij() {
		return vertexVij;
	}

	public JTextField getVertexVXIJ() {
		return vertexVXIJ;
	}

	public JTextField getVertexVYIJ() {
		return vertexVYIJ;
	}

	public JPanel getVertexVki() {
		return vertexVki;
	}

	public JTextField getVertexVXKI() {
		return vertexVXKI;
	}

	public JTextField getVertexVYKI() {
		return vertexVYKI;
	}

	public JPanel getVertexVjk() {
		return vertexVjk;
	}

	public JTextField getVertexVXJK() {
		return vertexVXJK;
	}

	public JTextField getVertexVYJK() {
		return vertexVYJK;
	}

	public ButtonGroup getbG() {
		return bG;
	}

}
