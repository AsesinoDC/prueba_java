package org.prueba.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static Connection connection = null;

    public static Connection openConnetion(){
        String URL = "jdbc:mysql://localhost:3306/riwiAcademyDB";
        String user = "root";
        String password = "Rlwl2023.";

        try{
            connection = DriverManager.getConnection(URL,user,password);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed" + e.getMessage());
        }
        return connection;
    }

    public static void closeConnetion(){
        if(connection != null){
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException error) {
                throw new RuntimeException("Connection is not close" + error.getMessage());
            }
        }
    }
}
