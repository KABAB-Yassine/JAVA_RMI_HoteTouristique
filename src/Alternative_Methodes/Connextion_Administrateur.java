package Alternative_Methodes;

import RMI.Obj_Remote;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;

public class Connextion_Administrateur implements Runnable {
    Socket data;
    ObjectOutputStream Out;
    InputStream In;

    Connextion_Administrateur(Socket data) throws IOException {
        this.data = data;
        this.In = data.getInputStream();
        this.Out = new ObjectOutputStream(data.getOutputStream());
    }

    public void run() {
        ResultSet res = null;
        int ligne ;
        System.out.println("test16");

        try {
            ligne = In.read();
            System.out.println(In.read());
            System.out.println("passed");

            Context ctx = new InitialContext();
            Obj_Remote stub = (Obj_Remote) ctx.lookup("OB");
            //res = stub.get_liste();

            Out.writeObject(res);
            Out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}