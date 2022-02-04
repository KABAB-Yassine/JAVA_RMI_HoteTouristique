import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import RMI.Obj_Remote;
//import liste_payees_recep;
//import RMI.liste_payees_recep;
import RMI.liste_payees_recep;
import net.proteanit.sql.DbUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class liste_reservations extends JFrame implements DAO_User{
    static int nb;
    static int id_res;
    private JPanel contentPane;
    public static int k;
    Connection conn=null;
    PreparedStatement prepared = null ;
    ResultSet resultat = null ;
    Statement stm ;
    private JTable table;
    float prix_paye;
    String full_name;
    int ID_chambre1;
    String Type1;
    float Nb_Jours1;
    int ID_Client1;
    float prix1;
    private JTable table_1;


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
                    liste_reservations  frame = new liste_reservations();
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.UpdateTable();
                    //frame.UpdateTable1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public liste_reservations( ) throws IOException {
        Creation();
    }
    public void Creation( ) throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 826, 498);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        conn = connectionDB.getConnection();
        contentPane.setLayout(null);
        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Auth frame1= null;
                try {
                    frame1 = new Auth();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
                frame1.setVisible(true);
                frame1.resize();
                //frame1.setBounds(100, 100, 826, 498);

            }
        });

        JLabel lblNewLabel_3_1 = new JLabel("");
        lblNewLabel_3_1.setIcon(new ImageIcon(currentPath + "\\Images\\invoice_88993.png"));
        lblNewLabel_3_1.setBounds(233, 282, 50, 47);
        contentPane.add(lblNewLabel_3_1);

        JLabel lblNewLabel_2_1 = new JLabel("Liste Des Factures");
        lblNewLabel_2_1.setForeground(Color.BLACK);
        lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        lblNewLabel_2_1.setBackground(Color.WHITE);
        lblNewLabel_2_1.setBounds(286, 282, 274, 47);
        lblNewLabel_2_1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("demande");

                Socket client = null;
                try {
                    client = new Socket("localhost",3001);
                    OutputStream is = (client.getOutputStream());
                    is.write(1);
                    is.flush();
                    ObjectInputStream flux_in = new ObjectInputStream(client.getInputStream());
                    Obj_Remote data= (Obj_Remote) flux_in.readObject();
                    System.out.println("resultat");
                    System.out.println(data.getMessage());
                    ArrayList<liste_payees_recep> liste=data.get_liste();
                    int a = liste.size();
                    String col[] = {"id_reservation","Full_name","ID_Client", "ID_chambre", "Nb_Jours", "Type", "Prix"};
                    System.out.println(liste.get(0).Full_name);
                    DefaultTableModel tableModel = new DefaultTableModel(col, 0);
                    // The 0 argument is number rows.
                    while(a!=0) {
                        tableModel.addRow(new Object [] {
                                (String) liste.get(a-1).id_reservation,
                                (String) liste.get(a-1).Full_name,
                                (String) liste.get(a-1).ID_Client,
                                (String) liste.get(a-1).ID_chambre,
                                (String) liste.get(a-1).Nb_Jours,
                                (String) liste.get(a-1).Type,
                                (String) liste.get(a-1).Prix,
                        });
                        a--;
                    }
                    System.out.println("table ajouter");
                    JTable table = new JTable(tableModel){
                        public boolean isCellEditable(int c,int d) {
                            return false;
                        }
                    };;
                    JScrollPane scrollPane_1 = new JScrollPane();
                    scrollPane_1.setBounds(10, 329, 790, 119);
                    scrollPane_1.setViewportView(table);
                    contentPane.add(scrollPane_1);
                    getContentPane().add(scrollPane_1);
                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });

        contentPane.add(lblNewLabel_2_1);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 329, 790, 119);
        contentPane.add(scrollPane_1);

        table_1 = new JTable() {
            public boolean isCellEditable(int c,int d) {
                return false;
            }
        };
        scrollPane_1.setViewportView(table_1);

        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setIcon(new ImageIcon(currentPath+"\\Images\\logout.png"));
        btnNewButton.setBounds(10, 11, 32, 28);
        contentPane.add(btnNewButton);



        JScrollPane scrollPane = new JScrollPane();
        scrollPane.addMouseListener(new MouseAdapter() {
        });
        scrollPane.setBounds(10, 107, 790, 126);
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
                id_res=(int) table.getModel().getValueAt(nb,1);
                full_name=table.getModel().getValueAt(nb,0).toString();
                ID_chambre1=(int) table.getModel().getValueAt(nb,2);
                Type1=table.getModel().getValueAt(nb,4).toString();
                Nb_Jours1=(int) table.getModel().getValueAt(nb,5);
                ID_Client1=(int) table.getModel().getValueAt(nb,3);
            }
        });
        scrollPane.setViewportView(table);


        JLabel lblNewLabel = new JLabel("  Interface Receptioniste");
        lblNewLabel.setBounds(87, 11, 647, 61);
        lblNewLabel.setForeground(new Color(128, 0, 0));
        lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("Liste Des Reservations");
        lblNewLabel_2.setForeground(Color.BLACK);
        lblNewLabel_2.setBackground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        lblNewLabel_2.setBounds(286, 59, 220, 47);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(currentPath + "\\Images\\checklist_25365.png"));
        lblNewLabel_3.setBounds(233, 59, 50, 47);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(0, -1, 810, 459);
        lblNewLabel_1.setForeground(new Color(160, 82, 45));
        lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblNewLabel_1.setIcon(new ImageIcon(currentPath + "\\Images\\img5.jpg"));
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
    public void UpdateTable1() {
        String sql ="select * from r_payees";
        try {
            prepared = conn.prepareStatement(sql);
            resultat = prepared.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(resultat));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}