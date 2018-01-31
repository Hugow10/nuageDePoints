package com.nuage.allmodes.lineandd2;

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

public class Data2DPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5696106447554694086L;
	private TableCustom2DModel modele;
	private JTable tableau;

	public Data2DPanel(TableCustom2DModel modele) {
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
		scrollPane.setMinimumSize(new Dimension(300, 1000));
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

		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridy = 4;
		gbc.gridx = 0;
		add(new JButton(new RemoveTwoAction()), gbc);

		gbc.gridx += 1;
		add(new JButton(new RemoveThreeAction()), gbc);

		gbc.gridx += 1;
		add(new JButton(new RemoveFiveAction()), gbc);

		gbc.gridx += 1;
		add(new JButton(new RemoveTenAction()), gbc);
	}

	@SuppressWarnings("serial")
	private class RemoveTwoAction extends AbstractAction {
		private RemoveTwoAction() {
			super("Supprimer 1/2");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selection = tableau.getSelectedRows();
			modele.removePoint(selection[0], selection[selection.length - 1], 2);
		}
	}

	@SuppressWarnings("serial")
	private class RemoveThreeAction extends AbstractAction {
		private RemoveThreeAction() {
			super("Supprimer 1/3");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selection = tableau.getSelectedRows();
			modele.removePoint(selection[0], selection[selection.length - 1], 3);
		}

	}

	@SuppressWarnings("serial")
	private class RemoveFiveAction extends AbstractAction {
		private RemoveFiveAction() {
			super("Supprimer 1/5");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selection = tableau.getSelectedRows();
			modele.removePoint(selection[0], selection[selection.length - 1], 5);
		}

	}

	@SuppressWarnings("serial")
	private class RemoveTenAction extends AbstractAction {
		private RemoveTenAction() {
			super("Supprimer 1/10");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selection = tableau.getSelectedRows();
			modele.removePoint(selection[0], selection[selection.length - 1], 10);
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		TableCustom2DModel mod = new TableCustom2DModel(new String[] { "n°", "Xi", "Yi" });
		frame.setContentPane(new Data2DPanel(mod));
		frame.pack();
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	private class AddAction extends AbstractAction {
		private AddAction() {
			super("Ajouter ligne");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			modele.addPoint(new Point2D(modele.getRowCount() + 1.0, 0.0, 0.0));
		}
	}

	@SuppressWarnings("serial")
	private class RemoveAction extends AbstractAction {
		private RemoveAction() {
			super("Supprimer");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selection = tableau.getSelectedRows();
			for (int i = selection.length - 1; i >= 0; i--) {
				modele.removePoint(selection[i]);
			}
			for (int i = 0; i < modele.getRowCount(); i++) {
				modele.setValueAt(i + 1.0, i, 0);
			}
		}
	}
}