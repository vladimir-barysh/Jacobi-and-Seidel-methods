import java.util.*;

public class SeidelMethod {

    public static Result SeidelCompute(double[][] matrix,double eps) {
        int variableNumber = matrix[0].length - 1;
        double[] x = new double[variableNumber];
        double[] newX = new double[variableNumber];

        Arrays.fill(x, 0.0);

        int iterations = 0;
        double realEps = 0;

        do {
            iterations++;

            double[] prevX = x.clone();
            for (int i = 0; i < variableNumber; i++) {
                double sum = 0;

                for (int j = 0; j < variableNumber; j++) {
                    if (j != i) {
                        sum += matrix[i][j] * x[j];
                    }
                }
                x[i] = (matrix[i][variableNumber] - sum) / matrix[i][i];

            }

            realEps = 0;
            for (int i = 0; i < variableNumber; i++) {
                realEps = Math.max(realEps, Math.abs(x[i] - prevX[i]));
            }

        }while (realEps > eps);

        double residualNorm = LinearSystem.NevyazkaNormCompute(matrix, x);
        return new Result(x, iterations, residualNorm);
    }

}
