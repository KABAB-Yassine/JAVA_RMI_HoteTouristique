package RMI;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Obj_Implimentation extends UnicastRemoteObject implements Obj_Remote, Serializable {


    public Obj_Implimentation() throws RemoteException {
        super();
    }
    @Override
    public ArrayList<liste_payees_recep> get_liste() throws RemoteException, SQLException {
        ArrayList<liste_payees_recep> liste=new ArrayList<>();
        System.out.println("get_liste() methode");
        Connection con= connectionDB.getConnection();
        String sql ="select * from r_payees";
        PreparedStatement prepared = con.prepareStatement(sql);
        ResultSet resultat = prepared.executeQuery();
        System.out.println(resultat);

        while (resultat.next()){
            liste_payees_recep row = new liste_payees_recep(resultat.getString(1),resultat.getString(2),resultat.getString(3),resultat.getString(4),resultat.getString(5), resultat.getString(6),resultat.getString(7));
            liste.add(row);
        }
        return liste;
    }
    @Override
    public String getMessage() throws RemoteException{
        return "Implimentation_RMI";
    }
}
