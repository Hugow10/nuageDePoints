package com.nuage.allmodes.toolbar;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class InfosWindow {

	private JTextArea infosDisplay;
	private JScrollPane scrollPane;
	private JButton save;
	private JButton print;
	private JButton preview;
	private JPanel contentPane;

	public InfosWindow(StringBuilder infos) {
		infosDisplay = new JTextArea(16, 58);
		infosDisplay.setText(infos.toString());
		infosDisplay.setFont(new Font("Tahoma", Font.PLAIN, 11));
		infosDisplay.setMaximumSize(new Dimension(400, 600));
		infosDisplay.setLineWrap(true);

		scrollPane = new JScrollPane(infosDisplay);
		scrollPane.setPreferredSize(infosDisplay.getMinimumSize());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		save = new JButton("Enregistrer");
		save.addActionListener(new SaveButtonListener(this));

		print = new JButton("Imprimer");
		print.addActionListener(new PrintButtonListener(this));

		preview = new JButton("Prévisualiser");
		preview.addActionListener(new PreviewButtonListener(this));
	}

	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @return the infosDisplay
	 */
	public JTextArea getInfosDisplay() {
		return infosDisplay;
	}

	/**
	 * @return the save
	 */
	public JButton getSave() {
		return save;
	}

	/**
	 * @return the print
	 */
	public JButton getPrint() {
		return print;
	}

	/**
	 * @return the preview
	 */
	public JButton getPreview() {
		return preview;
	}

	public void setInfos(StringBuilder infos) {
		infosDisplay.setText(infos.toString());
	}

	/**
	 * @return the contentPane
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

}
