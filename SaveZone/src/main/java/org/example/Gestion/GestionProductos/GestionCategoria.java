package org.example.Gestion.GestionProductos;

import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Categoria;

import java.sql.SQLException;

public class GestionCategoria {
    private ControladorBD controladorBD = new ControladorBD();

    public boolean crearCategoria (Categoria nuevaCategoria) {
        String queryInsert = null;
        try {
            controladorBD.ejecutarInsert("INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES " + nuevaCategoria.getNombre() + ", " + nuevaCategoria.getDescripcion() + "')");
            nuevaCategoria.setID(Integer.parseInt(controladorBD.ejecutarConsulta("SELECT * FROM CATEGORIA WHERE NOMBRE = '" + nuevaCategoria.getNombre() + "'").getString(1)));
            System.out.println("[!] Categoria creada (ID: " + nuevaCategoria.getID() + ")");
            return true;
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean modificarCategoria (Categoria categoriaModificar) {
        try {
            controladorBD.ejecutarInsert("UPDATE `safezone_db`.`categoria` SET NOMBRE = '" + categoriaModificar.getNombre() + "', DESCRIPCION = '" + categoriaModificar.getDescripcion() + "'");
            System.out.println("[!] Categoria modificada (ID: " + categoriaModificar.getID() + ")");
            return true;
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminarCategoria (Categoria categoriaEliminar) {
        try {
            controladorBD.ejecutarInsert("DELETE FROM `safezone_db`.`categoria` WHERE ID = '" + categoriaEliminar.getID());
            System.out.println("[!] Categoria eliminada (ID: " + categoriaEliminar.getID() + ")");
            return true;
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
