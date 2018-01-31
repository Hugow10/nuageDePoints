package com.nuage.allmodes.plane.constraints.oneconstraint;

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
public class OneConstraintChoicePanel extends JPanel {

	private CardLayout cl;

	private ButtonModel bm;

	private JPanel constraintDatas;
	private JPanel constraintChoice;

	public OneConstraintChoicePanel(OneConstraintChoice oneConstraintChoice) {
		super(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		cl = new CardLayout();

		constraintDatas = new JPanel(cl);
		constraintChoice = new JPanel(new GridLayout(2, 1));

		constraintDatas.add(oneConstraintChoice.getPointChosen(), "Point");
		constraintDatas.add(oneConstraintChoice.getVertexChosen(), "Slope");

		bm = oneConstraintChoice.getbG().getSelection();

		constraintChoice.add(oneConstraintChoice.getPointChoice(), "Point");
		constraintChoice.add(oneConstraintChoice.getSlopeChoice(), "Slope");

		addCustomListener(oneConstraintChoice, oneConstraintChoice.getPointChoice());
		addCustomListener(oneConstraintChoice, oneConstraintChoice.getSlopeChoice());

		this.add(constraintChoice, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		this.add(constraintDatas);

	}

	public void addCustomListener(OneConstraintChoice oneConstraintChoice, JRadioButton bouton) {
		bouton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (!bm.equals(oneConstraintChoice.getbG().getSelection())) {
					bm = oneConstraintChoice.getbG().getSelection();
					if (bm.equals(oneConstraintChoice.getPointChoice().getModel())) {
						cl.show(constraintDatas, "Point");
					}
					if (bm.equals(oneConstraintChoice.getSlopeChoice().getModel())) {
						cl.show(constraintDatas, "Slope");
					}
				}

			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("OneConstraintChoicePanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OneConstraintChoice oneConstraintChoice = new OneConstraintChoice();
		OneConstraintChoicePanel newContentPane = new OneConstraintChoicePanel(oneConstraintChoice);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

}
