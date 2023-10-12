package de.edux.math.vector;

import java.util.Random;


public class vectorDriver {
	public static void main(String[] args) {
		BigInterger a = new BigInteger("10000000000");
		BigInteger b = new BigInteger("10000000000");
    	//Vector a = createRandomVector(10000000000);
    	//Vector b = createRandomVector(10000000000);
    	
    	float expected = 0;
    	for (int i = 0; i < 10000000; i++) {
    		expected += a.get(i) * b.get(i);
    	}
    	long start = System.currentTimeMillis();
    	float result = a.dotProduct(b);
    	long end = System.currentTimeMillis();
    	System.out.println("Runtime: "+(end-start));
    	
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
