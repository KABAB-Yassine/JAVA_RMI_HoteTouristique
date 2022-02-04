package Alternative_Methodes;

import RMI.Obj_Remote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;

public class int_connection extends JFrame {

    int_connection() throws IOException, NamingException, SQLException, NotBoundException {
            ResultSet res = null;
            ServerSocket socketEcoute = new ServerSocket(3001);
            System.out.println("passed4");

            Socket socketConnexion = socketEcoute.accept();
            System.out.println("passed2");

            InputStream In = socketConnexion.getInputStream();
            ObjectOutputStream Ou=new ObjectOutputStream(socketConnexion.getOutputStream());
            int a=In.read();
            System.out.println(a);
            if(a==1){
                int choix=JOptionPane.showConfirmDialog(null,"accepter la demande","demande",JOptionPane.YES_NO_OPTION);;
                if(choix==0){
                    System.out.println("RMI context");
                    Registry registry = LocateRegistry.getRegistry("localhost",1099);
                    Context ctx = new InitialContext();
                    System.setProperty("java.rmi.server.hostname","localhost");
                    Obj_Remote stub = (Obj_Remote) ctx.lookup("OB");
                    String message=stub.getMessage();
                    System.out.println(message);
                    //JTable tab= stub.get_liste();
                    //System.out.println(tab);
                    Ou.writeObject(stub);
                }
            }

        }

    public void fermer() {
        dispose();
    }

    public void resize1() {
        setResizable(false);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int_connection frame = new int_connection();
                    frame.setVisible(false);
                    frame.setResizable(false);
                    frame.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
