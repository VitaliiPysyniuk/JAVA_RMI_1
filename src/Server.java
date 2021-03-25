import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server {
//    private static Server serviceImpl = null;
    private static Executor serviceImpl = null;
    public Server() { }




    public static void main(String[] args) {
        try {
            System.out.println("Server started...");
//            System.setProperty("java.rmi.server.hostname","ec2-34-194-59-42.compute-1.amazonaws.com");
            System.setProperty("java.rmi.server.hostname","ec2-3-131-94-51.us-east-2.compute.amazonaws.com");
//            Server obj = new Server();
            ExecutorImpl obj = new ExecutorImpl();
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
}
