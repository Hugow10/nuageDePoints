package com.nuage.fileschoosers.modefiles;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import com.nuage.allmodes.Updatable;
import com.nuage.allmodes.d2.D2;
import com.nuage.allmodes.d3.D3;
import com.nuage.allmodes.line.Line;
import com.nuage.allmodes.plane.Plane;

public class ModeFileFilter extends FileFilter {

	public final static String gd = "gd";
	public final static String g2d = "g2d";
	public final static String g3d = "g3d";
	public final static String gp = "gpd";
	private Updatable mode;

	public ModeFileFilter(Updatable mode) {
		this.mode = mode;
	}

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String extension = getExtension(f);
		if (extension != null) {
			if (mode instanceof Line) {
				if (extension.equals(gd)) {
					return true;
				} else {
					return false;
				}
			} else if (mode instanceof D2) {
				if (extension.equals(g2d)) {
					return true;
				} else {
					return false;
				}
			} else if (mode instanceof D3) {
				if (extension.equals(g3d)) {
					return true;
				} else {
					return false;
				}
			} else if (mode instanceof Plane) {
				if (extension.equals(gp)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		if (mode instanceof Line) {
			return ".gd";
		}
		if (mode instanceof Plane) {
			return ".gpd";
		}
		if (mode instanceof D2) {
			return ".g2d";
		}
		return ".g3d";
	}

	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

}
