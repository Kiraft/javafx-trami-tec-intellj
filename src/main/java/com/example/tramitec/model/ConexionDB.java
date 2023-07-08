package com.example.tramitec.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static String url = "jdbc:mysql://localhost:3306/tramites_tecnm?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "1234";

    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

}

//    private static PoolConnection dataSource;
//    private BasicDataSource basicDataSource = null;
//
//    private PoolConnection() {
//        basicDataSource = new BasicDataSource();
//        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        basicDataSource.setUsername(USER);
//        basicDataSource.setPassword(PASS);
//        basicDataSource.setUrl(URL);
//
//        basicDataSource.setMinIdle(5);
//        basicDataSource.setMaxIdle(20);
//        basicDataSource.setMaxTotal(50);
//        basicDataSource.setMaxWaitMillis(-1);
//
//    }
//
//    public static PoolConnection getInstance() {
//        if (dataSource == null) {
//            dataSource = new PoolConnection();
//            return dataSource;
//        } else {
//            return dataSource;
//        }
//    }
//
//    public Connection getConnection() throws SQLException{
//      return this.basicDataSource.getConnection();
//    }
//
//    public void closeConnection(Connection connection) throws SQLException {
//        connection.close();
//    }


