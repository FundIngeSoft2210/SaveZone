package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class controllerTarjetaCredito {

    @FXML
    private Button Boton_Ayuda;

    @FXML
    private Button Boton_Historial;

    @FXML
    private Button Boton_VerMisProductos;

    @FXML
    private ComboBox<?> Boton_categorias;

    @FXML
    private Button Boton_populares;

    @FXML
    private Button Boton_vender;

    @FXML
    private Button Button_Cancelar;

    @FXML
    private Button Button_Pagar;

    @FXML
    private Button Button_uscar;

    @FXML
    private TextField CVV;

    @FXML
    private Tab Codensa;

    @FXML
    private ComboBox<?> Cuotas;

    @FXML
    private DatePicker FechaVencimientoCodensa;

    @FXML
    private DatePicker FechaVencimientoMC;

    @FXML
    private Tab MC;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Nombre14;

    @FXML
    private TextField NombreTitular;

    @FXML
    private TextField NumeroTarjeta;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Tab Visa;

    @FXML
    private Label correo;

    @FXML
    private Label descripcion;

    @FXML
    private DatePicker fechaVencimientoVisa;

    @FXML
    private Label iva;

    @FXML
    private Label nombre;

    @FXML
    private Label numReferencia;

    @FXML
    private Label total;

    @FXML
    private Label totalCompra;

    @FXML
    void Ayuda(ActionEvent event) throws IOException {
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
    void ConfirmarDatosParaCompra(ActionEvent event) {

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
