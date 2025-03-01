import java.util.*;

public static void main(String[] args) {
    String fileA = "matrixA.txt";
    String fileB = "matrixB.txt";

    List<double[]> matrixListA = new ArrayList<>();
    List<Double> matrixListB = new ArrayList<>();

    MatrixFromFile.ReadMatrixFromFile(matrixListA, fileA, matrixListB, fileB);
    LinearSystem newSystem = new LinearSystem(matrixListA, matrixListB);

    for (int i = 0; i < 3; i++){
        for (int j = 0; j < 4; j++) {
            System.out.print(newSystem.getMatrixC()[i][j] + " ");
        }
        System.out.println();
    }

    Result res = JacobiMethod.JacobiCompute(newSystem.getMatrixC(), 0.01);

    System.out.println("Решение системы:");
    System.out.println(Arrays.toString(res.solution));
    System.out.println("Число итераций: " + res.iterations);
    System.out.println("Норма невязки: " + res.residualNorm);
}