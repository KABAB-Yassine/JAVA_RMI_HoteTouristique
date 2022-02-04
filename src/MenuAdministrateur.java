import java.awt.EventQueue;

import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class MenuAdministrateur extends JFrame implements  DAO_User{
    private static JFrame frame;
    private JPanel contentPane;

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
                    MenuAdministrateur frame = new MenuAdministrateur();
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
    public MenuAdministrateur() throws IOException {
        Creation();
    }

    public void Creation() throws IOException {
        frame = new JFrame();
        String currentPath = new java.io.File(".").getCanonicalPath();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 826, 498);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
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
                frame1.setVisible(true);
                dispose();
                frame1.resize();
            }
        });

        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setIcon(new ImageIcon(currentPath+"\\Images\\logout.png"));
        btnNewButton.setBounds(10, 11, 36, 31);
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setIcon(new ImageIcon(currentPath + "\\Images\\male-user-edit_25348.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Modifier_res obj1 = null;
                try {
                    obj1 = new Modifier_res();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                obj1.UpdateTable();
                obj1.setVisible(true);
                fermer();
                obj1.resize1();
            }
        });

        JLabel lblNewLabel_5 = new JLabel("Reservations Payees");
        lblNewLabel_5.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblNewLabel_5.setForeground(new Color(160, 82, 45));
        lblNewLabel_5.setBounds(296, 417, 225, 30);
        contentPane.add(lblNewLabel_5);


        JButton btnNewButton_1_2 = new JButton("");
        btnNewButton_1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservations_payees obj1 = null;
                try {
                    obj1 = new reservations_payees();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                obj1.UpdateTable();
                try {
                    obj1.UpdateTable1();
                } catch (NamingException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                obj1.setVisible(true);
                fermer();
                obj1.resize1();
            }
        });
        btnNewButton_1_2.setIcon(new ImageIcon(currentPath + "\\Images\\invoice_106601.png"));
        btnNewButton_1_2.setForeground(new Color(160, 82, 45));
        btnNewButton_1_2.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        btnNewButton_1_2.setBackground(new Color(255, 255, 255));
        btnNewButton_1_2.setBounds(325, 269, 138, 137);
        contentPane.add(btnNewButton_1_2);

        JLabel lblNewLabel_4 = new JLabel("Supprimer une Reservation");
        lblNewLabel_4.setForeground(new Color(160, 82, 45));
        lblNewLabel_4.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblNewLabel_4.setBounds(543, 214, 267, 31);
        contentPane.add(lblNewLabel_4);

        JButton btnNewButton_1_1 = new JButton("");
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Supp_res obj2 = null;
                try {
                    obj2 = new Supp_res();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                obj2.setVisible(true);
                obj2.UpdateTable();
                obj2.resize1();
                fermer();
            }
        });
        btnNewButton_1_1.setIcon(new ImageIcon(currentPath + "\\Images\\male-user-remove_25351.png"));
        btnNewButton_1_1.setForeground(new Color(160, 82, 45));
        btnNewButton_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        btnNewButton_1_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1_1.setBounds(596, 70, 138, 137);
        contentPane.add(btnNewButton_1_1);

        JLabel lblNewLabel_3 = new JLabel("Modifier Une Reservation");
        lblNewLabel_3.setForeground(new Color(160, 82, 45));
        lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblNewLabel_3.setBounds(279, 218, 254, 23);
        contentPane.add(lblNewLabel_3);
        btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        btnNewButton_1.setForeground(new Color(160, 82, 45));
        btnNewButton_1.setBounds(325, 70, 138, 137);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel_2 = new JLabel("Ajouter Une Reservation");
        lblNewLabel_2.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblNewLabel_2.setForeground(new Color(160, 82, 45));
        lblNewLabel_2.setBounds(10, 198, 239, 61);
        contentPane.add(lblNewLabel_2);

        JButton btnNewButton12 = new JButton("ajouter_res");
        btnNewButton12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouter_res obj2 = null;
                try {
                    obj2 = new ajouter_res();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                obj2.setVisible(true);
                obj2.resize1();
                fermer();
            }
        });

        btnNewButton12.setBackground(Color.WHITE);
        btnNewButton12.setIcon(new ImageIcon(currentPath + "\\Images\\male-user-accept_25361.png"));
        btnNewButton12.setBounds(62, 70, 138, 137);
        contentPane.add(btnNewButton12);

        JLabel lblNewLabel = new JLabel("Gestion Administrative");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
        lblNewLabel.setBounds(119, 11, 571, 61);
        contentPane.add(lblNewLabel);

        JLabel image = new JLabel("");
        image.setFont(new Font("Bahnschrift", Font.PLAIN, 60));
        image.setBounds(0, 0, 810, 459);
        image.setIcon(new ImageIcon(currentPath + "\\Images\\img3.jpg"));
        contentPane.add(image);
    }

    @Override
    public void UpdateTable() {

    }

    @Override
    public void UpdateTable1() {

    }
}
