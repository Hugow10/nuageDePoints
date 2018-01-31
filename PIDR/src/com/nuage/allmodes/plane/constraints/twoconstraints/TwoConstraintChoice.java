package com.nuage.allmodes.plane.constraints.twoconstraints;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.nuage.allmodes.plane.constraints.twoconstraints.coordandvertex.CoordAndVertexConstraintPanel;
import com.nuage.allmodes.plane.constraints.twoconstraints.coordandvertex.CoordAndVertexConstraints;
import com.nuage.allmodes.plane.constraints.twoconstraints.slopeandvertex.SlopeAndVertexConstraint;
import com.nuage.allmodes.plane.constraints.twoconstraints.slopeandvertex.SlopeAndVertexConstraintPanel;
import com.nuage.allmodes.plane.constraints.twoconstraints.twopoints.TwoPointCoordConstraint;
import com.nuage.allmodes.plane.constraints.twoconstraints.twopoints.TwoPointCoordConstraintPanel;

public class TwoConstraintChoice {

	private TwoPointCoordConstraintPanel twoPointCoordPane;
	private CoordAndVertexConstraintPanel coordAndVertexPane;
	private SlopeAndVertexConstraintPanel slopeAndVertexPane;

	private JRadioButton twoPointCoord;
	private JRadioButton coordAndVertex;
	private JRadioButton slopeAndVertex;

	private ButtonModel bm;
	private ButtonGroup bG;

	private JPanel constraintChoice;
	private JPanel constraintData;

	private CardLayout cl;
	private TwoPointCoordConstraint twoPointCoordConstraint;
	private CoordAndVertexConstraints coordAndVertexConstraints;
	private SlopeAndVertexConstraint slopeAndVertexConstraint;

	public TwoConstraintChoice() {
		initPanel();
	}

	private void initPanel() {
		bG = new ButtonGroup();
		twoPointCoord = new JRadioButton("Coordonnées de deux points");
		coordAndVertex = new JRadioButton("Coordonnées et pente selon un vecteur d'un plan");
		slopeAndVertex = new JRadioButton("Contrainte sur deux pentes selon deux vecteurs");
		bG.add(twoPointCoord);
		bG.add(coordAndVertex);
		bG.add(slopeAndVertex);
		twoPointCoord.setSelected(true);
		bm = bG.getSelection();

		constraintChoice = new JPanel(new GridLayout(3, 1));
		constraintChoice.add(twoPointCoord, "twoPointCoord");
		constraintChoice.add(coordAndVertex, "coordAndVertex");
		constraintChoice.add(slopeAndVertex, "slopeAndVertex");

		addCustomListener(twoPointCoord);
		addCustomListener(coordAndVertex);
		addCustomListener(slopeAndVertex);

		cl = new CardLayout();

		twoPointCoordConstraint = new TwoPointCoordConstraint();
		coordAndVertexConstraints = new CoordAndVertexConstraints();
		slopeAndVertexConstraint = new SlopeAndVertexConstraint();
		constraintData = new JPanel(cl);
		twoPointCoordPane = new TwoPointCoordConstraintPanel(twoPointCoordConstraint);
		coordAndVertexPane = new CoordAndVertexConstraintPanel(coordAndVertexConstraints);
		slopeAndVertexPane = new SlopeAndVertexConstraintPanel(slopeAndVertexConstraint);

		constraintData.add(twoPointCoordPane, "twoPointCoord");
		constraintData.add(coordAndVertexPane, "coordAndVertex");
		constraintData.add(slopeAndVertexPane, "slopeAndVertex");
	}

	private void addCustomListener(JRadioButton bouton) {
		bouton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (!bm.equals(bG.getSelection())) {
					bm = bG.getSelection();
					if (bm.equals(twoPointCoord.getModel())) {
						cl.show(constraintData, "twoPointCoord");
					}
					if (bm.equals(coordAndVertex.getModel())) {
						cl.show(constraintData, "coordAndVertex");
					}
					if (bm.equals(slopeAndVertex.getModel())) {
						cl.show(constraintData, "slopeAndVertex");
					}
				}

			}
		});
	}

	public TwoPointCoordConstraintPanel getTwoPointCoordPane() {
		return twoPointCoordPane;
	}

	public CoordAndVertexConstraints getCoordAndVertexConstraints() {
		return coordAndVertexConstraints;
	}

	public SlopeAndVertexConstraint getSlopeAndVertexConstraint() {
		return slopeAndVertexConstraint;
	}

	public CoordAndVertexConstraintPanel getCoordAndVertexPane() {
		return coordAndVertexPane;
	}

	public SlopeAndVertexConstraintPanel getSlopeAndVertexPane() {
		return slopeAndVertexPane;
	}

	public JRadioButton getTwoPointCoord() {
		return twoPointCoord;
	}

	public JRadioButton getCoordAndVertex() {
		return coordAndVertex;
	}

	public JRadioButton getSlopeAndVertex() {
		return slopeAndVertex;
	}

	public ButtonModel getBm() {
		return bm;
	}

	public ButtonGroup getbG() {
		return bG;
	}

	public JPanel getConstraintChoice() {
		return constraintChoice;
	}

	public JPanel getConstraintData() {
		return constraintData;
	}

	public CardLayout getCl() {
		return cl;
	}

	public TwoPointCoordConstraint getTwoPointCoordConstraint() {
		return twoPointCoordConstraint;
	}

}
