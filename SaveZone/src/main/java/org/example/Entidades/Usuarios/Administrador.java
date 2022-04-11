package org.example.Entidades.Usuarios;

public class Administrador extends Usuario{
    private int nivelAdmin;

    /**
     * @summary Método creador de un administrador a partir de la información en la BD
     * @param nombre: Nombre(s) del usuario registrado
     * @param apellido: Apellidos del usuario registrado
     * @param usuario: Nombre de usuario de la persona registrada
     * @param contrasena: Hash de la contraseña puesta por el usuario
     * @param correo: Correo registrado por el usuario
     * @param fechaNac: Fecha de nacimiento del usuario
     * @param telefono: Numero de telefono del usuario
     * @param direccion: Dirección de residencia del usuario
     * @param nivelAdmin
     */
    public  Administrador (String nombre, String apellido, String usuario, String contraseña, String correo,
                   Date fechaNac, String telefono, String direccion, int nivelAdmin){
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.direccion = direccion;
        Date fecha = new Date();
        fechaReg = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        setFechaReg(fechaReg);
        this.nivelAdmin = nivelAdmin;
    }

    public int getNivelAdmin() {
        return nivelAdmin;
    }

    public void setNivelAdmin(int nivelAdmin) {
        this.nivelAdmin = nivelAdmin;
    }
}
