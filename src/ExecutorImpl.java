public class ExecutorImpl implements Executor {
    static double[][] R10, R16, R20, R32, R42, R51, R61;

    public String ping() {
        System.out.println("Server is active");
        return ("Server is active");
    }

    public double[][] calculateR10(int n) {
        System.out.println("calculated R10");
        R10 = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                R10[j][i] = 20 * (Math.pow(i, 3) - Math.pow(j, 3) + 2);
        return R10;
    }

    public double[][] calculateR16(int n) {
        System.out.println("Calculated R16");
        R16 = new double[n][1];
        for (int i = 0; i < n; i++)
            R16[i][0] = 20 * (Math.pow(i, 3) + 20);
        return R16;
    }

    public double[][] calculateR20(double[][] a, double[][] b) {
        System.out.println("Calculated R20");
        R20 =  Matrix.mult(a, b);
        return R20;
    }

    public double[][] calculateR32(double[][] a) {
        System.out.println("Calculated R32");
        R32 =  Matrix.mult(a, R16);
        return R32;
    }

    public double[][] calculateR42(double[][] a) {
        System.out.println("Calculated R42");
        R42 =  Matrix.mult(a, R32);
        return R42;
    }

    public double[][] calculateR51(double[][] a, double[][] b) {
        System.out.println("Calculated R51");
        R51 =  Matrix.mult(a, b);
        return R51;
    }

    public double[][] calculateR61(double[][] a) {
        System.out.println("Calculated R61");
        R61 =  Matrix.mult(a, R51);
        return R61;
    }



//    public void simulateDelay(int milliseconds) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}

