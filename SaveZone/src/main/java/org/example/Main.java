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
        Producto productoPrueba = null;
        try {
            if (!gestionUsuario.crearUsuario(usuarioPrueba))
                usuarioPrueba = gestionUsuario.autenticarUsuario(usuarioPrueba.getUsuario(), usuarioPrueba.getContrasena());
                productoPrueba = new Producto(usuarioPrueba, "Flores rojas", 15, "Muchas flores rojas", 5.5f, 50000, 3, 5f, 5f, 5f, "Rojo", 1, 1);
                String tituloAnt = productoPrueba.getTitulo();
                gestionProducto.crearProducto(productoPrueba);
                productoPrueba.setCantidad(12);
                productoPrueba.setTitulo("Flores");
                productoPrueba.setPeso(5.0f);
                productoPrueba.setValor(45000);
                gestionProducto.modificarProducto(productoPrueba, tituloAnt);
            for (Producto producto : controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO"))) {
                System.out.println(producto.toString());
            }
                //gestionUsuario.recuperarContrasena("nicolasd-cubillos", "nicolasdavidcubillos@gmail.com");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
