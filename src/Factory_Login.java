import java.io.IOException;
import java.lang.*;
import java.awt.*;



public class Factory_Login {

    public static DAO_User GetLogintype(String user) throws IOException {


        return user.equalsIgnoreCase("receptionniste") ? ((DAO_User ) new liste_reservations()) : ((DAO_User) new MenuAdministrateur()) ;

    }
}
