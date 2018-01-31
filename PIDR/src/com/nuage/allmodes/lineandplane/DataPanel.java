package com.nuage.allmodes.lineandplane;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nuage.allmodes.Data;

@SuppressWarnings("serial")
public class DataPanel extends JPanel {

	public DataPanel(Data d) {
		super(new BorderLayout());
		/*
		 * GridBagConstraints gbc = new GridBagConstraints(); gbc.fill = gbc.BOTH;
		 * gbc.anchor = gbc.CENTER; gbc.gridx=0; gbc.gridy=0; gbc.weightx=0;
		 * gbc.weighty=0; gbc.gridheight=1; gbc.gridwidth=1;
		 */

		// Add the scroll pane to this panel.
		add(d.getScrollPane());
		// int colSize = d.getData().length;
		// int rowSize = d.getData()[0].length;
	}

	public DataPanel(Data d, Data transformedData) {
		super(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.CENTER;

		add(new JLabel("            Données brutes             "), gbc);
		gbc.gridx = 1;
		add(new JLabel("           Données transformées         "), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1;
		add(d.getScrollPane(), gbc);
		gbc.gridx = 1;
		add(transformedData.getScrollPane(), gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("DataPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Data d = new Data("n°", "xi", "yi");
		Data dtrans = new Data("n°", "xtrans", "ytrans");
		DataPanel newContentPane = new DataPanel(d, dtrans);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
