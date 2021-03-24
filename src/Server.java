import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server implements Executor {
    private static Server serviceImpl = null;
    public Server() { }

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

    public static void main(String[] args) {
        try {
            System.out.println("Server started...");
            System.setProperty("java.rmi.server.hostname","ec2-34-194-59-42.compute-1.amazonaws.com");
            Server obj = new Server();
            serviceImpl = obj;
            Registry registry = LocateRegistry.createRegistry(8080);
            Executor stub = (Executor) UnicastRemoteObject.exportObject(serviceImpl, 8080);
//            Executor stub = (Executor) UnicastRemoteObject.exportObject(new Server(), 8080);
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
}
