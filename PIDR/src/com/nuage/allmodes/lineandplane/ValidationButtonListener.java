package com.nuage.allmodes.lineandplane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.nuage.allmodes.PopUp;
import com.nuage.allmodes.line.Line;
import com.nuage.javagiac.loader.JavaGiacLoader;

import javagiac.context;
import javagiac.gen;
import javagiac.giac;

public class ValidationButtonListener implements ActionListener {

	private Line lineReference;

	public ValidationButtonListener(Line line) {
		lineReference = line;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JavaGiacLoader.loadJavaGiac((JFrame) JFrame.getFrames()[0]);
		String formulaXReference = lineReference.getTransformationsLine().getTransformX().getTransformX().getText();
		String formulaYReference = lineReference.getTransformationsLine().getTransformY().getTransformY().getText();

		LinkedList<Double> newCalculatedXPoints = new LinkedList<>();
		LinkedList<Double> newCalculatedYPoints = new LinkedList<>();

		if (formulaXReference != null && !formulaXReference.equals("")) {
			if (!formulaXReference.contains("x")) {
				new PopUp(formulaXReference, "x");
			} else {
				context C = new context();
				gen g;
				String formulaXCopy;
				for (int i = 0; i < lineReference.getData().getX().size(); i++) {
					formulaXCopy = formulaXReference.replaceAll("x", "" + lineReference.getData().getX().get(i));
					g = new gen(formulaXCopy, C);
					newCalculatedXPoints.add(Double.parseDouble(giac._factor(g, C).print(C)));
				}
			}
		}

		if (formulaYReference != null && !formulaYReference.equals("")) {
			if (!formulaYReference.contains("y")) {
				new PopUp(formulaYReference, "y");
			} else {
				context C = new context();
				gen g;
				String formulaYCopy;
				for (int i = 0; i < lineReference.getData().getY().size(); i++) {
					formulaYCopy = formulaYReference.replaceAll("y", "" + lineReference.getData().getY().get(i));
					g = new gen(formulaYCopy, C);
					newCalculatedYPoints.add(Double.parseDouble(giac._factor(g, C).print(C)));
				}
			}
		}

		lineReference.getDataLinePanel().getTc2dmTrans().fillPoints(newCalculatedXPoints, newCalculatedYPoints, null);
	}

}
