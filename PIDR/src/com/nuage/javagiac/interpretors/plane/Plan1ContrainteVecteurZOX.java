package com.nuage.javagiac.interpretors.plane;

import java.util.LinkedList;

import com.nuage.javagiac.interpretors.XcasProg;
import com.nuage.javagiac.prog.ProgLoader;

import javagiac.context;
import javagiac.gen;

public class Plan1ContrainteVecteurZOX extends XcasProg {

	public void run(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi,
			double vecteurDzOXZ, double vecteurDzOXX, double penteZOX) {
		parsingProg(ProgLoader.getPlan1ContraintesZOXProg());
		putArguments(listXi, listYi, listZi, vecteurDzOXZ, vecteurDzOXX, penteZOX);
	}

	protected void putArguments(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi,
			double vecteurDzOXZ, double vecteurDzOXX, double penteZOX) {
		algo = algo.substring(4, algo.length() - 1);
		algo = algo.replace("%1", getListForXcas(listXi));
		algo = algo.replace("%2", getListForXcas(listYi));
		algo = algo.replace("%3", getListForXcas(listZi));
		algo = algo.replace("%4", "" + vecteurDzOXZ);
		algo = algo.replace("%5", "" + vecteurDzOXX);
		algo = algo.replace("%6", "" + penteZOX);
		context c = new context();
		gen g = new gen(algo, c);
		res = g.eval(1, c).print(c);
	}

	// TODO: create getInfos()
}
