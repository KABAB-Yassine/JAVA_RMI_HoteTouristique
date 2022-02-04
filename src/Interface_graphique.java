import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class Interface_graphique implements DAO_traitement {

    public static String login(String username, String password) throws SQLException, IOException {
        Connection conn= connectionDB.getConnection();
        String sql="select * from utilisateurs ";
        PreparedStatement prepared = conn.prepareStatement(sql);
        ResultSet resultat = prepared.executeQuery();
        String profile = null;
        int i = 0;
        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez Remplir les champs vides ");
        } else {
            while (resultat.next()) {
                String username2 = resultat.getString("username");
                String password2 = resultat.getString("password");
                if (username2.equals(username) && password2.equals(password)) {
                    JOptionPane.showMessageDialog(null, "connexion Reussite ,Bienvenu :D ");
                    profile = resultat.getString("profile");
                    i = 1;
                    System.out.println(profile);
                    DAO_User obj =(DAO_User) Factory_Login.GetLogintype(profile);
                    System.out.println(obj);
                    obj.setVisible(true);
                    obj.resize1();
                    obj.UpdateTable();
                    obj.UpdateTable1();

                }
            }
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "Informations Incorrects ");
            }
        }
        return profile;
    }

}
