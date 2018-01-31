package com.nuage.javagiac.interpretors;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import com.nuage.Nuage;
import com.nuage.javagiac.loader.JavaGiacLoader;

import javagiac.context;
import javagiac.gen;
import javagiac.giac;

public abstract class XcasProg {

	protected String res;
	protected String algo;
	protected String path;
	protected StringBuilder infos;

	/**
	 * 
	 * @param ips
	 * 
	 */
	// le contenu des fichiers d'algo le stocke ensuite dans algo
	protected void parsingProg(InputStream ips) {
		JavaGiacLoader.loadJavaGiac(Nuage.getFrame());
		algo = null;
		try {
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				algo += ligne + "\n";
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param list
	 * @return la liste sous forme d'une chaîne de caractères syntaxiquement
	 *         correcte pour giac
	 */
	protected <E> CharSequence getListForXcas(LinkedList<E> list) {
		CharSequence res = "";
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null) {
					res = res + "" + list.get(i);
					if (i < list.size() - 1) {
						res = res + ",";
					}
				}
			}
		}
		return res;
	}

	public static double simpleEval(String expression) {
		JavaGiacLoader.loadJavaGiac(Nuage.getFrame());
		context c = new context();
		gen g = new gen(expression, c);
		gen h = giac._factor(g, c);
		return h.DOUBLE_val();
	}

}
