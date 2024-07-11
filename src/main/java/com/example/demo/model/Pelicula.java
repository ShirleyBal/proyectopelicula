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
    private int categoriaId;

    // Constructor vacío requerido por JPA
    //Constructor vacío: Este es requerido por JPA para crear instancias de la entidad.
    //Constructor completo: Este constructor incluye todos los atributos, incluido el id.
    //Constructor sin id: Este es útil cuando deseas crear una nueva película sin especificar el id, ya que este será generado automáticamente.
    public Pelicula(int id, String titulo, int anio, int puntuacion, String portada, String tipo, int categoriaId) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.puntuacion = puntuacion;
        this.portada = portada;
        this.tipo = tipo;
        this.categoriaId = categoriaId;
    }

    /**
     * Constructor que solo recibe 3 parámetros
     * @param titulo
     * @param anio
     * @param puntuacion
     * @param portada
     * @param tipo
     * @param categoriaId
     */

    public Pelicula(String titulo, int anio, int puntuacion, String portada, String tipo, int categoriaId) {
        this.titulo = titulo;
        this.anio = anio;
        this.puntuacion = puntuacion;
        this.portada = portada;
        this.tipo = tipo;
        this.categoriaId = categoriaId;
    }

    // Getters y Setters

    public Pelicula(Integer id) {
        this.id = id;
    }
    
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

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
}
