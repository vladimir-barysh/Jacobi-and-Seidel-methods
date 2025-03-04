import java.util.Random;

public class NewRandMatrix {

    public static double[][] generateMatrixA(int variableNumber) {
        double[][] matrixA = new double[variableNumber][variableNumber];
        Random rand = new Random();
        for (int i = 0; i < variableNumber; i++) {
            for (int j = 0; j < variableNumber; j++) {
                matrixA[i][j] = rand.nextInt(10);
            }
            if (matrixA[i][i] == 0.0) matrixA[i][i] = 1.0;
        }
        return matrixA;
    }

    public static double[] generateMatrixB(double[][] matrixA) {
        int variableNumber = matrixA.length;
        double[] matrixB = new double[variableNumber];
        Random rand = new Random();
        for (int i = 0; i < variableNumber; i++) {
            matrixB[i] = rand.nextInt(10);
        }
        return matrixB;
    }
}
