package org.example.AccesoDatos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.Entidades.Categoria;
import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Administrador;
import org.example.Entidades.Usuarios.Usuario;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

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
            nuevoUsuario.setCiudadID(resultSet.getInt(11));

            usuarios.add(nuevoUsuario);
        } while (resultSet.next());
        return usuarios;
    }

    public ObservableList<String> obtenerDeptos (ResultSet rs) throws SQLException{
        ObservableList<String> deptos= FXCollections.observableArrayList();
        if (rs == null) return null;

        do{
            String depto=null;
            depto = rs.getString(1);
            deptos.add(depto);
        } while(rs.next());
        return deptos;
    }

    public ObservableList<String> obtenerCiudades (ResultSet rs) throws SQLException{
        ObservableList<String> ciudades= FXCollections.observableArrayList();
        if (rs == null) return null;

        do{
            String ciudad=null;
            ciudad = rs.getString(1);
            ciudades.add(ciudad);
        } while(rs.next());
        return ciudades;
    }

    public ArrayList<Producto> obtenerProductosConsulta (ResultSet resultSet) throws SQLException, ClassNotFoundException {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        byte[] imgdata = null;

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
            nuevoProducto.setCategoria(obtenerCategoriaConsulta(ejecutarConsulta("SELECT * FROM CATEGORIA WHERE ID = " + resultSet.getInt(15))).get(0));

            try {
                System.out.println("");
                imgdata = Base64.getDecoder().decode(ejecutarConsulta("SELECT * FROM IMAGENES WHERE PRODUCTOID = " + nuevoProducto.getIdProducto() + " AND PRINCIPAL = 1").getBytes(3));
            } catch (Exception e) {
                imgdata = Base64.getDecoder().decode(ejecutarConsulta("SELECT * FROM IMAGENES WHERE ISNULL(PRODUCTOID)").getString(3).getBytes(StandardCharsets.UTF_8));
            } finally {
                System.out.println("");
                nuevoProducto.setImgdata(imgdata);
            }

            productos.add(nuevoProducto);
        } while (resultSet.next());
        return productos;
    }

    public ArrayList<Categoria> obtenerCategoriaConsulta (ResultSet resultSet) throws  SQLException, ClassNotFoundException {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();

        if (resultSet == null) return null;

        do {
            Categoria nuevaCategoria = new Categoria();
            nuevaCategoria.setID(resultSet.getInt(1));
            nuevaCategoria.setNombre(resultSet.getString(2));
            nuevaCategoria.setDescripcion(resultSet.getString(3));
            categorias.add(nuevaCategoria);
        } while (resultSet.next());
        return categorias;
    }
}
