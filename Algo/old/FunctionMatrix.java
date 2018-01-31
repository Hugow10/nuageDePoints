package model.math;

import java.util.LinkedList;

public class FunctionMatrix extends AbstractMatrix {

	private Matrix a;
	private Matrix b;
	private LinkedList<FonctionAffine> data;
	
	public FunctionMatrix(LinkedList<FonctionAffine> functionsList) {
		a = new Matrix(functionsList.size(),1);
		b = new Matrix(functionsList.size(),1);
		for (int i=0; i<functionsList.size(); i++){
			a.setElement(functionsList.get(i).getA(), i, 1);
			b.setElement(functionsList.get(i).getB(), i, 1);
		}
		data = functionsList;
	}
	
	public FunctionMatrix(Matrix a, Matrix b){
		this.a = a;
		this.b = b;
	}

	public Matrix getA() {
		return a;
	}

	public Matrix getB() {
		return b;
	}
	
	public LinkedList<FonctionAffine> getData(){
		return data;
	}

	public double getElement(int l, int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
