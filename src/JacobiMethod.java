import java.util.*;

class JacobiMethod extends LinearSystem{

    public JacobiMethod(List<double[]> matrixListA, List<Double> matrixListB) {
        super(matrixListA, matrixListB);
    }

    //работает
    public static Result JacobiCompute(double[][] matrix, double eps) {
        int n = matrix[0].length - 1;
        double[] x = new double[n];
        Arrays.fill(x, 0.0);
        double[] newX = new double[n];
        int iterations = 0;
        double realEps = 0;

        do {
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += matrix[i][j] * x[j];
                    }
                }
                newX[i] = (matrix[i][n] - sum) / matrix[i][i];
            }

            realEps = 0;
            for (int i = 0; i < n; i++) {
                realEps = Math.max(realEps, Math.abs(newX[i] - x[i]));
                x[i] = newX[i];
            }
            iterations++;

        } while (realEps > eps);
        double residualNorm = NevyazkaNormCompute(matrix, x);
        return new Result(x, iterations, residualNorm);
    }
}
