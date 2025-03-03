import java.io.*;
import java.util.*;

public static void main(String[] args) {
    String fileA = "matrixA.txt";
    String fileB = "matrixB.txt";
    String fileEps = "accuracy.txt";

    List<double[]> matrixListA = new ArrayList<>();
    List<Double> matrixListB = new ArrayList<>();

    double eps = ReadFromFile.ReadEps(fileEps);

    ReadFromFile.ReadMatrix(matrixListA, fileA, matrixListB, fileB);
    LinearSystem newSystem = new LinearSystem(matrixListA, matrixListB);

    /*for (int i = 0; i < 3; i++){
        for (int j = 0; j < 4; j++) {
            System.out.print(newSystem.getMatrixC()[i][j] + " ");
        }
        System.out.println();
    }*/

    Result res = JacobiMethod.JacobiCompute(newSystem.getMatrixC(), eps);

    System.out.println();
    System.out.println("Решение системы Якоби:");
    System.out.println(Arrays.toString(res.solution));
    System.out.println("Число итераций: " + res.iterations);
    System.out.println("Норма невязки: " + res.residualNorm);
    System.out.println();

    Result res1 = SeidelMethod.SeidelCompute(newSystem.getMatrixC(), eps);
    System.out.println("Решение системы Зейделя:");
    System.out.println(Arrays.toString(res1.solution));
    System.out.println("Число итераций: " + res1.iterations);
    System.out.println("Норма невязки: " + res1.residualNorm);
}