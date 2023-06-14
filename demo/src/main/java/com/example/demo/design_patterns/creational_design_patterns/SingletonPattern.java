package com.example.demo.design_patterns.creational_design_patterns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonPattern {
    private static SingletonPattern jdbc;

    private SingletonPattern() {
    }

    public static SingletonPattern getInstance() {
        if (jdbc == null) {
            jdbc = new SingletonPattern();
        }
        return jdbc;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/edureka?serverTimezone=Asia/Calcutta", "root", "");
        return con;
    }
}
