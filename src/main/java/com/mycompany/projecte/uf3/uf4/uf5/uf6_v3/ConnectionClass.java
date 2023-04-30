package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
public Connection connection;

    public Connection getConnection () {

        String dbName="caixerautomatic";
        String userName="root";
        String password="";
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}