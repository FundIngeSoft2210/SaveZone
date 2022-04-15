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

public class controllerAnadirProducto {

    @FXML
    private TextField Alto;

    @FXML
    private TextField Ancho;

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
    private Button Button_Anadir;

    @FXML
    private Button Button_Cancelar;

    @FXML
    private Button Button_uscar;

    @FXML
    private TextField Cantidad;

    @FXML
    private ComboBox<?> Categoria;

    @FXML
    private TextField Descripcion_Producto;

    @FXML
    private TextField Largo;

    @FXML
    private TextField Nombre1;

    @FXML
    private TextField Nombre_Producto;

    @FXML
    private TextField Peso;

    @FXML
    private TextField Porcentaje_Descuento;

    @FXML
    private TextField Precio;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Button subir_Producto;
    @FXML
    void AnadirProducto(ActionEvent event) throws IOException{

    }

    @FXML
    void Ayuda(ActionEvent event) throws Exception {
        ControladorRutas.launchConQuePodemosAyudarte();
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Buscar(ActionEvent event) throws IOException{

    }

    @FXML
    void BuscarProductos(ActionEvent event) throws IOException{

    }

    @FXML
    void Cancelar(ActionEvent event) throws IOException{

    }

    @FXML
    void Categorias(ActionEvent event) throws IOException{

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
    void SubirImagenProducto(ActionEvent event) throws IOException{

    }

    @FXML
    void VenderProductos(ActionEvent event) throws Exception {
        ControladorRutas.launchAnadirProductos();
        Stage myStage = (Stage) this.Boton_vender.getScene().getWindow();
        myStage.close();

    }
    @FXML
    void VolverInicio(ActionEvent event) throws Exception {
        ControladorRutas.launchPantallaPrincipal();
        Stage myStage = (Stage) this.RegresarAlInicio.getScene().getWindow();
        myStage.close();

    }

}
