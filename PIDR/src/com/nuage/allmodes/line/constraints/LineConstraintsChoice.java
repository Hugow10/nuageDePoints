package com.nuage.allmodes.line.constraints;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.nuage.allmodes.line.constraints.pointconstraint.PointConstraint;
import com.nuage.allmodes.line.constraints.pointconstraint.PointConstraintPanel;
import com.nuage.allmodes.line.constraints.slopeconstraint.SlopeConstraintPanel;
import com.nuage.allmodes.line.constraints.slopeconstraint.SlopeConstraints;

public class LineConstraintsChoice extends Observable {

	private JPanel chosenPanel;
	private CardLayout cl;
	private JPanel noConstraintPanel;
	private PointConstraint pointConstraint;
	private SlopeConstraints slopeConstraints;
	private PointConstraintPanel pointConstraintPanel;
	private SlopeConstraintPanel slopeConstraintPanel;
	private JPanel selectionPanel;
	private ButtonModel bm;
	private ButtonGroup bG;
	private JRadioButton noConstraintButton;
	private JRadioButton pointConstraintButton;
	private JRadioButton slopeConstraintButton;
	private int commutateur;

	public LineConstraintsChoice() {
		selectionPanel = new JPanel(new GridLayout(3, 1));
		bG = new ButtonGroup();
		noConstraintButton = new JRadioButton("Sans contrainte");
		pointConstraintButton = new JRadioButton("Droite passe par (Xw;Yw)");
		slopeConstraintButton = new JRadioButton("Fixer la pente de la droite");

		cl = new CardLayout();
		chosenPanel = new JPanel(cl);

		noConstraintPanel = new JPanel();
		pointConstraint = new PointConstraint();
		slopeConstraints = new SlopeConstraints();
		pointConstraintPanel = new PointConstraintPanel(pointConstraint);
		slopeConstraintPanel = new SlopeConstraintPanel(slopeConstraints);

		chosenPanel.add(noConstraintPanel, "Pas de contrainte");
		chosenPanel.add(pointConstraintPanel, "Contrainte sur un point");
		chosenPanel.add(slopeConstraintPanel, "Contrainte sur la pente");

		cl.first(chosenPanel);

		bG.add(noConstraintButton);
		bG.add(pointConstraintButton);
		bG.add(slopeConstraintButton);
		noConstraintButton.setSelected(true);
		bm = bG.getSelection();

		selectionPanel.add(noConstraintButton);
		selectionPanel.add(pointConstraintButton);
		selectionPanel.add(slopeConstraintButton);

		addCustomListener(noConstraintButton);
		addCustomListener(pointConstraintButton);
		addCustomListener(slopeConstraintButton);
	}

	public void addCustomListener(JRadioButton bouton) {
		bouton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (!bm.equals(bG.getSelection())) {
					bm = bG.getSelection();
					if (bm.equals(noConstraintButton.getModel())) {
						commutateur = 0;
						cl.first(chosenPanel);
					}
					if (bm.equals(pointConstraintButton.getModel())) {
						commutateur = 1;
						cl.show(chosenPanel, "Contrainte sur un point");
					}
					if (bm.equals(slopeConstraintButton.getModel())) {
						commutateur = 2;
						cl.show(chosenPanel, "Contrainte sur la pente");
					}
				}

			}
		});
	}

	/**
	 * @return the slopeConstraints
	 */
	public SlopeConstraints getSlopeConstraints() {
		return slopeConstraints;
	}

	/**
	 * @return the noConstraintButton
	 */
	public JRadioButton getNoConstraintButton() {
		return noConstraintButton;
	}

	/**
	 * @return the pointConstraint
	 */
	public PointConstraint getPointConstraint() {
		return pointConstraint;
	}

	/**
	 * @return the pointConstraintButton
	 */
	public JRadioButton getPointConstraintButton() {
		return pointConstraintButton;
	}

	/**
	 * @return the slopeConstraintButton
	 */
	public JRadioButton getSlopeConstraintButton() {
		return slopeConstraintButton;
	}

	/**
	 * @return the commutateur
	 */
	public int getCommutateur() {
		return commutateur;
	}

	public ButtonModel getBM() {
		return bm;
	}

	/**
	 * @return the chosenPanel
	 */
	public JPanel getChosenPanel() {
		return chosenPanel;
	}

	/**
	 * @return the cl
	 */
	public CardLayout getCl() {
		return cl;
	}

	/**
	 * @return the noConstraintPanel
	 */
	public JPanel getNoConstraintPanel() {
		return noConstraintPanel;
	}

	/**
	 * @return the pointConstraintPanel
	 */
	public PointConstraintPanel getPointConstraintPanel() {
		return pointConstraintPanel;
	}

	/**
	 * @return the slopeConstraintPanel
	 */
	public SlopeConstraintPanel getSlopeConstraintPanel() {
		return slopeConstraintPanel;
	}

	/**
	 * @return the selectionPanel
	 */
	public JPanel getSelectionPanel() {
		return selectionPanel;
	}

	/**
	 * @return the bm
	 */
	public ButtonModel getBm() {
		return bm;
	}

	/**
	 * @return the bG
	 */
	public ButtonGroup getbG() {
		return bG;
	}

}
