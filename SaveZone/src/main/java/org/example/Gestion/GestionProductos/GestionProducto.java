package org.example.Gestion.GestionProductos;

import org.example.AccesoDatos.ControladorBD;

import java.sql.SQLException;
import org.example.Entidades.Producto;

public class GestionProducto {
    public boolean crearProducto(Producto nuevoProducto){
        ControladorBD controladorBD = new ControladorBD();
        String queryInsert = null;
        try {
            queryInsert = "INSERT INTO `safezone_db`.`producto` (`Titulo`, `Cantidad`, `Descripcion`, `Peso`, `Valor`, " +
                    "`PorcentajeDescuento`, `Alto`, `Largo`, `Ancho`, `Color`, `VendedorID`, `EstadoProductoID`, `CiudadID`, `CategoriaID`) VALUES " +
                    "('" + nuevoProducto.getNombre() + "', " + nuevoProducto.getCantidad() + ", '" + nuevoProducto.getDescripcion() + "', " +
                    nuevoProducto.getPeso() + ", " + nuevoProducto.getValor() + ", " + nuevoProducto.getPorcentajeDesc() + ", " +
                    nuevoProducto.getAlto() + ", " + nuevoProducto.getLargo() + ", " + nuevoProducto.getAncho() + ", '" + nuevoProducto.getColor()
                    + "', " + nuevoProducto.getVendedor().getId() + ", " + nuevoProducto.getEstadoProductoID() + ", " + nuevoProducto.getCiudadID() +
                    ", " + nuevoProducto.getCategoriaID() + ")";
            controladorBD.ejecutarInsert(queryInsert);
            nuevoProducto.setIdProducto(Integer.parseInt(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE TITULO = '" + nuevoProducto.getNombre() + "' AND VENDEDORID = "
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
}
