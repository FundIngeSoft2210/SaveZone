package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class controllerRecuperarContrasena {

    @FXML
    private Button Button_Cancelar;

    @FXML
    private Button Button_RecuperarContraseña;

    @FXML
    void Cancelar(ActionEvent event) throws Exception {
        ControladorRutas.launchVista_Acceder();
        Stage myStage = (Stage) this.Button_Cancelar.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void RecuperarContraseña(ActionEvent event) throws Exception {
        ControladorRutas.launchVista_Acceder();
        Stage myStage = (Stage) this.Button_RecuperarContraseña.getScene().getWindow();
        myStage.close();
    }

}
