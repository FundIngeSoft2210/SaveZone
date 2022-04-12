package org.example.Entidades.Usuarios;

import java.text.SimpleDateFormat;
import java.util.*;

public class Usuario {
    protected String nombre;
    protected String apellido;
    protected String usuario;
    protected String contrasena;
    protected String correo;
    protected Date fechaNac;
    protected String fechaReg;
    protected String telefono;
    protected String direccion;
    protected Integer ciudadID;

    public Usuario() {

    }


    /**
     * @summary Método creador de un usuario a partir de la información capturada en la pantalla de registro
     * @param nombre: Nombre(s) del usuario registrado
     * @param apellido: Apellidos del usuario registrado
     * @param usuario: Nombre de usuario de la persona registrada
     * @param contrasena: Hash de la contraseña puesta por el usuario
     * @param correo: Correo registrado por el usuario
     * @param fechaNac: Fecha de nacimiento del usuario
     * @param telefono: Numero de telefono del usuario
     * @param direccion: Dirección de residencia del usuario
     * @param ciudadID: ID de Ciudad del usuario
     */
    public Usuario(String nombre, String apellido, String usuario, String contrasena, String correo,
                   Date fechaNac, String telefono, String direccion, Integer ciudadID){
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.direccion = direccion;
        Date fecha = new Date();
        fechaReg = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        setFechaReg(fechaReg);
        this.ciudadID = ciudadID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(String fechaReg) {
        this.fechaReg = fechaReg;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(Integer ciudadID) {
        this.ciudadID = ciudadID;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaNac=" + fechaNac +
                ", fechaReg='" + fechaReg + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
