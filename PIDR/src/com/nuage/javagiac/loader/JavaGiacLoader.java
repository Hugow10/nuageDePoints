package com.nuage.javagiac.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

import com.nuage.javagiac.ressource.DllProvider;

public class JavaGiacLoader extends ProgressMonitor {
	private static final int MAX = 5;
	private static JavaGiacLoader dial;
	private static boolean isLoaded = false;

	private JavaGiacLoader(JFrame frame) {
		super(frame, "Loading JavaGiac", "", 0, MAX);
		dial = this;
		setMillisToDecideToPopup(1);
	}

	/**
	 * extract and load the javagiac lib and display a JDialog with progress
	 * status if frame is null display status in console
	 * 
	 * @param frame
	 * @throws IOException
	 */
	public static boolean loadJavaGiac(JFrame frame) {
		if (isLoaded) {
			return true;
		}
		if (frame != null) {
			new JavaGiacLoader(frame);
		} else {
			dial = null;
		}
		try {
			LoaderMonitorer.monitor("starting loading libraries");
			loadLib();
			LoaderMonitorer.monitor("ending loading libraries");
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
			if (frame != null) {
				JOptionPane.showMessageDialog(frame, "Fail to Load JavaGiac " + e.getMessage());
			}
			return false;
		}
		return true;

	}

	private static void loadLib() throws IOException {
		File folder;
		LoaderMonitorer.monitor("Trying to create folder libNuage");
		if ((folder = createFolderIfNeeded()) != null) {
			LoaderMonitorer.monitor("Folder libNuage created");
			copyLibGccIfNeeded(folder);
			copyLibStdCppIfNeeded(folder);
			File libJavaGiac = copyLibJavaGiacIfNeeded(folder);
			System.load(libJavaGiac.getCanonicalPath());
			setProgressToDial(5);
		} else {
			LoaderMonitorer.monitor("Fail to create Folder");
			throw new IOException("Unable to create folder " + new File(DllProvider.FOLDERPAH).getCanonicalPath());
		}
	}

	private static File copyLibJavaGiacIfNeeded(File folder) throws IOException {
		File destination = new File(folder, DllProvider.getJavaGiacFileName());
		loadThisLib(destination, DllProvider.getJavaJiac());
		setProgressToDial(4);
		return destination;
	}

	private static File copyLibStdCppIfNeeded(File folder) throws IOException {
		File destination = new File(folder, DllProvider.getLibStdCppFileName());
		loadThisLib(destination, DllProvider.getLibStdCpp());
		setProgressToDial(3);
		return destination;

	}

	private static File copyLibGccIfNeeded(File folder) throws IOException {
		File destination = new File(folder, DllProvider.getLibGccFileName());
		loadThisLib(destination, DllProvider.getLibGCC());
		setProgressToDial(2);
		return destination;

	}

	private static void loadThisLib(File destination, InputStream input) throws IOException, FileNotFoundException {
		LoaderMonitorer.monitor("starting copying lib : " + destination.getName());
		if (!destination.exists()) {
			if (!destination.createNewFile()) {
				throw new IOException("Unable to create file " + destination.getName());
			}
			LoaderMonitorer.monitor("copying lib : " + destination.getName());
			InputStreamToFileApp.copy(input, destination);
			LoaderMonitorer.monitor("ending copying lib : " + destination.getName());

		} else {
			LoaderMonitorer.monitor("The file " + destination.getName() + " already exists");
		}
	}

	private static File createFolderIfNeeded() {
		File folder = new File(DllProvider.FOLDERPAH);
		if (folder.exists() && folder.isDirectory()) {
			setProgressToDial(1);
			return folder;
		}
		setProgressToDial(1);
		return folder.mkdirs() ? folder : null;
	}

	private static void setProgressToDial(int nv) {
		if (dial != null) {
			dial.setProgress(nv);
		}
		LoaderMonitorer.monitor("Progress Status : " + nv + "/" + MAX);
	}

}
