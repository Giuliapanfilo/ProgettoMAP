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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Properties;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author IlSestoSenso
 */
public class DBScore {


    private static Connection connection;

    public static final String CREATE_TABLE_SCORE = "CREATE TABLE IF NOT EXISTS score (username VARCHAR(15) PRIMARY KEY, time INT, score INT, data DATE)";


    public static void connect() throws SQLException {
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

    public static void insertScore(UserScore user) throws SQLException {


        PreparedStatement pstm = connection.prepareStatement("INSERT INTO score VALUES (?,?,?,?)");
        pstm.setString(1, user.getUsername());
        pstm.setInt(2, user.getTime());
        pstm.setInt(3, user.getScore());
        pstm.setString(4, user.getData());
        pstm.execute();
        pstm.close();
    }

    public static int averageScore() throws SQLException {
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

    public static List<UserScore> top3player() throws SQLException {
        List<UserScore> player = new ArrayList();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * FROM score");

        while (resultSet.next()) {
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

    public static Map<String, Double> averageScoreByDate() throws SQLException {
        Map<String, Double> averageScoreByDate = new HashMap<>();
        List<UserScore> player = new ArrayList();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * FROM score");

        while (resultSet.next()) {
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


    public static boolean alreadyExists(String username) throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT username FROM score WHERE username ='" + username + "'");
        Boolean b = false;

        if (rs.next())
            b = true;
        else
            b = false;

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

    public static void main(String[] args) throws SQLException {
        connect();
       UserScore user = new UserScore("Franco", "2023-05-20");
        user.setTime(300);
        UserScore user1 = new UserScore("Luca", "2023-05-20");
        user1.setTime(350);
        UserScore user2 = new UserScore("Giovanni", "2023-05-20");
        user2.setTime(400);
        UserScore user3 = new UserScore("Alberto", "2023-05-21");
        user3.setTime(500);
        UserScore user4 = new UserScore("Francesca", "2023-05-21");
        user4.setTime(600);
        UserScore user5 = new UserScore("Angela", "2023-05-21");
        user5.setTime(755);
        UserScore user6 = new UserScore("Raffaele", "2023-05-22");
        user6.setTime(999);
        UserScore user7 = new UserScore("Giulia", "2023-05-22");
        user7.setTime(367);
        UserScore user8 = new UserScore("Camil", "2023-05-22");
        user8.setTime(843);
        UserScore user9 = new UserScore("Gianni", "2023-05-23");
        user9.setTime(200);
        UserScore user10 = new UserScore("Giorgio", "2023-05-23");
        user10.setTime(800);
        UserScore user11 = new UserScore("Gino", "2023-05-23");
        user11.setTime(548);
        UserScore user12 = new UserScore("Guido", "2023-05-24");
        user12.setTime(688);

        insertScore(user);
        insertScore(user1);
        insertScore(user2);
        insertScore(user3);
        insertScore(user4);
        insertScore(user5);
        insertScore(user6);
        insertScore(user7);
        insertScore(user8);
        insertScore(user9);
        insertScore(user10);
        insertScore(user11);
        insertScore(user12);


        disconnect();
    }

}
