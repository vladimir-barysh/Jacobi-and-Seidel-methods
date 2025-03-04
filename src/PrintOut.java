import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class PrintOut {

    public static void PrintToConsole(Result resJacobi, Result resSeidel){
        System.out.println();
        System.out.println("Решение системы методом Якоби:");
        System.out.println(Arrays.toString(resJacobi.solution));
        System.out.println("Число итераций: " + resJacobi.iterations);
        System.out.println("Норма невязки: " + resJacobi.residualNorm);

        System.out.println();
        System.out.println("Решение системы методом Зейделя:");
        System.out.println(Arrays.toString(resSeidel.solution));
        System.out.println("Число итераций: " + resSeidel.iterations);
        System.out.println("Норма невязки: " + resSeidel.residualNorm);
        System.out.println();
    }

    public static void PrintToFile(Result resJacobi, Result resSeidel){
        String fileName = "result.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Решение системы методом Якоби:\n");
            writer.write(Arrays.toString(resJacobi.solution) + "\n");
            writer.write("Число итераций: " + resJacobi.iterations + "\n");
            writer.write("Норма невязки: " + resJacobi.residualNorm + "\n\n");

            writer.write("Решение системы методом Зейделя:\n");
            writer.write(Arrays.toString(resSeidel.solution) + "\n");
            writer.write("Число итераций: " + resSeidel.iterations + "\n");
            writer.write("Норма невязки: " + resSeidel.residualNorm + "\n");

            System.out.println("Результаты сохранены в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }

    }
}
