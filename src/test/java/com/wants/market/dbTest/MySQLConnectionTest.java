package com.wants.market.dbTest;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/wants-market?&serverTimezone=UTC&autoReconnect=true&allow&MultiQueries=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "gkgkghgh12";

    @Test
    public void testConnection() throws Exception {
        Class.forName(DRIVER);

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
