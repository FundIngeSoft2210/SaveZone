package org.example.Entidades;

public class Pedido {
    private int id;
    private int productoId ;
    private int compradorId;
    private int tarjetaId;
    private int estadoPedidoId;
    private int estadoPagoId;
    private String direccionO;
    private String direccionD;
    private String guiaRastreo;
    private float pesoTotal;
    private int cantidad;
    private float subtotal;
    private float total;

    /**
     * @summary Método creador de un pedido
     * @param productoId: Indica el producto del pedido realizado
     * @param direccionO: Es la dirección de origen del pedido
     * @param direccionD: Es la dirección de destino del pedido
     * @param guiaRastreo: Es la guía de rastreo del pedido realizado
     * @param cantidad: Indica la cantidad de elementos del mismo producto que se pidieron
     * @param subtotal: Es el valor del pedido sin impuestos y sin costo de envío
     */
    public Pedido(int productoId, int compradorId, int tarjetaId, int estadoPedidoId, int estadoPagoId, String direccionO, String direccionD, String guiaRastreo, float pesoTotal, int cantidad, float subtotal, float total) {
        this.productoId = productoId;
        this.compradorId = compradorId;
        this.tarjetaId = tarjetaId;
        this.estadoPedidoId = estadoPedidoId;
        this.estadoPagoId = estadoPagoId;
        this.direccionO = direccionO;
        this.direccionD = direccionD;
        this.guiaRastreo = guiaRastreo;
        this.pesoTotal = pesoTotal;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Pedido() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(int compradorId) {
        this.compradorId = compradorId;
    }

    public int getTarjetaId() {
        return tarjetaId;
    }

    public void setTarjetaId(int tarjetaId) {
        this.tarjetaId = tarjetaId;
    }

    public int getEstadoPedidoId() {
        return estadoPedidoId;
    }

    public void setEstadoPedidoId(int estadoPedidoId) {
        this.estadoPedidoId = estadoPedidoId;
    }

    public int getEstadoPagoId() {
        return estadoPagoId;
    }

    public void setEstadoPagoId(int estadoPagoId) {
        this.estadoPagoId = estadoPagoId;
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

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
