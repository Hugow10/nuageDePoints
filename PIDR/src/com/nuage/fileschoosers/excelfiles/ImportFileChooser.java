package com.nuage.fileschoosers.excelfiles;

import java.io.File;

import javax.swing.JFileChooser;

public class ImportFileChooser extends JFileChooser {

	private static final long serialVersionUID = 8961639910700730836L;
	private File directory;

	public ImportFileChooser() {
		directory = new File(".");

		setCurrentDirectory(directory);

		setAcceptAllFileFilterUsed(false);
		setFileFilter(new ExcelFileFilter());

	}

}
