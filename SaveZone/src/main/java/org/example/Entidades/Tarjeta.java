package org.example.Entidades;

public class Tarjeta {
    private int id;
    private int usuarioId;
    private String numeroTarjeta;
    private String cvv;
    private String anoVencimietno;
    private String mesVencimiento;
    private int activo;

    public Tarjeta(int usuarioId, String numeroTarjeta, String cvv, String anoVencimietno, String mesVencimiento, int activo) {
        this.usuarioId = usuarioId;
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.anoVencimietno = anoVencimietno;
        this.mesVencimiento = mesVencimiento;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getAnoVencimietno() {
        return anoVencimietno;
    }

    public void setAnoVencimietno(String anoVencimietno) {
        this.anoVencimietno = anoVencimietno;
    }

    public String getMesVencimiento() {
        return mesVencimiento;
    }

    public void setMesVencimiento(String mesVencimiento) {
        this.mesVencimiento = mesVencimiento;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
