package org.example.Gestion.GestionProductos;

import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Tarjeta;

import java.awt.*;
import java.sql.SQLException;

public class GestionTarjeta {

    private final ControladorBD controladorBD = new ControladorBD();


    public boolean crearTarjeta(Tarjeta tarjeta){
        try{
            controladorBD.ejecutarInsert("INSERT INTO `safezone_db`.`tarjeta` (`UsuarioID`, `NumeroTarjeta`, `CVV`, `AñoVencimiento`,`MesVencimiento`,`Activo`)" +
                    " VALUES (" + tarjeta.getUsuarioId() + ", '" + tarjeta.getNumeroTarjeta() + "', '" + tarjeta.getCvv() + "', '" + tarjeta.getAnoVencimietno() + "', '" + tarjeta.getMesVencimiento() + "', " + tarjeta.getActivo() + ")");
            return true;
        }catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int buscarTarjeta(String numeroTarjeta){
        try{
            int tarjetaEncontradaId=0;
            tarjetaEncontradaId =  controladorBD.ejecutarConsulta("SELECT ID from safezone_db.tarjeta t where t.NumeroTarjeta="+numeroTarjeta).getInt(1);
            return tarjetaEncontradaId;
        }catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
