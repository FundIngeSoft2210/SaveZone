package org.example.Gestion.GestionProductos;

import org.example.AccesoDatos.ControladorBD;

import java.sql.SQLException;
import org.example.Entidades.Producto;

public class GestionProducto {
    ControladorBD controladorBD = new ControladorBD();
    public boolean crearProducto(Producto nuevoProducto){
        String queryInsert = null;
        try {
            queryInsert = "INSERT INTO `safezone_db`.`producto` (`Titulo`, `Cantidad`, `Descripcion`, `Peso`, `Valor`, " +
                    "`PorcentajeDescuento`, `Alto`, `Largo`, `Ancho`, `Color`, `VendedorID`, `EstadoProductoID`, `CiudadID`, `CategoriaID`) VALUES " +
                    "('" + nuevoProducto.getTitulo() + "', " + nuevoProducto.getCantidad() + ", '" + nuevoProducto.getDescripcion() + "', " +
                    nuevoProducto.getPeso() + ", " + nuevoProducto.getValor() + ", " + nuevoProducto.getPorcentajeDesc() + ", " +
                    nuevoProducto.getAlto() + ", " + nuevoProducto.getLargo() + ", " + nuevoProducto.getAncho() + ", '" + nuevoProducto.getColor()
                    + "', " + nuevoProducto.getVendedor().getId() + ", " + nuevoProducto.getEstadoProductoID() + ", " + nuevoProducto.getCiudadID() +
                    ", " + nuevoProducto.getCategoriaID() + ")";
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

    public boolean modificarProducto (Producto productoModificar){
        String insert = null;
        try {
            insert = "UPDATE `safezone_db`.`producto` SET TITULO = " +
                    "'" + productoModificar.getTitulo() + "', CANTIDAD = " + productoModificar.getCantidad() + ", DESCRIPCION = '" + productoModificar.getDescripcion() +
                    "', PESO = " + productoModificar.getPeso() + ", VALOR = " + productoModificar.getValor() + ", PORCENTAJEDESC = " +
                    productoModificar.getPorcentajeDesc() + ", ALTO = " + productoModificar.getAlto() + ", LARGO = " + productoModificar.getLargo() + ", ANCHO = " + productoModificar.getAncho()
                    + ", COLOR = '" + productoModificar.getColor() + "', CIUDADID = " + productoModificar.getCiudadID() + ", ESTADOPRODUCTOID = " + productoModificar.getEstadoProductoID() +
                    " WHERE TITULO = '" + productoModificar.getTitulo() + "' AND VENDEDORID = " + productoModificar.getVendedor().getId();
            //System.out.println(queryInsert);
            controladorBD.ejecutarInsert(insert);
            System.out.println("[!] Usuario modificado (ID: " + productoModificar.getIdProducto() + ")");
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
