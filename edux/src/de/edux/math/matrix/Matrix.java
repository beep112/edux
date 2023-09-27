package de.edux.math.matrix;

public class Matrix {
	
	// storing row and cols and then the 2D array;
	private int rows;
	private int cols;
	private double[][] data;
	
	// Constructors for the matrix class
	// can either input data or input rows and columns
	
	public Matrix(int numRows, int numCols) {
		this.rows = numRows;
		this.cols = numCols;
		data = new double[rows][cols];
	}
	
	public Matrix(double[][] inputData) {
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
	public double[][] getData(){
		return data;
	}
	// will set set to data to the current input data only if columns and rows match
    public void setData(double[][] newData) {
        if (newData.length != rows || newData[0].length != cols) {
            throw new IllegalArgumentException("New data dimensions must match the matrix dimensions.");
        }
        this.data = newData;
    }
	
	public Matrix add(Matrix a, Matrix b) {
		boolean rowsInvalid = a.getRows() != b.getRows();
		boolean colsInvalid = a.getColumns() != b.getColumns();
		
		if(rowsInvalid || colsInvalid) {
            throw new IllegalArgumentException("Matrix dimensions for rows and colums must match for addition");
		}
		
		double[][] aData = a.getData();
		double[][] bData = b.getData();
		double[][] result = new double[a.getRows()][b.getColumns()];
		for(int r = 0; r < a.getRows(); r++) {
			for(int c = 0; c < a.getColumns(); c++) {
				result[r][c] = aData[r][c] + bData[r][c];
			}
		}
		return new Matrix(result);
	}
	// subtraciton of the matrix a from b
	public Matrix subtract(Matrix a, Matrix b) {
		boolean rowsInvalid = a.getRows() != b.getRows();
		boolean colsInvalid = a.getColumns() != b.getColumns();
		
		if(rowsInvalid || colsInvalid) {
            throw new IllegalArgumentException("Matrix dimensions for rows and colums must match for addition");
		}
		
		double[][] aData = a.getData();
		double[][] bData = b.getData();
		double[][] result = new double[a.getRows()][b.getColumns()];
		for(int r = 0; r < a.getRows(); r++) {
			for(int c = 0; c < a.getColumns(); c++) {
				result[r][c] = aData[r][c] - bData[r][c];
			}
		}
		return new Matrix(result);
		
	}
	
	public Matrix multiplication(Matrix a, Matrix b) {
		
		return new Matrix(1, 1);
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
	
}
