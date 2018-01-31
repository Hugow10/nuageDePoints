package com.nuage.javagiac.interpretors.plane;

import java.util.LinkedList;

import com.nuage.javagiac.interpretors.XcasProg;
import com.nuage.javagiac.prog.ProgLoader;

import javagiac.context;
import javagiac.gen;

public class Plan1ContraintePoint extends XcasProg {

	private LinkedList<Double> listXomega;
	private LinkedList<Double> listYomega;
	private LinkedList<Double> listZomega;

	public void run(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi,
			LinkedList<Double> listXomegaBT, LinkedList<Double> listYomegaBT, LinkedList<Double> listZomegaBT) {
		parsingProg(ProgLoader.getPlan1ContraintesProg());
		this.listXomega = listXomegaBT;
		this.listYomega = listYomegaBT;
		this.listZomega = listZomegaBT;
		putArguments(listXi, listYi, listZi, listXomegaBT, listYomegaBT, listZomegaBT);
	}

	protected void putArguments(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi,
			LinkedList<Double> listXomegaBT, LinkedList<Double> listYomegaBT, LinkedList<Double> listZomegaBT) {
		algo = algo.substring(4, algo.length() - 1);
		algo = algo.replace("%1", getListForXcas(listXi));
		algo = algo.replace("%2", getListForXcas(listYi));
		algo = algo.replace("%3", getListForXcas(listZi));
		algo = algo.replace("%4", getListForXcas(listXomegaBT));
		algo = algo.replace("%5", getListForXcas(listYomegaBT));
		algo = algo.replace("%6", getListForXcas(listZomegaBT));
		context c = new context();
		gen g = new gen(algo, c);
		res = g.eval(1, c).print(c);
	}

	public StringBuilder getInfos() {
		infos = new StringBuilder();
		String[] elements = (res.split(",")[1]).split(";");
		infos.append("Données mathématiques\n\n");
		infos.append("xw brut = " + listXomega.get(0) + "\n");
		infos.append("yw brut = " + listYomega.get(0) + "\n");
		infos.append("zw brut = " + listZomega.get(0) + "\n");
		infos.append("xw traité = " + listXomega.get(1) + "\n");
		infos.append("yw traité = " + listYomega.get(1) + "\n");
		infos.append("zw traité = " + listZomega.get(1) + "\n");
		infos.append("resorthomfz = " + elements[4] + "\n");
		infos.append("resorthomfx = " + elements[5] + "\n");
		infos.append("resorthomfy = " + elements[6] + "\n");
		infos.append("resorthomfortho = " + elements[7] + "\n");
		infos.append("z = fz(x;y) = " + elements[0] + "\n");
		infos.append("z = fx(x;y) = " + elements[1] + "\n");
		infos.append("z = fy(x;y) = " + elements[2] + "\n");
		infos.append("z = fortho(x;y) = " + elements[3] + "\n");
		return infos;
	}

}
