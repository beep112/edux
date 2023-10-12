package test.java.edux.de.math.matrix;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.function.Executable;

import de.edux.math.matrix.Matrix;

import org.junit.jupiter.api.Test;

class MatrixTest {

	@Test
	void doesAddingSameMatrixEqualTwoTimesScalarMultiple() {
		float[][] data = {{2.3f, 4.5f, 6.7f},{4.5f, 3.9f, 5.5f},{4.5f, 3.9f, 5.5f},{4.5f, 3.9f, 5.5f},{4.5f, 3.9f, 5.5f}};
		Matrix test = new Matrix(data);
		Matrix add = test.add(new Matrix(data));
		test.scalarMultiply(2);
		assertEquals(add.toString(), test.toString());
		
	}
	@Test
	void checkSimpleFloatCase() {
		float[][] data1 = {{1.51515f, 2.51515f, 3.51515f, 4.01313f}, 
                {5.66667f, 6.68688f, 7.50515f, 8.65942f}, 
                {9.12001f, 10.50124f, 11.94924f, 12.24392f}, 
                {13.02942f, 14.59054f, 15.49595f, 16.78795f}};
		float[][] data2 = {{13.02942f, 14.59054f, 15.49595f, 16.78795f},
				{1.51515f, 2.51515f, 3.51515f, 4.01313f}, 
                {9.12001f, 10.50124f, 11.94924f, 12.24392f},
                {5.66667f, 6.68688f, 7.50515f, 8.65942f}};
		Matrix a = new Matrix(data1);
		Matrix b = new Matrix(data2);
		Matrix result = a.add(b);
		assertEquals(result.toString(), "[[14.54457, 17.10569, 19.0111, 20.80108],\n"
				+ "[7.18182, 9.20203, 11.0203, 12.67255],\n"
				+ "[18.24002, 21.00248, 23.89848, 24.48784],\n"
				+ "[18.69609, 21.27742, 23.001099, 25.44737]]");
	}
	@Test
	void handleAdditonToAZeroMatrix() {
		float[][] data1 = {{1.5f, 1.5f}, {1.5f, 1.5f}};
		float[][] data2 = {{0, 0}, {0, 0}};
		Matrix a = new Matrix(data1);
		Matrix b = new Matrix(data2);
		Matrix answer = a.add(b);
		assertEquals(a.toString(),answer.toString());
	
	}

    @Test
    public void testMatrixAdditionInvalidDimensions() {
        float[][] dataA = {{1, 2, 3}, {4, 5, 6}};
        float[][] dataB = {{7, 8, 9}};
        Matrix matrixA = new Matrix(dataA);
        Matrix matrixB = new Matrix(dataB);

        assertThrows(IllegalArgumentException.class, () -> {
            matrixA.add(matrixB);
        });
    }

    @Test
    public void testMatrixSubtractionInvalidDimensions() {
        float[][] dataA = {{1, 2, 3}, {4, 5, 6}};
        float[][] dataB = {{7, 8, 9}};
        Matrix matrixA = new Matrix(dataA);
        Matrix matrixB = new Matrix(dataB);

        assertThrows(IllegalArgumentException.class, () -> {
            matrixA.subtract(matrixB);
        });
    }

    @Test
    public void testMatrixMultiplicationInvalidDimensions() {
        float[][] dataA = {{1, 2, 3, 4}, {4, 5, 6, 6}};
        float[][] dataB = {{7, 8}, {9, 10}, {11, 12}};
        Matrix matrixA = new Matrix(dataA);
        Matrix matrixB = new Matrix(dataB);

        assertThrows(IllegalArgumentException.class, () -> {
            matrixA.multiply(matrixB);
        });
    }
    @Test
    public void testScalarMultiplication() {
        Matrix matrixA = createRandomTestMatrix(100, 100);       
        Matrix matrixB = new Matrix(matrixA);
        float constant = 2.0f;
        matrixA.scalarMultiply(constant);
        float[][] dataB = matrixB.getData();
        for(int row = 0; row < dataB.length; row++) {
        	for(int col = 0; col < dataB[row].length; col++) {
        		dataB[row][col] *= 2;
        	}
        }
        matrixB = new Matrix(dataB);

        // Add your assertions here to verify scalar multiplication
        // For example:
        assertEquals(matrixB.toString(),matrixA.toString());
        // Add more assertions as needed
    }
    @Test
    public void testMatrixMultiplication() {
        int rows = 100;
        int cols = 100;
        // Create two random 100x100 matrices
        float[][] dataA = generateRandomMatrix(rows, cols);
        float[][] dataB = generateRandomMatrix(rows, cols);

        Matrix matrixA = new Matrix(dataA);
        Matrix matrixB = new Matrix(dataB);
        long start = System.currentTimeMillis();
        // Calculate the product
        Matrix result = matrixA.multiply(matrixB);
        long end = System.currentTimeMillis();
        System.out.println("100 by 100 matrix multiplciation takes "+(end-start)+"ms");

        // Perform manual multiplication to calculate the expected result
        float[][] expectedData = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                float sum = 0.0f;
                for (int k = 0; k < cols; k++) {
                    sum += dataA[i][k] * dataB[k][j];
                }
                expectedData[i][j] = sum;
            }
        }

        // Check if the result matches the expected data
        assertArrayEquals(expectedData, result.getData());
    }

    public static Matrix createRandomTestMatrix(int rows, int cols) {
        float[][] testData = generateRandomMatrix(rows, cols);
        return new Matrix(testData);
    }

    private static float[][] generateRandomMatrix(int rows, int cols) {
        float[][] matrix = new float[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextFloat() * 10000;
            }
        }
        return matrix;
    }

}

