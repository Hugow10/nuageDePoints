package com.nuage.allmodes.d2andd3;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class TestFunctionPanel extends JPanel {

	private static final int NB_LIGNE = 4;

	public TestFunctionPanel(TestFunction testFunction) {
		// Mise en place de la dimension minimum dans le cadre d'un test
		// dans une fenêtre seule.

		// TODO : Gérer la taille en fonction de la fenêtre.
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);

		// Mise en place de la partie fixe : nom de la fonction pour
		// correspondre à ce qui était
		// mis en place dans l'interface précédente.
		// En 2D, il n'y a que (x). En 3D, il faudrait ajouter y : (x,y).
		// Placement
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		// On centre le texte
		// gbc.anchor = GridBagConstraints.CENTER;
		JLabel label = testFunction.getFunction();
		gbc.ipadx = label.getFontMetrics(label.getFont()).stringWidth(testFunction.getParam()) + 20;
		add(label, gbc);

		// Mise en place de la zone de saisie. Dimension à gérer.
		JTextArea textArea = testFunction.getText();
		// Placement
		gbc.gridx = 1;
		gbc.gridheight = 2;
		gbc.ipady = (textArea.getFontMetrics(textArea.getFont()).getHeight() + 1) * NB_LIGNE;
		gbc.weightx = 1;
		gbc.weighty = 1;
		// Remplissage de la ligne

		JScrollPane panelText = new JScrollPane(textArea);
		panelText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(panelText, gbc);

		// TODO : Ajouter éventuellement des marges sur les pour l'esthétique.

	}

	// main pour test
	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.setSize(300, 500);
		TestFunction testFunction = new TestFunction("f(x) = ");
		TestFunctionPanel paneltest = new TestFunctionPanel(testFunction);
		test.add(paneltest, null);
		test.setVisible(true);
	}
}
