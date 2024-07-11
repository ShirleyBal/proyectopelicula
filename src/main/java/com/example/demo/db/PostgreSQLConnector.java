package com.example.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLConnector {
    
    public static void main(String[] args) {

        // Conexión a la base de datos
        try {

            // Establecer la conexión
            String url = "jdbc:postgresql://localhost:5432/java24119";
            String user = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, user, password);

            // Creación de un statement para ejecutar consultas
            Statement statement = connection.createStatement();

            // Ejecución de la consulta
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pelicula");
            System.out.println("conectado con exito");
            // Procesamiento de los resultados
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getString("id") + " titulo: " + resultSet.getString("titulo"));
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


