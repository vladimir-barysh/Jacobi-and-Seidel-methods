public class Result {
    public double[] solution;
    public int iterations;
    public double residualNorm;

    public Result(double[] solution, int iterations, double residualNorm) {
        this.solution = solution;
        this.iterations = iterations;
        this.residualNorm = residualNorm;
    }
}
