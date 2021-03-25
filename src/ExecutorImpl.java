//public class ExecutorImpl implements Executor {
//    ICalculation calc;
//    LambdaExecutor lambdaExecutor = new LambdaExecutor();
//    static double[][] C2, B, A2C2;
//    boolean singleThread = true;
//
//    public String ping() {
//        System.out.println("Server is active");
//        return ("Connection is active");
//    }
//
//    public String enableMultithreadedMode() {
//        singleThread = false;
//        return "Multithreaded mode ON";
//    }
//
//    public String enableSingleThreadedMode() {
//        singleThread = true;
//        return "Single Threaded mode ON";
//    }
//
//    public double[][] createMatrix(int n, double max, double min) {
//        System.out.println("Matrix creation");
//        double[][] ab = Matrix.create(n, max, min);
//        Matrix.show(ab);
//        System.out.println("Matrix created");
//        return ab;
//    }
//
//    public double[][] calculateC2(int n) {
//        System.out.println("calculated C2");
//        System.out.println(System.currentTimeMillis());
//        calc = (a, b) -> {
//            double[][] matrix = new double[n][n];
//            for (int i = 0; i < n; i++)
//                for (int j = 0; j < n; j++)
//                    matrix[j][i] = 20 * (Math.pow(i, 3) - Math.pow(j, 3) + 2);
//            return matrix;
//        };
//        CalculationProcess calculationProcess = new CalculationProcess(calc, lambdaExecutor);
//        if (singleThread)
//            waitForThread(calculationProcess.thread);
//        C2 = calculationProcess.getResult();
//        return C2;
//    }
//
//    public double[][] calculateB(int n) {
//        System.out.println("Calculated B");
//        System.out.println(System.currentTimeMillis());
//        calc = (a, b) -> {
//            double[][] vector = new double[n][1];
//            for (int i = 0; i < n; i++)
//                vector[i][0] = 20 * (Math.pow(i, 3) + 20);
//            return vector;
//        };
//        CalculationProcess calculationProcess = new CalculationProcess(calc, lambdaExecutor);
//        if (singleThread)
//            waitForThread(calculationProcess.thread);
//        B = calculationProcess.getResult();
//        return B;
//    }
//
//    public double[][] calculateC2andB(int n) {
//        System.out.println("calculated C2andB");
//        calc = (a, b) -> {
//            double[][] matrix = new double[n][n];
//            for (int i = 0; i < n; i++)
//                for (int j = 0; j < n; j++)
//                    matrix[j][i] = 20 * (Math.pow(i, 3) - Math.pow(j, 3) + 2);
//            return matrix;
//        };
//        CalculationProcess calculationProcess1 = new CalculationProcess(calc, lambdaExecutor);
//        calc = (a, b) -> {
//            double[][] vector = new double[n][1];
//            for (int i = 0; i < n; i++)
//                vector[i][0] = 20 * (Math.pow(i, 3) + 20);
//            return vector;
//        };
//        CalculationProcess calculationProcess2 = new CalculationProcess(calc, lambdaExecutor);
//        if (singleThread) {
//            waitForThread(calculationProcess1.thread);
//            waitForThread(calculationProcess2.thread);
//        }
//        C2 = calculationProcess1.getResult();
//        return C2;
//    }
//
//    public double[][] calculateA2C2(double[][] a, double[][] b) {
//        System.out.println("calculated C2");
//        calc = (aa, bb) -> {
//            return Matrix.mult(aa, bb);
//        };
//        CalculationProcess calculationProcess = new CalculationProcess(calc, lambdaExecutor);
//        if (singleThread)
//            waitForThread(calculationProcess.thread);
//        A2C2 = calculationProcess.getResult();
//        return A2C2;
//    }
//
//    public double[][] withoutThreadsB(int n) {
//        System.out.println("Calculated withoutThreadsB");
//        double[][] vector = new double[n][1];
//        for (int i = 0; i < n; i++)
//            vector[i][0] = 20 * (Math.pow(i, 3) + 20);
//        return vector;
//    }
//
//    public double[][] withoutThreadsC2(int n) {
//        System.out.println("Calculated withoutThreadsC2");
//        double[][] matrix = new double[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                matrix[j][i] = 20 * (Math.pow(i, 3) - Math.pow(j, 3) + 2);
//        return matrix;
//    }
//
//
//
//
//
//
//
//    public void waitForThread(Thread process) {
//        try {
//            process.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
////
////    public void simulateDelay(Thread thread, int milliseconds) {
////        try {
////            thread.sleep(1000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
//}
//
//
//
//
//interface ICalculation {
//    double[][] doCalculation(double[][] a, double[][] b);
//}
//
//class LambdaExecutor {
//    public double[][] doCalculation(ICalculation ref, double[][] a, double[][] b) {
//        return ref.doCalculation(a, b);
//    }
//}
//
////потік виконання обчислень
//class CalculationProcess implements Runnable {
//    public Thread thread;
//    private double[][] result;
//    LambdaExecutor executor;
//    ICalculation calc;
//    double[][] a = {{}};
//    double[][] b = {{}};
//
//
//    public CalculationProcess(ICalculation _calc, LambdaExecutor _executor, double[][] _a, double[][] _b) {
//        this.calc = _calc;
//        this.executor = _executor;
//        this.a = _a;
//        this.b = _b;
//        this.thread = new Thread(this);
//        this.thread.start();
//    }
//
//    public CalculationProcess(ICalculation _calc, LambdaExecutor _executor) {
//        this.calc = _calc;
//        this.executor = _executor;
//        this.thread = new Thread(this);
//        this.thread.start();
//    }
//
//    public void run() {
//        synchronized (executor) {
//            this.result = executor.doCalculation(calc, a, b);
//            System.out.println("In CalculationProcess");
//            Matrix.show(result);
//            System.out.println("---------------------------------------------------");
//        }
//    }
//
//    public double[][] getResult() {
//        System.out.print("Print matrix size: ");
//        System.out.println(Matrix.size(this.result));
//        return this.result;
//    }
//
//
//}