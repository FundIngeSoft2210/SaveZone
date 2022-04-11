package org.example.Entidades;

public class Categoria {
    private String descripcion;
    private String nombre;

    /**
     * @summary Método creador de categorías a partir de información capturada en la BD
     * @param nombre: nombre de la categoría
     * @param : breve descripción relacionada a la categoría
     */
    public Categoria(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
