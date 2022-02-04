package RMI;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {

    private static Connection con;
    private connectionDB(){};
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gtouristique","root","");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Driver");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return con;
    }
}