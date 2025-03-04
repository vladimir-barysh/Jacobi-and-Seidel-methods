import java.util.*;

public static void main(String[] args) {

    System.out.println("Выберите режим работы:");
    System.out.println("1 - считать матрицу из файла");
    System.out.println("2 - создать случайную матрицу размером n");

    Scanner in = new Scanner(System.in);
    int mode = in.nextInt();

    switch (mode) {
        case 1:{
            String fileA = "matrixA.txt";
            String fileB = "matrixB.txt";

            List<double[]> matrixListA = new ArrayList<>();
            List<Double> matrixListB = new ArrayList<>();

            ReadFromFile.ReadMatrix(matrixListA, fileA, matrixListB, fileB);

            LinearSystem newSystem = new LinearSystem(matrixListA, matrixListB);

            String fileEps = "accuracy.txt";
            double eps = ReadFromFile.ReadEps(fileEps);

            Result resJacobi = JacobiMethod.JacobiCompute(newSystem.getMatrixC(), eps);

            Result resSeidel = SeidelMethod.SeidelCompute(newSystem.getMatrixC(), eps);

            PrintOut.PrintToConsole(resJacobi, resSeidel);

            PrintOut.PrintToFile(resJacobi, resSeidel);

            break;
        }

        case 2:{
            System.out.println("Введите количество переменных:");
            int variableNumber = 0;

            do{
                variableNumber = in.nextInt();
            }while (variableNumber <= 1);

            double[][] matrixA = NewRandMatrix.generateMatrixA(variableNumber);
            double[] matrixB = NewRandMatrix.generateMatrixB(matrixA);

            System.out.println("Полученная СЛАУ в расширенной матрице:");
            for (int i = 0; i < matrixB.length; i++){
                for (int j = 0; j < matrixB.length; j++){
                    System.out.print(matrixA[i][j] + " ");
                }
                System.out.println(matrixB[i]);
            }
            LinearSystem newSystem = new LinearSystem(matrixA, matrixB);

            String fileEps = "accuracy.txt";
            double eps = ReadFromFile.ReadEps(fileEps);

            Result resJacobi = JacobiMethod.JacobiCompute(newSystem.getMatrixC(), eps);

            Result resSeidel = SeidelMethod.SeidelCompute(newSystem.getMatrixC(), eps);

            PrintOut.PrintToConsole(resJacobi, resSeidel);

            PrintOut.PrintToFile(resJacobi, resSeidel);

            break;
        }

        default:{
            System.out.println("Введен неправильный режим работы. Завершение работы программы...");
            break;
        }
    }
}