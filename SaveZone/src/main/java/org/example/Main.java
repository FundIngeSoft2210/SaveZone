package org.example;

import org.example.AccesoDatos.ControladorBD;
import org.example.AccesoDatos.ControladorPropiedades;
import org.example.Entidades.Categoria;
import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionProductos.GestionProducto;
import org.example.Gestion.GestionUsuario;
import org.example.controllerView.ControladorDespliegueProductos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
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

    public static void mainAnterior (String[] args) { // Prueba de conexión BD

        ControladorBD controladorBD = new ControladorBD();
        GestionUsuario gestionUsuario = new GestionUsuario();
        //Usuario usuarioPrueba = new Usuario("NATALIA", "GAONA", "nat-gaona", "Natalia123", "natagaona@gmail.com", new Date(), "3163009999", "Cra 55 55 3", 1);
        Usuario usuarioPrueba = gestionUsuario.autenticarUsuario("nat-gaona", "Natalia123");
        GestionProducto gestionProducto = new GestionProducto();
        Producto productoPrueba;
        try {
            /**if (!gestionUsuario.crearUsuario(usuarioPrueba))
            for (Producto producto : controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO"))) {
                System.out.println(producto.toString());
            }*/
            Categoria categoriaPrueba = controladorBD.obtenerCategoriaConsulta(controladorBD.ejecutarConsulta("SELECT * FROM CATEGORIA")).get(13);
            productoPrueba = new Producto(usuarioPrueba, "Flores rojas", 15, "Muchas flores rojas", 5.5f, 50000, 3, 5f, 5f, 5f, "Rojo", 1, categoriaPrueba);
            gestionProducto.crearProducto(productoPrueba);
            //gestionUsuario.recuperarContrasena("nicolasd-cubillos", "nicolasdavidcubillos@gmail.com");
            gestionProducto.eliminarProducto(productoPrueba);
            ArrayList<Producto> productos = null;
            productos = gestionProducto.buscarProducto("flores");
            for (Producto producto : productos){
                System.out.println(producto.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();

        System.out.println("Iniciado.");
        ArrayList <Producto> desplegar = new ArrayList<>();
        desplegar = gestion.buscarProducto("");
        System.out.println("[!] Llamada al armado del FXML sobre pantalla principal.");
        try {
            controladorDespliegue.desplegarProductos("/Principal", desplegar, 20, 114);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Función de armado fxml
        Launcher.main(args);
        System.out.println("[!] Despliegue finalizado.");
    }
}
