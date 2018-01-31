package com.nuage.allmodes.plane.constraints;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.nuage.allmodes.plane.constraints.oneconstraint.OneConstraintChoice;
import com.nuage.allmodes.plane.constraints.oneconstraint.OneConstraintChoicePanel;
import com.nuage.allmodes.plane.constraints.twoconstraints.TwoConstraintChoice;
import com.nuage.allmodes.plane.constraints.twoconstraints.TwoConstraintChoicePanel;

public class PlaneConstraintsChoice extends Observable {

	private ButtonGroup bG;
	private ButtonModel bm;
	private JRadioButton noConstraint;
	private JRadioButton oneConstraint;
	private JRadioButton twoConstraint;

	private JPanel constraintChoice;
	private JPanel constraintData;
	private JPanel noConstraintPane;
	private OneConstraintChoicePanel oneConstraintPane;
	private TwoConstraintChoicePanel twoConstraintPane;

	private CardLayout cl;
	private OneConstraintChoice oneConstraintChoice;
	private TwoConstraintChoice twoConstraintChoice;

	public PlaneConstraintsChoice() {
		bG = new ButtonGroup();
		noConstraint = new JRadioButton("Aucune contrainte");
		oneConstraint = new JRadioButton("Une contrainte");
		twoConstraint = new JRadioButton("Deux contraintes");
		bG.add(noConstraint);
		bG.add(oneConstraint);
		bG.add(twoConstraint);
		noConstraint.setSelected(true);
		bm = bG.getSelection();

		constraintChoice = new JPanel(new GridLayout(3, 1));
		constraintChoice.add(noConstraint, "noConstraint");
		constraintChoice.add(oneConstraint, "oneConstraint");
		constraintChoice.add(twoConstraint, "twoConstraint");

		addCustomListener(noConstraint);
		addCustomListener(oneConstraint);
		addCustomListener(twoConstraint);

		cl = new CardLayout();

		constraintData = new JPanel(cl);
		noConstraintPane = new JPanel();
		oneConstraintChoice = new OneConstraintChoice();
		oneConstraintPane = new OneConstraintChoicePanel(oneConstraintChoice);
		twoConstraintChoice = new TwoConstraintChoice();
		twoConstraintPane = new TwoConstraintChoicePanel(twoConstraintChoice);

		constraintData.add(noConstraintPane, "noConstraint");
		constraintData.add(oneConstraintPane, "oneConstraint");
		constraintData.add(twoConstraintPane, "twoConstraint");
	}

	private void addCustomListener(JRadioButton bouton) {
		bouton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (!bm.equals(bG.getSelection())) {
					bm = bG.getSelection();
					if (bm.equals(noConstraint.getModel())) {
						cl.show(constraintData, "noConstraint");
					}
					if (bm.equals(oneConstraint.getModel())) {
						cl.show(constraintData, "oneConstraint");
					}
					if (bm.equals(twoConstraint.getModel())) {
						cl.show(constraintData, "twoConstraint");
					}
				}

			}
		});
	}

	public OneConstraintChoice getOneConstraintChoice() {
		return oneConstraintChoice;
	}

	public TwoConstraintChoice getTwoConstraintChoice() {
		return twoConstraintChoice;
	}

	/**
	 * @return the bG
	 */
	public ButtonGroup getbG() {
		return bG;
	}

	/**
	 * @return the bm
	 */
	public ButtonModel getBm() {
		return bm;
	}

	/**
	 * @return the noConstraint
	 */
	public JRadioButton getNoConstraint() {
		return noConstraint;
	}

	/**
	 * @return the oneConstraint
	 */
	public JRadioButton getOneConstraint() {
		return oneConstraint;
	}

	/**
	 * @return the twoConstraint
	 */
	public JRadioButton getTwoConstraint() {
		return twoConstraint;
	}

	/**
	 * @return the constraintChoice
	 */
	public JPanel getConstraintChoice() {
		return constraintChoice;
	}

	/**
	 * @return the constraintData
	 */
	public JPanel getConstraintData() {
		return constraintData;
	}

	/**
	 * @return the noConstraintPane
	 */
	public JPanel getNoConstraintPane() {
		return noConstraintPane;
	}

	/**
	 * @return the oneConstraintPane
	 */
	public OneConstraintChoicePanel getOneConstraintPane() {
		return oneConstraintPane;
	}

	/**
	 * @return the twoConstraintPane
	 */
	public TwoConstraintChoicePanel getTwoConstraintPane() {
		return twoConstraintPane;
	}

	/**
	 * @return the cl
	 */
	public CardLayout getCl() {
		return cl;
	}

}
