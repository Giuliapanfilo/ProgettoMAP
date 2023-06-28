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
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

/**
 *
 * @author Camil
 */
public class DBScore {
    
    private static Connection connection;
    
    public static final String CREATE_TABLE_SCORE = "CREATE TABLE IF NOT EXISTS score (username VARCHAR(15) PRIMARY KEY, time INT, score INT, data DATE)";

    List<Integer> scores = new ArrayList<>();
    
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
    
    public static void insertScore (String username, int time, int score, String data) throws SQLException{
        
         PreparedStatement pstm = connection.prepareStatement("INSERT INTO score VALUES (?,?,?,?)");
         pstm.setString(1, username);
         pstm.setInt(2, time);
         pstm.setInt(3, score);
         pstm.setString(4, data);
         pstm.execute();
         pstm.close();
    }
    public int averageScore() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:./resources/db/score", "user", "1234");
                Statement stm = connection.createStatement(); 
                ResultSet resultSet = stm.executeQuery("SELECT score FROM score")){

            while (resultSet.next()) {
                int score = resultSet.getInt("score");
                scores.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        double average;
        average = scores.stream()
                .mapToInt(score -> score)
                .average()
                .orElse(0.0);
        int roundedAverage = (int) Math.round(average);
        
        System.out.print("La media dei punteggi e'");
        return roundedAverage;
    }
}
