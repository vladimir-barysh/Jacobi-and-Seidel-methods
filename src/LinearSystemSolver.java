import java.io.*;
import java.util.*;

public class LinearSystemSolver{

    public static void writeOutput(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    /**
     * Преобразование массива в строку для вывода.
     */
    public static String arrayToString(double[] arr) {
        StringBuilder sb = new StringBuilder();
        for (double d : arr) {
            sb.append(String.format("%.6f", d)).append(" ");
        }
        return sb.toString();
    }

    /**
     * Автоматическая генерация матрицы системы с заданным коэффициентом диагонального преобладания.
     * Для каждой строки i устанавливается: A[i][i] = α * (сумма модулей недиагональных элементов).
     */
    public static double[][] generateMatrix(int n, double alpha) {
        double[][] A = new double[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    A[i][j] = rand.nextDouble() * 10; // случайные значения от 0 до 10
                    sum += Math.abs(A[i][j]);
                }
            }
            A[i][i] = alpha * sum;
            // Если сумма равна 0, устанавливаем диагональный элемент в 1, чтобы избежать деления на 0
            if (A[i][i] == 0) {
                A[i][i] = 1;
            }
        }
        return A;
    }

    /**
     * Генерация правой части b как произведения матрицы A на известное решение.
     * Здесь в качестве истинного решения берётся вектор, заполненный единицами.
     */
    public static double[] generateRightHandSide(double[][] A, double[] trueSolution) {
        int n = A.length;
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += A[i][j] * trueSolution[j];
            }
            b[i] = sum;
        }
        return b;
    }

    /**
     * Основной метод.
     * Файл input.txt должен содержать параметры в следующем формате:
     *
     * Первая строка – режим работы: manual или auto.
     * Вторая строка – метод решения: jacobi или gauss-seidel.
     * Третья строка – размерность системы (n).
     *
     * Если режим manual:
     *   Далее n строк с элементами матрицы (по n чисел в каждой строке),
     *   затем одна строка с n чисел (столбец свободных членов),
     *   затем строка с требуемой точностью (eps),
     *   и опционально – строка с начальными приближениями (n чисел).
     *
     * Если режим auto:
     *   Далее одна строка – коэффициент α для диагонального преобладания,
     *   затем строка с требуемой точностью (eps).
     *   Матрица генерируется автоматически, а правую часть вычисляем как A*x,
     *   где x – вектор, заполненный единицами.
     *
     * Результаты записываются в файл output.txt.

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String mode = br.readLine().trim();
            String method = br.readLine().trim().toLowerCase();
            int n = Integer.parseInt(br.readLine().trim());

            double[][] A = new double[n][n];
            double[] b = new double[n];
            double eps;
            double[] initialGuess = null;
            StringBuilder outputContent = new StringBuilder();

            if (mode.equalsIgnoreCase("manual")) {
                // Чтение матрицы A
                for (int i = 0; i < n; i++) {
                    String[] line = br.readLine().trim().split("\\s+");
                    for (int j = 0; j < n; j++) {
                        A[i][j] = Double.parseDouble(line[j]);
                    }
                }
                // Чтение вектора b
                String[] bLine = br.readLine().trim().split("\\s+");
                for (int i = 0; i < n; i++) {
                    b[i] = Double.parseDouble(bLine[i]);
                }
                // Чтение точности
                eps = Double.parseDouble(br.readLine().trim());
                // Чтение начального приближения (если имеется)
                String guessLine = br.readLine();
                if (guessLine != null && !guessLine.trim().isEmpty()) {
                    String[] guessArr = guessLine.trim().split("\\s+");
                    initialGuess = new double[n];
                    for (int i = 0; i < n; i++) {
                        initialGuess[i] = Double.parseDouble(guessArr[i]);
                    }
                } else {
                    initialGuess = new double[n];
                    Arrays.fill(initialGuess, 0.0);
                }
            } else if (mode.equalsIgnoreCase("auto")) {
                // Чтение коэффициента диагонального преобладания α
                double alpha = Double.parseDouble(br.readLine().trim());
                // Чтение точности
                eps = Double.parseDouble(br.readLine().trim());
                // Автоматическая генерация матрицы A
                A = generateMatrix(n, alpha);
                // В качестве истинного решения возьмём вектор, заполненный единицами
                double[] trueSolution = new double[n];
                Arrays.fill(trueSolution, 1.0);
                // Вычисление правой части b = A * trueSolution
                b = generateRightHandSide(A, trueSolution);
                // Начальное приближение – вектор нулей
                initialGuess = new double[n];
                Arrays.fill(initialGuess, 0.0);
            } else {
                System.out.println("Неверный режим работы в файле ввода. Используйте 'manual' или 'auto'.");
                return;
            }

            int maxIterations = 10000;
            Result result;
            long startTime = System.currentTimeMillis();
            if (method.equals("jacobi")) {
                result = solveJacobi(A, b, eps, initialGuess, maxIterations);
                outputContent.append("Метод Якоби\n");
            } else if (method.equals("gauss-seidel") || method.equals("zeidely")) {
                result = solveGaussSeidel(A, b, eps, initialGuess, maxIterations);
                outputContent.append("Метод Зейделя\n");
            } else {
                outputContent.append("Неизвестный метод решения: ").append(method).append("\n");
                writeOutput(outputFile, outputContent.toString());
                return;
            }
            long endTime = System.currentTimeMillis();

            // Формирование строки с результатами
            outputContent.append("Решение системы:\n").append(arrayToString(result.solution)).append("\n");
            outputContent.append("Число итераций: ").append(result.iterations).append("\n");
            outputContent.append("Норма невязки: ").append(String.format("%.6f", result.residualNorm)).append("\n");
            outputContent.append("Время расчёта (мс): ").append(endTime - startTime).append("\n");

            writeOutput(outputFile, outputContent.toString());
            System.out.println("Результаты записаны в файл " + outputFile);

            /*
             * Для исследования зависимости числа итераций от начального приближения, точности и выбора метода,
             * а также влияния диагонального преобладания на сходимость, можно подготовить различные файлы ввода:
             * - Изменять начальное приближение (например, отличное от нулевого вектора).
             * - Варьировать требуемую точность eps.
             * - Пробовать оба метода (Якоби и Зейделя).
             * - Генерировать матрицы с различным значением коэффициента α (α > 1 гарантирует диагональное преобладание,
             *   α < 1 позволяет показать, что диагональное преобладание не является необходимым условием сходимости).
             *
             * Таким образом, подобрав соответствующие примеры, можно экспериментально изучить поведение алгоритмов.

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }*/
}
