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
import javafx.scene.control.DatePicker;

import java.io.IOException;


public class controllerRegistro {
    @FXML
    private TextField Apellido;

    @FXML
    private Button Button_Cancelar;

    @FXML
    private Button Button_Registrarse;

    @FXML
    private TextField Nombre;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField correoCuenta;

    @FXML
    private TextField direccionCuenta;

    @FXML
    private DatePicker fechaDeNacimientoCuenta;

    @FXML
    private TextField telefonoCuenta;
    @FXML
    void Cancelar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista_inicio.fxml"));
        Parent root = loader.load();
        GuiControler guiControler = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Acceder");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Button_Cancelar.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void Registro(ActionEvent event) {

    }
}

