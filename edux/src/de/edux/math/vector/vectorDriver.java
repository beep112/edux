package de.edux.math.vector;

import java.util.Random;

public class vectorDriver {
	public static void main(String[] args) {
        
    	Vector a = createRandomVector(10000000);
    	Vector b = createRandomVector(10000000);
    	
    	float expected = 0;
    	for (int i = 0; i < 10000000; i++) {
    		expected += a.get(i) * b.get(i);
    	}
    	
    	float result = a.dotProduct(b);
    	
    	System.out.println(expected);
    	System.out.println(result);
    }
    private static Vector createRandomVector(int length) {
        Random rand = new Random();
        float[] values = new float[length];
        for (int i = 0; i < length; i++) {
            values[i] = rand.nextFloat();
        }
        return new Vector(values);
    }
}
