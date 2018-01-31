package com.nuage.javagiac.interpretors;

import java.util.LinkedList;

import com.nuage.javagiac.interpretors.d2.Nuages2D;
import com.nuage.javagiac.interpretors.d3.Nuages3D;
import com.nuage.javagiac.interpretors.line.DroiteMoindreCarres;
import com.nuage.javagiac.interpretors.plane.Plan0Contrainte;
import com.nuage.javagiac.interpretors.plane.Plan1ContraintePoint;

public class Tests {

	public static void main(String[] args) {
		plan1Contrainte();
	}

	public static void test2D() {
		Nuages2D nuages2d = new Nuages2D();
		LinkedList<Double> listXi = new LinkedList<Double>();
		listXi.add(12.56637061436);
		listXi.add(15.70796326795);
		listXi.add(18.84955592154);
		listXi.add(20.94395102393);
		listXi.add(25.13274122872);
		listXi.add(26.17993877992);
		listXi.add(27.75073510671);
		listXi.add(29.32153143351);
		listXi.add(30.3687289847);
		listXi.add(32.4631240871);
		LinkedList<Double> listYi = new LinkedList<>();
		listYi.add(0.16);
		listYi.add(0.24);
		listYi.add(0.48);
		listYi.add(0.68);
		listYi.add(1.72);
		listYi.add(7.4);
		listYi.add(5.6);
		listYi.add(1.72);
		listYi.add(1.4);
		listYi.add(1.12);
		LinkedList<String> listPhi = new LinkedList<>();
		listPhi.add("x^3");
		listPhi.add("x^2");
		listPhi.add("x");
		listPhi.add("exp(-(x-26)^2)");
		listPhi.add("1");
		LinkedList<Double> listXomega = new LinkedList<>();
		listXomega.add(12.56637061436);
		LinkedList<Double> listYomega = new LinkedList<>();
		listYomega.add(0.16);
		LinkedList<Integer> listOrdreDerivation = new LinkedList<>();
		listOrdreDerivation.add(0);

		nuages2d.run(listXi, listYi, listPhi, listXomega, listYomega, listOrdreDerivation);
		System.out.println(nuages2d.getInfos());
	}

	public static void plan0Contrainte() {
		Plan0Contrainte plan = new Plan0Contrainte();
		LinkedList<Double> listXi = new LinkedList<Double>();
		listXi.add(-1.0);
		listXi.add(1.0);
		listXi.add(2.0);
		listXi.add(3.0);
		LinkedList<Double> listYi = new LinkedList<Double>();
		listYi.add(1.0);
		listYi.add(2.0);
		listYi.add(3.0);
		listYi.add(4.0);
		LinkedList<Double> listZi = new LinkedList<Double>();
		listZi.add(-1.0);
		listZi.add(6.0);
		listZi.add(11.0);
		listZi.add(16.0);
		plan.run(listXi, listYi, listZi);
		System.out.println(plan.getInfos());
	}

	public static void plan1Contrainte() {
		Plan1ContraintePoint plan = new Plan1ContraintePoint();
		LinkedList<Double> listXi = new LinkedList<>();
		listXi.add(-2.0);
		listXi.add(0.0);
		listXi.add(1.0);
		listXi.add(2.0);
		LinkedList<Double> listYi = new LinkedList<>();
		listYi.add(1.0);
		listYi.add(0.0);
		listYi.add(2.0);
		listYi.add(-1.0);
		LinkedList<Double> listZi = new LinkedList<>();
		listZi.add(3.0);
		listZi.add(-4.0);
		listZi.add(0.0);
		listZi.add(11.0);
		LinkedList<Double> listXomegaBT = new LinkedList<>();
		listXomegaBT.add(3.0);
		listXomegaBT.add(3.0);
		LinkedList<Double> listYomegaBT = new LinkedList<>();
		listYomegaBT.add(4.0);
		listYomegaBT.add(4.0);
		LinkedList<Double> listZomegaBT = new LinkedList<>();
		listZomegaBT.add(2.0);
		listZomegaBT.add(2.0);
		plan.run(listXi, listYi, listZi, listXomegaBT, listYomegaBT, listZomegaBT);
		System.out.println(plan.getInfos());
	}

	public static void test3D() {
		Nuages3D troisD = new Nuages3D();
		LinkedList<Double> listXi = new LinkedList<>();
		listXi.add(-2.0);
		listXi.add(0.0);
		listXi.add(1.0);
		listXi.add(2.0);
		LinkedList<Double> listYi = new LinkedList<>();
		listYi.add(1.0);
		listYi.add(0.0);
		listYi.add(2.0);
		listYi.add(-1.0);
		LinkedList<Double> listZi = new LinkedList<>();
		listZi.add(3.0);
		listZi.add(-4.0);
		listZi.add(0.0);
		listZi.add(-11.0);
		LinkedList<String> listPhi = new LinkedList<>();
		listPhi.add("x");
		listPhi.add("y");
		listPhi.add("1");
		LinkedList<Double> listXomega = new LinkedList<>();
		listXomega.add(3.0);
		LinkedList<Double> listYomega = new LinkedList<>();
		listYomega.add(4.0);
		LinkedList<Double> listZomega = new LinkedList<>();
		listZomega.add(2.0);
		LinkedList<Integer> listOrdreDerivation = new LinkedList<>();
		listOrdreDerivation.add(0);
		LinkedList<Double> listUx = new LinkedList<>();
		LinkedList<Double> listUy = new LinkedList<>();
		troisD.run(listXi, listYi, listZi, listPhi, listXomega, listYomega, listZomega, listOrdreDerivation, listUx,
				listUy);
		System.out.println(troisD.getInfos());
	}

	public static void dmcC2() {
		DroiteMoindreCarres dmc = new DroiteMoindreCarres();
		LinkedList<Double> listXi = new LinkedList<>();
		listXi.add(-1.0);
		listXi.add(2.0);
		listXi.add(5.0);
		LinkedList<Double> listYi = new LinkedList<>();
		listYi.add(1.0);
		listYi.add(4.0);
		listYi.add(9.0);
		LinkedList<Double> constraints = new LinkedList<>();
		constraints.add(0.0);
		constraints.add(0.0);
		constraints.add(0.0);
		constraints.add(0.0);
		dmc.run(listXi, listYi, 2, constraints, 2);
		System.out.println(dmc.getInfosC2());
	}

	public static void dmcC1() {
		DroiteMoindreCarres dmc = new DroiteMoindreCarres();
		LinkedList<Double> listXi = new LinkedList<>();
		listXi.add(-1.0);
		listXi.add(2.0);
		listXi.add(5.0);
		LinkedList<Double> listYi = new LinkedList<>();
		listYi.add(1.0);
		listYi.add(4.0);
		listYi.add(9.0);
		LinkedList<Double> constraints = new LinkedList<>();
		constraints.add(1.0);
		constraints.add(3.0);
		constraints.add(1.0);
		constraints.add(3.0);
		dmc.run(listXi, listYi, 1, constraints, 0);
		System.out.println(dmc.getInfosC0orC1());
	}

	public static void dmcC0() {
		DroiteMoindreCarres dmc = new DroiteMoindreCarres();
		LinkedList<Double> listXi = new LinkedList<>();
		listXi.add(1.0);
		listXi.add(3.0);
		listXi.add(5.0);
		LinkedList<Double> listYi = new LinkedList<>();
		listYi.add(5.0);
		listYi.add(9.0);
		listYi.add(25.0);
		LinkedList<Double> constraints = new LinkedList<>();
		constraints.add(0.0);
		constraints.add(0.0);
		constraints.add(0.0);
		constraints.add(0.0);
		dmc.run(listXi, listYi, 0, constraints, 0);
		System.out.println(dmc.getInfosC0orC1());
	}

	public static void testPourPisula() {
		Nuages2D n = new Nuages2D();
		LinkedList<Double> listXi = new LinkedList<>();
		listXi.add(0.02213);
		listXi.add(0.02459);
		listXi.add(0.0273);
		listXi.add(0.02976);
		listXi.add(0.03274);
		listXi.add(0.03597);
		listXi.add(0.03897);
		listXi.add(0.04155);
		listXi.add(0.04418);
		listXi.add(0.045);
		LinkedList<Double> listYi = new LinkedList<>();
		listYi.add(-0.00358);
		listYi.add(-0.01456);
		listYi.add(0.15953);
		listYi.add(1.04196);
		listYi.add(1.52963);
		listYi.add(1.27626);
		listYi.add(0.85025);
		listYi.add(0.36468);
		listYi.add(0.06704);
		listYi.add(0.04898);
		LinkedList<String> listPhi = new LinkedList<>();
		listPhi.add("x^3");
		listPhi.add("x^2");
		listPhi.add("x");
		listPhi.add("1");
		LinkedList<Double> listXomega = new LinkedList<>();
		listXomega.add(0.02213);
		listXomega.add(0.045);
		LinkedList<Double> listYomega = new LinkedList<>();
		listYomega.add(-0.00358);
		listYomega.add(0.04898);
		LinkedList<Integer> listOrdreDerivation = new LinkedList<>();
		listOrdreDerivation.add(0);
		listOrdreDerivation.add(0);

		n.run(listXi, listYi, listPhi, listXomega, listYomega, listOrdreDerivation);
		System.out.println(n.getInfos());
	}
}
