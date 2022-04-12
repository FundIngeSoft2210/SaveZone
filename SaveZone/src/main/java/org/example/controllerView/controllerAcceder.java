package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
    void Entrar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Principal.fxml"));
        Parent root = loader.load();
        controllerPaginaInicio controllerPaginaInicio = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Página Inicio");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Button_entrar.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void olvidoContraseña(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vista_olvidasteContrasena.fxml"));
        Parent root = loader.load();
        controllerRecuperarContrasena controllerRecuperarContrasena = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Recuperar Contraseña");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.olvidarContraseña.getScene().getWindow();
        myStage.close();

    }

}

