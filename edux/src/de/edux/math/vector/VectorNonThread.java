package de.edux.math.vector;

import java.util.Arrays;

public class VectorNonThread implements Comparable<VectorNonThread> {
    private final float[] data;

    public VectorNonThread(int size) {
        this.data = new float[size];
    }

    public VectorNonThread(float[] values) {
        this.data = values;
    }

    public int getSize() {
        return data.length;
    }

    public float get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return data[index];
    }

    public float[] getData() {
        return data;
    }

    public void set(int index, float value) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        data[index] = value;
    }

    public VectorNonThread add(VectorNonThread other) {
        if (this.data.length != other.data.length) {
            throw new IllegalArgumentException("Vector sizes must match for addition");
        }

        VectorNonThread result = new VectorNonThread(this.data.length);

        for (int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] + other.data[i];
        }

        return result;
    }

    public VectorNonThread subtract(VectorNonThread other) {
        if (this.data.length != other.data.length) {
            throw new IllegalArgumentException("Vector sizes must match for subtraction");
        }

        VectorNonThread result = new VectorNonThread(this.data.length);

        for (int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] - other.data[i];
        }

        return result;
    }

    public VectorNonThread scalarMultiply(float scalar) {
        VectorNonThread result = new VectorNonThread(this.data.length);

        for (int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] * scalar;
        }

        return result;
    }

    public float dotProduct(VectorNonThread other) {
        if (this.data.length != other.data.length) {
            throw new IllegalArgumentException("Vector sizes must match for dot product");
        }

        float dotProduct = 0;

        for (int i = 0; i < this.data.length; i++) {
            dotProduct += this.data[i] * other.data[i];
        }

        return dotProduct;
    }

    public VectorNonThread crossProduct(VectorNonThread other) {
        if (this.getSize() != 3 || other.getSize() != 3) {
            throw new IllegalArgumentException("Cross product is only defined for 3D vectors");
        }

        VectorNonThread result = new VectorNonThread(3);

        result.data[0] = this.data[1] * other.data[2] - this.data[2] * other.data[1];
        result.data[1] = this.data[2] * other.data[0] - this.data[0] * other.data[2];
        result.data[2] = this.data[0] * other.data[1] - this.data[1] * other.data[0];

        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    @Override
    public int compareTo(VectorNonThread otherVector) {
        if (this.data.length != otherVector.data.length) {
            return -1;
        }

        for (int i = 0; i < this.data.length; i++) {
            int cmp = Float.compare(this.data[i], otherVector.data[i]);
            if (cmp != 0) {
                return cmp;
            }
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        VectorNonThread vector = (VectorNonThread) obj;

        return this.compareTo(vector) == 0;
    }
}
