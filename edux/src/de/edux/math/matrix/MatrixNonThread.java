package de.edux.math.matrix;

import java.util.Arrays;
import de.edux.math.vector.Vector;

public class MatrixNonThread implements Comparable<MatrixNonThread> {
    private final int rows;
    private final int cols;
    private final float[][] data;

    public MatrixNonThread(float[][] inputData) {
        rows = inputData.length;
        cols = inputData[0].length;
        data = inputData;
    }

    public MatrixNonThread(int rows, int cols) {
        this(new float[rows][cols]);
    }

    public MatrixNonThread(int square) {
        this(new float[square][square]);
    }

    public MatrixNonThread(MatrixNonThread matrix) {
        this(matrix.data);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return cols;
    }

    public float[][] getData() {
        return data;
    }

    public float getData(int row, int col) {
        if (row < 0 || row >= data.length || col < 0 || col >= data[0].length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return data[row][col];
    }

    public MatrixNonThread add(MatrixNonThread other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Matrix dimensions do not match for addition.");
        }

        float[][] result = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = data[i][j] + other.data[i][j];
            }
        }
        return new MatrixNonThread(result);
    }

    public MatrixNonThread subtract(MatrixNonThread other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Matrix dimensions do not match for subtraction.");
        }

        float[][] result = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = data[i][j] - other.data[i][j];
            }
        }
        return new MatrixNonThread(result);
    }

    public void scalarMultiply(double constant) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                data[r][c] *= constant;
            }
        }
    }

    public MatrixNonThread multiply(MatrixNonThread other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("Matrix dimensions do not match for multiplication.");
        }

        int resultRows = rows;
        int resultCols = other.cols;
        float[][] result = new float[resultRows][resultCols];

        for (int i = 0; i < resultRows; i++) {
            for (int j = 0; j < resultCols; j++) {
                float sum = 0.0f;
                for (int k = 0; k < cols; k++) {
                    sum += data[i][k] * other.data[k][j];
                }
                result[i][j] = sum;
            }
        }
        return new MatrixNonThread(result);
    }

    public Vector multiply(Vector other) {
        if (cols != other.getSize()) {
            throw new IllegalArgumentException("Number of columns doesn't match size of vector.");
        }
        float[] vectData = other.getData();
        float[] result = new float[cols];
        for (int row = 0; row < rows; row++) {
            float sum = 0f;
            for (int col = 0; col < cols; col++) {
                sum += vectData[col] * data[col][row];
            }
            vectData[row] = sum;
        }
        return new Vector(result);
    }

    public void print() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.data[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int compareTo(MatrixNonThread otherMatrix) {
        if (this.data.length != otherMatrix.data.length || this.data[0].length != otherMatrix.data[0].length) {
            return -1;
        }

        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[0].length; j++) {
                int cmp = Float.compare(this.data[i][j], otherMatrix.data[i][j]);
                if (cmp != 0) {
                    return cmp;
                }
            }
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        MatrixNonThread matrix = (MatrixNonThread) obj;

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

