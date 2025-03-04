import java.util.Random;
import java.util.Scanner;

public class NewRandMatrix {

    public static double[][] generateMatrixA(int variableNumber) {
        double[][] matrixA = new double[variableNumber][variableNumber];
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Создать матрицу с преобладанием главной диагонали?");
        System.out.println("1 - да      2 - нет");
        int mode = 0;

        do{
            mode = scanner.nextInt();
        }while (mode != 1 && mode != 2);

        if (mode == 1){
            for (int i = 0; i < variableNumber; i++) {
                double sum = 0.0;
                for (int j = 0; j < variableNumber; j++) {
                    if (i != j) {
                        matrixA[i][j] = rand.nextInt(10);
                        sum += matrixA[i][j];
                    }
                }

                matrixA[i][i] = sum + 10;
            }
        }
        else{
            for (int i = 0; i < variableNumber; i++) {
                double sum = 0.0;
                for (int j = 0; j < variableNumber; j++) {
                    if (i != j) {
                        matrixA[i][j] = rand.nextInt(10);
                    }
                    if (matrixA[i][i] == 0.0) matrixA[i][i] = 1.0;
                }
            }
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