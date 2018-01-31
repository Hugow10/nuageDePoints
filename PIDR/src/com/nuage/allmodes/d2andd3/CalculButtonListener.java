package com.nuage.allmodes.d2andd3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nuage.allmodes.PopUp;

public class CalculButtonListener implements ActionListener {

	private CalculatedFunction calculatedFunctionReference;

	public CalculButtonListener(CalculatedFunction calculatedFunction) {
		calculatedFunctionReference = calculatedFunction;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (calculatedFunctionReference.getFunctionCalculated().getText().equals("")
				|| calculatedFunctionReference.getPoint().getText().equals("")) {
			new PopUp(this);
		} else {
			calculatedFunctionReference.eval();
		}
	}

}
