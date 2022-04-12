package org.example;

import org.example.AccesoDatos.ControladorBD;
import org.example.AccesoDatos.ControladorPropiedades;
import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionProductos.GestionProducto;
import org.example.Gestion.GestionUsuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Date;
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
        GestionUsuario gestionUsuario = new GestionUsuario();
        Usuario usuarioPrueba = new Usuario("NATALIA", "GAONA", "nat-gaona", "Natalia123", "natagaona@gmail.com", new Date(), "3163009999", "Cra 55 55 3", 1);
        GestionProducto gestionProducto = new GestionProducto();
        Producto productoPrueba = new Producto(15, "Flores rojas", 5.5f, 50000, 3, 5, 5, 5, "Rojo");

        try {
            /*
            gestionUsuario.crearUsuario(usuarioPrueba);
            usuarioPrueba.setContrasena("Laura123");
            usuarioPrueba.setTelefono("6969");
            gestionUsuario.modificarUsuario(usuarioPrueba);
             */
            System.out.println("");
            gestionProducto.crearProducto(productoPrueba);

            System.out.println("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
