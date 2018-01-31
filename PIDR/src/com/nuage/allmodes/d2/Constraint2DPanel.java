package com.nuage.allmodes.d2;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.nuage.allmodes.lineandd2.Table2DConstraintCustomModel;

@SuppressWarnings("serial")
public class Constraint2DPanel extends JPanel {

	private Table2DConstraintCustomModel modele;
	private JTable tableau;

	public Constraint2DPanel(Table2DConstraintCustomModel modele) {
		super();
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		gbc.gridx = 0;
		gbc.gridy = 0;

		this.modele = modele;
		tableau = new JTable(modele);
		tableau.setAutoCreateRowSorter(true);
		tableau.getColumnModel().getColumn(0).setMaxWidth(50);
		JScrollPane scrollPane = new JScrollPane(tableau);
		scrollPane.setMinimumSize(new Dimension(400, 200));
		add(scrollPane, gbc);

		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		gbc.gridy = 3;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;

		add(new JButton(new AddAction()), gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 2;
		add(new JButton(new RemoveAction()), gbc);
	}

	private class AddAction extends AbstractAction {
		private AddAction() {
			super("Ajouter ligne");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			modele.addConstraint(new Constraint2D(modele.getRowCount() + 1.0, 0.0, 0.0, 0.0));
		}
	}

	private class RemoveAction extends AbstractAction {
		private RemoveAction() {
			super("Supprimer");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selection = tableau.getSelectedRows();
			for (int i = selection.length - 1; i >= 0; i--) {
				modele.removeConstraint(selection[i]);
			}
			for (int i = 0; i < modele.getRowCount(); i++) {
				modele.setValueAt(i + 1.0, i, 0);
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Table2DConstraintCustomModel mod = new Table2DConstraintCustomModel(
				new String[] { "n°", "Xw", "Constraint Value", "Derivation Order" });
		frame.setContentPane(new Constraint2DPanel(mod));
		frame.pack();
		frame.setVisible(true);
	}
}
