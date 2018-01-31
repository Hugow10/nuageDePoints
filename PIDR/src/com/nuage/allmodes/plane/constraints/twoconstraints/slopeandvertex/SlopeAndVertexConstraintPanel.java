package com.nuage.allmodes.plane.constraints.twoconstraints.slopeandvertex;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class SlopeAndVertexConstraintPanel extends JPanel {

	private JPanel cardDisplayer;
	private JPanel constraintChoser;

	private ButtonModel bm;

	private CardLayout cl;

	public SlopeAndVertexConstraintPanel(SlopeAndVertexConstraint slopeAndVertexConstraint) {
		super(new GridBagLayout());

		constraintChoser = new JPanel();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		bm = slopeAndVertexConstraint.getbG().getSelection();

		cl = new CardLayout();

		cardDisplayer = new JPanel(cl);
		cardDisplayer.add(slopeAndVertexConstraint.getV1XoYV2XoY(), "v1XoYV2XoY");
		cardDisplayer.add(slopeAndVertexConstraint.getV1ZoXV2ZoX(), "v1ZoXV2ZoX");
		cardDisplayer.add(slopeAndVertexConstraint.getV1YoZV2YoZ(), "v1YoZV2YoZ");
		cardDisplayer.add(slopeAndVertexConstraint.getV1XoYV2ZoX(), "v1XoYV2ZoX");
		cardDisplayer.add(slopeAndVertexConstraint.getV1XoYV2YoZ(), "v1XoYV2YoZ");
		cardDisplayer.add(slopeAndVertexConstraint.getV1YoZV2ZoX(), "v1YoZV2ZoX");

		addCustomListener(slopeAndVertexConstraint, slopeAndVertexConstraint.getV1XoYV2XoYButton());
		addCustomListener(slopeAndVertexConstraint, slopeAndVertexConstraint.getV1ZoXV2ZoXButton());
		addCustomListener(slopeAndVertexConstraint, slopeAndVertexConstraint.getV1YoZV2YoZButton());
		addCustomListener(slopeAndVertexConstraint, slopeAndVertexConstraint.getV1XoYV2YoZButton());
		addCustomListener(slopeAndVertexConstraint, slopeAndVertexConstraint.getV1XoYV2ZoXButton());
		addCustomListener(slopeAndVertexConstraint, slopeAndVertexConstraint.getV1YoZV2ZoXButton());

		constraintChoser = slopeAndVertexConstraint.getConstraintChoser();

		add(constraintChoser, gbc);

		gbc.weightx = 1;
		gbc.gridx = 1;
		add(cardDisplayer, gbc);
	}

	private void addCustomListener(SlopeAndVertexConstraint slopeAndVertexConstraint, JRadioButton bouton) {
		bouton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (!bm.equals(slopeAndVertexConstraint.getbG().getSelection())) {
					bm = slopeAndVertexConstraint.getbG().getSelection();
					if (bm.equals(slopeAndVertexConstraint.getV1XoYV2XoYButton().getModel())) {
						cl.show(cardDisplayer, "v1XoYV2XoY");
					}
					if (bm.equals(slopeAndVertexConstraint.getV1XoYV2YoZButton().getModel())) {
						cl.show(cardDisplayer, "v1XoYV2YoZ");
					}
					if (bm.equals(slopeAndVertexConstraint.getV1ZoXV2ZoXButton().getModel())) {
						cl.show(cardDisplayer, "v1ZoXV2ZoX");
					}
					if (bm.equals(slopeAndVertexConstraint.getV1XoYV2ZoXButton().getModel())) {
						cl.show(cardDisplayer, "v1XoYV2ZoX");
					}
					if (bm.equals(slopeAndVertexConstraint.getV1YoZV2YoZButton().getModel())) {
						cl.show(cardDisplayer, "v1YoZV2YoZ");
					}
					if (bm.equals(slopeAndVertexConstraint.getV1YoZV2ZoXButton().getModel())) {
						cl.show(cardDisplayer, "v1YoZV2ZoX");
					}
				}

			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("ConstraintsPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SlopeAndVertexConstraint slopeAndVertexConstraint = new SlopeAndVertexConstraint();
		SlopeAndVertexConstraintPanel newContentPane = new SlopeAndVertexConstraintPanel(slopeAndVertexConstraint);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

}
