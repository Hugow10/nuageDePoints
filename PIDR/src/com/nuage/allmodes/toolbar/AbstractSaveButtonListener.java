package com.nuage.allmodes.toolbar;

import java.awt.event.ActionListener;

import com.nuage.allmodes.Updatable;
import com.nuage.allmodes.d2.D2;
import com.nuage.allmodes.line.Line;
import com.nuage.allmodes.plane.Plane;

public abstract class AbstractSaveButtonListener implements ActionListener {

	protected Tools toolsReference;
	protected InfosWindow infosWindow;

	public AbstractSaveButtonListener(Tools toolsReference) {
		this.toolsReference = toolsReference;
	}

	public AbstractSaveButtonListener(InfosWindow infosWindow) {
		this.infosWindow = infosWindow;
	}

	public String getDescription() {
		Updatable mode = toolsReference.getMainWindow().getMode();
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
}
