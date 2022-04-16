package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.Gestion.GestionUsuario;

import java.io.IOException;

public class controllerRecuperarContrasena {

    @FXML
    private Button Button_Cancelar;

    @FXML
    private Button Button_RecuperarContraseña;

    @FXML
    private TextField txtCorreoOUser;

    @FXML
    void Cancelar(ActionEvent event) throws Exception {
        ControladorRutas.launchVista_Acceder();
        Stage myStage = (Stage) this.Button_Cancelar.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void RecuperarContraseña(ActionEvent event) throws Exception {
        try {
            String userOCorreo;
            Boolean enviado;
            userOCorreo = this.txtCorreoOUser.getText();
            GestionUsuario gestionUsuario = new GestionUsuario();
            enviado = gestionUsuario.recuperarContrasena(userOCorreo);
            if (enviado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("CONFIRMACIÓN");
                alert.setContentText("Correo de recuperación de contraseña enviado.");
                alert.showAndWait();

                ControladorRutas.launchVista_Acceder();
                Stage myStage = (Stage) this.Button_RecuperarContraseña.getScene().getWindow();
                myStage.close();
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Revise la información registrada.");
            alert.showAndWait();
        }
    }

}
