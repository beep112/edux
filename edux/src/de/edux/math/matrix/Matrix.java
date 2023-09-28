package de.edux.math.matrix;

public class Matrix {

	// storing row and cols and then the 2D array and parallel task start
	private final int rows;
	private final int cols;
	private final float[][] data;

	// Constructors for the matrix class
	// can either input data or input rows and columns

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		data = new float[rows][cols];
	}

	public Matrix(float[][] inputData) {
		rows = inputData.length;
		cols = inputData[0].length;
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
	
	public float getDate(int row, int col, int value) {
		return data[row][col];
	}
	
	
	/*
	 * // will set set to data to the current input data only if columns and rows
	 * match public void setData(float[][] newData) { if (newData.length != rows ||
	 * newData[0].length != cols) { throw new
	 * IllegalArgumentException("New data dimensions must match the matrix dimensions."
	 * ); } data = newData; }
	 */

	public Matrix add(Matrix other) {
		boolean rowsInvalid = this.rows != other.rows;
		boolean colsInvalid = this.cols != other.cols;
		

		if (rowsInvalid || colsInvalid) {
			throw new IllegalArgumentException("Matrix dimensions for rows and columns must match for addition.");
		}
		float[][] result = new float[this.rows][other.cols];
		for (int r = 0; r < this.rows; r++) {
			for (int c = 0; c < this.cols; c++) {
				result[r][c] = this.data[r][c] + other.data[r][c];
			}
		}
		return new Matrix(result);
	}

	// subtraciton of the matrix a from other
	public Matrix subtract(Matrix other) {
		boolean rowsInvalid = this.rows != other.rows;
		boolean colsInvalid = this.cols != other.cols;

		if (rowsInvalid || colsInvalid) {
			throw new IllegalArgumentException("Matrix dimensions for rows and columns must match for subtraction.");
		}

		float[][] result = new float[this.rows][other.cols];
		for (int r = 0; r < this.rows; r++) {
			for (int c = 0; c < this.cols; c++) {
				result[r][c] = this.data[r][c] - other.data[r][c];
			}
		}
		return new Matrix(result);

	}

	public void scalarMultiply(double constant) {
		for (int r = 0; r < this.rows; r++) {
			for (int c = 0; c < this.cols; c++) {
				this.data[r][c] *= constant;
			}
		}
	}
	
	// Implementation of the basic naive algorithm
    public Matrix multiply(Matrix other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("Number of columns in the first matrix doesn't match rows in the second.");
        }

        Matrix result = new Matrix(rows, other.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                float sum = 0.0f;
                for (int k = 0; k < cols; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }
    
	// if the vector would need to be printed to console
	public String print() {
		String result = "";
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				result += this.data[i][j] + " ";
			}
			result += "\n";
		}
		return result;
	}

}
