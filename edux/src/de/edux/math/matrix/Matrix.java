package de.edux.math.matrix;
import java.util.Arrays;

public class Matrix {

	// Storing row and cols and then the 2D array
	private final int rows;
	private final int cols;
	private final float[][] data;


    /**
     * Creates a empty matrix from the provided rows and columns.
     *
     * @param rows and cols, integer values of the row and column length
     */
	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		data = new float[rows][cols];
	}
    /**
     * Creates a matrix from the provided 2D array of float values.
     *
     * @param inputData The 2D array representing the matrix.
     */
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
	// get a single point of data
	public float getData(int row, int col) {
        if (row < 0 || row  >= data.length || col < 0 || col >= data[0].length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
		return data[row][col];
	}
	
	
	/*
	 * // will set set to data to the current input data only if columns and rows
	 * match public void setData(float[][] newData) { if (newData.length != rows ||
	 * newData[0].length != cols) { throw new
	 * IllegalArgumentException("New data dimensions must match the matrix dimensions."
	 * ); } data = newData; }
	 */
    /**
     * Adds another matrix to this matrix.
     *
     * @param other The matrix to add.
     * @return A new matrix representing the result of the addition.
     * @throws IllegalArgumentException If matrix dimensions do not match.
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

    /**
     * Subtracts another matrix to this matrix.
     *
     * @param other The matrix to subtract.
     * @return A new matrix representing the result of the subtraction.
     * @throws IllegalArgumentException If matrix dimensions do not match.
     */
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
	
	/**
     * Multiplies this matrix with another matrix.
     *
     * @param other The matrix to multiply with.
     * @return A new matrix representing the result of the multiplication.
     * @throws IllegalArgumentException If matrix dimensions are invalid for multiplication.
     */
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
    
    /**
     * Create a string representation that can be printed
     *
     * @return A string representation of the matrix.
     */
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
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	for(int row = 0; row<this.rows; row++) {
    		 sb.append(Arrays.toString(this.data[row]));
    		 if(row < (this.rows-1)) {
    		 	sb.append(",\n");
    		 }
    	}
    	return sb.append("]").toString();
    	
    }
}
