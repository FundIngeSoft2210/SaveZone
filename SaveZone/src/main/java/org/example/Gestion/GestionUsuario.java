package org.example.Gestion;

import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Usuarios.Usuario;

import java.sql.SQLException;

public class GestionUsuario {
    public boolean crearUsuario (Usuario nuevoUsuario) {
        ControladorBD controladorBD = new ControladorBD();
        String queryInsert = null, ID;
        try {
            queryInsert = "INSERT INTO `safezone_db`.`usuario` (`Nombre`, `Apellido`, `Usuario`, `Contraseña`, `CorreoElectronico`, " +
                    "`FechaNacimiento`, `FechaRegistro`, `Telefono`, `Direccion`, `CiudadID`, `TipoUsuarioID`) VALUES " +
                    "('" + nuevoUsuario.getNombre() + "', '" + nuevoUsuario.getApellido() + "', '" + nuevoUsuario.getUsuario() + "', '" +
                    nuevoUsuario.getContrasena() + "', '" + nuevoUsuario.getCorreo() + "', " + "CURDATE(), CURDATE(), '" + nuevoUsuario.getTelefono() + "', '" +
                    nuevoUsuario.getDireccion() + "', '" + nuevoUsuario.getCiudadID().toString() + "', '1')"; // Arreglar tipo ciudad ID
            //System.out.println(queryInsert);
            controladorBD.ejecutarInsert(queryInsert);
            ID = controladorBD.ejecutarConsulta("SELECT * FROM USUARIO WHERE USUARIO = '" + nuevoUsuario.getUsuario() + "'").getString(1);
            System.out.println("[!] Usuario creado (ID: " + ID + ")");
            return true;
         } catch (SQLException e) {
            System.out.print("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
