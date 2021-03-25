public class ExecutorImpl implements Executor {
    ICalculation calc, multiplication, multiplicationT, subtraction, addition;
    LambdaExecutor lambdaExecutor = new LambdaExecutor();
    static double[][] C2;

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

    public double[][] calculateC2(int n) {
        System.out.println("calculateC2");
        calc = (a, b) -> {
            double[][] ab = new double[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    ab[j][i] = 20 * (Math.pow(i, 3) - Math.pow(j, 3) + 2);
            return ab;
        };
        new CalculationProcess(calc, lambdaExecutor, ExecutorImpl.C2);
        return ExecutorImpl.C2;
    }
}

interface ICalculation {
    double[][] doCalculation(double[][] a, double[][] b);
}


class LambdaExecutor {
    public void doCalculation(ICalculation ref, double[][] a, double[][] b, double[][] var) {
        var = ref.doCalculation(a, b);
//        if (Decomposition.showІntermResults) {
        if (false) {
            System.out.println("\t\tCalculation result: ");
            Matrix.show(var);
        }
    }
}

//потік виконання обчислень
class CalculationProcess implements Runnable {
    LambdaExecutor executor;
    ICalculation calc;
    double[][] a = {{}};
    double[][] b = {{}};
    double[][] var;
    Thread process;

    public CalculationProcess(ICalculation _calc, LambdaExecutor _executor, double[][] _a, double[][] _b, double[][] _var) {
        this.calc = _calc;
        this.executor = _executor;
        this.a = _a;
        this.b = _b;
        this.var = _var;
        this.process = new Thread(this);
        this.process.start();
    }

    public CalculationProcess(ICalculation _calc, LambdaExecutor _executor, double[][] _var) {
        this.calc = _calc;
        this.executor = _executor;
        this.var = _var;
        this.process = new Thread(this);
        this.process.start();
    }

    public void run() {
        synchronized (executor) {
//            if (Decomposition.showІntermResults)
            if (false)
                System.out.println("\n" + this.process.getName() + " execute calculation: ");
            executor.doCalculation(calc, a, b, var);
        }
    }
}