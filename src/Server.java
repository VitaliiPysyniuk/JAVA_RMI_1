import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server implements Executor {
    private static Server serviceImpl = null;
//    private static Executor serviceImpl = null;
    public Server() { }



    public static void main(String[] args) {
        try {
            System.out.println("Server started...");
            System.setProperty("java.rmi.server.hostname","ec2-34-194-59-42.compute-1.amazonaws.com");
            Server obj = new Server();
//            ExecutorImpl obj = new ExecutorImpl();
            serviceImpl = obj;
            Registry registry = LocateRegistry.createRegistry(8080);
            Executor stub = (Executor) UnicastRemoteObject.exportObject(serviceImpl, 8080);
            registry.rebind("Executor", stub);
            System.out.println("Server ready...");
            System.out.println("Registry list: " + registry.list()[0] + "\n");
            new Scanner(System.in).nextLine();
            System.out.println("Server stopped...");
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

//}



    ICalculation calc, multiplication, multiplicationT, subtraction, addition;
    LambdaExecutor lambdaExecutor = new LambdaExecutor();
    static double[][] C2;
    static double[][] B;

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
        System.out.println("calculateB");
//        calc = (a, b) -> {
//            double[][] vector = new double[n][1];
//            for (int i = 0; i < n; i++)
//                vector[i][0] = 20 * (Math.pow(i, 3) + 20);
//            return vector;
//        };
//        CalculationProcess calculationProcess = new CalculationProcess(calc, lambdaExecutor);
//        try {
//            calculationProcess.process.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        B = calculationProcess.result;
//        return B;
        return new double[n][n];
    }


    public double[][] calculateC2(int n) {
        System.out.println("calculateC2");
        calc = (a, b) -> {
            double[][] matrix = new double[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    matrix[j][i] = 20 * (Math.pow(i, 3) - Math.pow(j, 3) + 2);
            return matrix;
        };
        CalculationProcess calculationProcess = new CalculationProcess(calc, lambdaExecutor);
        try {
            calculationProcess.process.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
//        if (Decomposition.showІntermResults) {
        if (false) {
            System.out.println("\t\tCalculation result: ");
            Matrix.show(result);
        }
        return result;
    }
}

//потік виконання обчислень
class CalculationProcess implements Runnable {
    public Thread process;
    public double[][] result;
    LambdaExecutor executor;
    ICalculation calc;
    double[][] a;
    double[][] b;

    public CalculationProcess(ICalculation _calc, LambdaExecutor _executor, double[][] _a, double[][] _b) {
        this.calc = _calc;
        this.executor = _executor;
        this.a = _a;
        this.b = _b;
        this.process = new Thread(this);
        this.process.start();
    }

    public CalculationProcess(ICalculation _calc, LambdaExecutor _executor) {
        this.calc = _calc;
        this.executor = _executor;
        this.process = new Thread(this);
        this.process.start();
    }

    public void run() {
        synchronized (executor) {
//            if (Decomposition.showІntermResults)
            if (false)
                System.out.println("\n" + this.process.getName() + " execute calculation: ");
            result = executor.doCalculation(calc, a, b);
        }
    }
}