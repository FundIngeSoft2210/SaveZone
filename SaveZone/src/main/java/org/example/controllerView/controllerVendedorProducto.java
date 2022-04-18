package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class controllerVendedorProducto {
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
    private Button Boton_Favoritos;

    @FXML
    private Button Boton_vender;

    @FXML
    private Button Button_uscar;

    @FXML
    private TextField Nombre1;

    @FXML
    private Label Precio;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Label alto;

    @FXML
    private Label ancho;

    @FXML
    private Button botonEliminar;

    @FXML
    private Button botonModificar;

    @FXML
    private Label cant;

    @FXML
    private TextArea comentarios;

    @FXML
    private TextArea descripcion;

    @FXML
    private Label nombreProducto;

    @FXML
    private Label peso;

    @FXML
    private Label porcentajeDescuento;


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
        ControladorRutas.launchAnadirProducto();
        Stage myStage = (Stage) this.Boton_vender.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void VolverInicio(ActionEvent event) throws Exception {
        ControladorRutas.launchPantallaPrincipal();
        Stage myStage = (Stage) this.RegresarAlInicio.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void ModificarProducto(ActionEvent event) throws Exception {
        Button temp = (Button) event.getSource();
        ControladorRutas.launchModificarProducto(Integer.parseInt(temp.getId()));
        Stage myStage = (Stage) this.botonModificar.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void EliminarProducto(ActionEvent event) throws Exception {
        Button temp = (Button) event.getSource();
        ControladorRutas.launchEliminarProducto(Integer.parseInt(temp.getId()));
        Stage myStage = (Stage) this.botonEliminar.getScene().getWindow();
        myStage.close();
    }
    @FXML
    void Favoritos(ActionEvent event) throws Exception {
        ControladorRutas.launchFavoritos();
        Stage myStage = (Stage) this.Boton_Favoritos.getScene().getWindow();
        myStage.close();

    }
}
