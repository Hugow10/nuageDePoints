package com.nuage.fileschoosers.modefiles;

import java.io.File;

import javax.swing.JFileChooser;

import com.nuage.allmodes.Updatable;

@SuppressWarnings("serial")
public class UpdatableFileChooser extends JFileChooser {

	private File directory;

	public UpdatableFileChooser(Updatable mode) {
		directory = new File(".");

		setCurrentDirectory(directory);

		setAcceptAllFileFilterUsed(false);
		setFileFilter(new ModeFileFilter(mode));

	}

}
