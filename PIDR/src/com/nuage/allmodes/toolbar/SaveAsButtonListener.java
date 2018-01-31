package com.nuage.allmodes.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.nuage.fileschoosers.modefiles.UpdatableFileChooser;

public class SaveAsButtonListener extends AbstractSaveButtonListener implements ActionListener {

	public SaveAsButtonListener(Tools tools) {
		super(tools);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File file = toolsReference.getMainWindow().getCurrentFile();
		UpdatableFileChooser saveFileChooser = new UpdatableFileChooser(toolsReference.getMainWindow().getMode());
		int returnVal = saveFileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = new File(saveFileChooser.getSelectedFile() + getDescription());
			toolsReference.getMainWindow().setCurrentFile(file);
			toolsReference.getMainWindow().saveInFile(file);
		}
	}

}
