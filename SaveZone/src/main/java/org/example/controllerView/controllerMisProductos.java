package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class controllerMisProductos {

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
    private Button Button_AnadirProductos;

    @FXML
    private Button Button_uscar;

    @FXML
    private ImageView ImagenProducto;

    @FXML
    private TextField Nombre1;

    @FXML
    private Label NombreProducto;

    @FXML
    private Button Producto;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Button Button_ModificarProducto;
    @FXML
    private Button Boton_eliminarProducto;

    @FXML
    void Ayuda(ActionEvent event) throws Exception {
        ControladorRutas.launchConQuePodemosAyudarte();
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
    void Categorias(ActionEvent event) {

    }

    @FXML
    void Historial(ActionEvent event) throws Exception {
        ControladorRutas.launchHistorial();
        Stage myStage = (Stage) this.Boton_Historial.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Populares(ActionEvent event) throws Exception {
        ControladorRutas.launchProductosPopulares();
        Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Productos(ActionEvent event) throws Exception {
        ControladorRutas.launchMisProductos();
        Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void VenderProductos(ActionEvent event) throws Exception {
        ControladorRutas.launchAnadirProductos();
        Stage myStage = (Stage) this.Boton_vender.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void VerDetallesProducto(ActionEvent event) throws Exception {
        ControladorRutas.launchDetallesProducto();
        Stage myStage = (Stage) this.Producto.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void VolverInicio(ActionEvent event) throws Exception {
        ControladorRutas.launchPantallaPrincipal();
        Stage myStage = (Stage) this.RegresarAlInicio.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void anadirProductos(ActionEvent event) throws Exception {
        ControladorRutas.launchAnadirProducto();
        Stage myStage = (Stage) this.Button_AnadirProductos.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void ModificarProducto(ActionEvent event) throws Exception {
        ControladorRutas.launchModificarProducto();
        Stage myStage = (Stage) this.Button_ModificarProducto.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void EliminarProducto(ActionEvent event) throws Exception {
        ControladorRutas.launchEliminarProducto();
        Stage myStage = (Stage) this.Button_ModificarProducto.getScene().getWindow();
        myStage.close();
    }
}