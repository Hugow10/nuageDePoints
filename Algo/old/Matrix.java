package model.math;

import java.util.LinkedList;

public class Matrix extends AbstractMatrix {
    
    private final double[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // copy constructor
    private Matrix(Matrix A) { this(A.data); }

    // create and return a random M-by-N matrix with values between 0 and 1
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // create and return the N-by-N identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // does A = B exactly?
    public boolean eq(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }
    
    // return C = A * lambda
    public Matrix times(double lambda) {
        Matrix A = this;
        Matrix C = new Matrix(A.M, A.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
            	C.data[i][j] = (A.data[i][j] * lambda);
        return C;
    }


    // return x = A^-1 b, assuming A is square and has full rank
    public Matrix solve(Matrix rhs) {
        if (M != N || rhs.M != N || rhs.N != 1)
            throw new RuntimeException("Illegal matrix dimensions.");

        // create copies of the data
        Matrix A = new Matrix(this);
        Matrix b = new Matrix(rhs);

        // Gaussian elimination with partial pivoting
        for (int i = 0; i < N; i++) {

            // find pivot row and swap
            int max = i;
            for (int j = i + 1; j < N; j++)
                if (Math.abs(A.data[j][i]) > Math.abs(A.data[max][i]))
                    max = j;
            A.swap(i, max);
            b.swap(i, max);

            // singular
            if (A.data[i][i] == 0.0) throw new RuntimeException("Matrix is singular.");

            // pivot within b
            for (int j = i + 1; j < N; j++)
                b.data[j][0] -= b.data[i][0] * A.data[j][i] / A.data[i][i];

            // pivot within A
            for (int j = i + 1; j < N; j++) {
                double m = A.data[j][i] / A.data[i][i];
                for (int k = i+1; k < N; k++) {
                    A.data[j][k] -= A.data[i][k] * m;
                }
                A.data[j][i] = 0.0;
            }
        }

        // back substitution
        Matrix x = new Matrix(N, 1);
        for (int j = N - 1; j >= 0; j--) {
            double t = 0.0;
            for (int k = j + 1; k < N; k++)
                t += A.data[j][k] * x.data[k][0];
            x.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
        }
        return x;
   
    }
    
    public Matrix getColumn(int numCol){
    	Matrix column = new Matrix(M,1);
    	for (int row = 0; row <= M; row++){
    		column.setElement(data[row][numCol],row,1);
    	}
    	return column;
    }
    
	public void setColumn(int col, LinkedList<Double> listCol) {
		for (int row = 0; row <= M; row++){
			data[row][col] = listCol.get(row);
		}
	}
	
	public Matrix getRow(int numRow){
    	Matrix row = new Matrix(1,N);
    	for (int col = 0; col <= M; col++){
    		row.setElement(data[numRow][col],col,1);
    	}
    	return row;
    }
    
	public void setRow(int row, LinkedList<Double> listCol) {
		for (int col = 0; col <= N; col++){
			data[row][col] = listCol.get(col);
		}
	}
	
	public double getElement(int row, int col){
		return data[row][col];
	}
   
    public void setElement(double elt, int row, int col){
    	data[row][col] = elt;
    }
    /**
	 * @return the m
	 */
	public int getM() {
		return M;
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return N;
	}

	/**
	 * @return the data
	 */
	public double[][] getData() {
		return data;
	}

	// print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
        	String line = "(";
            for (int j = 0; j < N; j++) {
            	line = line + data[i][j] + " ";
            //StdOut.println();
            }
            line = line + ")";
            System.out.println(line);
        }
    }



    // test client
    public static void main(String[] args) {
        double[][] d = { { 3, 4, 5 }, { 1, 2, 6 } };
        System.out.println("1");
        Matrix A = new Matrix(d);
        A.show();        
        //StdOut.println();
        
        System.out.println("lambda");
        Matrix LambdaRes = A.times(3);
        LambdaRes.show();
        
        //System.out.println("subMat");
		//Matrix Oin = subMat(A,-1,1,1,2);

        //Matrix A = Matrix.random(5, 5);
        //A.show(); 
        //StdOut.println();

        System.out.println("2");
        A.swap(0, 1);
        A.show(); 
        //StdOut.println();

        System.out.println("3");
        Matrix B = A.transpose();
        B.show(); 
        //StdOut.println();

        System.out.println("4");
        Matrix C = Matrix.identity(5);
        C.show(); 
        //StdOut.println();

        System.out.println("5");
        A.plus(B).show();
        //StdOut.println();

        System.out.println("6");
        B.times(A).show();
        //StdOut.println();

        // shouldn't be equal since AB != BA in general    
        //StdOut.println(A.times(B).eq(B.times(A)));
        //StdOut.println();

        System.out.println("7");
        Matrix b = Matrix.random(5, 1);
        b.show();
        //StdOut.println();

        System.out.println("8");
        //Matrix x = A.solve(b);
        //x.show();
        //StdOut.println();

        System.out.println("9");
        //A.times(x).show();
        
        System.out.println("10");
        LinkedList<Double> listPhi = new LinkedList<Double>();
        for (int i = 0; i < 13; i++){
        	listPhi.add((double) i);
        	System.out.println("oin");
        }
        double[][] oin = new double[listPhi.size()][1];
		Matrix matPhi = new Matrix(oin);
		matPhi.show();
		
		System.out.println("11");
		oin = new double[listPhi.size()][4];
		matPhi = new Matrix(oin);
		matPhi.show();
        
    }

	public double getDet() {
		// TODO Auto-generated method stub
		return 0.0;
	}
}
