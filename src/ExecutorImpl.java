public class ExecutorImpl implements Executor{
    public String ping() {
        System.out.println("Server is active");
        return ("Connection is active");
    }

    public int add(int a, int b) {
        int sum = a + b;
        System.err.println("Sum: " + sum);
        return sum;
    }

    public double[][] createMatrix(int n, double max, double min) {
        System.out.println("Matrix creation");
        double[][] ab = MyJAMA.create(n, max, min);
        MyJAMA.show(ab);
        System.out.println("Matrix created");
        return ab;
    }
}
