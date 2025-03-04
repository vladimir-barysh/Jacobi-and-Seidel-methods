import java.util.*;
public class LinearSystem {
    private double[][] matrixA;
    private double[] matrixB;
    private double[][] matrixC;

    public LinearSystem(List<double[]> matrixListA, List<Double> matrixListB){

        int matrixSize = matrixListB.size();
        this.matrixA = matrixListA.toArray(new double[0][]);
        this.matrixB = new double[matrixSize];
        this.matrixC = new double[matrixSize][matrixSize + 1];

        for (int i = 0; i < matrixSize; i++) {
            this.matrixB[i] = matrixListB.get(i);
        }

        for (int i = 0; i < matrixSize; i++){
            System.arraycopy(this.matrixA[i], 0, this.matrixC[i], 0, matrixSize);
            this.matrixC[i][matrixSize] = this.matrixB[i];
        }

    }

    public LinearSystem(double[][] matrixA, double[] matrixB){
        int matrixSize = matrixB.length;
        this.matrixC = new double[matrixSize][matrixSize + 1];

        for (int i = 0; i < matrixSize; i++){
            System.arraycopy(matrixA[i], 0, this.matrixC[i], 0, matrixSize);
            this.matrixC[i][matrixSize] = matrixB[i];
        }
    }

    public double[][] getMatrixA() {
        return matrixA;
    }

    public double[] getMatrixB() {
        return matrixB;
    }

    public double[][] getMatrixC() {
        return matrixC;
    }

    public static double NevyazkaNormCompute(double[][] matrix, double[] x) {
        int n = matrix[0].length - 1;
        double sumSquares = 0;
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j] * x[j];
            }
            sumSquares += Math.pow(sum - matrix[i][n], 2);
        }
        return Math.sqrt(sumSquares);
    }

}