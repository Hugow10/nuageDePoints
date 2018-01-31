package com.nuage.allmodes.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.nuage.fileschoosers.modefiles.UpdatableFileChooser;

public class SaveButtonListener extends AbstractSaveButtonListener implements ActionListener {

	public SaveButtonListener(Tools tools) {
		super(tools);
	}

	public SaveButtonListener(InfosWindow infosWindow) {
		super(infosWindow);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		File file = toolsReference.getMainWindow().getCurrentFile();
		if (toolsReference.getMainWindow().getCurrentFile() != null) {
			toolsReference.getMainWindow().saveInFile(file);
		} else {
			UpdatableFileChooser saveFileChooser = new UpdatableFileChooser(toolsReference.getMainWindow().getMode());
			int returnVal = saveFileChooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = new File(saveFileChooser.getSelectedFile() + getDescription());
				toolsReference.getMainWindow().setCurrentFile(file);
				toolsReference.getMainWindow().saveInFile(file);
			}
		}
	}
}
