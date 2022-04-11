package org.example.AccesoDatos;

import java.io.*;
import java.util.Properties;

public class ControladorPropiedades {

    private final String NOMBRE_ARCHIVO = "safezone.properties";

    public boolean crearArchivoPropiedades() {
        try {
            File archivo = new File (NOMBRE_ARCHIVO);
            OutputStream outputStream = new FileOutputStream(archivo);
            Properties properties = new Properties();

            properties.setProperty("usuarioBD", "root");
            properties.setProperty("contrasenaBD", "1234");
            properties.setProperty("driverBD", "jdbc:mysql://localhost:3306/safezone_db");
            properties.setProperty("classDriver", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("ultimoUser", "");

            properties.store(outputStream, "Configuración aplicación SafeZone");

            return true;
        } catch (Exception e) {
            System.out.println("[Error al crear archivo properties] " + e.getStackTrace());
            return false;
        }
    }

    public String getPropiedad(String propiedad) {
        try {
            if (!existeArchivo()) crearArchivoPropiedades();

            Properties properties = new Properties();
            properties.load(new FileReader(NOMBRE_ARCHIVO));

            return properties.getProperty(propiedad);
        } catch (Exception e) {
            System.out.println("[Error al obtener property " + propiedad + "] " + e.getStackTrace());
            return null;
        }
    }

    public boolean setPropiedad(String propiedad, String valor) {
        try {
            if (!existeArchivo()) return false;

            Properties properties = new Properties();
            properties.load(new FileReader(NOMBRE_ARCHIVO));
            properties.setProperty(propiedad, valor);

            return true;
        } catch (Exception e) {
            System.out.println("[Error al almacenar property " + propiedad + ":" + valor + "] " + e.getStackTrace());
            return false;
        }
    }

    private boolean existeArchivo() {
        File file = new File(NOMBRE_ARCHIVO);
        return file.exists();
    }
}
