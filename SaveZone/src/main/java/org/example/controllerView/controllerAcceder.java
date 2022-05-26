package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionUsuario;
import org.example.Launcher;

import java.util.Optional;
import java.io.IOException;

public class controllerAcceder {

    @FXML
    private Button Button_entrar;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField correoCuenta;

    @FXML
    private Button olvidarContraseña;

    @FXML
    private Button Boton_Regresar;

    @FXML
    void Regresar(ActionEvent event) throws Exception {
        ControladorRutas.launchPantallaInicio();
        Stage myStage = (Stage) this.Button_entrar.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Entrar(ActionEvent event) throws IOException {
        try {
            GestionUsuario gestionUsuario = new GestionUsuario();
            String contrasena, usuario;
            contrasena = this.contraseña.getText();
            if (contrasena.isEmpty() || contrasena == null){
                throw new Exception();
            }

            usuario = this.correoCuenta.getText();
            if (usuario.isEmpty() || usuario == null){
                throw new Exception();
            }
            Usuario user = gestionUsuario.autenticarUsuario(usuario, contrasena);
            if (user != null){
                ControladorRutas.ultimoUser(user);
                ControladorRutas.launchPantallaPrincipal();
                Stage myStage = (Stage) this.Button_entrar.getScene().getWindow();
                myStage.close();
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error.");
            alert.setContentText("Ocurrió un error con el inicio de sesión. Descripción del error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void olvidoContraseña(ActionEvent event) throws Exception {
        ControladorRutas.launchOlvidasteContra();
        Stage myStage = (Stage) this.Button_entrar.getScene().getWindow();
        myStage.close();
    }

}

