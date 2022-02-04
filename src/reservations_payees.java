import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import RMI.Obj_Remote;
import net.proteanit.sql.DbUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;
import RMI.Obj_Implimentation;

public class reservations_payees extends JFrame{
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
                    reservations_payees  frame = new reservations_payees();
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.UpdateTable();
                    frame.UpdateTable1();
                    frame.getConnection_receptionniste();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public reservations_payees() throws IOException {
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

        JLabel lblNewLabel_3_1 = new JLabel("");
        lblNewLabel_3_1.setIcon(new ImageIcon(currentPath + "\\Images\\invoice_88993.png"));
        lblNewLabel_3_1.setBounds(233, 282, 50, 47);
        contentPane.add(lblNewLabel_3_1);

        JLabel lblNewLabel_2_1 = new JLabel("Liste Des Factures");
        lblNewLabel_2_1.setForeground(Color.BLACK);
        lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        lblNewLabel_2_1.setBackground(Color.WHITE);
        lblNewLabel_2_1.setBounds(286, 282, 274, 47);
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
        btnNewButton.setIcon(new ImageIcon(currentPath+"\\Images\\back.png"));
        btnNewButton.setBounds(10, 11, 32, 28);
        contentPane.add(btnNewButton);

        JButton Ajouter = new JButton("Ajouter aux Factures");
        Ajouter.setIcon(null);
        Ajouter.setBounds(286, 242, 182, 40);
        Ajouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql="select t.Prix_Type from reservation r join type t on (r.type=t.Type and r.id_reservation=?) ";
                String sql2="INSERT INTO r_payees (id_reservation,Full_name,ID_Client,ID_chambre,Nb_Jours,Type,Prix) VALUES (?,?,?,?,?,?,?)";
                String sql3="select id_reservation from r_payees ";
                try {
                    prepared = conn.prepareStatement(sql3);
                    resultat=prepared.executeQuery();
                    int a=0;
                    while(resultat.next()) {
                        int id=resultat.getInt("id_reservation");
                        if(id==id_res) {
                            a=1;
                            JOptionPane.showMessageDialog(null,"Déja Ajouter :( ");
                        }
                    }
                    while(a!=1) {
                        prepared = conn.prepareStatement(sql);
                        prepared.setInt(1,id_res);
                        resultat=prepared.executeQuery();
                        while(resultat.next()) {
                            prix1=resultat.getFloat("Prix_Type");
                        }
                        prix_paye=(float)  prix1 *Nb_Jours1;
                        prepared = conn.prepareStatement(sql2);
                        prepared.setInt(1,id_res);
                        prepared.setString(2,full_name);
                        prepared.setFloat(7,prix_paye);
                        prepared.setInt(4,ID_chambre1);
                        prepared.setInt(3,ID_Client1);
                        prepared.setString(6,Type1);
                        prepared.setFloat(5,Nb_Jours1);
                        prepared.execute();
                        UpdateTable1();
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (NamingException namingException) {
                    namingException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

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
        Ajouter.setForeground(Color.GREEN);
        Ajouter.setBackground(new Color(255, 255, 255));
        Ajouter.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        contentPane.add(Ajouter);

        JLabel lblNewLabel = new JLabel("Paiement Des Reservations");
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
        lblNewLabel_1.setIcon(new ImageIcon(currentPath + "\\Images\\photo-636250158080377143-1.jpg"));
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
    public void UpdateTable1() throws NamingException, IOException, SQLException {
        String sql = "select * from r_payees";
        try {
            prepared = conn.prepareStatement(sql);
            resultat = prepared.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(resultat));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void getConnection_receptionniste()throws IOException, NamingException, SQLException, NotBoundException{
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
                //LocateRegistry.createRegistry(1099);
                //final Hashtable jndiProperties = new Hashtable();
                //jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
                //jndiProperties.put(Context.PROVIDER_URL, "rmi://localhost:1010");
                //InitialContext ctx = new InitialContext(jndiProperties);

                Registry registry = LocateRegistry.getRegistry("localhost",1099);
                Context ctx = new InitialContext();
                //ctx.addToEnvironment(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
                //ctx.addToEnvironment("java.naming.provider.url", "rmi://localhost:1099");

                //registry.LocateRegistry
                //RMI.Obj_Remote stub = (RMI.Obj_Remote) registry.lookup("OB");

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
}