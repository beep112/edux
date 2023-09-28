package test.java.edux.de.math.matrix;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import de.edux.math.matrix.Matrix;

class SubtractTest {

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
