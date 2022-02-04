import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.*;
import java.sql.*;
import java.lang.*;
import java.awt.Color;

public class Auth extends JFrame {

    private static JFrame frame;
    private JTextField username;
    private JPasswordField password;
    String currentPath = new java.io.File(".").getCanonicalPath();
    String profile;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Auth login = new Auth();
                    login.setVisible(true);
                    login.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Auth() throws IOException {
        frame=new  JFrame();
        setBounds(100, 100, 826, 498);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setTitle("Application Gestion Touristique");
        setResizable(false);
        JButton connecter = new JButton("Se connecter");
        connecter.setBounds(355, 346, 127, 36);
        connecter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                try {
                    Interface_graphique.login(username1,password1);
                    dispose();

                } catch (SQLException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        connecter.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        connecter.setBackground(new Color(128, 0, 0));
        connecter.setForeground(new Color(255, 250, 250));
        getContentPane().add(connecter);

        JLabel titre = new JLabel("Gestion D'un Hote Touristique");
        titre.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
        titre.setForeground(new Color(16, 194, 201));
        titre.setBounds(135, 26, 540, 110);
        getContentPane().add(titre);

        password = new JPasswordField();
        password.setBounds(305, 285, 220, 25);
        getContentPane().add(password);

        JLabel titre_password = new JLabel("Password :");
        titre_password.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        titre_password.setBounds(209, 286, 75, 25);
        titre_password.setForeground(new Color(40, 232, 232));
        getContentPane().add(titre_password);

        JLabel Username_titre = new JLabel("Username :");
        Username_titre.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        Username_titre.setBounds(209, 250, 75, 25);
        Username_titre.setForeground(new Color(40, 232, 232));
        getContentPane().add(Username_titre);

        username = new JTextField();
        username.setForeground(new Color(0, 0, 0));
        username.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        username.setBounds(305, 249, 220, 25);
        getContentPane().add(username);
        username.setColumns(10);

        JLabel icone = new JLabel("");
        icone.setIcon(new ImageIcon(currentPath+"\\Images\\user (1).png"));
        icone.setBounds(351, 97, 154, 141);
        getContentPane().add(icone);

        JLabel image = new JLabel(" ");
        image.setFont(new Font("Bahnschrift", Font.PLAIN, 60));
        image.setBounds(0, 0, 810, 459);
        image.setIcon(new ImageIcon(currentPath+"\\Images\\img.jpg"));
        getContentPane().add(image);

    }
    public void resize() {
        setResizable(false);
    }
    public static void fermer() {
        frame.dispose();
    }

    public void Creation() {


    }
}
