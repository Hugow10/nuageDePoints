package com.nuage.allmodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.nuage.allmodes.d2.D2;
import com.nuage.allmodes.d3.D3;
import com.nuage.allmodes.line.Line;
import com.nuage.allmodes.plane.Plane;
import com.nuage.allmodes.toolbar.InfosWindowFrame;
import com.nuage.allmodes.toolbar.Tools;
import com.nuage.javagiac.interpretors.d2.Nuages2D;
import com.nuage.javagiac.interpretors.d3.Nuages3D;
import com.nuage.javagiac.interpretors.line.DroiteMoindreCarres;

public class MainWindow extends Observable {

	private Tools tools;
	private Line line;
	private Plane plane;
	private D2 d2;
	private D3 d3;
	private JTabbedPane onglets;

	private StringBuilder infosLine;
	private StringBuilder currentInfos;

	private File currentFile = null;

	public MainWindow() {
		tools = new Tools(this);
		line = new Line();
		plane = new Plane();
		d2 = new D2();
		d3 = new D3();
		onglets = new JTabbedPane(SwingConstants.TOP);
		currentInfos = new StringBuilder("");
	}

	public void sendNewFile(MainWindow mainWindow, File file) {
		getMode().getData().warnView(mainWindow, file);
	}

	public void readFile(File file) {
		reset();
		FileReader fileReader;
		BufferedReader bufferedReader;
		String line, item;
		Scanner scanner;
		LinkedList<Double> listXi = new LinkedList<Double>();
		LinkedList<Double> listYi = new LinkedList<Double>();
		LinkedList<Double> listZi = new LinkedList<Double>();
		LinkedList<Double> listXiTraité = new LinkedList<Double>();
		LinkedList<Double> listYiTraité = new LinkedList<Double>();
		LinkedList<Double> listZiTraité = new LinkedList<Double>();
		LinkedList<Double> xwList = new LinkedList<Double>();
		LinkedList<Double> ywList = new LinkedList<Double>();
		LinkedList<Double> constraintValueList = new LinkedList<Double>();
		LinkedList<Integer> derivationOrderList = new LinkedList<Integer>();
		LinkedList<Double> uxList = new LinkedList<Double>();
		LinkedList<Double> uyList = new LinkedList<Double>();
		String xTreatment = "", yTreatment = "", zTreatment = "";
		Double xwbrut = null, ywbrut = null, xwtraité = null, ywtraité = null, pente = null;
		String textBoxFx = "";
		String textBoxCompoFx = "";
		String mode, scanResult;
		try {
			fileReader = new FileReader(file.getAbsolutePath());
			bufferedReader = new BufferedReader(fileReader);
			line = bufferedReader.readLine();
			mode = line;
			while (line != null) {
				scanner = new Scanner(line);
				scanner.useDelimiter(";");
				item = scanner.next();
				if (item.equals("DB")) {
					int index;
					index = Integer.parseInt(scanner.next()) - 1;
					if (index > listXi.size() - 1) {
						listXi.add(Double.parseDouble(scanner.next()));
					} else {
						listXi.add(index, Double.parseDouble(scanner.next()));
					}
					if (index > listYi.size() - 1) {
						listYi.add(Double.parseDouble(scanner.next()));
					} else {
						listYi.add(index, Double.parseDouble(scanner.next()));
					}
					if (mode.equals("3DKC") || mode.equals("2PLMC")) {
						if (index > listZi.size() - 1) {
							listZi.add(Double.parseDouble(scanner.next()));
						} else {
							listZi.add(index, Double.parseDouble(scanner.next()));
						}
					}
				}
				if (item.equals("DT")) {
					int index;
					index = Integer.parseInt(scanner.next()) - 1;
					if (index > listXiTraité.size() - 1) {
						listXiTraité.add(Double.parseDouble(scanner.next()));
					} else {
						listXiTraité.add(index, Double.parseDouble(scanner.next()));
					}
					if (index > listYiTraité.size() - 1) {
						listYiTraité.add(Double.parseDouble(scanner.next()));
					} else {
						listYiTraité.add(index, Double.parseDouble(scanner.next()));
					}
					if (mode.equals("3DKC") || mode.equals("2PLMC")) {
						if (index > listZiTraité.size() - 1) {
							listZiTraité.add(Double.parseDouble(scanner.next()));
						} else {
							listZiTraité.add(index, Double.parseDouble(scanner.next()));
						}
					}
				}
				if (item.trim().equals("Contrainte:")) {
					int index;
					index = Integer.parseInt(scanner.next()) - 1;
					if (index > xwList.size() - 1) {
						xwList.add(Double.parseDouble(scanner.next()));
					} else {
						xwList.add(index, Double.parseDouble(scanner.next()));
					}
					if (mode.equals("3DKC")) {
						if (index > ywList.size() - 1) {
							ywList.add(Double.parseDouble(scanner.next()));
						} else {
							ywList.add(Double.parseDouble(scanner.next()));
						}
					}
					if (index > constraintValueList.size() - 1) {
						constraintValueList.add(Double.parseDouble(scanner.next()));
					} else {
						constraintValueList.add(index, Double.parseDouble(scanner.next()));
					}
					if (index > derivationOrderList.size() - 1) {
						derivationOrderList.add(Integer.parseInt(scanner.next()));
					} else {
						derivationOrderList.add(index, Integer.parseInt(scanner.next()));
					}
					if (mode.equals("3DKC")) {
						scanResult = scanner.next();
						if (scanResult.isEmpty()) {
							if (index > uxList.size() - 1) {
								uxList.add(0.0);
							} else {
								uxList.add(index, 0.0);
							}
						} else {
							if (index > uxList.size() - 1) {
								uxList.add(Double.parseDouble(scanResult));
							} else {
								uxList.add(index, Double.parseDouble(scanResult));
							}
						}
						scanResult = scanner.next();
						if (scanResult.isEmpty()) {
							if (index > uyList.size() - 1) {
								uyList.add(0.0);
							} else {
								uyList.add(index, 0.0);
							}
						} else {
							if (index > uyList.size() - 1) {
								uyList.add(Double.parseDouble(scanResult));
							} else {
								uyList.add(index, Double.parseDouble(scanResult));
							}
						}
					}
				}
				if (item.equals("X=tx(x)") && scanner.hasNext()) {
					xTreatment = scanner.next();
				}
				if (item.equals("Y=ty(y)") && scanner.hasNext()) {
					yTreatment = scanner.next();
				}
				if (item.equals("Z=tz(z)") && scanner.hasNext()) {
					zTreatment = scanner.next();
				}
				if (item.equals("xw brut") && scanner.hasNext()) {
					xwbrut = Double.parseDouble(scanner.next());
				}
				if (item.equals("yw brut") && scanner.hasNext()) {
					ywbrut = Double.parseDouble(scanner.next());
				}
				if (item.equals("xw traité") && scanner.hasNext()) {
					xwtraité = Double.parseDouble(scanner.next());
				}
				if (item.equals("yw traité") && scanner.hasNext()) {
					ywtraité = Double.parseDouble(scanner.next());
				}
				if (item.equals("pente") && scanner.hasNext()) {
					pente = Double.parseDouble(scanner.next());
				}
				if (item.equals("TextBoxfx :") && scanner.hasNext()) {
					textBoxFx = scanner.next();
				}
				if (item.equals("TextBoxCompoFx :") && scanner.hasNext()) {
					textBoxCompoFx = scanner.next();
				}
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
		} catch (NoSuchElementException g) {
			new PopUp(
					"La lecture de la sauvegarde a échoué. S'il ne s'agit pas d'une sauvegarde de l'ancien logiciel, merci de contacter les développeurs.");
		}
		/*
		 * System.out.println("listXi "+listXi);
		 * System.out.println("listYi "+listYi);
		 * System.out.println("listZi "+listZi);
		 * System.out.println("listXiTraité "+listXiTraité);
		 * System.out.println("listYiTraité "+listYiTraité);
		 * System.out.println("listZiTraité "+listZiTraité);
		 * System.out.println("xwList "+xwList);
		 * System.out.println("yxList "+ywList);
		 * System.out.println("constraintValueList "+constraintValueList);
		 * System.out.println("derivationOrderList "+derivationOrderList);
		 * System.out.println("uxList "+uxList);
		 * System.out.println("uyList "+uyList);
		 * System.out.println("xTreatment "+xTreatment);
		 * System.out.println("yTreatment "+yTreatment);
		 * System.out.println("zTreatment "+zTreatment);
		 * System.out.println("xwbrut "+xwbrut);
		 * System.out.println("ywbrut "+ywbrut);
		 * System.out.println("xwtraité "+xwtraité);
		 * System.out.println("ywtraité "+ywtraité);
		 * System.out.println("pente "+pente);
		 * System.out.println("textBoxFx "+textBoxFx);
		 * System.out.println("textBoxCompoFx "+textBoxCompoFx);
		 */

		switch (getOnglets().getSelectedIndex()) {
		case 0:
			Line lineMode = (Line) getMode();
			lineMode.getData().fillPoints(listXi, listYi, null);
			lineMode.getTransformX().setTransformX(xTreatment);
			lineMode.getTransformY().setTransformY(yTreatment);
			lineMode.getDataLinePanel().getTc2dmTrans().fillPoints(listXiTraité, listYiTraité, null);
			if (pente != null) {
				lineMode.getLineConstraintsChoice().getSlopeConstraints().setSlopeText("" + pente);
			}
			if (xwbrut != null) {
				lineMode.getLineConstraintsChoice().getPointConstraint().setxWTrueText("" + xwbrut);
			}
			if (ywbrut != null) {
				lineMode.getLineConstraintsChoice().getPointConstraint().setyWTrueText("" + ywbrut);
			}
			if (xwtraité != null) {
				lineMode.getLineConstraintsChoice().getPointConstraint().setxWTreatedText("" + xwtraité);
			}
			if (ywtraité != null) {
				lineMode.getLineConstraintsChoice().getPointConstraint().setyWTreatedText("" + ywtraité);
			}
			break;
		case 1:
			D2 d2Mode = (D2) getMode();
			d2Mode.getData().fillPoints(listXi, listYi, null);
			d2Mode.getTestFunction().getText().setText(textBoxFx);
			d2Mode.getTwoDCalculatedFunction().getFunctionCalculated().setText(textBoxCompoFx);
			d2Mode.getTwoDConstraints().fillConstraints(xwList, constraintValueList, derivationOrderList);
			break;
		case 2:
			Plane planeMode = (Plane) getMode();
			planeMode.getData().fillPoints(listXi, listYi, listZi);
			planeMode.getTransformX().setTransformX(xTreatment);
			planeMode.getTransformY().setTransformY(yTreatment);
			planeMode.getTransformZ().setTransformZ(zTreatment);
			planeMode.getDataPlanePanel().getTc3dmTrans().fillPoints(listXiTraité, listYiTraité, listZiTraité);
			break;
		case 3:
			D3 d3Mode = (D3) getMode();
			d3Mode.getData().fillPoints(listXi, listYi, listZi);
			d3Mode.getTestFunction().getText().setText(textBoxFx);
			d3Mode.getThreeDCalculatedFunction().getFunctionCalculated().setText(textBoxCompoFx);
			d3Mode.getThreeDConstraints().fillConstraints(xwList, ywList, constraintValueList, derivationOrderList,
					uxList, uyList);
			break;
		}
	}

	public void saveInFile(File file) {
		currentFile = file;
		Updatable mode = getMode();
		if (mode instanceof Line) {
			System.out.println("Hello");
			LinkedList<Double> listXi = mode.getData().getX();
			LinkedList<Double> listYi = mode.getData().getY();
			LinkedList<Double> transformedListXi = ((Line) mode).getDataLinePanel().getTc2dmTrans().getX();
			LinkedList<Double> transformedListYi = ((Line) mode).getDataLinePanel().getTc2dmTrans().getY();
			String transformX = mode.getTransformX().getTransformX().getText();
			String transformY = mode.getTransformY().getTransformY().getText();
			String xwBrut = ((Line) mode).getLineConstraintsChoice().getPointConstraint().getxWTrue().getText();
			String ywBrut = ((Line) mode).getLineConstraintsChoice().getPointConstraint().getyWTrue().getText();
			String xwTraité = ((Line) mode).getLineConstraintsChoice().getPointConstraint().getxWTreated().getText();
			String ywTraité = ((Line) mode).getLineConstraintsChoice().getPointConstraint().getyWTreated().getText();
			String pente = ((Line) mode).getLineConstraintsChoice().getSlopeConstraints().getSlope().getText();
			StringBuilder saveContent = new StringBuilder("");

			saveContent.append("2DMC\n");
			for (int i = 0; i < listXi.size(); i++) {
				System.out.println("DB");
				saveContent.append("DB;" + (i + 1) + ";" + listXi.get(i) + ";" + listYi.get(i) + "\n");
			}
			for (int i = 0; i < transformedListXi.size(); i++) {
				System.out.println("DT");
				saveContent.append(
						"DT;" + (i + 1) + ";" + transformedListXi.get(i) + ";" + transformedListYi.get(i) + "\n");
			}

			// TODO ajout polygone ?

			saveContent.append("X=tx(x);" + transformX + "\n");
			saveContent.append("Y=ty(y);" + transformY + "\n");
			saveContent.append("xw brut;" + xwBrut + "\n");
			saveContent.append("yw brut;" + ywBrut + "\n");
			saveContent.append("xw traité;" + xwTraité + "\n");
			saveContent.append("yw traité;" + ywTraité + "\n");
			saveContent.append("pente;" + pente + "\n");

			try {
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(saveContent.toString());
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Line lineMode = (Line) getMode();
			// lineMode.getData().fillPoints(listXi, listYi, null);
			// lineMode.getTransformX().setTransformX(xTreatment);
			// lineMode.getTransformY().setTransformY(yTreatment);
			// lineMode.getDataLinePanel().getTc2dmTrans().fillPoints(listXiTraité,
			// listYiTraité, null);
			// if (pente != null) {
			// lineMode.getLineConstraintsChoice().getSlopeConstraints().setSlopeText(""
			// + pente);
			// }
			// if (xwbrut != null) {
			// lineMode.getLineConstraintsChoice().getPointConstraint().setxWTrueText(""
			// + xwbrut);
			// }
			// if (ywbrut != null) {
			// lineMode.getLineConstraintsChoice().getPointConstraint().setyWTrueText(""
			// + ywbrut);
			// }
			// if (xwtraité != null) {
			// lineMode.getLineConstraintsChoice().getPointConstraint().setxWTreatedText(""
			// + xwtraité);
			// }
			// if (ywtraité != null) {
			// lineMode.getLineConstraintsChoice().getPointConstraint().setyWTreatedText(""
			// + ywtraité);
			// }

		}
		if (mode instanceof Plane) {

		}
		if (mode instanceof D2) {

		}
		if (mode instanceof D3) {

		}
	}

	public void reset() {
		currentFile = null;
		Updatable mode = getMode();
		if (mode instanceof Line) {
			mode.getTransformX().getTransformX().setText("");
			mode.getTransformY().getTransformY().setText("");
			((Line) mode).getLineConstraintsChoice().getPointConstraint().setxWTreatedText("");
			((Line) mode).getLineConstraintsChoice().getPointConstraint().setyWTreatedText("");
			((Line) mode).getLineConstraintsChoice().getPointConstraint().setxWTrueText("");
			((Line) mode).getLineConstraintsChoice().getPointConstraint().setyWTrueText("");
			((Line) mode).getLineConstraintsChoice().getSlopeConstraints().getSlope().setText("");
			mode.getData().fillPoints(new LinkedList<Double>(), new LinkedList<Double>(), null);
			mode.getData().fireTableDataChanged();
			((Line) mode).getDataLinePanel().getTc2dmTrans().fillPoints(new LinkedList<Double>(),
					new LinkedList<Double>(), null);
			((Line) mode).getDataLinePanel().getTc2dmTrans().fireTableDataChanged();
		}
		if (mode instanceof Plane) {
			mode.getTransformX().getTransformX().setText("");
			mode.getTransformY().getTransformY().setText("");
			((Plane) mode).getTransformZ().getTransformZ().setText("");
			mode.getData().fillPoints(new LinkedList<Double>(), new LinkedList<Double>(), new LinkedList<Double>());
			mode.getData().fireTableDataChanged();
			((Plane) mode).getPlaneData().fillPoints(new LinkedList<Double>(), new LinkedList<Double>(),
					new LinkedList<Double>());
			mode.getData().fireTableDataChanged();
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getX().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getY().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getZ().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexIJSlope().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexJKSlope().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexKISlope().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexVXIJ().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexVXJK().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexVXKI().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexVYIJ().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexVYJK().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexVYKI().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexIJSlope().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexJKSlope().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getOneConstraintChoice().getVertexConstraintChoice()
					.getVertexKISlope().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getTwoPointCoordConstraint().getXw1()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getTwoPointCoordConstraint().getXw2()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getTwoPointCoordConstraint().getYw1()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getTwoPointCoordConstraint().getYw2()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getTwoPointCoordConstraint().getZw1()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getTwoPointCoordConstraint().getZw2()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints()
					.getVertexVXIJ().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints()
					.getVertexVXJK().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints()
					.getVertexVXKI().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints()
					.getVertexVYIJ().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints()
					.getVertexVYJK().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints()
					.getVertexVYKI().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints().getXij()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints().getXjk()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints().getXki()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints().getYij()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints().getYjk()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints().getYki()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints().getZij()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints().getZjk()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getCoordAndVertexConstraints().getZki()
					.setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2XoYModel().getV1x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2XoYModel().getV1y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2XoYModel().getV2x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2XoYModel().getV2y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2YoZModel().getV1x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2YoZModel().getV1y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2YoZModel().getV2x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2YoZModel().getV2y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2ZoXModel().getV1x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2ZoXModel().getV1y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2ZoXModel().getV2x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1XoYV2ZoXModel().getV2y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2YoZModel().getV1x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2YoZModel().getV1y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2YoZModel().getV2x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2YoZModel().getV2y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2ZoXModel().getV1x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2ZoXModel().getV1y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2ZoXModel().getV2x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2ZoXModel().getV2y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1ZoXV2ZoXModel().getV1x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1ZoXV2ZoXModel().getV1y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1ZoXV2ZoXModel().getV2x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1ZoXV2ZoXModel().getV2y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2ZoXModel().getV1x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2ZoXModel().getV1y().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2ZoXModel().getV2x().setText("");
			((Plane) mode).getPlaneConstraintsChoice().getTwoConstraintChoice().getSlopeAndVertexConstraint()
					.getV1YoZV2ZoXModel().getV2y().setText("");
		}
		if (mode instanceof D2) {
			mode.getTestFunction().getText().setText("");
			mode.getData().fillPoints(new LinkedList<Double>(), new LinkedList<Double>(), new LinkedList<Double>());
			mode.getData().fireTableDataChanged();
			((D2) mode).getTwoDConstraints().fillConstraints(new LinkedList<Double>(), new LinkedList<Double>(),
					new LinkedList<Integer>());
			((D2) mode).getTwoDConstraints().fireTableDataChanged();
			mode.getTestFunction().setValue("");
			((D2) mode).getTwoDCalculatedFunction().getCalculatedPoint().setText("");
			((D2) mode).getTwoDCalculatedFunction().getFunctionCalculated().setText("");
			((D2) mode).getTwoDCalculatedFunction().getPoint().setText("");
		}
		if (mode instanceof D3) {
			mode.getTestFunction().getText().setText("");
			mode.getData().fillPoints(new LinkedList<Double>(), new LinkedList<Double>(), new LinkedList<Double>());
			mode.getData().fireTableDataChanged();
			((D3) mode).getThreeDConstraints().fillConstraints(new LinkedList<Double>(), new LinkedList<Double>(),
					new LinkedList<Double>(), new LinkedList<Integer>(), new LinkedList<Double>(),
					new LinkedList<Double>());
			((D3) mode).getThreeDConstraints().fireTableDataChanged();
			((D3) mode).getThreeDCalculatedFunction().getCalculatedPoint().setText("");
			((D3) mode).getThreeDCalculatedFunction().getFunctionCalculated().setText("");
			((D3) mode).getThreeDCalculatedFunction().getPoint().setText("");
		}
	}

	public void runMath() {
		// Droite 0/1/2 + data (via cdv)
		if (getOnglets().getSelectedIndex() == 0) {
			DroiteMoindreCarres dmc = new DroiteMoindreCarres();
			Line mode = (Line) getMode();
			String tX = mode.getTransformX().getTransformX().getText();
			String tY = mode.getTransformY().getTransformY().getText();
			if (!tX.equals("")) {

			}
			if (!tY.equals("")) {

			}
			if (mode.getLineConstraintsChoice().getNoConstraintButton().isSelected()) {
				dmc.run(mode.getData().getX(), mode.getData().getY(), 0, new LinkedList<Double>(), 0);
				infosLine = dmc.getInfosC0orC1();
			}
			if (mode.getLineConstraintsChoice().getPointConstraintButton().isSelected()) {
				dmc.run(mode.getData().getX(), mode.getData().getY(), 1,
						mode.getLineConstraintsChoice().getPointConstraint().getOmegaDatas(), 0);
				infosLine = dmc.getInfosC0orC1();
			}
			if (mode.getLineConstraintsChoice().getSlopeConstraintButton().isSelected()) {
				dmc.run(mode.getData().getX(), mode.getData().getY(), 2,
						mode.getLineConstraintsChoice().getPointConstraint().getOmegaDatas(),
						Double.parseDouble(mode.getLineConstraintsChoice().getSlopeConstraints().getSlope().getText()));
				infosLine = dmc.getInfosC2();
			}
			mode.getLineGraph().fill(mode.getData().getX(), mode.getData().getY());
			currentInfos = infosLine;
		}
		// TODO Plans 0/1/2 +++++
		// 2D : phi + datas
		if (getOnglets().getSelectedIndex() == 1) {
			Nuages2D nuages2D = new Nuages2D();
			D2 mode = (D2) getMode();
			String[] phi = mode.getTestFunction().getText().getText().split(",");
			LinkedList<String> phiList = new LinkedList<>();
			for (int i = 0; i < phi.length; i++) {
				phiList.add(phi[i]);
			}
			LinkedList<Integer> derivationList = new LinkedList<>();
			LinkedList<Double> oldDerivationList = mode.getTwoDConstraints().getDerivationOrder();
			for (int i = 0; i < oldDerivationList.size(); i++) {
				derivationList.add(Integer.parseInt("" + oldDerivationList.get(i).intValue()));
			}
			// Y avait ça a la fin de run :
			// mode.getTwoDConstraints().getColumn(3)
			nuages2D.run(mode.getData().getX(), mode.getData().getY(), phiList, mode.getTwoDConstraints().getXw(),
					mode.getTwoDConstraints().getConstraintValue(), derivationList);
			currentInfos = nuages2D.getInfos();
			mode.getTwoDCalculatedFunction().setFunctionCalculated(nuages2D.getFunction());
		}
		// 3D : phi + datas
		if (getOnglets().getSelectedIndex() == 3) {
			Nuages3D nuages3D = new Nuages3D();
			D3 mode = (D3) getMode();
			String[] phi = mode.getTestFunction().getText().getText().split(",");
			LinkedList<String> phiList = new LinkedList<>();
			for (int i = 0; i < phi.length; i++) {
				phiList.add(phi[i]);
			}
			LinkedList<Integer> derivationList = new LinkedList<>();
			LinkedList<Double> oldDerivationList = mode.getThreeDConstraints().getDerivationOrder();
			for (int i = 0; i < oldDerivationList.size(); i++) {
				derivationList.add(Integer.parseInt("" + oldDerivationList.get(i).intValue()));
			}
			nuages3D.run(mode.getData().getX(), mode.getData().getY(), mode.getData().getZ(), phiList,
					mode.getThreeDConstraints().getXw(), mode.getThreeDConstraints().getYw(),
					mode.getThreeDConstraints().getConstraintValue(), derivationList,
					mode.getThreeDConstraints().getUx(), mode.getThreeDConstraints().getUy());
			currentInfos = nuages3D.getInfos();
			mode.getThreeDCalculatedFunction().setFunctionCalculated(nuages3D.getFunction());
			// TODO mode.getD3Graph().update(nuages3D.getFunction());
		}
		InfosWindowFrame.getCurrent().setInfos(currentInfos);
		setChanged();
		notifyObservers();
	}

	public void updateInfos() {
		InfosWindowFrame.getCurrent().setInfos(currentInfos);
		setChanged();
		notifyObservers("infos");
	}

	public Updatable getMode() {
		UpdatablePanel modePanel = (UpdatablePanel) onglets.getSelectedComponent();
		Updatable onglet = modePanel.getOnglet();
		return onglet;
	}

	public Tools getTools() {
		return tools;
	}

	public void setCurrentFile(File file) {
		currentFile = file;
	}

	public File getCurrentFile() {
		return currentFile;
	}

	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * @return the plane
	 */
	public Plane getPlane() {
		return plane;
	}

	/**
	 * @return the d2
	 */
	public D2 getD2() {
		return d2;
	}

	/**
	 * @return the d3
	 */
	public D3 getD3() {
		return d3;
	}

	/**
	 * @return the onglets
	 */
	public JTabbedPane getOnglets() {
		return onglets;
	}

}
