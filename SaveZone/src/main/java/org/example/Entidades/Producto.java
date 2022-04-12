package org.example.Entidades;

import org.example.Entidades.Usuarios.Usuario;

public class Producto {
    private int idProducto;
    private Usuario vendedor;
    private String nombre;
    private int cantidad;
    private String descripcion;
    private float peso;
    private int valor;
    private int porcentajeDesc;
    private float alto;
    private float largo;
    private float ancho;
    private String color;

    /**
     * @param vendedor: Es el vendedor que publicó el producto
     * @param nombre: Es el nombre del producto
     * @param cantidad: Indica la cantidad de producto que hay disponible
     * @param descripcion: Breve descripción del producto publicado
     * @param peso: Peso en kgs de cada unidad de producto
     * @param valor: Valor en pesos de la unidad de cada producto
     * @param porcentajeDesc: Indica si hay un porcentaje de descuento en el pedido
     * @param alto: Alto de cada unidad en centimetros
     * @param largo: Largo de cada unidad en centimetros
     * @param ancho: Ancho de cada unidad en centimetros
     * @param color: Color del producto
     */
    public Producto(Usuario vendedor, String nombre, int cantidad, String descripcion,
                    float peso, int valor, int porcentajeDesc, float alto, float largo,
                    float ancho, String color) {
        this.vendedor = vendedor;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.peso = peso;
        this.valor = valor;
        this.porcentajeDesc = porcentajeDesc;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.color = color;
    }

    /**
     * @summary Método creador de un producto a partir de información ingresada por el usuario
     * @param cantidad: Cantidad de elementos disponibles del mismo producto
     * @param descripcion: Descripcion breve del producto
     * @param peso: Peso del producto en kg
     * @param valor: Costo individual del producto
     * @param porcentajeDesc: Indica si el dueño del producto desea agragar algún descuento en caso de compra
     * @param alto: Alto en cms del producto
     * @param largo: Largo en cms del producto
     * @param ancho: Ancho en cms del producto
     * @param color: Color del producto publicado
     */


    public int getIdProducto() {return idProducto; }

    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public Usuario getVendedor() { return vendedor; }

    public void setVendedor(Usuario vendedor) { this.vendedor = vendedor; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getPorcentajeDesc() {
        return porcentajeDesc;
    }

    public void setPorcentajeDesc(int porcentajeDesc) {
        this.porcentajeDesc = porcentajeDesc;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public float getLargo() {
        return largo;
    }

    public void setLargo(float largo) {
        this.largo = largo;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
