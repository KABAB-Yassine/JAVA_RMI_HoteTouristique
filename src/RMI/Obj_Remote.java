package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Obj_Remote extends Remote {
    public ArrayList<liste_payees_recep> get_liste() throws RemoteException, SQLException;
    public String getMessage() throws RemoteException;
}
