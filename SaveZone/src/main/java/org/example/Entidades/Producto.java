package org.example.Entidades;

import org.example.Entidades.Usuarios.Usuario;

public class Producto {
    private Integer idProducto;
    private Usuario vendedor;
    private String titulo;
    private Integer cantidad;
    private String descripcion;
    private Float peso;
    private Integer valor;
    private Integer porcentajeDesc;
    private Float alto;
    private Float largo;
    private Float ancho;
    private String color;
    private Integer ciudadID;
    private Integer estadoProductoID;
    private Integer categoria;
    private byte[] imgdata;
    private Integer IDpedido;

    /**
     * @summary Método creador de un producto a partir de información ingresada por el usuario
     * @param vendedor: Es el vendedor que publicó el producto
     * @param titulo: Es el nombre del producto
     * @param cantidad: Indica la cantidad de producto que hay disponible
     * @param descripcion: Breve descripción del producto publicado
     * @param peso: Peso en kgs de cada unidad de producto
     * @param valor: Valor en pesos de la unidad de cada producto
     * @param porcentajeDesc: Indica si hay un porcentaje de descuento en el pedido
     * @param alto: Alto de cada unidad en centimetros
     * @param largo: Largo de cada unidad en centimetros
     * @param ancho: Ancho de cada unidad en centimetros
     * @param color: Color del producto
     * @param ciudadID: Ciudad a la que pertenece el producto
     * @param categoria: Categoria a la que pertenece el producto
     * @param imgdatq: Datos en base64 de la imagen del producto.
     */
    public Producto(Usuario vendedor, String titulo, Integer cantidad, String descripcion,
                    Float peso, Integer valor, Integer porcentajeDesc, Float alto, Float largo,
                    Float ancho, String color, Integer ciudadID, Integer categoria) {
        this.vendedor = vendedor;
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.peso = peso;
        this.valor = valor;
        this.porcentajeDesc = porcentajeDesc;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.color = color;
        this.ciudadID = ciudadID;
        estadoProductoID = porcentajeDesc > 0 ? 5 : 1;
        this.categoria = categoria;
    }

    public Producto() {

    }

    public Integer getIDpedido() {
        return IDpedido;
    }

    public void setIDpedido(Integer IDpedido) {
        this.IDpedido = IDpedido;
    }

    public Integer getIdProducto() {return idProducto; }

    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public Usuario getVendedor() { return vendedor; }

    public void setVendedor(Usuario vendedor) { this.vendedor = vendedor; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getPorcentajeDesc() {
        return porcentajeDesc;
    }

    public void setPorcentajeDesc(Integer porcentajeDesc) {
        this.porcentajeDesc = porcentajeDesc;
    }

    public Float getAlto() {
        return alto;
    }

    public void setAlto(Float alto) {
        this.alto = alto;
    }

    public Float getLargo() {
        return largo;
    }

    public void setLargo(Float largo) {
        this.largo = largo;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(Integer ciudadID) {
        this.ciudadID = ciudadID;
    }

    public Integer getEstadoProductoID() {
        return estadoProductoID;
    }

    public void setEstadoProductoID(Integer estadoProductoID) {
        this.estadoProductoID = estadoProductoID;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public byte[]  getImgdata() {
        return imgdata;
    }

    public void setImgdata(byte[] imgdata) {
        this.imgdata = imgdata;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", vendedor=" + vendedor +
                ", titulo='" + titulo + '\'' +
                ", cantidad=" + cantidad +
                ", descripcion='" + descripcion + '\'' +
                ", peso=" + peso +
                ", valor=" + valor +
                ", porcentajeDesc=" + porcentajeDesc +
                ", alto=" + alto +
                ", largo=" + largo +
                ", ancho=" + ancho +
                ", color='" + color + '\'' +
                ", ciudadID=" + ciudadID +
                ", estadoProductoID=" + estadoProductoID +
                ", categoria=" + categoria +
                '}';
    }
}
