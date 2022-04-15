package org.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;

import java.sql.SQLException;

public class App extends Application
{
    private final static String ICON_NAME="logo.jpg";
    private final static String MAIN_FXML_NAME = "vista_inicio.fxml";
    private final static String STYLE_SHEET_NAME="styles.css";
    private final static String WINDOW_NAME= "JavaFX and Maven";

    @Override

    public void start (Stage stage) throws Exception {
        ControladorBD controladorBD = new ControladorBD();
        try {
            controladorBD.conectarBD();
            Parent root =  FXMLLoader.load(getClass().getResource (MAIN_FXML_NAME));
            Scene scene= new Scene (root);
            scene.getStylesheets().add(getClass().getResource(STYLE_SHEET_NAME).toExternalForm());
            stage.getIcons().add(new Image (getClass().getResourceAsStream(ICON_NAME)));
            stage.setTitle(WINDOW_NAME);
            stage.setScene (scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error de conexión con la base de datos.");
            alert.setContentText("Ocurrió un error al conectarse con la base de datos mySQL: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
