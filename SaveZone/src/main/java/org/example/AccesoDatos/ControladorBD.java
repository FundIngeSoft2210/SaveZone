package org.example.AccesoDatos;

import java.sql.*;

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

    public ResultSet ejecutarQuery (String query) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/safezone_db", "root", "1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.out.print("[SQL] " + e.getStackTrace());
            return null;
        } catch (Exception e) {
            System.out.print("[Error al ejecutar consulta '] " + query + "'" + e.getStackTrace());
            return null;
        }
    }
}
