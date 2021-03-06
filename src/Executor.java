import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Executor extends Remote {
    String ping() throws RemoteException;
    double[][] calculateR10(int n) throws RemoteException;
    double[][] calculateR16(int n) throws RemoteException;
    double[][] calculateR20(double[][] a, double[][] b) throws RemoteException;
    double[][] calculateR32(double[][] a) throws RemoteException;
    double[][] calculateR42(double[][] a) throws RemoteException;
    double[][] calculateR50(double[][] a) throws RemoteException;
    double[][] calculateR60() throws RemoteException;
}