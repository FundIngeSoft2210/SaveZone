package org.example.Entidades;

public class Pedido {
    private Producto producto = null;
    private String direccionO;
    private String direccionD;
    private String guiaRastreo;
    private float pesoTotal;
    private int cantidad;
    private float subtotal;
    private float total;

    /**
     * @summary Método creador de un pedido
     * @param producto: Indica el producto del pedido realizado
     * @param direccionO: Es la dirección de origen del pedido
     * @param direccionD: Es la dirección de destino del pedido
     * @param guiaRastreo: Es la guía de rastreo del pedido realizado
     * @param cantidad: Indica la cantidad de elementos del mismo producto que se pidieron
     * @param subtotal: Es el valor del pedido sin impuestos y sin costo de envío
     */
    public Pedido(Producto producto, String direccionO, String direccionD,
                  String guiaRastreo, int cantidad, float subtotal) {
        this.producto = producto;
        this.direccionO = direccionO;
        this.direccionD = direccionD;
        this.guiaRastreo = guiaRastreo;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getDireccionO() {
        return direccionO;
    }

    public void setDireccionO(String direccionO) {
        this.direccionO = direccionO;
    }

    public String getDireccionD() {
        return direccionD;
    }

    public void setDireccionD(String direccionD) {
        this.direccionD = direccionD;
    }

    public String getGuiaRastreo() {
        return guiaRastreo;
    }

    public void setGuiaRastreo(String guiaRastreo) {
        this.guiaRastreo = guiaRastreo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
