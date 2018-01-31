package com.nuage.allmodes.d2andd3;

import java.awt.Color;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class TestFunction extends Observable {

	private String value = "";
	private JLabel function;
	private JTextArea text;
	private String param;

	public TestFunction(String s) {

		param = s;
		function = new JLabel("<html>&phi;j " + param + " = </html>", SwingConstants.CENTER);
		// TODO : Gérer la taille en fonction de la fenêtre.
		text = new JTextArea(value);
		// Première valeur = largeur, deuxième = hauteur.
		// Hauteur inutile...?
		// text.setSize(800,400);
		// 3 colonnes de texte disponibles nativement, s'étend au besoin.
		text.setRows(2);
		// Renvoie à la ligne si manque de place
		text.setLineWrap(true);
		// Coloration
		text.setBackground(Color.WHITE);
	}

	// TODO ne pas ins�rer des fonctions au milieu de fonctions, e.g : tan() =>
	// cos()tan() ou tan()cos() ou tan(cos()) mais c'est tout.
	// text.getCaretPosition() permet de r�cup�rer la position du curseur
	public void warnMainWindowFrame(String content) {
		if (text.getText() == null) {
			text.setText("");
		} else {
			text.setText(text.getText().substring(0, text.getCaretPosition()) + content + "()"
					+ text.getText().substring(text.getCaretPosition(), text.getText().length()));
		}
		setChanged();
		notifyObservers();
	}

	// Un setter de la valeur à mettre dans la partie textuelle quand on clique
	// sur
	// les raccourcis.
	public void setValue(String newValue) {
		value = newValue;
		setChanged();
		notifyObservers();
	}

	public String getValue() {
		return value;
	}

	public JLabel getFunction() {
		return function;
	}

	public JTextArea getText() {
		return text;
	}

	public String getParam() {
		return param;
	}

}
