import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Executor extends Remote {
    String ping() throws RemoteException;
    String enableMultithreadedMode() throws RemoteException;
    String enableSingleThreadedMode() throws RemoteException;
    double[][] createMatrix(int n, double max, double min) throws RemoteException;
    double[][] calculateB(int n) throws RemoteException;
    double[][] calculateC2(int n) throws RemoteException;
    double[][] calculateC2andB(int n) throws RemoteException;
    double[][] calculateA2C2(double[][] a, double[][] b) throws RemoteException;


}