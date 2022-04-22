package org.example.Gestion;

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

    /**
     * @summary Método encargado de realizar la creación de un nuevo usuario que se recibe por parametro
     *          y realizar el insert de este con todos sus datos en la Base de Datos del aplicativo
     * @param nuevoUsuario: Se recibe el usuario a crear con todos sus datos capturados
     * @return un booleano el cual indica si la inserción del usuario se pudo hacer correctamente
     */
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

    /**
     * @summary Método encargado de realizar la modificación a cualquier atributo de un usuario realizando su debido UPDATE en la Base de Datos
     * @param usuarioModificar: Es un objeto de tipo usuario que contiene al usuario deseado con sus datos ya modificados
     * @return un booleano el cual indica si el UPDATE en la base de datos se pudo realizar correctamente
     */
    public boolean modificarUsuario (Usuario usuarioModificar) {
        String insert = null;
        try {
            insert = "UPDATE `safezone_db`.`usuario` SET NOMBRE = " +
                    "'" + usuarioModificar.getNombre() + "', APELLIDO = '" + usuarioModificar.getApellido() + "', CONTRASEÑA = '" + usuarioModificar.getContrasena() +
                    "', CORREOELECTRONICO = '" + usuarioModificar.getCorreo() + "', FECHANACIMIENTO = CURDATE(), TELEFONO = '" + usuarioModificar.getTelefono() + "', DIRECCION = '" +
                    usuarioModificar.getDireccion() + "', CIUDADID = " + usuarioModificar.getCiudadID().toString() + " WHERE USUARIO = '" + usuarioModificar.getUsuario() + "'"; // Arreglar tipo USUARIO
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

    /**
     * @summary Método encargado de recibir un Usuario por parametro, para posteriormente realizar la buqueda y eliminación de este en la Base de Datos
     *          del aplicativo
     * @param usuarioEliminar: Se recibe el usuario a buscar y eliminar de la Base de Datos
     * @return un booleano el cual nos indica false en caso de que se genere cualquier tipo de excepción al momento de realizar el delete en la Base de
     *         Datos
     */
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

    /**
     * @summary Metodo encargado de enviar la contraseña de un usuario a su correo, recibiendo bien sea su nombre de usuario o su correo electrónico
     *          buscandolo en la Base de Datos y enviando la contraseña al correo registrado
     * @param usernameOCorreo: Es un String que contiene el nombre de Usuario o Correo Electrónico de la cuenta a recuperar
     * @return un booleano el cual nos indica si dicho correo electrónico o nombre de usuario está registrado en el sistema y en caso de que esto se
     *         cumpla que el envío de la contraseña al correo electrónico se realice correctamente
     */
    public boolean recuperarContrasena(String usernameOCorreo) { // Recuperar por usuario o por correo
        ArrayList<Usuario> usuarios = null;
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        Properties props = System.getProperties();
        String correoSafeZoneAdm = controladorPropiedades.getPropiedad("correo_adm");
        try {
            usuarios = controladorBD.obtenerUsuariosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM USUARIO"));
            for (Usuario usuario : usuarios) {
                if (usuario.getUsuario().equals(usernameOCorreo) || usuario.getCorreo().equals(usernameOCorreo)) {
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.user", correoSafeZoneAdm);
                    props.put("mail.smtp.clave", "");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.port", "587");

                    Session session = Session.getDefaultInstance(props);
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(controladorPropiedades.getPropiedad("correo_adm")));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getCorreo()));   //Se podrían añadir varios de la misma manera
                    message.setSubject("Recuperación de contraseña - Administración de SafeZone.");
                    message.setText("La contraseña de tu cuenta es " + usuario.getContrasena());
                    Transport transport = session.getTransport("smtp");
                    System.out.println("[!] Enviando correo de recuperación de contraseña...");
                    transport.connect("smtp.gmail.com", correoSafeZoneAdm, controladorPropiedades.getPropiedad("password_adm"));
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
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

    /**
     * @summary método encargado de autorizar el ingreso de un usuario registrado al aplicativo, esto lo validará a partir de el nombre de usuario y la
     *          contraseña ingresada por el usuario, posteriormente buscará ese nombre de usuario en la Base de Datos y validará si la contraseña
     *          coincide con la registrada
     * @param nombreUsuario: String el cual contiene el nombre de usuario ingresado por el cliente del aplicativo
     * @param contrasena: String que contiene la contraseña a validar en el sistema según el usuario registrado
     * @return un objeto de Tipo Usuario el cuál en caso de que el ingreso se realice correctamente contendrá todos los atributos del usuario que
     *         ingresó al sistema, en caso de que el ingreso no sea posible este objeto será null y nos permitirá informarle al usuario que algo
     *         ocurrió al validarse en el sistema
     */
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
