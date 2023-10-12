package de.edux.math.matrix;


import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


import de.edux.math.vector.Vector;

/* TO-DO
 * need to implement multithreading for larger arrays
 * need to clean up the contructors
 * Need a copy constructor
 * need override the equals method
 * need to override the compareTo method (what would this method compare?)
 */
public class Matrix implements Comparable<Matrix> {

	// Storing row and cols and then the 2D array
	private final int rows;
	private final int cols;
	private final float[][] data;
	/**
	 * Creates a empty matrix from the provided rows and columns.
	 *
	 * @param rows and cols, integer values of the row and column length
	 */


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
	public Matrix(int rows, int cols) {
		this(new float[rows][cols]);
	}
	// if one dimension is given create a empty square matrix 
	public Matrix(int square) {
		this(new float[square][square]);
	}
	
	public Matrix(Matrix matrix) {
		this(matrix.data);
	}
	
	public Matrix() {
		this(new float[1][1]);
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
		if (row < 0 || row >= data.length || col < 0 || col >= data[0].length) {
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
	 * @throws IllegalArgumentException If matrix dimensions are invalid for
	 *                                  multiplication.
	 */
	public Matrix multiply(Matrix other) {
		if (cols != other.rows) {
			throw new IllegalArgumentException(
					"Number of columns in the first matrix doesn't match rows in the second.");
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
     * Multiplies this matrix with vector.
     *
     * @param other The Vector to multiply with.
     * @return A new vector representing the result of the multiplication.
     * @throws IllegalArgumentException If matrix dimensions are invalid for multiplication.
     */
    public Vector multiply(Vector other) {
    	if(cols != other.getSize()) {
    		throw new IllegalArgumentException("Number of columns doesn't match size of vector.");
    	}
    	float[] vectData = other.getData();
    	float[] result = new float[cols];
    	for(int row = 0; row < rows; row++) {
    		float sum = 0f;
    		for(int col = 0; col < cols; col++) {
    			sum += vectData[col] * data[col][row];
    		}
    		vectData[row] = sum;
    	}
    	
    	return new Vector(result);
    	
    }

	/**
	 * print out the representation of the matrix
	 *
	 * A string representation of the matrix.
	 */
	public void print() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				System.out.print(this.data[i][j] + " ");
			}
			System.out.println();
		}
	}
	@Override
    public int compareTo(Matrix otherMatrix) {
        if (this.data.length != otherMatrix.data.length || this.data[0].length != otherMatrix.data[0].length) {
            // Matrices with different dimensions are not directly comparable.
            return -1;
        }

        // Compare lexicographically element by element
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[0].length; j++) {
                int cmp = Float.compare(this.data[i][j], otherMatrix.data[i][j]);
                if (cmp != 0) {
                    return cmp;
                }
            }
        }

        // If all elements are equal, the matrices are considered equal.
        return 0;
    }
    
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		/* We cast obj so we can access the matrix instance variables */
		Matrix matrix = (Matrix) obj;

		return this.compareTo(matrix) == 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int row = 0; row < this.rows; row++) {
			sb.append(Arrays.toString(this.data[row]));
			if (row < (this.rows - 1)) {
				sb.append(",\n");
			}
		}
		return sb.append("]").toString();

	}
}
