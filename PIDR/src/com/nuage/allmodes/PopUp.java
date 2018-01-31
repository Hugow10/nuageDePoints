package com.nuage.allmodes;

import java.awt.Frame;

import javax.swing.JOptionPane;

import com.nuage.allmodes.d2andd3.CalculButtonListener;
import com.nuage.allmodes.toolbar.CloseButtonListener;
import com.nuage.allmodes.toolbar.ExecuteButtonListener;

public class PopUp {

	private int answer;

	public PopUp(CalculButtonListener calculButtonListener) {
		JOptionPane.showMessageDialog(Frame.getFrames()[0],
				"Impossible de calculer l'image : \n" + "- f est manquante \n" + "ou\n"
						+ "- le point n'est pas de la forme (x0) ou (x0;y0)",
				"Erreur dans le calcul de point", JOptionPane.WARNING_MESSAGE);
	}

	public PopUp(CloseButtonListener closeButtonLister) {
		answer = JOptionPane.showConfirmDialog(Frame.getFrames()[0],
				"Des données n'ont potentiellement pas été sauvegardées. Etes-vous sûr de vouloir quitter ?");
	}

	public PopUp(ExecuteButtonListener executeButtonListener) {
		JOptionPane.showMessageDialog(Frame.getFrames()[0],
				"Les points rentrés ne sont pas tous corrects : calculs impossibles", "Exécution impossible",
				JOptionPane.WARNING_MESSAGE);
	}

	public PopUp(String string) {
		JOptionPane.showMessageDialog(Frame.getFrames()[0], string, "Erreur lecture sauvegarde",
				JOptionPane.WARNING_MESSAGE);
	}

	public PopUp(String formula, String variable) {
		JOptionPane.showMessageDialog(Frame.getFrames()[0],
				"La formule " + formula + " ne contient pas de variable " + variable, "Erreur changement de variable",
				JOptionPane.WARNING_MESSAGE);
	}

	public int getAnswer() {
		return answer;
	}

}
