package model.math;

import java.util.LinkedList;

import javagiac.context;
import javagiac.gen;

public class Nuage2D extends MathAlgo {

	private LinkedList<Double> listXi, listYi, listXomega, listYomega, listContraintes, listOrdreDerivation, listUux, listUuy, listCarresResidus, 
								listMataMata, listImage, listMataMatat, listLambda, listDerivePhiJ, listDeltall, listSomme, listMata, listMatax, listDeltaLambdal,
								listCol, listDeltaLambdall, listLambdaLambda, listResidus;
	LinkedList<FonctionAffine> listPhi1k;
	LinkedList<FonctionAffine> listPhik1;
	LinkedList<FonctionAffine> listPhik;
	LinkedList<FonctionAffine> listFx;
	private LinkedList<FonctionAffine> listPhi, listDerivePhiJx, listImageX, listSigma, listDeltaDelta0;
	private int n, p, k;
	private Matrix matLambdaJ, matDelta0t, matDeltal, matDelatlJ, mata, mataTranspose, matImage, matLambda, matCont, matYi, matDelta, matDelta0,
					matDeltaTranspose, matDeltalTranspose, matDeltalTransposeJ, matDeltall, matDeltalJ, matDeltaLambdaL, matDeltaLambdaLTranspose, matDeltaLamdbaLambdalTranspose,
					matDeltaLambdall, matLambdaLambda, matDeltaLambdalTranspose, matDeltaLambdaLambdalTranspose, matDeltaLambdal;
	FunctionMatrix matPhi1k;
	FunctionMatrix matPhik;
	FunctionMatrix matPhi;
	private context context;
	private double delta0, detMatDeltaL, detMatDeltallj, listSommeSomme, detMatDeltaLambdal, longueurPolygone, longueurCourbeF, sommeCarresResidus, residuMoyen, residuCarreMoyen,
					xMaxGraph, xMinGraph, yMaxGraph, yMinGraph, aireSousFRomb, aireSousTendue, aireI, qualRep, c, delta1, sommeResidus, xMoyen, xMax, xMin, yMoyen, yMax, yMin;
	private Object sigma; //TODO type ?
	private Object lambdaP; //TODO type ?
	private double detMatDeltaLTranspose;
	private FonctionAffine f, h;
	private gen deriveef;
	private Matrix matDeltallj;
	private double detMatDeltaLTransposeJ;
	
	public void init(LinkedList<Double> listXi, LinkedList<Double> listYi, String listPhi, LinkedList<Double> listXomega, LinkedList<Double> listYomega, LinkedList<Double> listOrdreDerivation){
		context = new context();
		this.listXi = listXi;
		xMoyen = getMoyenne(listXi,listXi.size());
		xMax = getMax(listXi);
		xMin = getMin(listXi);
		this.listYi = listYi;
		yMoyen = getMoyenne(listYi,listYi.size());
		yMax = getMax(listYi);
		yMin = getMin(listYi);
		this.listPhi = findFonctionAffine(listPhi);
		this.listXomega = listXomega;
		this.listYomega = listYomega;
		listContraintes = listYomega;
		this.listOrdreDerivation = listOrdreDerivation;
		
		n = listXi.size();
		p = this.listPhi.size();
		k = listContraintes.size();
		
		matLambdaJ = new Matrix(p, 1);
		matPhi = list2FunctionMat(this.listPhi,1);
		matPhik = subMat(matPhi,p-k,0,p,0);
		matPhi1k = subMat(matPhi,0,0,p-k-1,0);
		listPhik = mat2List(matPhik);
		listPhik1 = mat2List(matPhi1k);
		
		if (k != 0){
			matDelta0t = new Matrix(k,k);
			matDeltal = new Matrix(k,k);
			matDelatlJ = new Matrix(k,k);
		}
		if (k==0){
			fonctionK0();
		}
		if (k==1){
			fonctionK1();
		}
		//calcul de delta0
		matCont = list2Mat(listContraintes,1);
		matYi = list2Mat(listYi,1);
		matDelta = new Matrix(k,p);
		matPhi = list2FunctionMat(this.listPhi,1);
		matPhik = subMat(matPhi, p-k, 0, p, 0);
		matPhi1k = subMat(matPhi, 0, 0, p-k-1, 0);
		listPhik = mat2List(matPhik);
		listPhi1k = mat2List(matPhi1k);
		for (int j=0; j<=k-1; j++){
			c = listOrdreDerivation.get(j);
			listDeltaDelta0 = getAllDerivatives(this.listPhi);
			matDelta.setColumn(j, subst(listDeltaDelta0,listXomega.get(j)));
		}
		matDelta0 = subMat(matDelta, 0, p-k, k-1, p-1);
		matDelta0t = matDelta0.transpose();
		delta0 = matDelta0.getDet();
		if (delta0==0){
			System.out.println("Calculs impossibles. Modifiez 'l'ordre des fonctions d'essai, en commençant du côté droit, par exemple.");
		}
		matDeltaTranspose = matDelta.transpose();
		matDeltall = new Matrix(k,1);
		matDeltallj = new Matrix(k, p-k);
		matDeltalTranspose = matDelta0.transpose();
		matDeltalTranspose.setRow(0, listContraintes);
		matDeltalTransposeJ = matDeltalTranspose;
		detMatDeltaL = matDeltaTranspose.getDet();
		matDeltall.setElement(detMatDeltaL, 0, 0);
		
		for (int j=0; j<=p-k-1; j++){
			matDeltalJ = matDeltaTranspose;
			matDeltalTransposeJ.setRow(0, mat2List(matDeltaTranspose.getRow(j)));
			matDeltalJ = matDeltalTransposeJ.transpose();
			detMatDeltallj = matDeltalTransposeJ.getDet();
			matDeltallj.setElement(detMatDeltallj, 0, j);
		}
		
		for (int l=1; l<=k-1; l++){
			matDeltalTranspose.setRow(l, mat2List(matDelta0.getColumn(l-1)));
			detMatDeltaLTranspose = matDeltaTranspose.getDet();
			matDeltall.setElement(detMatDeltaLTranspose, l, 0);
			for (int jj=0; jj<=p-k-1; jj++){
				matDeltalTransposeJ = matDeltalTranspose;
				matDeltalTransposeJ.setRow(0, mat2List(matDeltaTranspose.getRow(jj)));
				matDeltalJ = matDelatlJ.transpose();
				detMatDeltaLTransposeJ = matDeltalTransposeJ.getDet();
				matDeltallj.setElement(detMatDeltaLTransposeJ, l, jj);
			}
		}
		listSomme = new LinkedList<Double>();
		listDeltall = mat2List(matDeltall);
		listSommeSomme = calculListSommeSomme(); //TODO apparemment listSommeSomme c'est une liste, pas un double
		h = new FonctionAffine("h", 0.0, "x", listSommeSomme);
		for (int jj=0; jj<=n; jj++){
			listSomme.set(jj,listSommeSomme.get(listXi.get(jj))); 
		}
		listMata = new LinkedList<Double>();
		listMatax = new LinkedList<Double>();
		listImage = calculListImage();
		for (int j=0; j<=p-k-1; j++){
			listMataX.set(j) = calculListMataX(listPhi1k.get(j),j);
			fmatax = eval(listMatax);
			for (int jj=0; jj<=n-1; jj++){
				listMata.set(jj, listMatax.get(listXi.get(jj)));
			}
			mataTranspose.set(j,listMata);
		}
		//zéros listimage et mata
		for (int r=0; r<=n-1; r++){
			for (int t=0; t<=k-1; t++){
				System.out.println("à compléter"); //TODO à compléter
			}
		}
		matImage = list2Mat(listImage,1);
		matLambda = (mataTranspose.times(mata)).solve(mataTranspose.times(matImage));
		listLambda = mat2List(matLambda);
		listSigma = new LinkedList<Double>();
		for (int jj=0; jj<=k-1; jj++){
			for (int j=0; j<=p-k-1; j++){
				listSigma.set(jj, matLambda[j,0]*matDelta[jj,j]);
			}
		}
		matDeltaLambdaL = new Matrix(k, k);
		matDeltaLambdaLTranspose = new Matrix(k, k);
		matDeltaLamdbaLambdalTranspose = new Matrix(k,k);
		matDeltaLambdall = new Matrix(k,1);
		matLambdaLambda = new Matrix(k,1);
		matDeltaLambdalTranspose = matDelta0.transpose();
		listDeltaLambdal = new LinkedList<Double>();
	    listCol = new LinkedList<Double>();
	    listDeltaLambdall = new LinkedList<Double>();
	    listLambdaLambda = new LinkedList<Double>();
	    matDeltaLambdalTranspose.set(0, listSigma);
	    matDeltaLambdaLambdalTranspose = matDeltaLambdalTranspose;
	    matDeltaLambdal = matDeltaLambdalTranspose.transpose();
	    detMatDeltaLambdal = matDeltaLambdal.getDet();
	    matDeltaLambdall.set(0, detMatDeltaLambdal);
	    matDeltaLambdaLambdalTranspose = matDeltaLambdalTranspose;
	    
	    for (int l=1; l<=k-1; l++){
	    	matCol = matDelta0.getColumn(l-1);
	    	listCol = mat2List(matCol);
	    	matDeltaLambdalTranspose.setColumn(l, listCol);
	    	matDeltaLambdal = matDeltaLambdalTranspose.transpose();
	    	detMatDeltaLambdal = matDeltaLambdalTranspose.getDet();
	    	matDeltaLambdall.setColumn(l, detMatDeltaLambdal);
	    	listDeltaLambdall = mat2List(matDeltaLambdall);
	    	matDeltaLambdalTranspose.setColumn(l, matDelta0Transpose.getColumn(l-1));
	    }
	    
	    for (int l=0; l<=k-1; l++){
	    	listLambdaLambda.set(l, (Math.pow(-1, l)*listDeltall.get(l) - listDeltaLambdall.get(l))/delta0);
	    	listLambda.set(p-k+1, listLambdaLambda.get(l));
	    }
	    
	    matLambda = list2Mat(listLambda,1);
	    listFx = listLambda * listPhi;
	    f = getSomme(listFx);
	    
	    //Recalcule les points contraints en tenant compte de l'odre de dérivation
	    for (int j=0; j<=k-1; j++){
	    	if (listOrdreDerivation.get(j) > 0){
	    		listContraintes.set(j, f.eval(listXomega.get(j)));
	    	}
	    	else {
	    		listContraintes.set(j, 0);
	    	}
	    }
	}

	private Double calculListMataX(Double elt, int j) {
		double res = 0.0;
		for (int l=0; l<=k-1; l++){
			res = res + Math.pow(-1, l)*matDeltall.getElement(l,j)*matPhik.getElement(l,0);
		}
		return delta0 * elt - res;
	}

	private LinkedList<Double> calculListImage() {
		listImage = new LinkedList<Double>();
		for (int i=0; i<listYi.size(); i++){
			listImage.set(i, delta0*listYi.get(i)-listSomme.get(i));
		}
		return listImage;
	}

	private double calculListSommeSomme() {
		double res = 0.0;
		for (int j=0; j<=k-1; j++){
			res = res + Math.pow(-1,j)*(listDeltall.get(j))*listPhik.get(j);
		}
		return res;
	}

	private void fonctionK1() {
		c = listOrdreDerivation.get(0);
		listDerivePhiJx = getAllDerivatives(listPhi);
		listDerivePhiJ = subst(listDerivePhiJx,listXomega.get(0));
		delta0 = listDerivePhiJ.get(p-1);
		if (delta0 == 0){
			System.out.println("Calculs impossibles. Modifiez l'ordre des fonctions d'essais phi en commençant par la droite.");
		}
		delta1 = listContraintes.get(0);
		listImageX = createListImageX(listPhi);
		listImage = createListImage();
		matImage = list2Mat(listImage,1);
		mata = new Matrix(n,p-1);
		mataTranspose = new Matrix(p-1,n);
		for (int j=0; j<=p-2; j++){
			listMataTranspose = delta0*subst(listPhi.get(j),(listXi)) - listDerivePhiJ.get(j)*subst(listPhi.get(p-1), (listXi));
			mataTranspose.set(j, listMataT);
		}
		mata = mataTranspose.transpose();
		matLambda = (mataTranspose.times(mataTranspose)).solve(mataTranspose.times(matImage));
		listLambda = mat2List(matLambda);
		listSigma = new LinkedList<FonctionAffine>();
		for (int j=0; j<=p; j++){
			listSigma.set(j, listLambda.get(j)*listDerivePhiJ.get(j)); //TODO check typage de listLambda listDerivePhiJ
		}
		sigma = getFunctionsSomme(listSigma);
		lambdaP = (delta1-sigma)/delta0;
		listLambda.set(p-1, lambdaP);
		listFx = listLambda*listPhi;
		FonctionAffine f = new FonctionAffine("f", 0, "x", listFx);
		
		//Recalcule les points contraints en tenant compte de l'ordre de dérivation
		for (int j=0; j<=k-1; j++){
			if (listOrdreDerivation.get(j) > 0){
				listContraintes.set(j, f.eval(listXomega.get(j)));
			}
			else {
				listContraintes.set(j, 0.0);
			}
		}
	}
	
	private LinkedList<Double> createListImage() {
		LinkedList<Double> res = new LinkedList<Double>();
		for (int i=0; i<=listXi.size(); i++){
			res.set(i, delta0*listYi.get(i) - (subst(listImageX,listXi.get(i)).get(i)));
		}
		return res;
	}

	private LinkedList<FonctionAffine> createListImageX(LinkedList<FonctionAffine> functionsList){
		LinkedList<FonctionAffine> res = new LinkedList<FonctionAffine>();
		FonctionAffine newFunction = new FonctionAffine(functionsList.get(p-1));
		newFunction.setA(newFunction.getA()*delta1);
		res.add(newFunction);
		return res;
	}

	private LinkedList<FonctionAffine> getAllDerivatives(LinkedList<FonctionAffine> functionsList) {
		LinkedList<FonctionAffine> derivatedFunctions = new LinkedList<FonctionAffine>();
		for (FonctionAffine function : functionsList){
			derivatedFunctions.add(toFunctionAffine(new gen("diff("+function+","+"x"+","+c+")",context)));
		}
		
		return derivatedFunctions;
	}

	private FonctionAffine toFunctionAffine(gen gen) {
		// TODO Auto-generated method stub
		return null;
	}

	private void fonctionK0() {
		mata = new Matrix(n,p);
		mataTranspose = new Matrix(p,n);
		listMataMata = new LinkedList<Double>();
		listImage = (LinkedList<Double>) listYi.clone();
		matImage = list2Mat(listImage, 1);
		for (int j=0; j<=p-1; j++){//pour chaque ligne de mataTranspose
			for (int k=0; k<=n-1; k++){
				LinkedList<Double> temp = subst(listPhi,listXi.get(k));
				mataTranspose.setRow(k, temp);
			}
			mataTranspose.setColumn(j, listMataMata);
		}
		for (int j=0; j<=p-1; j++){
			if (listPhi.get(j).equals(new FonctionAffine("constante", 0.0, "x", 1))) {
				listMataMatat = new LinkedList<Double>();
				Fill(1,listMataMatat);
				matat.setget(j) = listMataMatat;
			}
		}
		mata = mataTranspose.transpose();
		matLambda = ((mata.transpose()).times(mata)).solve((mata.transpose()).times(matImage));
		listLambda = mat2List(matLambda);
		listFx = produitTermeATermeForFunctions(listLambda, listPhi);
		FonctionAffine f = getFunctionsSomme(listFx);
		f.setFunctionName("f");
		
		//TODO
		affichages();
		
	}

	private void affichages() {
		listCarresResidus = new LinkedList<Double>();
		longueurPolygone = calculLongueurPolygone();
		deriveef = new gen("diff("+f+",x,1", context);
		longueurCourbeF = new gen("abs(int(sqrt(1+(deriveefx(x)).^2),x,listxi[0],listxi[n-1])", context).DOUBLE_val();
		qualRep = longueurPolygone/longueurCourbeF;
		for (int jj=0; jj<=n-1; jj++){
			listCarresResidus.set(jj, new gen("approx(abs("+listYi.get(jj)+"-("+f.eval(listXi.get(jj))+")))",context).DOUBLE_val());
			sommeResidus = getSomme(listResidus);
			listCarresResidus.set(jj, listYi.get(jj)-f.eval(listXi.get(jj))*(listYi.get(jj)-f.eval(listXi.get(jj))));
			sommeCarresResidus = getSomme(listCarresResidus);
		}
		residuMoyen = sommeResidus/n;
		residuCarreMoyen = sommeCarresResidus/n;
		concat(listXi, listXomega);
		xMaxGraph = getMax(listXi);
		xMinGraph = getMin(listXi);
		concat(listYi,listYomega);
		yMaxGraph = getMax(listYi);
		yMinGraph = getMin(listYi);
		
		aireSousFRomb = new gen("romberg(abs("+f+")),x="+xMinGraph+".."+xMaxGraph+")",context).DOUBLE_val();
		aireSousTendue = 0;
		aireI = 0;
		for (int j=0; j<= n-2; j++){
			double xni;
			if (listYi.get(j)*listYi.get(j+1) < 0){
				xni = (listXi.get(j)*listYi.get(j+1) - listXi.get(j+1)*listYi.get(j))/(listYi.get(j+1) - listYi.get(j));
				aireI = 0.5*(Math.abs(listYi.get(j)*(xni-listXi.get(j)) - getSign(listYi.get(j)*listYi.get(j+1)*Math.abs(listYi.get(j+1)*(xni-listXi.get(j+1))))));
			}
			else {
				aireI = 0.5*Math.abs(Math.abs(listYi.get(j)) + Math.abs(listYi.get(j+1)) * Math.abs(listXi.get(j) - listXi.get(j+1)));
			}
			aireSousTendue = aireSousTendue + aireI;
		}
	}

	private int getSign(double d) {
		int sign = 0;
		if (d > 0) {
			sign = 1;
		}
		if (d < 0){
			sign = -1;
		}
		if (d==0){
			sign = 0;
		}
		return sign;
	}

	private double calculLongueurPolygone() {
		double res = 0.0;
		for (int j=0; j <= n-2; j++){
			res = res + Math.sqrt(Math.pow(listXi.get(j+1)-listXi.get(j), 2) + Math.pow(listYi.get(j+1) - listYi.get(j), 2));
		}
		return res;
	}

	//TODO bwaaaaaaa
	private LinkedList<FonctionAffine> findFonctionAffine(String phi){
		LinkedList<FonctionAffine> res = null;
		String[] list = phi.split(",");
		for (String f : list){
			//fonction avec des parenthèses
			String nom = "";
			for (int i=0; i<list.length; i++){
				if (f.charAt(i) != '('){
					nom = nom + f.charAt(i);
				}
				 
			}
			//fonction sans parenthèses
		}
		
		return res;
	}
	
	private LinkedList<Double> subst(LinkedList<FonctionAffine> functionsList, double value){
		LinkedList<Double> res = new LinkedList<Double>();
		for (FonctionAffine function : functionsList){
			res.add(function.eval(value));
		}
		return res;
	}
	
	
}
