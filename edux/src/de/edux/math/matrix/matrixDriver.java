package de.edux.math.matrix;


public class matrixDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * float[][] data = {{1.51515f, 2.51515f, 3.51515f, 4.01313f}, {5.66667f,
		 * 6.68688f, 7.50515f, 8.65942f}, {9.12001f, 10.50124f, 11.94924f, 12.24392f},
		 * {13.02942f, 14.59054f, 15.49595f, 16.78795f}}; float[][] dataA = {{13.02942f,
		 * 14.59054f, 15.49595f, 16.78795f}, {1.51515f, 2.51515f, 3.51515f, 4.01313f},
		 * {9.12001f, 10.50124f, 11.94924f, 12.24392f}, {5.66667f, 6.68688f, 7.50515f,
		 * 8.65942f}}; Matrix a = new Matrix(data); Matrix b = new Matrix(dataA); Matrix
		 * result = a.add(b); System.out.println(result);
		 */
		 
		//float[][] data1 = {{2f,3f},{2f,3f},{2f,3f}};
		//float[][] data2 = {{1f,2f,3f},{1f,2f,3f},{1f,2f,3f}};
		//Matrix c = new Matrix(data1);
		//Matrix d = new Matrix(data2);
		//System.out.println(c);
		//System.out.println();
		float[][] data = {{2.3f, 4.5f, 6.7f},{4.5f, 3.9f, 5.5f},{4.5f, 3.9f, 5.5f},{4.5f, 3.9f, 5.5f},{4.5f, 3.9f, 5.5f}};
		Matrix test = new Matrix(data);
		Matrix add = test.add(new Matrix(data));
		test.scalarMultiply(2);
		System.out.println(test);
		System.out.println();
		System.out.println(add);
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
		System.out.println(result); 
		//System.out.println("[[14.54457, 17.10569, 19.0111, 20.80108],\n"
		//		+ "[7.18182, 9.20203, 11.0203, 12.67255],\n"
		//		+ "[18.24002, 21.00248, 23.89848, 24.48784],\n"
		//		+ "[18.69609, 21.27742 23.001099 25.44737]]");
	}

}
