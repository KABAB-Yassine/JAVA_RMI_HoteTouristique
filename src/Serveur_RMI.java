import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import RMI.Obj_Implimentation;
import RMI.Obj_Implimentation;

public class Serveur_RMI {

    public static void main(String[] args) throws IOException, NamingException, SQLException {

        LocateRegistry.createRegistry(1010);
        System.setProperty("java.security.policy","file:./security.policy");
        Obj_Implimentation obj=new Obj_Implimentation();
        Context cont=new InitialContext();
        //Obj_Implimentation stub= (Obj_Implimentation) UnicastRemoteObject.exportObject(obj, 1010);
        System.out.println((obj.get_liste()));
        //Remote proxy=UnicastRemoteObject.exportObject(obj,1010);
        //System.setProperty("java.rmi.server.hostname", REGISTRY_HOST);
        System.setProperty("java.rmi.server.hostname","localhost");
        cont.rebind("OB",obj);
        System.out.println("object envoyer");
        System.out.println(obj);

    }

}
