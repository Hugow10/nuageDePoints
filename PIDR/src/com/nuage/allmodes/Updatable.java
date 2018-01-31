package com.nuage.allmodes;

import javax.swing.table.AbstractTableModel;

import com.nuage.allmodes.d2andd3.TestFunction;
import com.nuage.allmodes.lineandplane.TransformX;
import com.nuage.allmodes.lineandplane.TransformY;

public interface Updatable {

	TableCustomModel getData();

	TestFunction getTestFunction();

	TransformX getTransformX();

	TransformY getTransformY();

	AbstractTableModel getDataModel();

}
