/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.ilsestosenso.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Camil
 */
public class DBScore {
    
    private static Connection connection;
    
    public static final String CREATE_TABLE_SCORE = "CREATE TABLE IF NOT EXISTS score (username VARCHAR(15) PRIMARY KEY, time INT, score INT, data DATE)";

    
    public static void connect() throws SQLException{
        Properties dbprops = new Properties();
        dbprops.setProperty("user", "user");
        dbprops.setProperty("password", "1234");
        connection = DriverManager.getConnection("jdbc:h2:./resources/db/score", dbprops);
        Statement stm = connection.createStatement();
        stm.executeUpdate(CREATE_TABLE_SCORE);
        stm.close();
    }
    
    public static void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public static void insertScore (UserScore user) throws SQLException{
        
         
         PreparedStatement pstm = connection.prepareStatement("INSERT INTO score VALUES (?,?,?,?)");
         pstm.setString(1, user.getUsername());
         pstm.setInt(2, user.getTime());
         pstm.setInt(3, user.getScore());
         pstm.setString(4, user.getData());
         pstm.execute();
         pstm.close();
    }
    
    public static boolean alreadyExists(String username) throws SQLException{
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT username FROM score WHERE username ='" + username + "'");
        Boolean b=false;
        
        if(rs.next()) 
            b=true;
        else
            b=false;
        
        rs.close();
        stm.close();
        return b;
    }
    
     public static List<UserScore> allScore() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM score");
        List<UserScore> list = new ArrayList();
        while (rs.next()) {
            UserScore user = new UserScore(rs.getString(1), rs.getString(4));
            user.setTime(rs.getInt(2));
            list.add(user);
        }
        rs.close();
        stm.close();
        return list;
    }
    
}
