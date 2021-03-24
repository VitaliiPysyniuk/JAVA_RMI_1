import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Executor extends Remote {
    String pingExecutor(long time) throws RemoteException;
    int add(int a, int b) throws  RemoteException;
    double[][] createMatrix(int n, double max, double min);
}