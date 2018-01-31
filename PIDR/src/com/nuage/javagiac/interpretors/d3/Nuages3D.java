package com.nuage.javagiac.interpretors.d3;

import java.util.LinkedList;

import com.nuage.javagiac.interpretors.XcasProg;
import com.nuage.javagiac.prog.ProgLoader;

import javagiac.context;
import javagiac.gen;

public class Nuages3D extends XcasProg {

	private int nbElements;
	private LinkedList<Double> listXomega;
	private LinkedList<Double> listYomega;
	private LinkedList<Double> listZomega;
	private LinkedList<Integer> listOrdreDerivation;
	private LinkedList<Double> listUx;
	private LinkedList<Double> listUy;
	private LinkedList<String> listPhi;
	private String function;

	public void run(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi,
			LinkedList<String> listPhi, LinkedList<Double> listXomega, LinkedList<Double> listYomega,
			LinkedList<Double> listZomega, LinkedList<Integer> listOrdreDerivation, LinkedList<Double> listUx,
			LinkedList<Double> listUy) {
		parsingProg(ProgLoader.getNuage3DProg());
		nbElements = listXi.size();
		this.listXomega = listXomega;
		this.listYomega = listYomega;
		this.listZomega = listZomega;
		this.listOrdreDerivation = listOrdreDerivation;
		this.listUx = listUx;
		this.listUy = listUy;
		this.listPhi = listPhi;
		print(listXi, "listXi");
		print(listYi, "listYi");
		print(listZi, "listZi");
		print(listPhi, "listPhi");
		print(listXomega, "listXomega");
		print(listYomega, "listYomega");
		print(listZomega, "listZomega");
		print(listOrdreDerivation, "listOrdreDerivation");
		print(listUx, "listUx");
		print(listUy, "listUy");
		putArguments(listXi, listYi, listZi, listPhi, listXomega, listYomega, listZomega, listOrdreDerivation, listUx,
				listUy);
	}

	private <E> void print(LinkedList<E> list, String functionName) {
		System.out.println("print de " + functionName);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	protected void putArguments(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi,
			LinkedList<String> listPhi, LinkedList<Double> listXomega, LinkedList<Double> listYomega,
			LinkedList<Double> listZomega, LinkedList<Integer> listOrdreDerivation, LinkedList<Double> listUx,
			LinkedList<Double> listUy) {
		algo = algo.substring(4, algo.length() - 1);
		algo = algo.replace("%10", getListForXcas(listUy));
		algo = algo.replace("%1", getListForXcas(listXi));
		algo = algo.replace("%2", getListForXcas(listYi));
		algo = algo.replace("%3", getListForXcas(listZi));
		algo = algo.replace("%4", getListForXcas(listPhi));
		algo = algo.replace("%5", getListForXcas(listXomega));
		algo = algo.replace("%6", getListForXcas(listYomega));
		algo = algo.replaceAll("%7", getListForXcas(listZomega).toString());
		algo = algo.replace("%8", getListForXcas(listOrdreDerivation));
		algo = algo.replace("%9", getListForXcas(listUx));
		System.out.println("uy");
		System.out.println(getListForXcas(listUy));
		System.out.println("xi");
		System.out.println(getListForXcas(listXi));
		System.out.println("yi");
		System.out.println(getListForXcas(listYi));
		System.out.println("zi");
		System.out.println(getListForXcas(listZi));
		System.out.println("phi");
		System.out.println(getListForXcas(listPhi));
		System.out.println("xomega");
		System.out.println(getListForXcas(listXomega));
		System.out.println("yomega");
		System.out.println(getListForXcas(listYomega));
		System.out.println("zomega");
		System.out.println(getListForXcas(listZomega));
		System.out.println("deriv");
		System.out.println(getListForXcas(listOrdreDerivation));
		System.out.println("ux");
		System.out.println(getListForXcas(listUx));
		context c = new context();
		gen g = new gen(algo, c);
		res = g.eval(1, c).print(c);
		System.out.println(res);
	}

	public StringBuilder getInfos() {
		infos = new StringBuilder();
		String[] elements = (res.split(",")[1]).split(";");
		function = elements[0].substring(1);
		infos.append("Données mathématiques\n\n");
		infos.append("Nombre de données brutes : " + nbElements + "\n\n");
		infos.append("Nombre de contraintes : " + listZomega.size() + "\n\n");
		if (listZomega.size() > 0) {
			infos.append("Contraintes\n");
			for (int i = 0; i < listXomega.size(); i++) {
				infos.append("xw : " + listXomega.get(i) + ", yw : " + listYomega.get(i) + ", zw : " + listZomega.get(i)
						+ ", ordre dérivation : " + listOrdreDerivation.get(i));
				if (listUx.size() > i && listUx.get(i) != null) {
					infos.append(", ux : " + listUx.get(i));
				}
				if (listUy.size() > i && listUy.get(i) == null) {
					infos.append(", uy : " + listUy.get(i));
				}
				infos.append("\n");
			}

		}
		infos.append("\n");
		infos.append("Nombre de fonctions d'essai : " + listPhi.size() + "\n");
		infos.append("phi(x) ");
		for (int i = 0; i < listPhi.size(); i++) {
			infos.append(listPhi.get(i));
			if (i < listPhi.size() - 1) {
				infos.append(", ");
			}
		}
		infos.append("\n");
		infos.append("f(x) = " + elements[0] + "\n");
		infos.append("Somme des résidus : " + elements[1] + "\n");
		infos.append("Résidus moyen : " + elements[2] + "\n");
		infos.append("Résidus carré moyen : " + elements[3] + "\n");
		infos.append("\n");
		infos.append("x moyen : " + elements[4] + "\n");
		infos.append("y moyen : " + elements[5] + "\n");
		infos.append("z moyen : " + elements[6] + "\n");
		infos.append("x mini : " + elements[8] + "\n");
		infos.append("x maxi : " + elements[7] + "\n");
		infos.append("y mini : " + elements[10] + "\n");
		infos.append("y maxi : " + elements[9] + "\n");
		infos.append("z mini : " + elements[12] + "\n");
		infos.append("z maxi : " + elements[11] + "\n");
		return infos;
	}

	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

}
