import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server implements Executor {
    private static Server serviceImpl = null;
    public Server() { }

    public String pingExecutor(long time) {
        System.out.println("Server is active");
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
//        Date date = new Date(time);
//        System.out.println(formatter.format(date));
        long connectionTime = System.currentTimeMillis() - time;
//        formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
//        date = new Date(System.currentTimeMillis());
//        System.out.println(formatter.format(date));
        return ("Connection time: " + connectionTime + " ms");
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
            System.setProperty("java.rmi.server.hostname","ec2-34-194-59-42.compute-1.amazonaws.com");
            Server obj = new Server();
            serviceImpl = obj;
            Registry registry = LocateRegistry.createRegistry(8080);
            Executor stub = (Executor) UnicastRemoteObject.exportObject(serviceImpl, 8080);
            registry.rebind("Executor", stub);
            System.err.println("Server ready");
            System.out.println("Registry list:");
            System.out.println(registry.list()[0]);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
