package com.example.demo.dao;

import com.example.demo.model.Pelicula;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculasCRUD {
    static String url = "jdbc:postgresql://localhost:5432/java24119";
    static String user = "postgres";
    static String password = "Qwerty1997";
    public List<Pelicula> listar() {
        String sql = "SELECT id, titulo, anio, puntuacion, portada, tipo, categoria FROM pelicula";
        List<Pelicula> listPeli = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
    
            while (resultSet.next()) {
                int id = resultSet.getInt("id");  // Incluimos el id en la lectura de resultados
                String titulo = resultSet.getString("titulo");
                int anio = resultSet.getInt("anio");
                int puntuacion = resultSet.getInt("puntuacion");
                String portada = resultSet.getString("portada");
                String tipo = resultSet.getString("tipo");
                String categoria = resultSet.getString("categoria");
    
                Pelicula pelicula = new Pelicula(id, titulo, anio, puntuacion, portada, tipo, categoria);  // Pasamos el id al constructor de Pelicula
                listPeli.add(pelicula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPeli;
    }
    public void createMovie(String titulo, int anio, int puntuacion, String portada, String tipo, String categoria) {
        String sql = "INSERT INTO pelicula (titulo, anio, puntuacion, portada, tipo, categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, titulo);
            preparedStatement.setInt(2, anio);
            preparedStatement.setInt(3, puntuacion);
            preparedStatement.setString(4, portada);
            preparedStatement.setString(5, tipo);
            preparedStatement.setString(6, categoria);
    
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pelicula creada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateMovie(int id, String titulo, int anio, int puntuacion, String portada, String tipo, String categoria) {
        String sql = "UPDATE pelicula SET titulo = ?, anio = ?, puntuacion = ?, portada = ?, tipo = ?, categoria = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, titulo);
            preparedStatement.setInt(2, anio);
            preparedStatement.setInt(3, puntuacion);
            preparedStatement.setString(4, portada);
            preparedStatement.setString(5, tipo);
            preparedStatement.setString(6, categoria);
            preparedStatement.setInt(7, id); // Asegúrate de pasar el id como parámetro
    
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Edición realizada");
            } else {
                System.out.println("No se encontró ninguna película con el ID especificado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void deleteMovie(int id) {
        String sql = "DELETE FROM pelicula WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pelicula eliminada");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    }

    // public Pelicula getPeliculaById(Integer id) {
    //     Pelicula pelicula = null;
    //     String sql = "SELECT * FROM pelicula WHERE id = ?";
    //     try (Connection connection = DriverManager.getConnection(url, user, password);
    //          PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    //         preparedStatement.setInt(1, id);
    //         ResultSet resultSet = preparedStatement.executeQuery();
    //         if (resultSet.next()) {
    //             String titulo = resultSet.getString("titulo");
    //             int anio = resultSet.getInt("anio");
    //             int puntuacion = resultSet.getInt("puntuacion");
    //             String portada = resultSet.getString("portada");
    //             String tipo = resultSet.getString("tipo");
    //             int categoria = resultSet.getString("categoria");

    //             pelicula = new Pelicula(peliculaId, titulo, anio, puntuacion, portada, tipo, categoria);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return pelicula;
    // }

