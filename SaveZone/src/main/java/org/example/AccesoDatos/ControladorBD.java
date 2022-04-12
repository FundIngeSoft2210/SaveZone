package org.example.AccesoDatos;

import org.example.Entidades.Producto;
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
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/safezone_db", controladorPropiedades.getPropiedad("usuarioBD"), controladorPropiedades.getPropiedad("contrasenaBD"));
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        if (!resultSet.next()) return null;
        return resultSet;
    }

    public int ejecutarInsert(String insert) throws SQLException, ClassNotFoundException {
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(controladorPropiedades.getPropiedad("driverBD"), controladorPropiedades.getPropiedad("usuarioBD"), controladorPropiedades.getPropiedad("contrasenaBD"));
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(insert);
    }

    public ArrayList<Usuario> obtenerUsuariosConsulta (ResultSet resultSet) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        if (resultSet == null) return null;

        do {
            Usuario nuevoUsuario = null;

            if (resultSet.getInt(12) == 1)
                nuevoUsuario = new Usuario();
            else if (resultSet.getInt(12) == 2)
                nuevoUsuario = new Administrador();

            nuevoUsuario.setId(resultSet.getInt(1));
            nuevoUsuario.setNombre(resultSet.getString(2));
            nuevoUsuario.setApellido(resultSet.getString(3));
            nuevoUsuario.setUsuario(resultSet.getString(4));
            nuevoUsuario.setContrasena(resultSet.getString(5));
            nuevoUsuario.setCorreo(resultSet.getString(6));

            // Faltan fechas

            nuevoUsuario.setTelefono(resultSet.getString(9));
            nuevoUsuario.setDireccion(resultSet.getString(10));

            usuarios.add(nuevoUsuario);
        } while (resultSet.next());
        return usuarios;
    }

    public ArrayList<Producto> obtenerProductosConsulta (ResultSet resultSet) throws SQLException, ClassNotFoundException {
        ArrayList<Producto> productos = new ArrayList<Producto>();

        if (resultSet == null) return null;

        do {
            Producto nuevoProducto = new Producto();
            nuevoProducto.setIdProducto(resultSet.getInt(1));
            nuevoProducto.setVendedor(obtenerUsuariosConsulta(ejecutarConsulta("SELECT * FROM USUARIO WHERE ID = " + resultSet.getInt(2))).get(0));
            nuevoProducto.setCantidad(resultSet.getInt(3));
            nuevoProducto.setTitulo(resultSet.getString(4));
            nuevoProducto.setDescripcion(resultSet.getString(5));
            nuevoProducto.setValor(resultSet.getInt(6));
            nuevoProducto.setPorcentajeDesc(resultSet.getInt(7));
            nuevoProducto.setPeso(resultSet.getFloat(8));
            nuevoProducto.setAlto(resultSet.getFloat(9));
            nuevoProducto.setLargo(resultSet.getFloat(10));
            nuevoProducto.setAncho(resultSet.getFloat(11));
            nuevoProducto.setColor(resultSet.getString(12));
            nuevoProducto.setEstadoProductoID(resultSet.getInt(13));
            nuevoProducto.setCiudadID(resultSet.getInt(14));
            nuevoProducto.setCategoriaID(resultSet.getInt(15));

        } while (resultSet.next());
        return productos;
    }
}
