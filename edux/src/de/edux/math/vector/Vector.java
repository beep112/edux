package de.edux.math.vector;

public class Vector {
	// implemenation for column vectors
	private int column;
	private double[] data;
	
	public Vector(int column) {
		this.column = column;
		data = new double[column];
	}
	
	public Vector(double[] data) {
		column = data.length;
		this.data = data;
	}
	
	
}
