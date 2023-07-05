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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Properties;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    
    public static void insertScore (String username, int time, int score, String data) throws SQLException{
        
         PreparedStatement pstm = connection.prepareStatement("INSERT INTO score VALUES (?,?,?,?)");
         pstm.setString(1, username);
         pstm.setInt(2, time);
         pstm.setInt(3, score);
         pstm.setString(4, data);
         pstm.execute();
         pstm.close();
    }
    
    public int averageScore() throws SQLException {
             List<Integer> scores = new ArrayList();
             Statement stm = connection.createStatement(); 
             ResultSet resultSet = stm.executeQuery("SELECT score FROM score");

            while (resultSet.next()) {
                int score = resultSet.getInt("score");
                scores.add(score);
            }
        double average;
        average = scores.stream()
                .mapToInt(score -> score)
                .average()
                .orElse(0.0);
        stm.close();
        resultSet.close();
        return (int) Math.round(average);
    }
    
   public List<UserScore> top3player() throws SQLException{
       List<UserScore> player = new ArrayList();
       Statement stm = connection.createStatement();
       ResultSet resultSet = stm.executeQuery("SELECT * FROM score");
       
       while(resultSet.next()){
           UserScore user = new UserScore(resultSet.getString(1), resultSet.getString(4));
           user.setTime(resultSet.getInt(2));
           player.add(user);   
       }
       stm.close();
       resultSet.close();
       
       return player.stream()
            .sorted(Comparator.comparingInt(UserScore::getScore).reversed())
            .limit(3)
            .collect(Collectors.toList());   
   }
   
   public Map<String, Double> averageScoreByDate() throws SQLException{
        Map<String, Double> averageScoreByDate = new HashMap<>(); 
        List<UserScore> player = new ArrayList();
       Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT date, score FROM score");
        
        while(resultSet.next()){
           UserScore user = new UserScore(resultSet.getString(1), resultSet.getString(4));
           user.setTime(resultSet.getInt(2));
           player.add(user);   
       }
       stm.close();
       resultSet.close();
       
       averageScoreByDate = player.stream()
               .collect(Collectors.groupingBy(UserScore::getData, Collectors.averagingInt(UserScore::getScore)));
               
               
       return averageScoreByDate;
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
    
    
}
