package de.edux.math.matrix;

public class matrixDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * float[][] data1 = {{1.51515f, 2.51515f, 3.51515f, 4.01313f}, {5.66667f,
		 * 6.68688f, 7.50515f, 8.65942f}, {9.12001f, 10.50124f, 11.94924f, 12.24392f},
		 * {13.02942f, 14.59054f, 15.49595f, 16.78795f}}; float[][] data2 = {{13.02942f,
		 * 14.59054f, 15.49595f, 16.78795f}, {1.51515f, 2.51515f, 3.51515f, 4.01313f},
		 * {9.12001f, 10.50124f, 11.94924f, 12.24392f}, {5.66667f, 6.68688f, 7.50515f,
		 * 8.65942f}}; Matrix a = new Matrix(data1); Matrix b = new Matrix(data2);
		 * Matrix result = a.add(b); System.out.print(result.print()); float[][] data3 =
		 * new float[100][100]; for(float i = 0; i<100; i++) { for(float j = 0; j<100;
		 * j++) { data3[(int)i][(int)j] = i*j; } }
		 */
		float[][] data1 = {{2f,3f},{2f,3f},{2f,3f}};
		float[][] data2 = {{1f,2f,3f},{1f,2f,3f},{1f,2f,3f}};
		Matrix a = new Matrix(data1);
		Matrix b = new Matrix(data2);
		a.add(b);
	}

}
