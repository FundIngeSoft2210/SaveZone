package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class controllerPago {

    @FXML
    private TextField Apartamento;

    @FXML
    private TextField Apellido;

    @FXML
    private Button Boton_Ayuda;

    @FXML
    private Button Boton_Historial;

    @FXML
    private Button Boton_VerMisProductos;

    @FXML
    private Button Boton_cancelar;

    @FXML
    private ComboBox<?> Boton_categorias;

    @FXML
    private Button Boton_confirmar;

    @FXML
    private Button Boton_populares;

    @FXML
    private Button Boton_vender;

    @FXML
    private Button Button_Nombre_Producto;

    @FXML
    private Button Button_Valor_Producto;

    @FXML
    private Button Button_uscar;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Nombre1;

    @FXML
    private ComboBox<?> Pais;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private TextField correoCuenta;

    @FXML
    private TextField direccionCuenta;

    @FXML
    private TextField telefonoCuenta;

    @FXML
    void Ayuda(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ConQuePodemosAyudarte.fxml"));
        Parent root = loader.load();
        controllerAyuda controllerAyuda = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Ayuda");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void Buscar(ActionEvent event) {

    }

    @FXML
    void BuscarProductos(ActionEvent event) {

    }

    @FXML
    void Cancelar(ActionEvent event) {

    }

    @FXML
    void Categorias(ActionEvent event) {

    }

    @FXML
    void ConfirmarDatos(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Paso2.fxml"));
        Parent root = loader.load();
        controllerEscogerMetodoPago controllerEscogerMetodoPago = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Escoger Metodo De Pago");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_Historial.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Historial(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Historial.fxml"));
        Parent root = loader.load();
        controllerHistorial controllerHistorial = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Historiales");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_Historial.getScene().getWindow();
        myStage.close();

    }


    @FXML
    void Populares(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ProductosPopulares.fxml"));
        Parent root = loader.load();
        controllerPopulares controllerPopulares = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Productos Populares");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Productos(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MisProductos.fxml"));
        Parent root = loader.load();
        controllerMisProductos controllerMisProductos = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Ver mis Productos");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
        myStage.close();

    }


    @FXML
    void VenderProductos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../anadirProducto.fxml"));
        Parent root = loader.load();
        controllerAnadirProducto controllerVender = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("VenderTuProducto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_vender.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void VolverInicio(ActionEvent event) throws IOException {
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

        Stage myStage = (Stage) this.RegresarAlInicio.getScene().getWindow();
        myStage.close();

    }
}