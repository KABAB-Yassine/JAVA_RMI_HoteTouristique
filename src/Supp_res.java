import java.awt.*;
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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Supp_res extends JFrame {
    static int nb;
    static String id_res;
    private JPanel contentPane;
    public static int k;
    Connection conn=null;
    PreparedStatement prepared = null ;
    ResultSet resultat = null ;
    private JTable table;

    /**
     * Launch the application.
     */
    public void fermer(){
        dispose();
    }
    public void resize1() {
        setResizable(false);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Supp_res  frame = new Supp_res();
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
    public Supp_res() throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 826, 498);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        conn = connectionDB.getConnection();

        JButton Ajouter = new JButton("Supprimer");
        Ajouter.setIcon(new ImageIcon(currentPath+"\\Images\\delete.png"));
        Ajouter.setBounds(301, 393, 179, 55);
        Ajouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql="delete from reservation where id_reservation=?";
                try {
                    prepared = conn.prepareStatement(sql);
                    prepared.setString(1,id_res);
                    prepared.execute();
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
        btnNewButton.setIcon(new ImageIcon(currentPath+"\\Images\\back.png"));
        btnNewButton.setBounds(10, 11, 36, 31);
        contentPane.add(btnNewButton);
        scrollPane.setBounds(54, 105, 708, 277);
        contentPane.add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int c,int d) {
                return false;
            }
        };

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nb= table.getSelectedRow();
                id_res=table.getModel().getValueAt(nb,1).toString();
            }
        });

        scrollPane.setViewportView(table);
        Ajouter.setForeground(Color.RED);
        Ajouter.setBackground(new Color(255, 255, 255));
        Ajouter.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        contentPane.add(Ajouter);

        JLabel lblNewLabel = new JLabel("Supprimer Une Reservation");
        lblNewLabel.setBounds(88, 11, 647, 61);
        lblNewLabel.setForeground(new Color(128, 0, 0));
        lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(0, -1, 810, 459);
        lblNewLabel_1.setForeground(new Color(160, 82, 45));
        lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblNewLabel_1.setIcon(new ImageIcon(currentPath+"\\Images\\img1.jpg"));
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