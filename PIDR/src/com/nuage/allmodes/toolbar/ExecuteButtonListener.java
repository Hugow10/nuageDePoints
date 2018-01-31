package com.nuage.allmodes.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nuage.allmodes.PopUp;

public class ExecuteButtonListener implements ActionListener {

	Tools toolsReference;

	public ExecuteButtonListener(Tools tools) {
		toolsReference = tools;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (toolsReference.getMainWindow().getMode().getData().isEmpty()) {
			new PopUp(this);
		} else {
			toolsReference.getMainWindow().runMath();
		}
	}

}
