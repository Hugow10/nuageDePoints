package com.nuage.allmodes.d2andd3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.nuage.allmodes.calculator.MathShortcuts;

public class CalculatorListener implements ActionListener {

	MathShortcuts calculatorReference;
	String functionClicked;

	public CalculatorListener(MathShortcuts calculator, String function) {
		calculatorReference = calculator;
		functionClicked = function;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String text;
		if (!functionClicked.equals("")) {
			text = functionClicked;
		} else {
			text = ((JButton) arg0.getSource()).getText();
		}
		calculatorReference.updatePhiJ(text);
	}

}
