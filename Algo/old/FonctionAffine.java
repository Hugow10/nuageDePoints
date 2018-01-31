package model.math;

public class FonctionAffine {

	private String functionName, x;
	private double a,b;
	
	public FonctionAffine(String functionName, double a, String x, double b){
		this.functionName = functionName;
		this.a = a;
		this.x = x;
		this.b = b;
	}
	
	public FonctionAffine(FonctionAffine other){
		this.functionName = other.functionName;
		this.a = other.getA();
		this.x = other.getX();
		this.b = other.getB();
	}
	
	public double eval(double x){
		return a*x+b;
	}
	
	public String toString(){
		if (b < 0){
			return a+"*"+x+b;
		}
		return a+"*"+x+"+"+b;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public String getX() {
		return x;
	}
	
	public void times(double lambda){
		a = lambda * a;
		b = lambda * b;
	}
	
	public boolean equals(FonctionAffine other){
		if (a == other.getA() && b == other.getB() && functionName.equals(other.getFunctionName())){
			return true;
		}
		return false;
	}

	
}
