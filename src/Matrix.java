import java.util.List;
import java.util.Random;

public class Matrix {
    public static double[][] create(int n, double max, double min) {
        Random r = new Random();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = min + (max - min) * r.nextDouble();
        return matrix;
    }

    public static double[][] create(int n) {
        Random r = new Random();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = -20 + (20 - (-20)) * r.nextDouble();
        return matrix;
    }

    public static double[][] create(boolean column, int n, double max, double min) {
        Random r = new Random();
        double[][] matrix = new double[n][1];
        for (int i = 0; i < n; i++)
            matrix[i][0] = min + (max - min) * r.nextDouble();
        return matrix;
    }

    public static double[][] create(boolean column, int n) {
        Random r = new Random();
        double[][] matrix = new double[n][1];
        for (int i = 0; i < n; i++)
            matrix[i][0] = -20 + (20 - (-20)) * r.nextDouble();
        return matrix;
    }

    public static double[][] createFromFile(int n , List<Double> a) {
        Random r = new Random();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = a.get(i * n + j);
        return matrix;
    }

    public static double[][] createFromFile(boolean column, int n , List<Double> a) {
        Random r = new Random();
        double[][] matrix = new double[n][1];
        for (int i = 0; i < n; i++)
            matrix[i][0] = a.get(i);
        return matrix;
    }

    public static double[][] mult(double[][] a, double[][] b) {
        double[][] matrix;
        if(!(b.length == 1 && b[0].length == 1)) {
            matrix = new double[a.length][b[0].length];
            for (int i = 0; i < a.length; i++)
                for (int j = 0; j < b[0].length; j++)
                    for (int k = 0; k < b.length; k++) {
                        matrix[i][j] += a[i][k] * b[k][j];
                    }
        } else {
            matrix = new double[a.length][a[0].length];
            for (int i = 0; i < a.length; i++)
                for (int j = 0; j < a[0].length; j++)
                    matrix[i][j] = a[i][j] * b[0][0];
        }
        return matrix;
    }

    public static double[][] transp(double[][] a) {
        double[][] matrix = new double[a[0].length][a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                matrix[j][i] = a[i][j];
        return matrix;
    }

    public static double[][] add(double[][] a, double[][] b) {
        double[][] matrix = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                matrix[i][j] = a[i][j] + b[i][j];
        return matrix;
    }

    public static double[][] substr(double[][] a, double[][] b) {
        double[][] matrix = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                matrix[i][j] = a[i][j] - b[i][j];
        return matrix;
    }

    public static void show(double[][] a) {
        int[] size = Matrix.size(a);
        System.out.print("---------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Dimension of the matrix: " + size[0] + "x" + size[1]);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                if(a[i][j] > 0)
                    System.out.print("+" + a[i][j] + "\t");
                else
                    System.out.print(a[i][j] + "\t");
            System.out.println();
        }
        System.out.print("---------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------\n");
    }

    public static int[] size(double[][] a) {
        int[] size = {a.length, a[0].length};
        return size;
    }
}
