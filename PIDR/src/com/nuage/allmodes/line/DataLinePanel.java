package com.nuage.allmodes.line;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.nuage.allmodes.lineandd2.Data2DPanel;
import com.nuage.allmodes.lineandd2.Point2D;
import com.nuage.allmodes.lineandd2.TableCustom2DModel;

public class DataLinePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5696106447554694086L;
	private TableCustom2DModel modele;
	private JTable tableauDatas;
	private String tx;
	private String ty;
	private TableCustom2DModel tc2dmDatas;
	private TableCustom2DModel tc2dmTrans;
	private Data2DPanel d2dpDatas;
	private Data2DPanel d2dpTrans;

	public DataLinePanel(TableCustom2DModel modele, String tx, String ty) {
		this.setTx(tx);
		this.setTy(ty);

		tc2dmDatas = modele;
		tc2dmTrans = applyChanges(modele);

		setD2dpDatas(new Data2DPanel(modele));
		setD2dpTrans(new Data2DPanel(tc2dmTrans));

		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		add(setD2dpDatas(new Data2DPanel(tc2dmDatas)));
		gbc.gridx = 1;
		add(setD2dpTrans(new Data2DPanel(tc2dmTrans)));
	}

	private TableCustom2DModel applyChanges(TableCustom2DModel modele2) {
		TableCustom2DModel model = new TableCustom2DModel(new String[] { "n°", "Xi = tx(xi)", "Yi = ty(yi)" });
		model.getPoints().clear();
		for (Point2D p : modele2.getPoints()) {
			model.addPoint(p);
		}
		return model;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		TableCustom2DModel mod = new TableCustom2DModel(new String[] { "n°", "Xi", "Yi" });
		frame.setContentPane(new Data2DPanel(mod));
		frame.pack();
		frame.setVisible(true);
	}

	public Data2DPanel getD2dpDatas() {
		return d2dpDatas;
	}

	public Data2DPanel setD2dpDatas(Data2DPanel d2dpDatas) {
		this.d2dpDatas = d2dpDatas;
		return d2dpDatas;
	}

	public Data2DPanel getD2dpTrans() {
		return d2dpTrans;
	}

	public Data2DPanel setD2dpTrans(Data2DPanel d2dpTrans) {
		this.d2dpTrans = d2dpTrans;
		return d2dpTrans;
	}

	public String getTx() {
		return tx;
	}

	public void setTx(String tx) {
		this.tx = tx;
	}

	public String getTy() {
		return ty;
	}

	public void setTy(String ty) {
		this.ty = ty;
	}

	@SuppressWarnings({ "unused", "serial" })
	private class AddAction extends AbstractAction {
		private AddAction() {
			super("Ajouter ligne");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			modele.addPoint(new Point2D(modele.getRowCount() + 1.0, 0.0, 0.0));
		}
	}

	@SuppressWarnings({ "unused", "serial" })
	private class RemoveAction extends AbstractAction {
		private RemoveAction() {
			super("Supprimer");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selection = tableauDatas.getSelectedRows();
			for (int i = selection.length - 1; i >= 0; i--) {
				modele.removePoint(selection[i]);
			}
			for (int i = 0; i < modele.getRowCount(); i++) {
				modele.setValueAt(i + 1.0, i, 0);
				System.out.println(modele.getValueAt(i, 1));
				System.out.println(i + 1.0);
			}
			System.out.println(modele.getRowCount());
		}
	}

	/**
	 * @return the tc2dmTrans
	 */
	public TableCustom2DModel getTc2dmTrans() {
		return tc2dmTrans;
	}

}