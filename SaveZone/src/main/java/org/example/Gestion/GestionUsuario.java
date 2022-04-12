package org.example.Gestion;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.AccesoDatos.ControladorBD;
import org.example.AccesoDatos.ControladorPropiedades;
import org.example.Entidades.Usuarios.Usuario;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class GestionUsuario {
    private ControladorBD controladorBD = new ControladorBD();

    public boolean crearUsuario (Usuario nuevoUsuario) {
        String queryInsert = null;
        try {
            queryInsert = "INSERT INTO `safezone_db`.`usuario` (`Nombre`, `Apellido`, `Usuario`, `Contraseña`, `CorreoElectronico`, " +
                    "`FechaNacimiento`, `FechaRegistro`, `Telefono`, `Direccion`, `CiudadID`, `TipoUsuarioID`) VALUES " +
                    "('" + nuevoUsuario.getNombre() + "', '" + nuevoUsuario.getApellido() + "', '" + nuevoUsuario.getUsuario() + "', '" +
                    nuevoUsuario.getContrasena() + "', '" + nuevoUsuario.getCorreo() + "', " + "CURDATE(), CURDATE(), '" + nuevoUsuario.getTelefono() + "', '" +
                    nuevoUsuario.getDireccion() + "', '" + nuevoUsuario.getCiudadID().toString() + "', '1')"; // Arreglar tipo ciudad ID
            //System.out.println(queryInsert);
            controladorBD.ejecutarInsert(queryInsert);
            nuevoUsuario.setId(Integer.parseInt(controladorBD.ejecutarConsulta("SELECT * FROM USUARIO WHERE USUARIO = '" + nuevoUsuario.getUsuario() + "'").getString(1)));
            System.out.println("[!] Usuario creado (ID: " + nuevoUsuario.getId() + ")");
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
        String insert = null;
        try {
            insert = "UPDATE `safezone_db`.`usuario` SET NOMBRE = " +
                    "'" + usuarioModificar.getNombre() + "', APELLIDO = '" + usuarioModificar.getApellido() + "', CONTRASEÑA = '" + usuarioModificar.getContrasena() +
                    "', CORREOELECTRONICO = '" + usuarioModificar.getCorreo() + "', FECHANACIMIENTO = CURDATE(), TELEFONO = '" + usuarioModificar.getTelefono() + "', DIRECCION = '" +
                    usuarioModificar.getDireccion() + "', CIUDADID = " + usuarioModificar.getCiudadID().toString() + " WHERE USUARIO = '" + usuarioModificar.getUsuario() + "'"; // Arreglar tipo USUARIO
            //System.out.println(queryInsert);
            controladorBD.ejecutarInsert(insert);
             System.out.println("[!] Usuario modificado (ID: " + usuarioModificar.getId() + ")");
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

    public boolean recuperarContrasena(String nombreUsuario, String correo) { // Recuperar por usuario o por correo
        ArrayList<Usuario> usuarios = null;
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        Properties props = System.getProperties();
        String correoSafeZoneAdm = controladorPropiedades.getPropiedad("correo_adm");
        try {
            usuarios = controladorBD.obtenerUsuariosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM USUARIO"));
            for (Usuario usuario : usuarios) {
                if (usuario.getUsuario().equals(nombreUsuario) || usuario.getCorreo().equals(correo)) {
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.user", correoSafeZoneAdm);
                    props.put("mail.smtp.clave", "");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.port", "587");

                    Session session = Session.getDefaultInstance(props);
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(controladorPropiedades.getPropiedad("correo_adm")));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));   //Se podrían añadir varios de la misma manera
                    message.setSubject("Recuperación de contraseña - Administración de SafeZone.");
                    message.setText("La contraseña de tu cuenta es " + usuario.getContrasena());
                    Transport transport = session.getTransport("smtp");
                    System.out.println("[!] Enviando correo de recuperación de contraseña...");
                    transport.connect("smtp.gmail.com", correoSafeZoneAdm, controladorPropiedades.getPropiedad("password_adm"));
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                    System.out.println("[!] Correo de recuperación de contraseña enviado a " + correo + ".");
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Usuario autenticarUsuario (String nombreUsuario, String contrasena) {
        ArrayList<Usuario> usuarios = null;
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        try {
            usuarios = controladorBD.obtenerUsuariosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM USUARIO"));
            for (Usuario usuario : usuarios) {
                if (usuario.getUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                    System.out.println("[!] " + usuario.getUsuario() + " ingresó a la aplicación.");
                    controladorPropiedades.setPropiedad("ultimoUser", usuario.getUsuario());
                    return usuario;
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.print("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
