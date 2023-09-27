package de.edux.math.matrix;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class Matrix {

	// storing row and cols and then the 2D array and parallel task start
	private static final ForkJoinPool pool = new ForkJoinPool();
	private int rows;
	private int cols;
	private float[][] data;

	// Constructors for the matrix class
	// can either input data or input rows and columns

	public Matrix(int numRows, int numCols) {
		this.rows = numRows;
		this.cols = numCols;
		data = new float[rows][cols];
	}

	public Matrix(float[][] inputData) {
		this.rows = inputData.length;
		this.cols = inputData[0].length;
		data = inputData;
	}

	// getter that return the number of rows in the matrix
	public int getRows() {
		return rows;
	}

	// getter that return the number of columns in the matrix
	public int getColumns() {
		return cols;
	}

	// returns the current data that is being stored
	public float[][] getData() {
		return data;
	}

	// will set set to data to the current input data only if columns and rows match
	public void setData(float[][] newData) {
		if (newData.length != rows || newData[0].length != cols) {
			throw new IllegalArgumentException("New data dimensions must match the matrix dimensions.");
		}
		this.data = newData;
	}

	public Matrix add(Matrix a, Matrix b) {
		boolean rowsInvalid = a.getRows() != b.getRows();
		boolean colsInvalid = a.getColumns() != b.getColumns();

		if (rowsInvalid || colsInvalid) {
			throw new IllegalArgumentException("Matrix dimensions for rows and colums must match for addition");
		}

		float[][] aData = a.getData();
		float[][] bData = b.getData();
		float[][] result = new float[a.getRows()][b.getColumns()];
		for (int r = 0; r < a.getRows(); r++) {
			for (int c = 0; c < a.getColumns(); c++) {
				result[r][c] = aData[r][c] + bData[r][c];
			}
		}
		return new Matrix(result);
	}

	// subtraciton of the matrix a from b
	public Matrix subtract(Matrix a, Matrix b) {
		boolean rowsInvalid = a.getRows() != b.getRows();
		boolean colsInvalid = a.getColumns() != b.getColumns();

		if (rowsInvalid || colsInvalid) {
			throw new IllegalArgumentException("Matrix dimensions for rows and colums must match for addition");
		}

		float[][] aData = a.getData();
		float[][] bData = b.getData();
		float[][] result = new float[a.getRows()][b.getColumns()];
		for (int r = 0; r < a.getRows(); r++) {
			for (int c = 0; c < a.getColumns(); c++) {
				result[r][c] = aData[r][c] - bData[r][c];
			}
		}
		return new Matrix(result);

	}

	public Matrix scalarMultiply(Matrix a, double constant) {
		float[][] result = a.getData();
		for (int r = 0; r < a.getRows(); r++) {
			for (int c = 0; c < a.getColumns(); c++) {
				result[r][c] *= constant;
			}
		}
		return new Matrix(result);
	}

	public Matrix multiply(Matrix a, Matrix b) {
		int rowsA = a.getRows();
		int colsA = a.getColumns();
		int rowsB = b.getRows();
		int colsB = b.getColumns();

		if (colsA != rowsB) {
			throw new IllegalArgumentException("Matrix dimension are not compatible");
		}

		float[][] result = new float[rowsA][colsB];
		pool.invoke(new MultiplyTask(a, b, result, 0, 0, 0, 0, 0, 0, rowsA, colsB, colsA));
		return new Matrix(result);
	}

	// if the vector would need to be printed to console
	public void print() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static class MultiplyTask extends RecursiveTask<Void>{
		private float[][] A, B, C;
		private int aRow, aCol, bRow, bCol, cRow, cCol;
		private int size;
		
		MultiplyTask(float[][] A, float[][] B, float[][] C, int aRow, int aCol, int bRow, int bCol, int cRow, int cCol, int size){
			this.A = A;
			this.B = B;
			this.C = C;
			this.aRow = aRow;
			this.aCol = aCol;
			this.bRow = bRow;
			this.bCol = bCol;
			this.cRow = cRow;
			this.cCol = cCol;
			this.size = size; 
		}
		
		@Override
		protected Void compute() {
			if(size == 1) {
				C[cRow][cCol] = A[aRow][aCol]*B[bRow][bCol];
			} else {
				// use divided and conquer with a parallel workload to get result
				int newSize = size/2;
				MultiplyTask[] tasks = new MultiplyTask[7];
				 
			}
		}
		
	}

}
