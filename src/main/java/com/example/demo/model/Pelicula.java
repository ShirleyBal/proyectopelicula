package com.example.demo.model;

/**
 * Objeto que representa una fila de la tabla Películas.
 */
public class Pelicula {
    private int id;
    private String titulo;
    private int anio;
    private int puntuacion;
    private String portada;
    private String tipo;
    private String categoria;

    // Constructor por defecto
    public Pelicula() {}

    // Constructor completo
    public Pelicula(int id, String titulo, int anio, int puntuacion, String portada, String tipo, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.puntuacion = puntuacion;
        this.portada = portada;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    // Constructor sin id
    public Pelicula(String titulo, int anio, int puntuacion, String portada, String tipo, String categoria) {
        this.titulo = titulo;
        this.anio = anio;
        this.puntuacion = puntuacion;
        this.portada = portada;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
