import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ajouter_res extends JFrame {

    private JPanel contentPane;
    private JTextField Full_name;
    private JTextField ID_chambre;
    private JTextField ID_Client;
    private JTextField Nb_Jours;
    Connection conn=null;
    PreparedStatement prepared = null ;
    ResultSet resultat = null ;
    private JTextField Type;
    public void fermer() {
        dispose();
    }
    public void resize1() {
        setResizable(false);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ajouter_res frame = new ajouter_res();
                    frame.setVisible(true);
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ajouter_res() throws IOException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String currentPath = new java.io.File(".").getCanonicalPath();
        setBounds(100, 100, 826, 498);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        conn = connectionDB.getConnection();

        JButton Ajouter = new JButton("Ajouter");
        Ajouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql="insert into reservation (full_name,id_client,id_chambre,nb_jours,type) values (?,?,?,?,?)";
                try {
                    prepared = conn.prepareStatement(sql);
                    prepared.setString(1,Full_name.getText().toString());
                    prepared.setString(2,ID_Client.getText().toString());
                    prepared.setString(3,ID_chambre.getText().toString());
                    prepared.setString(4,Nb_Jours.getText().toString());
                    prepared.setString(5,Type.getText().toString());
                    prepared.execute();
                    JOptionPane.showMessageDialog(null, "Reservation Ajouter");
                    Full_name.setText("");
                    ID_Client.setText("");
                    ID_chambre.setText("");
                    Nb_Jours.setText("");
                    Type.setText("");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        contentPane.setLayout(null);
        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuAdministrateur frame1= null;
                try {
                    frame1 = new MenuAdministrateur();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame1.setVisible(true);
                fermer();
                frame1.resize1();
            }
        });
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setIcon(new ImageIcon(currentPath+"\\Images\\back.png"));
        btnNewButton.setBounds(10, 11, 32, 28);
        contentPane.add(btnNewButton);

        Type = new JTextField();
        Type.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        Type.setHorizontalAlignment(SwingConstants.CENTER);
        Type.setForeground(new Color(139, 0, 0));
        Type.setColumns(10);
        Type.setBounds(343, 222, 180, 29);
        contentPane.add(Type);
        Ajouter.setForeground(new Color(50, 205, 50));
        Ajouter.setBackground(new Color(160, 82, 45));
        Ajouter.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        Ajouter.setBounds(366, 319, 134, 39);
        contentPane.add(Ajouter);

        Nb_Jours = new JTextField();
        Nb_Jours.setHorizontalAlignment(SwingConstants.CENTER);
        Nb_Jours.setForeground(new Color(139, 0, 0));
        Nb_Jours.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        Nb_Jours.setColumns(10);
        Nb_Jours.setBounds(343, 265, 180, 29);
        contentPane.add(Nb_Jours);

        JLabel lblNewLabel_2_3_1 = new JLabel("Nb_Jours :");
        lblNewLabel_2_3_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        lblNewLabel_2_3_1.setBounds(237, 269, 107, 22);
        contentPane.add(lblNewLabel_2_3_1);

        JLabel lblNewLabel_2_3 = new JLabel("Type  :");
        lblNewLabel_2_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        lblNewLabel_2_3.setBounds(237, 222, 107, 22);
        contentPane.add(lblNewLabel_2_3);

        JLabel lblNewLabel_2_2 = new JLabel("ID_Client");
        lblNewLabel_2_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        lblNewLabel_2_2.setBounds(238, 185, 68, 26);
        contentPane.add(lblNewLabel_2_2);

        JLabel lblNewLabel_2_1 = new JLabel("ID_chambre");
        lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        lblNewLabel_2_1.setBounds(238, 145, 81, 29);
        contentPane.add(lblNewLabel_2_1);

        JLabel lblNewLabel_2 = new JLabel("Full Name");
        lblNewLabel_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        lblNewLabel_2.setBounds(237, 108, 81, 26);
        contentPane.add(lblNewLabel_2);

        ID_Client = new JTextField();
        ID_Client.setHorizontalAlignment(SwingConstants.CENTER);
        ID_Client.setForeground(new Color(139, 0, 0));
        ID_Client.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        ID_Client.setColumns(10);
        ID_Client.setBounds(343, 183, 180, 29);
        contentPane.add(ID_Client);

        ID_chambre = new JTextField();
        ID_chambre.setHorizontalAlignment(SwingConstants.CENTER);
        ID_chambre.setForeground(new Color(139, 0, 0));
        ID_chambre.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        ID_chambre.setColumns(10);
        ID_chambre.setBounds(343, 143, 180, 29);
        contentPane.add(ID_chambre);

        Full_name = new JTextField();
        Full_name.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        Full_name.setHorizontalAlignment(SwingConstants.CENTER);
        Full_name.setForeground(new Color(139, 0, 0));
        Full_name.setBounds(343, 103, 180, 29);
        contentPane.add(Full_name);
        Full_name.setColumns(10);

        JLabel lblNewLabel = new JLabel("Ajouter Une Reservation");
        lblNewLabel.setForeground(new Color(128, 0, 0));
        lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
        lblNewLabel.setBounds(119, 11, 571, 61);
        contentPane.add(lblNewLabel);

        JLabel image = new JLabel(" ");
        image.setFont(new Font("Bahnschrift", Font.PLAIN, 60));
        image.setBounds(0, 0, 810, 459);
        image.setIcon(new ImageIcon(currentPath + "\\Images\\hotel.png"));
        contentPane.add(image);


    }

}
