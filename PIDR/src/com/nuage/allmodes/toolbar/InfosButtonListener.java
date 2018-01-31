package com.nuage.allmodes.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfosButtonListener implements ActionListener {

	private Tools toolsReference;

	public InfosButtonListener(Tools tools) {
		toolsReference = tools;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		toolsReference.getMainWindow().updateInfos();
	}

}
