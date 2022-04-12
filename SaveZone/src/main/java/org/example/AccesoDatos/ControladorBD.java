package org.example.AccesoDatos;

import org.example.Entidades.Usuarios.Administrador;
import org.example.Entidades.Usuarios.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class ControladorBD {

    private static boolean conexionEstablecida = false;

    public static boolean isConexionEstablecida() {
        return conexionEstablecida;
    }

    public void conectarBD() throws SQLException, ClassNotFoundException {
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(controladorPropiedades.getPropiedad("driverBD"), controladorPropiedades.getPropiedad("usuarioBD"), controladorPropiedades.getPropiedad("contrasenaBD"));
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO");
        System.out.println("[!] Conexion exitosa a la base de datos.");
        conexionEstablecida = true;
    }

    public ResultSet ejecutarConsulta(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/safezone_db", "root", "1234");
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        resultSet.next();
        return resultSet;
    }

    public int ejecutarInsert(String insert) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/safezone_db", "root", "1234");
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(insert);
    }

    public ArrayList<Usuario> obtenerUsuariosConsulta (ResultSet resultSet) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        while (resultSet.next()) {
            System.out.println("");
            Usuario nuevoUsuario = null;

            if (resultSet.getInt(12) == 1)
                nuevoUsuario = new Usuario();
            else if (resultSet.getInt(12) == 2)
                nuevoUsuario = new Administrador();

            nuevoUsuario.setNombre(resultSet.getString(2));
            nuevoUsuario.setApellido(resultSet.getString(3));
            nuevoUsuario.setUsuario(resultSet.getString(4));
            nuevoUsuario.setContrasena(resultSet.getString(5));
            nuevoUsuario.setCorreo(resultSet.getString(6));

            // Faltan fechas

            nuevoUsuario.setTelefono(resultSet.getString(9));
            nuevoUsuario.setDireccion(resultSet.getString(10));

            usuarios.add(nuevoUsuario);
        }

        return usuarios;
    }
}
