package org.example.Gestion.GestionProductos;

import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Pedido;
import org.example.controllerView.ControladorRutas;

import java.sql.SQLException;

public class GestionPedido {
    private final ControladorBD controladorBD = new ControladorBD();

    public boolean crearPedido(Pedido pedido) throws SQLException, ClassNotFoundException {
        try{
            controladorBD.ejecutarInsert("INSERT INTO `safezone_db`.`pedido` (`CompradorId`, `ProductoId`, `TarjetaId`, `EstadoPedidoId`,`EstadoPagoId`,`DireccionOrigen`,`DireccionDestino`,`GuiaRastreo`,`PesoTotal`,`Cantidad`,`Subtotal`,`Total`)" +
                    " VALUES (" + pedido.getCompradorId() + ", " + pedido.getProductoId() + ", " + pedido.getTarjetaId() + ", " + pedido.getEstadoPedidoId() + ", " + pedido.getEstadoPagoId() + ", '" + pedido.getDireccionO() + "', '" +
                    pedido.getDireccionD() + "', '" + pedido.getGuiaRastreo() + "', " + pedido.getPesoTotal() + ", " + pedido.getCantidad() + ", " + pedido.getSubtotal() + ", " + pedido.getTotal() + ")");
            return true;
        }catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean actualizarPedido(int pedidoId, int tarjetaId){
        try{
            controladorBD.ejecutarInsert("UPDATE safezone_db.pedido set TarjetaId="+tarjetaId+" where ID="+pedidoId);
            return true;
        }catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminarPedido() {
        return false;
    }

    public boolean rastrearPedido() {
        return false;
    }

    public boolean calcularCostoEnvio() {
        return false;
    }
}
