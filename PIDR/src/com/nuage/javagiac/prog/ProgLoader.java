package com.nuage.javagiac.prog;

import java.io.InputStream;

public class ProgLoader {

	public static InputStream getNuage2DProg() {
		return ProgLoader.class.getResourceAsStream("nuages2D.cas");
	}

	public static InputStream getDroiteDesMoindresCarresProg() {
		return ProgLoader.class.getResourceAsStream("droite des moindres carres.cas");
	}

	public static InputStream getNuage3DProg() {
		return ProgLoader.class.getResourceAsStream("nuages3D.cas");
	}

	public static InputStream getPlan0ContraintesProg() {
		return ProgLoader.class.getResourceAsStream("plan 0 contrainte.cas");
	}

	public static InputStream getPlan1ContraintesProg() {
		return ProgLoader.class.getResourceAsStream("plan 1 contraintes sur point.cas");
	}

	public static InputStream getPlan1ContraintesXOYProg() {
		return ProgLoader.class.getResourceAsStream("plan 1 contraintes sur vecteur xoy.cas");
	}

	public static InputStream getPlan1ContraintesYOZProg() {
		return ProgLoader.class.getResourceAsStream("plan 1 contraintes sur vecteur yoz.cas");
	}

	public static InputStream getPlan1ContraintesZOXProg() {
		return ProgLoader.class.getResourceAsStream("plan 1 contraintes sur vecteur zox.cas");
	}

	public static InputStream getPlan2ContraintesProg() {
		return ProgLoader.class.getResourceAsStream("plan 2 contraintes sur 2 points.cas");
	}

}
