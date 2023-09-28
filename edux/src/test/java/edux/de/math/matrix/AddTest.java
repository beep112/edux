package test.java.edux.de.math.matrix;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;

import de.edux.math.matrix.Matrix;

import org.junit.jupiter.api.Test;

class AddTest {

	@Test
	void doesAddingSameMatrixEqualTwoTimesScalarMultiple() {
		float[][] data = {{2.3f, 4.5f, 6.7f},{4.5f, 3.9f, 5.5f},{4.5f, 3.9f, 5.5f},{4.5f, 3.9f, 5.5f},{4.5f, 3.9f, 5.5f}};
		Matrix test = new Matrix(data);
		Matrix add = test.add(new Matrix(data));
		test.scalarMultiply(2);
		assertEquals(add.print(), test.print());
		
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
		assertEquals(result.print(), "14.54457 17.10569 19.0111 20.80108 \n"
				+ "7.18182 9.20203 11.0203 12.67255 \n"
				+ "18.24002 21.00248 23.89848 24.48784 \n"
				+ "18.69609 21.27742 23.001099 25.44737 \n"
				+ "");
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
	void handleMismatchDimensions() {
		assertThrows(IllegalArgumentException.class, new Executable() {
            
            @Override
            public void execute() throws Throwable {
            	float[][] data = {{1f,2f},{1f,2f},{1f,2f}};
            	float[][] data1 = {{1f,2f,3f},{1f,2f,3f},{1f,2f,3f}};
                Matrix a = new Matrix(data);
                Matrix b = new Matrix(data1);
                a.add(b);
            }
        });
	}
}
class subtractTest{
	@Test
	void handleMismatchDimensions() {
		assertThrows(IllegalArgumentException.class, new Executable() {
            
            @Override
            public void execute() throws Throwable {
            	float[][] data = {{1f,2f},{1f,2f},{1f,2f}};
            	float[][] data1 = {{1f,2f,3f},{1f,2f,3f},{1f,2f,3f}};
                Matrix a = new Matrix(data);
                Matrix b = new Matrix(data1);
                a.subtract(b);
            }
        });
	}
}
