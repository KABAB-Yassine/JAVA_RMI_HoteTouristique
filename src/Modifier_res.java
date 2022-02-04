import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Modifier_res extends JFrame {

    private JPanel contentPane;
    private JTextField Full_name;
    private JTextField ID_chambre;
    private JTextField ID_Client;
    private JTextField Nb_Jours;
    Connection conn=null;
    PreparedStatement prepared = null ;
    ResultSet resultat = null ;
    private JTextField Type;
    private JTable table;
    private JTextField id_reservation;
    public void fermer(){
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
                    Modifier_res frame = new Modifier_res();
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.UpdateTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Modifier_res() throws IOException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String currentPath = new java.io.File(".").getCanonicalPath();
        setBounds(100, 100, 826, 498);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        conn = connectionDB.getConnection();

        JButton Ajouter = new JButton("Modifier");
        Ajouter.setBounds(153, 354, 118, 33);
        Ajouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql="update reservation set full_name=?,id_client=?,id_chambre=?,nb_jours=?,type=? where id_reservation=?";
                try {
                    prepared = conn.prepareStatement(sql);
                    prepared.setString(1,Full_name.getText().toString());
                    prepared.setString(2,ID_Client.getText().toString());
                    prepared.setString(3,ID_chambre.getText().toString());
                    prepared.setString(4,Nb_Jours.getText().toString());
                    prepared.setString(5,Type.getText().toString());
                    prepared.setString(6,id_reservation.getText().toString());
                    prepared.execute();
                    JOptionPane.showMessageDialog(null, "Modification Effectuée");
                    Full_name.setText("");
                    ID_Client.setText("");
                    ID_chambre.setText("");
                    Nb_Jours.setText("");
                    Type.setText("");
                    id_reservation.setText("");

                    UpdateTable();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        contentPane.setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.addMouseListener(new MouseAdapter() {
        });
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
                dispose();
                frame1.resize1();
            }
        });

        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setIcon(new ImageIcon(currentPath + "\\Images\\back.png"));
        btnNewButton.setBounds(10, 11, 32, 28);
        contentPane.add(btnNewButton);
        scrollPane.setBounds(54, 105, 708, 277);
        contentPane.add(scrollPane);
        scrollPane.setBounds(295, 102, 515, 230);
        contentPane.add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int c,int d) {
                return false;
            }
        };
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int nb= table.getSelectedRow();
                String id_res=table.getModel().getValueAt(nb,1).toString();
                String full_name=table.getModel().getValueAt(nb,0).toString();
                String ID_chambre1=table.getModel().getValueAt(nb,2).toString();
                String Type1=table.getModel().getValueAt(nb,4).toString();
                String Nb_Jours1=table.getModel().getValueAt(nb,5).toString();
                String ID_Client1=table.getModel().getValueAt(nb,3).toString();
                Full_name.setText(full_name);
                ID_Client.setText(ID_Client1);
                ID_chambre.setText(ID_chambre1);
                Nb_Jours.setText(Nb_Jours1);
                Type.setText(Type1);
                id_reservation.setText(id_res);
            }
        });
        scrollPane.setViewportView(table);

        id_reservation = new JTextField();
        id_reservation.setHorizontalAlignment(SwingConstants.CENTER);
        id_reservation.setForeground(new Color(139, 0, 0));
        id_reservation.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        id_reservation.setColumns(10);
        id_reservation.setBounds(129, 103, 156, 29);
        contentPane.add(id_reservation);

        JLabel lblNewLabel_2_4 = new JLabel("ID_Reservation");
        lblNewLabel_2_4.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        lblNewLabel_2_4.setBounds(12, 104, 107, 26);
        contentPane.add(lblNewLabel_2_4);

        Type = new JTextField();
        Type.setBounds(129, 263, 156, 29);
        Type.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        Type.setHorizontalAlignment(SwingConstants.CENTER);
        Type.setForeground(new Color(139, 0, 0));
        Type.setColumns(10);
        contentPane.add(Type);
        Ajouter.setForeground(new Color(50, 205, 50));
        Ajouter.setBackground(new Color(160, 82, 45));
        Ajouter.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        contentPane.add(Ajouter);

        Nb_Jours = new JTextField();
        Nb_Jours.setBounds(129, 303, 156, 29);
        Nb_Jours.setHorizontalAlignment(SwingConstants.CENTER);
        Nb_Jours.setForeground(new Color(139, 0, 0));
        Nb_Jours.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        Nb_Jours.setColumns(10);
        contentPane.add(Nb_Jours);

        JLabel lblNewLabel_2_3_1 = new JLabel("Nb_Jours :");
        lblNewLabel_2_3_1.setBounds(12, 310, 107, 22);
        lblNewLabel_2_3_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        contentPane.add(lblNewLabel_2_3_1);

        JLabel lblNewLabel_2_3 = new JLabel("Type  :");
        lblNewLabel_2_3.setBounds(12, 270, 107, 22);
        lblNewLabel_2_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        contentPane.add(lblNewLabel_2_3);

        JLabel lblNewLabel_2_2 = new JLabel("ID_Client");
        lblNewLabel_2_2.setBounds(10, 229, 68, 26);
        lblNewLabel_2_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        contentPane.add(lblNewLabel_2_2);

        JLabel lblNewLabel_2_1 = new JLabel("ID_chambre");
        lblNewLabel_2_1.setBounds(10, 181, 81, 29);
        lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        contentPane.add(lblNewLabel_2_1);

        JLabel lblNewLabel_2 = new JLabel("Full Name");
        lblNewLabel_2.setBounds(10, 144, 81, 26);
        lblNewLabel_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        contentPane.add(lblNewLabel_2);

        ID_Client = new JTextField();
        ID_Client.setBounds(129, 223, 156, 29);
        ID_Client.setHorizontalAlignment(SwingConstants.CENTER);
        ID_Client.setForeground(new Color(139, 0, 0));
        ID_Client.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        ID_Client.setColumns(10);
        contentPane.add(ID_Client);

        ID_chambre = new JTextField();
        ID_chambre.setBounds(129, 183, 156, 29);
        ID_chambre.setHorizontalAlignment(SwingConstants.CENTER);
        ID_chambre.setForeground(new Color(139, 0, 0));
        ID_chambre.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        ID_chambre.setColumns(10);
        contentPane.add(ID_chambre);

        Full_name = new JTextField();
        Full_name.setBounds(129, 143, 156, 29);
        Full_name.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        Full_name.setHorizontalAlignment(SwingConstants.CENTER);
        Full_name.setForeground(new Color(139, 0, 0));
        contentPane.add(Full_name);
        Full_name.setColumns(10);

        JLabel lblNewLabel = new JLabel("Modifie Une Reservation");
        lblNewLabel.setBounds(119, 11, 571, 61);
        lblNewLabel.setForeground(new Color(128, 0, 0));
        lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel();
        lblNewLabel_1.setBounds(0, -1, 810, 459);
        lblNewLabel_1.setForeground(new Color(160, 82, 45));
        lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblNewLabel_1.setIcon(new ImageIcon(currentPath+"\\Images\\hotel.png"));
        contentPane.add(lblNewLabel_1);
    }
    public void UpdateTable() {
        String sql ="select * from reservation";
        try {
            prepared = conn.prepareStatement(sql);
            resultat = prepared.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultat));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
}