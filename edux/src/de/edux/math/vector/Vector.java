package de.edux.math.vector;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Arrays;

public class Vector {
    private final float[] data;

    public Vector(int size) {
        this.data = new float[size];
    }

    public Vector(float[] values) {
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

    public void set(int index, float value) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        data[index] = value;
    }
    /**
     * Adds another vector to this vector.
     *
     * @param other The vector to add.
     * @return A new vector representing the result of the addition.
     * @throws IllegalArgumentException If vector sizes do not match.
     */
    public Vector add(Vector other) {
        if (this.data.length != other.data.length) {
            throw new IllegalArgumentException("Vector sizes must match for addition");
        }

        Vector result = new Vector(this.data.length);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new VectorAddTask(this.data, other.data, result.data, 0, this.data.length));

        return result;
    }
    /**
     * Subtracts another vector from this vector.
     *
     * @param other The vector to subtract.
     * @return A new vector representing the result of the subtraction.
     * @throws IllegalArgumentException If vector sizes do not match.
     */
    public Vector subtract(Vector other) {
        if (this.data.length != other.data.length) {
            throw new IllegalArgumentException("Vector sizes must match for subtraction");
        }

        Vector result = new Vector(this.data.length);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new VectorSubtractTask(this.data, other.data, result.data, 0, this.data.length));

        return result;
    }
    /**
     * Multiplies the vector by a scalar value.
     *
     * @param scalar The scalar value to multiply the vector by.
     * @return A new vector representing the result of the multiplication.
     */
    public Vector scalarMultiply(float scalar) {
        Vector result = new Vector(this.data.length);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new ScalarMultiplyTask(this.data, result.data, 0, this.data.length, scalar));

        return result;
    }
    /**
     * Computes the dot product of this vector with another vector.
     *
     * @param other The vector to compute the dot product with.
     * @return The dot product of the two vectors.
     * @throws IllegalArgumentException If vector sizes do not match.
     */
    public float dotProduct(Vector other) {
        if (this.data.length != other.data.length) {
            throw new IllegalArgumentException("Vector sizes must match for dot product");
        }

        float dotProduct = 0;

        ForkJoinPool pool = new ForkJoinPool();
        dotProduct = pool.invoke(new DotProductTask(this.data, other.data, 0, this.data.length));

        return dotProduct;
    }

    /**
     * Computes the cross product of this 3D vector with another 3D vector.
     *
     * @param other The 3D vector to compute the cross product with.
     * @return A new 3D vector representing the result of the cross product.
     * @throws IllegalArgumentException If either vector is not a 3D vector.
     */
    public Vector crossProduct(Vector other) {
        if (this.getSize() != 3 || other.getSize() != 3) {
            throw new IllegalArgumentException("Cross product is only defined for 3D vectors");
        }

        Vector result = new Vector(3);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new CrossProductTask(this.data, other.data, result.data));

        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
    // Task to perform vector addition in parallel
    private static class VectorAddTask extends RecursiveTask<Void> {
        private final float[] a;
        private final float[] b;
        private final float[] result;
        private final int start;
        private final int end;
        private static final int THRESHOLD = 10000; // Adjust as needed

        VectorAddTask(float[] a, float[] b, float[] result, int start, int end) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Void compute() {
        	// If the range is small enough, perform the addition sequentially
            if (end - start <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    result[i] = a[i] + b[i];
                }
            } else {
            	 // Otherwise, split the task into subtasks and invoke them in parallel
                int mid = (start + end) / 2;
                VectorAddTask leftTask = new VectorAddTask(a, b, result, start, mid);
                VectorAddTask rightTask = new VectorAddTask(a, b, result, mid, end);
                invokeAll(leftTask, rightTask);
            }
            return null;
        }
    }
    // Task to perform vector subtraction in parallel
    private static class VectorSubtractTask extends RecursiveTask<Void> {
        private final float[] a;
        private final float[] b;
        private final float[] result;
        private final int start;
        private final int end;
        private static final int THRESHOLD = 10000; // Adjust as needed

        VectorSubtractTask(float[] a, float[] b, float[] result, int start, int end) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Void compute() {
            if (end - start <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    result[i] = a[i] - b[i];
                }
            } else {
                int mid = (start + end) / 2;
                VectorSubtractTask leftTask = new VectorSubtractTask(a, b, result, start, mid);
                VectorSubtractTask rightTask = new VectorSubtractTask(a, b, result, mid, end);
                invokeAll(leftTask, rightTask);
            }
            return null;
        }
    }
    private static class ScalarMultiplyTask extends RecursiveTask<Void> {
        private final float[] input;
        private final float[] result;
        private final int start;
        private final int end;
        private final float scalar;
        private static final int THRESHOLD = 10000; // Adjust as needed

        ScalarMultiplyTask(float[] input, float[] result, int start, int end, float scalar) {
            this.input = input;
            this.result = result;
            this.start = start;
            this.end = end;
            this.scalar = scalar;
        }

        @Override
        protected Void compute() {
            if (end - start <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    result[i] = input[i] * scalar;
                }
            } else {
                int mid = (start + end) / 2;
                ScalarMultiplyTask leftTask = new ScalarMultiplyTask(input, result, start, mid, scalar);
                ScalarMultiplyTask rightTask = new ScalarMultiplyTask(input, result, mid, end, scalar);
                invokeAll(leftTask, rightTask);
            }
            return null;
        }
    }
    // Task to perform vector dot product in parallel
    private static class DotProductTask extends RecursiveTask<Float> {
        private final float[] a;
        private final float[] b;
        private final int start;
        private final int end;
        private static final int THRESHOLD = 10000; // Adjust as needed

        DotProductTask(float[] a, float[] b, int start, int end) {
            this.a = a;
            this.b = b;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Float compute() {
            if (end - start <= THRESHOLD) {
                float dotProduct = 0;
                for (int i = start; i < end; i++) {
                    dotProduct += a[i] * b[i];
                }
                return dotProduct;
            } else {
                int mid = (start + end) / 2;
                DotProductTask leftTask = new DotProductTask(a, b, start, mid);
                DotProductTask rightTask = new DotProductTask(a, b, mid, end);
                invokeAll(leftTask, rightTask);
                return leftTask.join() + rightTask.join();
            }
        }
    }
    // Task to perform vector cross product in parallel
    private static class CrossProductTask extends RecursiveTask<Void> {
        private final float[] a;
        private final float[] b;
        private final float[] result;
        private static final int THRESHOLD = 10000; // Adjust as needed

        CrossProductTask(float[] a, float[] b, float[] result) {
            this.a = a;
            this.b = b;
            this.result = result;
        }

        @Override
        protected Void compute() {
            if (result.length != 3) {
                throw new IllegalArgumentException("Result vector for cross product must have a size of 3");
            }

            float[] temp = new float[3];
            temp[0] = a[1] * b[2] - a[2] * b[1];
            temp[1] = a[2] * b[0] - a[0] * b[2];
            temp[2] = a[0] * b[1] - a[1] * b[0];

            System.arraycopy(temp, 0, result, 0, 3);

            return null;
        }
    }
    
}


