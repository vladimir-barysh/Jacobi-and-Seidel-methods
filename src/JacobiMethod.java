import java.util.*;

public class JacobiMethod{

    public static Result JacobiCompute(double[][] matrix, double eps) {
        int variableNumber = matrix[0].length - 1;
        double[] x = new double[variableNumber];
        double[] newX = new double[variableNumber];
        boolean converges = true;

        Arrays.fill(x, 0.0);

        int iterations = 0;
        double realEps = 0;

        for (int i = 0; i < variableNumber; i++) {
            double sum = 0;
            if (matrix[i][i] == 0) {
                throw new IllegalArgumentException("Элемент на диагонали не должен быть равен 0 в строке " + i);
            }
            for (int j = 0; j < variableNumber; j++){
                if (i != j){
                    sum += Math.abs(matrix[i][j]);
                }
            }

            if (Math.abs(matrix[i][i]) <= sum){
                converges = false;
            }

        }

        if (!converges){
            System.out.println("Внимание!");
            System.out.println("Строгое диагональное преобладание не соблюдается, метод может не сходиться.");
            System.out.println("Продолжить вычисление?");
            System.out.println("1 - да, 2 - нет");
            Scanner scanner = new Scanner(System.in);
            int cont = scanner.nextInt();
            switch (cont){
                case 1: break;
                case 2: throw new IllegalArgumentException("Error, doesn't converges");
                default: throw new IllegalArgumentException("Error, there is no variant like that");
            }
        }

        do {
            iterations++;

            for (int i = 0; i < variableNumber; i++) {
                double sum = 0;

                for (int j = 0; j < variableNumber; j++) {
                    if (j != i) {
                        sum += matrix[i][j] * x[j];
                    }
                }
                newX[i] = (matrix[i][variableNumber] - sum) / matrix[i][i];
            }

            realEps = 0;
            for (int i = 0; i < variableNumber; i++) {
                realEps = Math.max(realEps, Math.abs(newX[i] - x[i]));
                x[i] = newX[i];
            }

        } while (realEps > eps);

        double residualNorm = LinearSystem.NevyazkaNormCompute(matrix, x);
        return new Result(x, iterations, residualNorm);
    }
}
