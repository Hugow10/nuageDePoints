package com.nuage.javagiac.interpretors.d2;

import java.util.LinkedList;

import com.nuage.javagiac.interpretors.XcasProg;
import com.nuage.javagiac.prog.ProgLoader;

import javagiac.context;
import javagiac.gen;

public class Nuages2D extends XcasProg {

	private int size;
	private int nombresContraintes;
	private int nombreFunctionsPhi;
	private LinkedList<Double> listXi;
	private LinkedList<Double> listYi;
	private LinkedList<String> listPhi;
	private LinkedList<Integer> listOrdreDerivation;
	private String function;

	public void run(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<String> listPhi,
			LinkedList<Double> listXomega, LinkedList<Double> listYomega, LinkedList<Integer> listOrdreDerivation) {
		parsingProg(ProgLoader.getNuage2DProg());
		size = listXi.size();
		nombresContraintes = listXomega.size();
		this.listXi = listXi;
		this.listYi = listYi;
		this.listPhi = listPhi;
		nombreFunctionsPhi = listPhi.size();
		this.listOrdreDerivation = listOrdreDerivation;
		putArguments(listXi, listYi, listPhi, listXomega, listYomega, listOrdreDerivation);
	}

	protected void putArguments(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<String> listPhi,
			LinkedList<Double> listXomega, LinkedList<Double> listYomega, LinkedList<Integer> listOrdreDerivation) {
		algo = algo.substring(4, algo.length() - 1);
		algo = algo.replace("%1", getListForXcas(listXi));
		System.out.println("xi size " + listXi.size());
		algo = algo.replace("%2", getListForXcas(listYi));
		System.out.println("yi size " + listYi.size());
		algo = algo.replace("%3", getListForXcas(listPhi));
		for (int i = 0; i < listPhi.size(); i++) {
			System.out.println(listPhi.get(i));
		}
		algo = algo.replace("%4", getListForXcas(listXomega));
		System.out.println("xomeage size " + listXomega.size());
		algo = algo.replace("%5", getListForXcas(listYomega));
		System.out.println("yomeage size " + listYomega.size());
		algo = algo.replace("%6", getListForXcas(listOrdreDerivation));
		System.out.println("derivation size " + listOrdreDerivation.size());
		context c = new context();
		gen g = new gen(algo, c);
		res = g.eval(1, c).print(c);
		System.out.println(res);
	}

	public StringBuilder getInfos() {
		infos = new StringBuilder();
		res = res.substring(7);
		String[] elements = res.split(";");
		for (int i = 0; i < elements.length; i++) {
			System.out.println(elements[i] + "\n");
		}
		function = elements[0].substring(1);
		infos.append("Données mathématiques\n");
		infos.append("Nombre de données brutes  : " + size + "\n");
		if (nombresContraintes > 0) {
			infos.append("Nombre de contraintes : " + nombresContraintes + "\n");
			infos.append("Contraintes\n");
			for (int i = 0; i < nombresContraintes; i++) {
				infos.append("xw : " + listXi.get(i) + ", Valeur contrainte : " + listYi.get(i)
						+ ", Ordre dérivation : " + listOrdreDerivation.get(i) + "\n");
			}
		}
		infos.append("\n\n");
		infos.append("Nombre de fonctions d'essai phi : " + nombreFunctionsPhi + "\n");
		infos.append("phi(x) ");
		for (int i = 0; i < nombreFunctionsPhi; i++) {
			infos.append(listPhi.get(i));
			if (i < nombreFunctionsPhi - 1) {
				infos.append(",");
			}
		}
		infos.append("\n\n");
		infos.append("f(x) = " + function + "\n");
		infos.append("Somme des résidus : " + elements[1] + "\n");
		infos.append("Résidus moyen : " + elements[2] + "\n");
		infos.append("Résidus carré moyen : " + elements[3] + "\n");
		infos.append("Qualité de représentation du nuage par f(x) : " + elements[23] + "\n");
		infos.append("Aire sous tendue par f(x) : " + elements[24].split(",")[0] + "\n");
		infos.append("Aire sous tendue par le polygone Mi: " + elements[25] + "\n");
		infos.append("x moyen : " + elements[4] + "\n");
		infos.append("y moyen : " + elements[5] + "\n");
		infos.append("x mini : " + elements[7] + "\n");
		infos.append("x maxi : " + elements[6] + "\n");
		infos.append("y mini : " + elements[9] + "\n");
		infos.append("y maxi : " + elements[8] + "\n");
		return infos;
	}

	public String getFunction() {
		return function;
	}

}
