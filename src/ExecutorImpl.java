public class ExecutorImpl implements Executor {
    ICalculation calc;
//    multiplication, multiplicationT, subtraction, addition;
    LambdaExecutor lambdaExecutor = new LambdaExecutor();
    static double[][] C2, B;

    public String ping() {
        System.out.println("Server is active");
        return ("Connection is active");
    }

    public double[][] createMatrix(int n, double max, double min) {
        System.out.println("Matrix creation");
        double[][] ab = Matrix.create(n, max, min);
        Matrix.show(ab);
        System.out.println("Matrix created");
        return ab;
    }

    public double[][] calculateB(int n) {
        System.out.println("calculated B");
        calc = (a, b) -> {
            double[][] vector = new double[n][1];
            for (int i = 0; i < n; i++)
                vector[i][0] = 20 * (Math.pow(i, 3) + 20);
            return vector;
        };
        CalculationProcess calculationProcess = new CalculationProcess(calc, lambdaExecutor);
        simulateDelay(calculationProcess.thread, 1000);
        waitForThread(calculationProcess.thread);
        B = calculationProcess.result;
        return B;
    }

    public void waitForThread(Thread process) {
        try {
            process.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void simulateDelay(Thread thread, int milliseconds) {
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public double[][] calculateC2(int n) {
        System.out.println("calculated C2");
        calc = (a, b) -> {
            double[][] matrix = new double[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    matrix[j][i] = 20 * (Math.pow(i, 3) - Math.pow(j, 3) + 2);
            return matrix;
        };
        CalculationProcess calculationProcess = new CalculationProcess(calc, lambdaExecutor);
        waitForThread(calculationProcess.thread);
        C2 = calculationProcess.result;
        return C2;
    }
}

interface ICalculation {
    double[][] doCalculation(double[][] a, double[][] b);
}


class LambdaExecutor {
    public double[][] doCalculation(ICalculation ref, double[][] a, double[][] b) {
        double[][] result = ref.doCalculation(a, b);
//        if (Decomposition.showIntermediateResults) {
//        if (false) {
//            System.out.println("\t\tCalculation result: ");
//            Matrix.show(result);
//        }
        return result;
    }
}

//потік виконання обчислень
class CalculationProcess implements Runnable {
    public Thread thread;
    public double[][] result;
    LambdaExecutor executor;
    ICalculation calc;
    double[][] a = {{}};
    double[][] b = {{}};


    public CalculationProcess(ICalculation _calc, LambdaExecutor _executor, double[][] _a, double[][] _b) {
        this.calc = _calc;
        this.executor = _executor;
        this.a = _a;
        this.b = _b;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public CalculationProcess(ICalculation _calc, LambdaExecutor _executor) {
        this.calc = _calc;
        this.executor = _executor;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void run() {
        synchronized (executor) {
//            if (Decomposition.showIntermediateResults)
//            if (false)
//                System.out.println("\n" + this.process.getName() + " execute calculation: ");
            result = executor.doCalculation(calc, a, b);
        }
    }
}