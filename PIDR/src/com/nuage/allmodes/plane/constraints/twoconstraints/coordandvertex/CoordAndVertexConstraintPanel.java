package com.nuage.allmodes.plane.constraints.twoconstraints.coordandvertex;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class CoordAndVertexConstraintPanel extends JPanel {

	private ButtonModel bm;

	private JPanel vertexChoice;
	private JPanel vertexDatas;

	private CardLayout cl;

	public CoordAndVertexConstraintPanel(CoordAndVertexConstraints coordAndVertexConstraints) {
		super(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		cl = new CardLayout();

		vertexChoice = new JPanel(new GridLayout(3, 1));
		vertexDatas = new JPanel(cl);

		bm = coordAndVertexConstraints.getbG().getSelection();

		vertexChoice.add(coordAndVertexConstraints.getVertexIJ(), "Vij");
		vertexChoice.add(coordAndVertexConstraints.getVertexKI(), "Vki");
		vertexChoice.add(coordAndVertexConstraints.getVertexJK(), "Vjk");

		addCustomListener(coordAndVertexConstraints, coordAndVertexConstraints.getVertexIJ());
		addCustomListener(coordAndVertexConstraints, coordAndVertexConstraints.getVertexKI());
		addCustomListener(coordAndVertexConstraints, coordAndVertexConstraints.getVertexJK());

		vertexDatas.add(coordAndVertexConstraints.getVertexVij(), "Vij");
		vertexDatas.add(coordAndVertexConstraints.getVertexVki(), "Vki");
		vertexDatas.add(coordAndVertexConstraints.getVertexVjk(), "Vjk");

		this.add(vertexChoice, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		this.add(vertexDatas, gbc);
	}

	private void addCustomListener(CoordAndVertexConstraints coordAndVertexConstraints, JRadioButton bouton) {
		bouton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (!bm.equals(coordAndVertexConstraints.getbG().getSelection())) {
					bm = coordAndVertexConstraints.getbG().getSelection();
					if (bm.equals(coordAndVertexConstraints.getVertexIJ().getModel())) {
						cl.show(vertexDatas, "Vij");
					}
					if (bm.equals(coordAndVertexConstraints.getVertexKI().getModel())) {
						cl.show(vertexDatas, "Vki");
					}
					if (bm.equals(coordAndVertexConstraints.getVertexJK().getModel())) {
						cl.show(vertexDatas, "Vjk");
					}
				}

			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("ConstraintsPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CoordAndVertexConstraints coordAndVertexConstraints = new CoordAndVertexConstraints();
		CoordAndVertexConstraintPanel newContentPane = new CoordAndVertexConstraintPanel(coordAndVertexConstraints);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
