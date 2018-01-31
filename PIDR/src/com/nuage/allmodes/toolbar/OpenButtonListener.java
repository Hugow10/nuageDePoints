package com.nuage.allmodes.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.nuage.allmodes.Updatable;
import com.nuage.fileschoosers.modefiles.UpdatableFileChooser;

public class OpenButtonListener implements ActionListener {

	private Tools toolsReference;

	public OpenButtonListener(Tools tools) {
		toolsReference = tools;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Updatable mode = toolsReference.getMainWindow().getMode();
		UpdatableFileChooser openFileChooser = new UpdatableFileChooser(mode);
		int returnVal = openFileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = openFileChooser.getSelectedFile();
			toolsReference.getMainWindow().setCurrentFile(file);
			toolsReference.getMainWindow().readFile(file);
		}
	}

}
