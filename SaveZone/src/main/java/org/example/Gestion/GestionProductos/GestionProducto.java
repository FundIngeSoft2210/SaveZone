package org.example.Gestion.GestionProductos;

import org.example.AccesoDatos.ControladorBD;

import java.sql.SQLException;
import java.util.ArrayList;

import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;

public class GestionProducto {
    ControladorBD controladorBD = new ControladorBD();
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

    public boolean buscarProducto (String productoBuscar) {
        ArrayList <Producto> productos = null;
        try {
            productos = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE TITULO LIKE '%" + productoBuscar + "%'"));
            for (Producto producto : productos){
                System.out.println(producto.toString());
            }
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
