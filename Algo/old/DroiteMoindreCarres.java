package model.math;

import java.util.LinkedList;

import javagiac.context;
import javagiac.gen;

public class DroiteMoindreCarres extends MathAlgo {

	private context c;
	private LinkedList<Double> listXi, listYi, listXxi, listYyi, longueurI;
	private int n;
	private double xMoyen, yMoyen, xOmega, yOmega, xmaxgraph, xmingraph, ymaxgraph, ymingraph,
					p2, residuVerticalMoyen, residuOrthogonalMoyen, longueurDroite, longueurPolygone,
					facteurQualite, v1, v2, h1, h2, denominateur, alpha, teta, o1, o2, residuMoyenOrthoProjVertical,
					residuMoyenOrthoProjHorizontal, residuMoyenOrthoProjOrthogonal, residuCarreMoyenOrthoProjVertical,
					residuCarreMoyenOrthoProjHorizontal, residuCarreMoyenOrthoProjOrthogonal, residuCarreMoyenOrtho, 
					residuMoyenOrtho;
	private FonctionAffine f, fv, fh, fo, f1;
	private String conseil;
	
	public DroiteMoindreCarres() {
		System.out.println("constructeur");
		try {
			//System.out.println("Loading giac java interface");
		        //System.load("/usr/local/lib/libgiacjava.so");
		        System.loadLibrary("javagiac");
		    } catch (UnsatisfiedLinkError e) {
		      System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
		      System.exit(1);
		    }
	}
	
	//TODO ne pas oublier de l'appeler
	@SuppressWarnings("unchecked")
	public void init(LinkedList<Double> listXi, LinkedList<Double> listYi, int commutateur, double xOmega, double yOmega, double p1){
		System.out.println("//////////////////////////////////////////////////////////////////////////////");
		System.out.println("init");
		this.listXi = listXi;
		this.listYi = listYi;
		this.xOmega = xOmega;
		this.yOmega = yOmega;
		
		System.out.println("context ?");
		c = new context();
		System.out.println("context !");
		
		n = listXi.size();
		xMoyen = getMoyenne(listXi,n);
		System.out.println("xMoyen : " + xMoyen);
		yMoyen = getMoyenne(listYi,n);
		System.out.println("yMoyen : " + yMoyen);
		
		switch (commutateur) {
		case 0: //pas de contraintes
			System.out.println("cas 0");
			this.xOmega = xMoyen;
			this.yOmega = yMoyen;
			System.out.println("size avant "+listXi.size());
			listXxi = (LinkedList<Double>) listXi.clone();
			concat(listXxi,xOmega);
			listYyi = (LinkedList<Double>) listYi.clone();
			concat(listYyi,yOmega);
			xmaxgraph = getMax(listXi);
			System.out.println("xMax : " + xmaxgraph);
			xmingraph = getMin(listXi);
			System.out.println("xMin : " + xmingraph);
			ymaxgraph = getMax(listYi);
			System.out.println("yMax : " + ymaxgraph);
			ymingraph = getMin(listYi);
			System.out.println("yMin : " + ymingraph);
			break;
		case 1: //contrainte sur coordonnées d'un point de la droite
			System.out.println("cas 1");
			this.xOmega = xOmega;
			this.yOmega = yOmega;
			listXxi = (LinkedList<Double>) listXi.clone();
			concat(listXxi,xOmega);
			listYyi = (LinkedList<Double>) listYi.clone();
			concat(listYyi,yOmega);
			xmaxgraph = getMax(listXxi);
			xmingraph = getMin(listXxi);
			ymaxgraph = getMax(listYyi);
			ymingraph = getMin(listYyi);
			break;
		case 2: //contrainte sur la pente p de la droite
			System.out.println("cas 2");
			xmaxgraph = getMax(listXi);
			xmingraph = getMin(listXi);
			ymaxgraph = getMax(listYi);
			ymingraph = getMin(listYi);
			p2 = yMoyen-p1*xMoyen;
			f = new FonctionAffine("f", p1, "x", p2);
			residuVerticalMoyen = calculResiduVerticalMoyen();
			residuOrthogonalMoyen = residuVerticalMoyen*Math.cos(Math.atan(p1));
			longueurDroite = calculLongueurDroite();
			calculLongueurI();
			longueurPolygone = getSomme(longueurI);
			facteurQualite = longueurDroite/longueurPolygone;
			break;
		default:
			break;
		}
		
		v1 = calculV1();
		v2 = this.yOmega-v1*this.xOmega;
		System.out.println("v2 "+v2);
		h1 = calculH1();
		h2 = this.yOmega-h1*this.xOmega;
		System.out.println("h2 "+h2);
		
		denominateur = calculDenominateur();
				
		if (denominateur == 0) {
			System.out.println("Calculs de projection orthogonale impossibles");
		}
		else {
			alpha = 0.5*Math.atan((-2*(getMoyenne(produitTermeATerme(listXi, listYi),n) - this.xOmega*yMoyen - this.yOmega*xMoyen + this.xOmega*this.yOmega))/denominateur);
			if (calculD1() <= calculD2()){
				teta = alpha;
			}
			else {
				teta = alpha+Math.PI/2;
			}
			o1 = -1/Math.tan(teta);
			o2 = this.yOmega-o1*this.xOmega;
		}
		fv = new FonctionAffine("fv", v1, "x", v2);
		fh = new FonctionAffine("fh", h1, "x", h2);
		fo = new FonctionAffine("fo", o1, "x", o2);
		residuMoyenOrthoProjVertical = calculResiduMoyenOrthoProj(fv);
		residuMoyenOrthoProjHorizontal = calculResiduMoyenOrthoProj(fh);
		residuMoyenOrthoProjOrthogonal = calculResiduMoyenOrthoProj(fo);
		residuCarreMoyenOrthoProjVertical = calculResiduCarreMoyenOrthoProj(fv);
		residuCarreMoyenOrthoProjHorizontal = calculResiduCarreMoyenOrthoProj(fh);
		residuCarreMoyenOrthoProjOrthogonal = calculResiduCarreMoyenOrthoProj(fo);
		residuCarreMoyenOrtho = Math.min(residuCarreMoyenOrthoProjVertical, Math.min(residuCarreMoyenOrthoProjHorizontal, residuCarreMoyenOrthoProjOrthogonal));
		residuMoyenOrtho = Math.min(residuMoyenOrthoProjVertical, Math.min(residuMoyenOrthoProjHorizontal, residuMoyenOrthoProjOrthogonal));
		
		System.out.println("fv " + fv);
		System.out.println("fh " + fh);
		System.out.println("fo " + fo);
		System.out.println("residuMoyenOrthoProjVertical " + residuMoyenOrthoProjVertical);
		System.out.println("residuMoyenOrthoProjHorizontal " + residuMoyenOrthoProjHorizontal);
		System.out.println("residuMoyenOrthoProjOrthogonal " + residuMoyenOrthoProjOrthogonal);
		System.out.println("residuCarreMoyenOrthoProjVertical " + residuCarreMoyenOrthoProjVertical);
		System.out.println("residuCarreMoyenOrthoProjHorizontal " + residuCarreMoyenOrthoProjHorizontal);
		System.out.println("residuCarreMoyenOrthoProjOrthogonal " + residuCarreMoyenOrthoProjOrthogonal);
		System.out.println("residuCarreMoyenOrtho " + residuCarreMoyenOrtho);
		System.out.println("residuMoyenOrtho " + residuMoyenOrtho);
		System.out.println("longueur droite "+longueurDroite);
		System.out.println("longueur polygone "+longueurPolygone);
	}
	
	private void calculLongueurI(){
		System.out.println("calculLongueurI");
		longueurI = new LinkedList<Double>();
		for (int j=0; j<=n-2; j++){
			longueurI.add(j, Math.sqrt(Math.pow(listXi.get(j+1)-listXi.get(j), 2)+Math.pow(listYi.get(j+1)-listYi.get(j), 2)));
		}
	}
	
	private double calculDenominateur(){
		System.out.println("det");
		return getMoyenne(pow2ListElements(listYi),n) - getMoyenne(pow2ListElements(listXi),n) + Math.pow(yOmega, 2) - Math.pow(xOmega, 2) - 2*(yOmega*getMoyenne(listYi,n) - xOmega*getMoyenne(listXi,n));
	}

	//TODO ne pas oublier de l'appeler
	public void testConseil() {
		if (residuMoyenOrthoProjVertical == residuMoyenOrtho){
			conseil = "Choisir la droite de projection verticale : y=f(x)=v1*x+v2";
			f = fv;
		}
		if (residuMoyenOrthoProjHorizontal == residuMoyenOrtho){
			conseil = "Choisir la droite de projection horizontale : y=f(x)=h1*x+h2";
			f = fh;
		}
		if (residuMoyenOrthoProjOrthogonal == residuMoyenOrtho){
			conseil = "Choisir la droite de projection orthogonale : y=f(x)=o1*x+o2";
			f = fo;
		}
		f1 = new FonctionAffine("f1", 0.0, "x", new gen("diff("+f+",x,1)", c).DOUBLE_val()); 
		longueurDroite = new gen("approx(int(sqrt(1+("+f1+").^2),x,"+getMin(listXxi)+","+getMax(listXxi)+"))", c).DOUBLE_val(); //TODO quel est le but de conseil ? car ici on modifie une variable
		calculLongueurI();
		longueurPolygone = getSomme(longueurI);
		//System.out.println("longueur droite conseil "+longueurDroite);
		//System.out.println("longueur polygone "+longueurPolygone);
		System.out.println("v1 " + v1 + 
						"\nv2 " + v2 + 
						"\nfv " + fv + 
						"\nh1 " + h1 + 
						"\nh2 " + h2 + 
						"\nfh " + fh + 
						"\nvalue " + Math.sqrt(Math.min(v1/h1,h1/v1)) + 
						"\no1 " + o1 + 
						"\no2" + o2+ 
						"\nfo" + fo + 
						"\nresiducarremoyenorthoprojverti " + residuCarreMoyenOrthoProjVertical + 
						"\nresiducarremoyenorthoprojhoriz " +  residuCarreMoyenOrthoProjHorizontal + 
						"\nresiducarremoyenorthoprojortho " + residuCarreMoyenOrthoProjOrthogonal + 
						"\nresidumoyenorthoprojverti " + residuMoyenOrthoProjVertical + 
						"\nresidumoyenorthoprojhoriz" + residuMoyenOrthoProjHorizontal + 
						"\nresidumoyenorthoprojortho " + residuMoyenOrthoProjOrthogonal + 
						"\nlongueurdroite " + longueurDroite  + 
						"\nlongueurpolygone " + longueurPolygone + 
						"\nlongueurdroite/longueurPolygone " + longueurDroite/longueurPolygone + 
						"\nmin(listxi) " + getMin(listXi) + 
						"\nmax(listxi) " + getMax(listXi) + 
						"\nxMoyen" +  xMoyen + 
						"\nyMoyen" + yMoyen + 
						"\nConseil" + conseil + 
						"\nxmingraph" + xmingraph  + 
						"\nxmaxgraph" +  xmaxgraph+ 
						"\nymingraph" + ymingraph   + 
						"\nymaxgraph" + ymaxgraph);
	}
	
	
	private double calculD1(){
		System.out.println("calcul D1");
		double res = 0.0;
		for (int i=0; i<listXi.size(); i++){
			res = res + Math.pow((listXi.get(i) - xMoyen)*Math.cos(alpha) + (listYi.get(i) - yMoyen)*Math.sin(alpha),2);
		}
		return res;
	}
	
	private double calculD2(){
		System.out.println("calcul D2");
		double res = 0.0;
		for (int i=0; i<listXi.size(); i++){
			res = res + Math.pow((listXi.get(i) - xMoyen)*Math.cos(alpha+Math.PI/2) + (listYi.get(i) - yMoyen)*Math.sin(alpha+Math.PI/2),2);
		}
		return res;
	}

	private double calculH1(){
		System.out.println("calcul H1");
		double num = 0.0;
		double denom = 0.0;
		for (int i=0; i<listXi.size(); i++){
			num = num + Math.pow(listYi.get(i) - yOmega, 2);
			denom = denom + (listYi.get(i) - yOmega)*(listXi.get(i) - xOmega);
		}
		System.out.println("denom : "+denom);
		if (denom!=0.0){
			System.out.println("h1 "+ num/denom);
			return num/denom;
		}
		return 0.0;
	}

	private double calculV1() {
		System.out.println("calcul V1");
		double num = 0.0;
		double denom = 0.0;
		System.out.println("yomega : "+yOmega);
		System.out.println("xomega : "+xOmega);
		for (int i=0; i<listXi.size(); i++){
			System.out.println("listXi : "+listXi.get(i));
			System.out.println("listYi : "+listYi.get(i));
			num = num + (listYi.get(i) - yOmega) * (listXi.get(i) - xOmega);
			denom = denom + Math.pow(listXi.get(i)-xOmega,2);
		}
		System.out.println("num "+ num);
		System.out.println("denom : "+denom);
		if (denom!=0.0){
			System.out.println("v1 "+ num/denom);
			return num/denom;
		}
		return 0.0;
	}
	
	private double calculLongueurDroite(){
		System.out.println("calcul longueur droite");
		return Math.sqrt(Math.pow(xmaxgraph-xmingraph,2)+Math.pow(f.eval(xmaxgraph)-f.eval(xmingraph), 2));
	}
	
	public double calculResiduVerticalMoyen(){
		System.out.println("calcul residu vertical moyen");
		double res = 0.0;
		for (int i=0; i<listXi.size(); i++){
			res = res + Math.abs(listYi.get(i) - f.eval(listXi.get(i)));
		}
		return res/n;
	}
	
	/**
	 * Même formule pour vertical, horizontal, orthogonal
	 * @return
	 */
	public double calculResiduMoyenOrthoProj(FonctionAffine function){
		System.out.println("calcul residu moyen ortho proj");
		double somme = 0.0;
		for (int i=0; i<listXi.size(); i++){
			somme = somme + Math.abs(listYi.get(i)-function.eval(listXi.get(i)))*Math.cos(Math.atan(function.getA()));
		}
		return somme/n;
	}
	
	/**
	 * Même formule pour vertical, horizontal, orthogonal
	 * @return
	 */
	public double calculResiduCarreMoyenOrthoProj(FonctionAffine function){
		System.out.println("calcul Residu carre Moyen ortho proj");
		double somme = 0.0;
		for (int i=0; i<listXi.size(); i++) {
			somme = somme + (listYi.get(i)-function.eval(listXi.get(i)))*Math.cos(Math.atan(function.getA()))*(listYi.get(i)-function.eval(listXi.get(i)))*Math.cos(Math.atan(function.getA())); 
		}
		return somme/n;
	}
	
	private LinkedList<Double> pow2ListElements(LinkedList<Double> list){
		System.out.println("pow2ListElements");
		LinkedList<Double> res = new LinkedList<Double>();
		for (int i=0; i<list.size(); i++){
			res.add(i, Math.pow(list.get(i),2));
		}
		return res;
	}

	/**
	 * @return the conseil
	 */
	public String getConseil() {
		return conseil;
	}

	/**
	 * @return the c
	 */
	public context getC() {
		return c;
	}

	/**
	 * @return the xMoyen
	 */
	public double getxMoyen() {
		return xMoyen;
	}

	/**
	 * @return the yMoyen
	 */
	public double getyMoyen() {
		return yMoyen;
	}

	/**
	 * @return the xOmega
	 */
	public double getxOmega() {
		return xOmega;
	}

	/**
	 * @return the yOmega
	 */
	public double getyOmega() {
		return yOmega;
	}

	/**
	 * @return the xmaxgraph
	 */
	public double getXmaxgraph() {
		return xmaxgraph;
	}

	/**
	 * @return the xmingraph
	 */
	public double getXmingraph() {
		return xmingraph;
	}

	/**
	 * @return the ymaxgraph
	 */
	public double getYmaxgraph() {
		return ymaxgraph;
	}

	/**
	 * @return the ymingraph
	 */
	public double getYmingraph() {
		return ymingraph;
	}

	/**
	 * @return the residuVerticalMoyen
	 */
	public double getResiduVerticalMoyen() {
		return residuVerticalMoyen;
	}

	/**
	 * @return the residuOrthogonalMoyen
	 */
	public double getResiduOrthogonalMoyen() {
		return residuOrthogonalMoyen;
	}

	/**
	 * @return the longueurDroite
	 */
	public double getLongueurDroite() {
		return longueurDroite;
	}

	/**
	 * @return the longueurPolygone
	 */
	public double getLongueurPolygone() {
		return longueurPolygone;
	}

	/**
	 * @return the facteurQualite
	 */
	public double getFacteurQualite() {
		return facteurQualite;
	}

	/**
	 * @return the h1
	 */
	public double getH1() {
		return h1;
	}

	/**
	 * @return the h2
	 */
	public double getH2() {
		return h2;
	}

	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * @return the residuMoyenOrthoProjVertical
	 */
	public double getResiduMoyenOrthoProjVertical() {
		return residuMoyenOrthoProjVertical;
	}

	/**
	 * @return the residuMoyenOrthoProjHorizontal
	 */
	public double getResiduMoyenOrthoProjHorizontal() {
		return residuMoyenOrthoProjHorizontal;
	}

	/**
	 * @return the residuMoyenOrthoProjOrthogonal
	 */
	public double getResiduMoyenOrthoProjOrthogonal() {
		return residuMoyenOrthoProjOrthogonal;
	}

	/**
	 * @return the residuCarreMoyenOrthoProjVertical
	 */
	public double getResiduCarreMoyenOrthoProjVertical() {
		return residuCarreMoyenOrthoProjVertical;
	}

	/**
	 * @return the residuCarreMoyenOrthoProjHorizontal
	 */
	public double getResiduCarreMoyenOrthoProjHorizontal() {
		return residuCarreMoyenOrthoProjHorizontal;
	}

	/**
	 * @return the residuCarreMoyenOrthoProjOrthogonal
	 */
	public double getResiduCarreMoyenOrthoProjOrthogonal() {
		return residuCarreMoyenOrthoProjOrthogonal;
	}

	/**
	 * @return the residuCarreMoyenOrtho
	 */
	public double getResiduCarreMoyenOrtho() {
		return residuCarreMoyenOrtho;
	}

	/**
	 * @return the residuMoyenOrtho
	 */
	public double getResiduMoyenOrtho() {
		return residuMoyenOrtho;
	}

	/**
	 * @return the f
	 */
	public FonctionAffine getF() {
		return f;
	}

	/**
	 * @return the fv
	 */
	public FonctionAffine getFv() {
		return fv;
	}

	/**
	 * @return the fh
	 */
	public FonctionAffine getFh() {
		return fh;
	}

	/**
	 * @return the fo
	 */
	public FonctionAffine getFo() {
		return fo;
	}
	
	
}
