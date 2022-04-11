package org.example;

import org.example.AccesoDatos.ControladorBD;
import org.example.AccesoDatos.ControladorPropiedades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void pruebaProperties (String[] args) { // Prueba de archivo properties
        try {
            ControladorBD controladorBD = new ControladorBD();
            ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
            System.out.println(controladorPropiedades.getPropiedad("usuarioBD"));

            System.out.println("Fin test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) { // Prueba de conexión BD

            ControladorBD controladorBD = new ControladorBD();
            try {
                controladorBD.conectarBD();
                System.out.println("\nSigo ejecutando el codigo...");
            } catch (SQLException e) {
                System.out.print("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}
