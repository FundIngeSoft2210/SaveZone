package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.Entidades.Producto;
import org.example.Gestion.GestionProductos.GestionProducto;

import java.io.IOException;
import java.util.ArrayList;


public class GuiControler {

    @FXML
    private Button boton_Invitado;
    @FXML
    private Button boton_crearCuenta;
    @FXML
    private Button boton_IniciarSesion;

    @FXML
    void CrearCuenta(ActionEvent event) throws Exception {
        ControladorRutas.launchVista_Registro();
        Stage myStage = (Stage) this.boton_crearCuenta.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void EntrarComoInvitado(ActionEvent event) throws Exception {
        ControladorRutas.launchPantallaPrincipal();
        Stage myStage = (Stage) this.boton_Invitado.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void IniciarSesion(ActionEvent event) throws Exception {
        ControladorRutas.launchVista_Acceder();
        Stage myStage = (Stage) this.boton_IniciarSesion.getScene().getWindow();
        myStage.close();
    }
}
