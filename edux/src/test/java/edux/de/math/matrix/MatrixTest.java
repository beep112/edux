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
		float[][] data = {{14.54457f, 17.10569f, 19.0111f, 20.80108f},
				{7.18182f, 9.20203f, 11.0203f, 12.67255f},
				{18.24002f, 21.00248f, 23.89848f, 24.48784f},
				{18.69609f, 21.27742f, 23.001099f, 25.44737f}
				};
		Matrix a = new Matrix(data);
		Matrix b = new Matrix(data.length, data[0].length);
		assertEquals(a.add(b).print(), "14.54457 17.10569 19.0111 20.80108 \n"
				+ "7.18182 9.20203 11.0203 12.67255 \n"
				+ "18.24002 21.00248 23.89848 24.48784 \n"
				+ "18.69609 21.27742 23.001099 25.44737 \n"
				+ "");
	
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
        Matrix matrixA = createTestMatrix(100, 100);
        float constant = 2.0f;
        matrixA.scalarMultiply(constant);

        // Add your assertions here to verify scalar multiplication
        // For example:
        assertEquals(200.0, matrixA.getData(0, 0));
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

        // Calculate the product
        Matrix result = matrixA.multiply(matrixB);

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

    // Helper method to generate a random matrix with values between 0 and 1
    private float[][] generateRandomMatrix(int rows, int cols) {
        float[][] matrix = new float[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextFloat();
            }
        }
        return matrix;
    }

    // Helper method to create a test matrix with random data
    private Matrix createTestMatrix(int rows, int cols) {
        float[][] testData = new float[rows][cols];
        // Fill testData with appropriate values for testing
        // You can use random values or predefined values for testing
        // For simplicity, you can initialize it with zeros
        return new Matrix(testData);
    }
    // Helper method to generate a random matrix with values between 0 and 1

}

