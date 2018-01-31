package com.nuage.allmodes.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class ImportButtonListener implements ActionListener {

	Tools toolsReference;

	public ImportButtonListener(Tools tools) {
		toolsReference = tools;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int returnVal = toolsReference.getImportFileChooser().showOpenDialog(null);
		System.out.println(toolsReference.getImportFileChooser().getCurrentDirectory());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = toolsReference.getImportFileChooser().getSelectedFile();
			toolsReference.getMainWindow().sendNewFile(toolsReference.getMainWindow(), file);
		}
	}

}
