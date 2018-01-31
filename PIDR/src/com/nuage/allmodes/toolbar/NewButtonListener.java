package com.nuage.allmodes.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewButtonListener implements ActionListener {

	private Tools toolsReference;

	public NewButtonListener(Tools tools) {
		toolsReference = tools;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toolsReference.getMainWindow().reset();

	}

}
