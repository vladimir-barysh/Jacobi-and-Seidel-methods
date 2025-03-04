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

    Result resJacobi = JacobiMethod.JacobiCompute(newSystem.getMatrixC(), eps);

    System.out.println();
    System.out.println("Решение системы Якоби:");
    System.out.println(Arrays.toString(resJacobi.solution));
    System.out.println("Число итераций: " + resJacobi.iterations);
    System.out.println("Норма невязки: " + resJacobi.residualNorm);
    System.out.println();

    Result resSeidel = SeidelMethod.SeidelCompute(newSystem.getMatrixC(), eps);

    System.out.println("Решение системы Зейделя:");
    System.out.println(Arrays.toString(resSeidel.solution));
    System.out.println("Число итераций: " + resSeidel.iterations);
    System.out.println("Норма невязки: " + resSeidel.residualNorm);
}