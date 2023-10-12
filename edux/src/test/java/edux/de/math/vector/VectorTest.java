package test.java.edux.de.math.vector;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import de.edux.math.vector.Vector;
import java.util.Random;

public class VectorTest {

    private static final int VECTOR_LENGTH_100 = 100;
    private static final int VECTOR_LENGTH_100000 = 100000;

    // Test cases for vector addition
    @Test
    public void testVectorAddition1() {
        Vector a = new Vector(new float[]{1, 2, 3});
        Vector b = new Vector(new float[]{4, 5, 6});
        Vector expected = new Vector(new float[]{5, 7, 9});

        Vector result = a.add(b);

        assertEquals(expected.toString(), result.toString());
    }

    // Add more vector addition test cases with vectors of length 100
    @Test
    public void testVectorAddition2() {
        Vector a = createRandomVector(VECTOR_LENGTH_100);
        Vector b = createRandomVector(VECTOR_LENGTH_100);

        Vector expected = new Vector(VECTOR_LENGTH_100);
        for (int i = 0; i < VECTOR_LENGTH_100; i++) {
            expected.set(i, a.get(i) + b.get(i));
        }

        Vector result = a.add(b);

        assertEquals(expected.toString(), result.toString());
    }
    // Add more vector addition with test cases of vectors length 10000000
    @Test
    public void testVectorAddition3( ) {
    	Vector a = createRandomVector(VECTOR_LENGTH_100000);
    	Vector b = createRandomVector(VECTOR_LENGTH_100000);
    	
        Vector expected = new Vector(VECTOR_LENGTH_100000);
        for (int i = 0; i < VECTOR_LENGTH_100000; i++) {
            expected.set(i, a.get(i) + b.get(i));
        }
        
        Vector result = a.add(b);
        
        assertEquals(expected.toString(), result.toString());
    }

    // Test cases for vector subtraction
    @Test
    public void testVectorSubtraction1() {
        Vector a = new Vector(new float[]{1, 2, 3});
        Vector b = new Vector(new float[]{4, 5, 6});
        Vector expected = new Vector(new float[]{-3, -3, -3});

        Vector result = a.subtract(b);

        assertEquals(expected.toString(), result.toString());
    }

    // Add more vector subtraction test cases with vectors of length 100
    @Test
    public void testVectorSubtraction2() {
        Vector a = createRandomVector(VECTOR_LENGTH_100);
        Vector b = createRandomVector(VECTOR_LENGTH_100);

        Vector expected = new Vector(VECTOR_LENGTH_100);
        for (int i = 0; i < VECTOR_LENGTH_100; i++) {
            expected.set(i, a.get(i) - b.get(i));
        }

        Vector result = a.subtract(b);

        assertEquals(expected.toString(), result.toString());
    }

    // Test cases for vector dot product
    @Test
    public void testVectorDotProduct1() {
        Vector a = new Vector(new float[]{1, 2, 3});
        Vector b = new Vector(new float[]{4, 5, 6});
        double expected = 32;

        double result = a.dotProduct(b);

        assertEquals(expected, result);
    }

    // Add more vector dot product test cases with vectors of length 100
    @Test
    public void testVectorDotProduct2() {
        Vector a = createRandomVector(VECTOR_LENGTH_100);
        Vector b = createRandomVector(VECTOR_LENGTH_100);

        float expected = 0;
        for (int i = 0; i < VECTOR_LENGTH_100; i++) {
            expected += a.get(i) * b.get(i);
        }

        float result = a.dotProduct(b);

        assertEquals(expected, result);
    }
    // test case of a vectors that are length 10000
    @Test
    public void testVectorDotProduct3() {
    	Vector a = createRandomVector(10000);
    	Vector b = createRandomVector(10000);
    	
    	float expected = 0;
    	for (int i = 0; i < 10000; i++) {
    		expected += a.get(i) * b.get(i);
    	}
    	
    	float result = a.dotProduct(b);
    	
    	assertEquals(result, expected);
    }

    // Test cases for vector cross product
    @Test
    public void testVectorCrossProduct1() {
        Vector a = new Vector(new float[]{1, 2, 3});
        Vector b = new Vector(new float[]{4, 5, 6});
        Vector expected = new Vector(new float[]{-3, 6, -3});

        Vector result = a.crossProduct(b);

        assertEquals(expected.toString(), result.toString());
    }

    // Add more vector cross product test cases with vectors of length 100
    @Test
    public void testVectorCrossProduct2() {
        Vector a = createRandomVector(3);  // 3D vector
        Vector b = createRandomVector(3);  // 3D vector
        Vector expected = new Vector(3);

        expected.set(0, a.get(1) * b.get(2) - a.get(2) * b.get(1));
        expected.set(1, a.get(2) * b.get(0) - a.get(0) * b.get(2));
        expected.set(2, a.get(0) * b.get(1) - a.get(1) * b.get(0));

        Vector result = a.crossProduct(b);

        assertEquals(expected.toString(), result.toString());
    }

    // Helper method to create a random vector of a given length
    private Vector createRandomVector(int length) {
        Random rand = new Random();
        float[] values = new float[length];
        for (int i = 0; i < length; i++) {
            values[i] = rand.nextFloat()*10000f;
        }
        return new Vector(values);
    }
}

