package org.example;

import org.example.AccesoDatos.ControladorBD;
import org.example.AccesoDatos.ControladorPropiedades;

import java.sql.ResultSet;

public class Main {
    public static void main (String[] args) {
        try {
            ControladorBD controladorBD = new ControladorBD();
            ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
            controladorPropiedades.crearArchivoPropiedades();
            System.out.println(controladorPropiedades.getPropiedad("usuarioBD"));

            /*
            ResultSet rs = controladorBD.ejecutarQuery("SELECT * FROM USUARIO");
            int columnas = rs.getMetaData().getColumnCount(), i;


            while (rs.next()) {
                System.out.println(columnas +" es");
                for (i = 1; i < columnas; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println("");
            }
             */


            System.out.println("Fin test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
