package org.example.Gestion;

import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Usuarios.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestionUsuario {
    private ControladorBD controladorBD = new ControladorBD();

    public boolean crearUsuario (Usuario nuevoUsuario) {
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
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean modificarUsuario (Usuario usuarioModificar) {
        String insert = null, ID;
        try {
            insert = "UPDATE `safezone_db`.`usuario` SET NOMBRE = " +
                    "'" + usuarioModificar.getNombre() + "', APELLIDO = '" + usuarioModificar.getApellido() + "', CONTRASEÑA = '" + usuarioModificar.getContrasena() +
                    "', CORREOELECTRONICO = '" + usuarioModificar.getCorreo() + "', FECHANACIMIENTO = CURDATE(), TELEFONO = '" + usuarioModificar.getTelefono() + "', DIRECCION = '" +
                    usuarioModificar.getDireccion() + "', CIUDADID = " + usuarioModificar.getCiudadID().toString() + " WHERE USUARIO = '" + usuarioModificar.getUsuario() + "'"; // Arreglar tipo USUARIO
            //System.out.println(queryInsert);
            controladorBD.ejecutarInsert(insert);
            ID = controladorBD.ejecutarConsulta("SELECT * FROM USUARIO WHERE USUARIO = '" + usuarioModificar.getUsuario() + "'").getString(1);
            System.out.println("[!] Usuario modificado (ID: " + ID + ")");
            return true;
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario (Usuario usuarioEliminar) {
        String delete;
        try {
            delete = "DELETE FROM USUARIO WHERE USUARIO = '" + usuarioEliminar.getUsuario() + "'";
            controladorBD.ejecutarInsert(delete);
            // Validar si la eliminación fue exitosa por medio del return del ejecutarInsert (integer)
            return true;
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String recuperarContrasena(String nombreUsuario, String correo) { // Recuperar por usuario o por correo
        ArrayList<Usuario> usuarios = null;
        try {
            usuarios = controladorBD.obtenerUsuariosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM USUARIO"));
            for (Usuario usuario : usuarios) {
                if (usuario.getUsuario().equals(nombreUsuario) || usuario.getCorreo().equals(correo)) {
                    return usuario.getContrasena();
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean autenticarUsuario (String nombreUsuario, String contrasena) {
        ArrayList<Usuario> usuarios = null;
        try {
            usuarios = controladorBD.obtenerUsuariosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM USUARIO"));
            for (Usuario usuario : usuarios) {
                if (usuario.getUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                    System.out.println("[!] " + usuario.getUsuario() + " ingresó a la aplicación.");
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.print("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
