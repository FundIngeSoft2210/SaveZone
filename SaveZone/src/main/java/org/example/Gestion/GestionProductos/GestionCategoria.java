package org.example.Gestion.GestionProductos;

import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Categoria;

import java.sql.SQLException;

public class GestionCategoria {
    private final ControladorBD controladorBD = new ControladorBD();

    /**
     * @summary Metodo encargado de permitir la creación de una categoría dentro del aplicativo e insertarla en
     *          la Base de Datos del mismo para que posteriormente pueda ser usada para creaciones, busquedas y
     *          modificaciones de productos
     * @param nuevaCategoria: Objeto de tipo Categoría el cual contiene todos los atributos de la categoria a
     *                        insertar en la Base de Datos
     * @return un booleano el cuál nos indica si la inserción de la categoría en la Base de Datos pudo o no ser
     *         realizada correctamente
     */
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

    /**
     * @summary Metodo encargado de recibir un objeto de tipo Categoria con datos modificados y realizar el
     *          debido cambio o actualización en la Base de Datos del aplicativo.
     * @param categoriaModificar Objeto de tipo Categoría el cual contiene todos los atributos de la categoria a
     *      *                    modificar en la Base de Datos
     * @return un boolean el cual nos indicará si la modificación pudo realizarse correctamente, siendo true en
     *         caso que el proceso haya sido exitoso
     */
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

    /**
     * @Summary Método encargado de realizar la busqueda y debida eliminación de una categoría dentro de la Base de
     *          Datos, esto a través de un objeto de tipo Categoría el cuál contiene todos los atributos de la
     *          categoría a eliminar
     * @param categoriaEliminar: Objeto de tipo categoría que nos permitirá a través de sus atributos encontrar la
     *                           categoría en la Base de Datos para posteriormente poder eliminarla
     * @return un booleano que nos indicará si la categoría pudo o no ser eliminada correctamente, será true si
     *         el proceso fue exitoso
     */
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
