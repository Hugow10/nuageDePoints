package com.nuage.allmodes.calculator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.nuage.allmodes.d2andd3.TestFunction;

@SuppressWarnings("serial")
public class MathShortcutsPanel extends JPanel {

	public MathShortcutsPanel(MathShortcuts math) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 1, 1);

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(math.getSin(), gbc);

		gbc.gridx = 1;
		add(math.getCos(), gbc);

		gbc.gridx = 2;
		add(math.getTan(), gbc);

		gbc.gridx = 3;
		add(math.getSinh(), gbc);

		gbc.gridx = 4;
		add(math.getCosh(), gbc);

		gbc.gridx = 5;
		add(math.getTanh(), gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		add(math.getAsin(), gbc);

		gbc.gridx = 1;
		add(math.getAcos(), gbc);

		gbc.gridx = 2;
		add(math.getAtan(), gbc);

		gbc.gridx = 3;
		add(math.getAsinh(), gbc);

		gbc.gridx = 4;
		add(math.getAcosh(), gbc);

		gbc.gridx = 5;
		add(math.getAtanh(), gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		add(math.getAbs(), gbc);

		gbc.gridx = 1;
		add(math.getPi(), gbc);

		gbc.gridx = 2;
		add(math.getPow(), gbc);

		gbc.gridx = 3;
		add(math.getLog10(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(math.getLn(), gbc);

		gbc.gridx = 1;
		add(math.getExp(), gbc);

		gbc.gridx = 2;
		add(math.getFact(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		add(math.getInv(), gbc);

		gbc.gridx = 1;
		add(math.getSqrt(), gbc);

		gbc.gridx = 2;
		add(math.getInteg(), gbc);

	}

	// main pour test
	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.setSize(300, 500);
		MathShortcuts math = new MathShortcuts(new TestFunction("f(x) = "));
		MathShortcutsPanel paneltest = new MathShortcutsPanel(math);
		test.add(paneltest, null);
		test.setVisible(true);
	}
}
