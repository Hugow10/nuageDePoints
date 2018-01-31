package com.nuage.javagiac.interpretors.plane;

import java.util.LinkedList;

import com.nuage.javagiac.interpretors.XcasProg;
import com.nuage.javagiac.prog.ProgLoader;

import javagiac.context;
import javagiac.gen;

public class Plan0Contrainte extends XcasProg {

	public void run(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi) {
		parsingProg(ProgLoader.getPlan0ContraintesProg());
		putArguments(listXi, listYi, listZi);
	}

	protected void putArguments(LinkedList<Double> listXi, LinkedList<Double> listYi, LinkedList<Double> listZi) {
		algo = algo.substring(4, algo.length() - 1);
		algo = algo.replace("%1", getListForXcas(listXi));
		algo = algo.replace("%2", getListForXcas(listYi));
		algo = algo.replace("%3", getListForXcas(listZi));
		context c = new context();
		gen g = new gen(algo, c);
		res = g.eval(1, c).print(c);
	}

	public StringBuilder getInfos() {
		infos = new StringBuilder();
		String[] elements = (res.split(",")[1]).split(";");
		infos.append("Données mathématiques\n");
		infos.append("approx(listfeqplans[0]) = " + elements[0] + "\n");
		infos.append("approx(listfeqplans[1]) = " + elements[1] + "\n");
		infos.append("approx(listfeqplans[2]) = " + elements[2] + "\n");
		infos.append("approx(eqcartplanprojz(x,y,z)) = " + elements[3] + "\n");
		infos.append("approx(eqcartplanprojx(x,y,z)) = " + elements[4] + "\n");
		infos.append("approx(eqcartplanprojy(x,y,z)) = " + elements[5] + "\n");
		infos.append("approx(eqcartplanprojortho(x,y,z)) = " + elements[6] + "\n");
		infos.append("approx(fz(x,y,z)) = " + elements[7] + "\n");
		infos.append("approx(fx(x,y,z)) = " + elements[8] + "\n");
		infos.append("approx(fy(x,y,z)) = " + elements[9] + "\n");
		infos.append("approx(fortho(x,y,z)) = " + elements[10] + "\n");
		infos.append("approx(resorthomfz) = " + elements[11] + "\n");
		infos.append("approx(resorthomfx) = " + elements[12] + "\n");
		infos.append("approx(resorthomfy) = " + elements[13] + "\n");
		infos.append("approx(resorthomortho) = " + elements[14] + "\n");
		infos.append("approx(f(x,y)) = " + elements[15] + "\n");
		infos.append("approx(resorthomfxy) = " + elements[16] + "\n");
		return infos;
	}

}
