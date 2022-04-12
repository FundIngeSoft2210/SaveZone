package org.example.Gestion.GestionProductos;

import org.example.AccesoDatos.ControladorBD;

import java.sql.SQLException;
import org.example.Entidades.Producto;

public class GestionProducto {
    public boolean crearProducto(Producto nuevoProducto){
        ControladorBD controladorBD = new ControladorBD();
        String queryInsert = null, ID;
        try {
            queryInsert = "INSERT INTO `safezone_db`.`producto` (`Nombre`, `Cantidad`, `Descripcion`, `Peso`, `Valor`, " +
                    "`PorcentajeDescuento`, `Alto`, `Largo`, `Ancho`, `Color`, `UsuarioID`) VALUES " +
                    "('" + nuevoProducto.getNombre() + "', '" + nuevoProducto.getCantidad() + "', '" + nuevoProducto.getDescripcion() + "', '" +
                    nuevoProducto.getPeso() + "', '" + nuevoProducto.getValor() + "', '" + nuevoProducto.getPorcentajeDesc() + "', '" +
                    nuevoProducto.getAlto() + "', '" + nuevoProducto.getLargo() + "', '" + nuevoProducto.getAncho() + "', '" + nuevoProducto.getColor() +"')";
            //System.out.println(queryInsert);
            controladorBD.ejecutarInsert(queryInsert);
            ID = controladorBD.ejecutarConsulta("SELECT * FROM USUARIO WHERE USUARIO = '" + nuevoProducto.getUsuario() + "'").getString(1);
            System.out.println("[!] Usuario creado (ID: " + ID + ")");
            return true;
        } catch (SQLException e) {
            System.out.print("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
