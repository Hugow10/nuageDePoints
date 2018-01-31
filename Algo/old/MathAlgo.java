package model.math;

import java.util.LinkedList;

public abstract class MathAlgo {

	public FunctionMatrix list2FunctionMat(LinkedList<FonctionAffine> functionsList, int nbCol){
		return new FunctionMatrix(functionsList);
	}
	
	public static Matrix list2Mat(LinkedList<Double> list, int nbCol){
		int size = list.size();
		if (nbCol < 0) {
			System.err.println("Le nombre de colonnes dans doit être positif !");
			return null;
		}
		while (size - nbCol > nbCol) {
			size = size - nbCol;
		}
		int toAdd = nbCol - size;
		for (int i = 0; i < toAdd; i++) {
			list.add(0.0);
		}
		size = list.size();
		int row = size / nbCol;
		if (row * nbCol < size) {
			row = row + 1;
		}
		double[][] res = new double[row][nbCol];
		for (int i = 0; i < size; i++) {
			res[i / nbCol][i % nbCol] = list.get(i).doubleValue();
		}

		return new Matrix(res);
	}
	
	public static LinkedList<Double> mat2List(Matrix A) {
		LinkedList<Double> list = new LinkedList<Double>();
		double data[][] = A.getData();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				list.add((Double) data[i][j]);
			}
		}
		return list;
	}
	
	public static LinkedList<FonctionAffine> mat2List(FunctionMatrix functionMatrix){
		return functionMatrix.getData();
	}
	
	public static FunctionMatrix subMat(FunctionMatrix functionMatrix, int debLigne, int debColonne, int finLigne, int finColonne){
		Matrix a = functionMatrix.getA();
		Matrix b = functionMatrix.getB();
		a = subMat(a, debLigne, debColonne, finLigne, finColonne);
		b = subMat(b, debLigne, debColonne, finLigne, finColonne);
		return new FunctionMatrix(a, b);
	}
	
	public static Matrix subMat(Matrix A, int debLigne, int debColonne, int finLigne, int finColonne){
    	int nbLignes = finLigne - debLigne + 1;
    	int nbColonnes = finColonne - debColonne + 1;
    	if (0 <= debLigne && debLigne <= nbLignes - 1
    		&& 0 <= debColonne && debColonne <= nbColonnes - 1
    		&& 0 <= finLigne && finLigne <= nbLignes - 1
    		&& 0 <= finColonne && finColonne <= nbColonnes - 1
    		&& debLigne < finLigne
    		&& debColonne < finColonne){
    		int compteurLignes = 0;
        	int compteurColonnes = 0;
        	double[][] subData = new double[nbLignes][nbColonnes];
        	for (int row = debLigne; row <= finLigne ; row++){
        		for (int col = debColonne; col <= finColonne ; col++){
            		subData[compteurLignes][compteurColonnes] = A.getData()[row][col];
            		System.out.println("j'ai mis "+A.getData()[row][col]+ " en coord ("+compteurLignes+","+compteurColonnes+")");

            		compteurColonnes++;
            		System.out.println("cc "+compteurColonnes);
            	}
        		compteurColonnes = 0;
        		compteurLignes++;
        		System.out.println("cl " +compteurLignes);
        	}
        	return new Matrix(subData);
    	}
    	else {
    		System.err.println("Error : illegal indexes arguments");
    		return null;
    	}
 
    }

	protected double getSomme(LinkedList<Double> list) {
		System.out.println("getSomme");
		double somme = 0.0;
		for (int i=0; i<list.size(); i++){
			somme = somme + list.get(i);
		}
		return somme;
	}
	
	protected FonctionAffine getFunctionsSomme(LinkedList<FonctionAffine> functionsList){
		double bSum = 0.0;
		double aSum = 0.0;
		for (FonctionAffine function : functionsList){
			bSum = bSum + function.getB();
			aSum = aSum + function.getA();
		}
		return new FonctionAffine("somme", aSum, "x", bSum);
	}
	
	protected LinkedList<Double> produitTermeATerme(LinkedList<Double> x, LinkedList<Double> y){
		System.out.println("produitTermeATerme");
		LinkedList<Double> produit = new LinkedList<Double>();
		for (int i=0; i<x.size(); i++){
			produit.add(i, x.get(i)*y.get(i));
		}
		return produit;
	}
	
	protected LinkedList<FonctionAffine> produitTermeATermeForFunctions(LinkedList<Double> lambda, LinkedList<FonctionAffine> functionsList) {
		System.out.println("produitTermeATerme");
		LinkedList<FonctionAffine> produit = new LinkedList<FonctionAffine>();
		for (int i=0; i<lambda.size(); i++){
			functionsList.get(i).times(lambda.get(i));
			produit.add(i, functionsList.get(i));
		}
		return produit;
	}
	
	public void concat(LinkedList<Double> list, double elt){
		System.out.println("concat");
		list.add(elt);
	}
	
	public void concat(LinkedList<Double> list1, LinkedList<Double> list2){
		list1.addAll(list2);
	}
	
	public double getMax(LinkedList<Double> list){
		System.out.println("getMax");
		double max = Integer.MIN_VALUE;
		for (int i=0; i<list.size(); i++){
			if (list.get(i) > max){
				max = list.get(i);
			}
		} 
		return max;
	}
	
	public double getMin(LinkedList<Double> list){
		System.out.println("getMin");
		double min = Integer.MAX_VALUE;
		for (int i=0; i<list.size(); i++){
			if (list.get(i) < min){
				min = list.get(i);
			}
		} 
		return min;
	}

	public double getMoyenne(LinkedList<Double> list, int nbElements){
		return getSomme(list)/nbElements;
	}
	
	public static void main(String[] args) {
        double[][] d = { { 3, 4, 5 }, { 1, 2, 6 } };
        System.out.println("d: "+d);
        Matrix A = new Matrix(d);
        System.out.println("A: ");
        A.show();
        LinkedList<Double> list = mat2List(A);
        System.out.println("list: "+list);
        Matrix B = list2Mat(list,3);
        System.out.println("B: ");
        B.show();
        Matrix C = list2Mat(list,4);
        System.out.println("C: ");
        C.show();
	}
	
}
