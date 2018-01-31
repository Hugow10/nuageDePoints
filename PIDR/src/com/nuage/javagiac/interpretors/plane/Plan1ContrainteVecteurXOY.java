package com.nuage.javagiac.interpretors.plane;

import java.util.LinkedList;

import com.nuage.javagiac.interpretors.XcasProg;
import com.nuage.javagiac.prog.ProgLoader;

import javagiac.context;
import javagiac.gen;

public class Plan1ContrainteVecteurXOY extends XcasProg {

	public void run(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi,
			double vecteurDxOYX, double vecteurDxOYY, double penteXOY) {
		parsingProg(ProgLoader.getPlan1ContraintesXOYProg());
		putArguments(listXi, listYi, listZi, vecteurDxOYX, vecteurDxOYY, penteXOY);
	}

	protected void putArguments(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi,
			double vecteurDxOYX, double vecteurDxOYY, double penteXOY) {
		algo = algo.substring(4, algo.length() - 1);
		algo = algo.replace("%1", getListForXcas(listXi));
		algo = algo.replace("%2", getListForXcas(listYi));
		algo = algo.replace("%3", getListForXcas(listZi));
		algo = algo.replace("%4", "" + vecteurDxOYX);
		algo = algo.replace("%5", "" + vecteurDxOYY);
		algo = algo.replace("%6", "" + penteXOY);
		context c = new context();
		gen g = new gen(algo, c);
		res = g.eval(1, c).print(c);
	}

	// TODO: create getInfos()

}
