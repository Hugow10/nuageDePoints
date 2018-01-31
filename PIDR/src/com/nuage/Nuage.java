package com.nuage;

import javax.swing.UIManager;

import com.nuage.allmodes.MainWindow;
import com.nuage.allmodes.MainWindowFrame;

public class Nuage {

	private static MainWindowFrame frame;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MainWindowFrame(new MainWindow());
	}

	public static MainWindowFrame getFrame() {
		return frame;
	}

	public static void setFrame(MainWindowFrame frame1) {
		frame = frame1;
	}
}
