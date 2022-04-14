package org.example.Gestion.GestionProductos;

import org.example.AccesoDatos.ControladorBD;

import java.sql.SQLException;
import java.util.ArrayList;

import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;

public class GestionProducto {
    ControladorBD controladorBD = new ControladorBD();

    /**
     * @summary Método encargado de crear el producto recibido como parametro y meterlo en la Base de Datos del programa
     * @param nuevoProducto: Es un objeto de tipo producto el cuál tiene todos los atributos del producto que se creará
     *                     e ingresará en la Base de Datos
     * @return un booleano que en caso de que la inserción del producto se haya realizado correctamente será true y en caso
     *         de presentar algún problema o excepción false
     */
    public boolean crearProducto(Producto nuevoProducto){
        String queryInsert;
        try {
            if(controladorBD.ejecutarConsulta("SELECT * FROM `safezone_db`.`producto` WHERE TITULO = '" + nuevoProducto.getTitulo() + "' AND VENDEDORID = " +
                                                    nuevoProducto.getVendedor().getId())!=null) return false;
            queryInsert = "INSERT INTO `safezone_db`.`producto` (`Titulo`, `Cantidad`, `Descripcion`, `Peso`, `Valor`, " +
                    "`PorcentajeDescuento`, `Alto`, `Largo`, `Ancho`, `Color`, `VendedorID`, `EstadoProductoID`, `CiudadID`, `CategoriaID`) VALUES " +
                    "('" + nuevoProducto.getTitulo() + "', " + nuevoProducto.getCantidad() + ", '" + nuevoProducto.getDescripcion() + "', " +
                    nuevoProducto.getPeso() + ", " + nuevoProducto.getValor() + ", " + nuevoProducto.getPorcentajeDesc() + ", " +
                    nuevoProducto.getAlto() + ", " + nuevoProducto.getLargo() + ", " + nuevoProducto.getAncho() + ", '" + nuevoProducto.getColor()
                    + "', " + nuevoProducto.getVendedor().getId() + ", " + nuevoProducto.getEstadoProductoID() + ", " + nuevoProducto.getCiudadID() +
                    ", " + nuevoProducto.getCategoria().getID() + ")";
            controladorBD.ejecutarInsert(queryInsert);
            nuevoProducto.setIdProducto(Integer.parseInt(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE TITULO = '" + nuevoProducto.getTitulo() + "' AND VENDEDORID = "
                    + nuevoProducto.getVendedor().getId()).getString(1)));
            System.out.println("[!] Producto creado (ID: " + nuevoProducto.getIdProducto() + ")");
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
     * @summary Método encargado de la modificación de un produto en cualquiera de sus atributos (exceptuando aquellos que
     *          se generen de manera automática como el ID del producto)
     * @param productoModificar: Se recibe como parametro el objeto a buscar y modificar en la Base de Datos
     * @param tituloAnt: Recibe el titulo del producto antes de modificaciones esto para que si hay un cambio en este
     *                 atributo no afecte la busqueda del producto
     * @return un booleano que en caso de que la modificación del producto se haya realizado correctamente será true y en
     *         caso de presentar algún problema o excepción false
     */
    public boolean modificarProducto (Producto productoModificar, String tituloAnt){
        String insert;
        try {
            if(controladorBD.ejecutarConsulta("SELECT * FROM `safezone_db`.`producto` WHERE TITULO = '" + productoModificar.getTitulo() + "' AND VENDEDORID = " +
                    productoModificar.getVendedor().getId() + " AND ESTADOPRODUCTOID != " + 6)==null) return false;
            insert = "UPDATE `safezone_db`.`producto` SET TITULO = " +
                    "'" + productoModificar.getTitulo() + "', CANTIDAD = " + productoModificar.getCantidad() + ", DESCRIPCION = '" + productoModificar.getDescripcion() +
                    "', PESO = " + productoModificar.getPeso() + ", VALOR = " + productoModificar.getValor() + ", PORCENTAJEDESCUENTO = " +
                    productoModificar.getPorcentajeDesc() + ", ALTO = " + productoModificar.getAlto() + ", LARGO = " + productoModificar.getLargo() + ", ANCHO = " + productoModificar.getAncho()
                    + ", COLOR = '" + productoModificar.getColor() + "', CIUDADID = " + productoModificar.getCiudadID() + ", ESTADOPRODUCTOID = " + productoModificar.getEstadoProductoID() +
                    " WHERE TITULO = '" + tituloAnt + "' AND VENDEDORID = " + productoModificar.getVendedor().getId();
            productoModificar.setIdProducto(Integer.parseInt(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE TITULO = '" + productoModificar.getTitulo() + "' AND VENDEDORID = "
                    + productoModificar.getVendedor().getId()).getString(1)));
            controladorBD.ejecutarInsert(insert);
            System.out.println("[!] Producto modificado (ID: " + productoModificar.getIdProducto() + ")");
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
     * @summary Método encargado de eliminar un elemento del aplicativo, para ello se realiza una modificación a través de
     *          la cual se cambia su estado a eliminado
     * @param productoEliminar Es el producto a eliminar del aplicativo
     * @return un booleano que indica que la eliminación y por defecto modificación del producto se hizo correctamente,
     *         su valor será true si fue así y en caso de presentar algún problema o excepción false
     */
    public boolean eliminarProducto (Producto productoEliminar) {
        try {
            productoEliminar.setEstadoProductoID(6);
            String titulo = productoEliminar.getTitulo();
            GestionProducto gestion = new GestionProducto();
            gestion.modificarProducto(productoEliminar, titulo);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * @summary Metodo encargado de realizar una busqueda de todos aquellos productos que contengan en su titulo la palabra
     *          indicada como parametro y retornar el arreglo de estos productos
     * @param productoBuscar Es el titulo a buscar en la base de datos del aplicativo
     * @return un ArrayList de tipo Producto el cual contiene todos aquellos productos de la BD los cuales contengan la
     *         palabra que se envió como parametro de la busqueda
     */
    public ArrayList<Producto> buscarProducto (String productoBuscar) {
        ArrayList <Producto> productos = null;
        try {
            productos = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE TITULO LIKE '%" + productoBuscar + "%'"));
            return productos;
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return productos;
        }
    }
}
