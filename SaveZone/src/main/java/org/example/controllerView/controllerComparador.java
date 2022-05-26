package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.example.Entidades.Producto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class controllerComparador {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button Boton_populares;

    @FXML
    private ComboBox<?> Boton_categorias;

    @FXML
    private TextField Nombre1;

    @FXML
    private Button Boton_Historial;

    @FXML
    private Button Boton_Ayuda;

    @FXML
    private Button Boton_VerMisProductos;

    @FXML
    private Button Button_uscar;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Button Boton_Favoritos;

    @FXML
    private Button ButtonPerfil;

    @FXML
    private ImageView imagen11;

    @FXML
    private Label porcentajeDescuento1;

    @FXML
    private Label Precio1;

    @FXML
    private Label cant1;

    @FXML
    private Label ancho1;

    @FXML
    private Label peso1;

    @FXML
    private Label alto1;

    @FXML
    private Label largo1;

    @FXML
    private Label color1;

    @FXML
    private Label versus;

    @FXML
    private ImageView imagen1;

    @FXML
    private Label nombreProducto1;

    @FXML
    private Label nombreProducto2;

    @FXML
    private Label porcentajeDescuento2;

    @FXML
    private Label Precio2;

    @FXML
    private Label cant2;

    @FXML
    private Label ancho2;

    @FXML
    private Label peso2;

    @FXML
    private Label alto2;

    @FXML
    private Label largo2;

    @FXML
    private Label color2;

    @FXML
    void Ayuda(ActionEvent event) {

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
    void Favoritos(ActionEvent event) {

    }

    @FXML
    void Historial(ActionEvent event) {

    }

    @FXML
    void Populares(ActionEvent event) {

    }

    @FXML
    void Productos(ActionEvent event) {

    }

    @FXML
    void VolverInicio(ActionEvent event) {

    }

    @FXML
    void irAPerfil(ActionEvent event) {

    }

    @FXML
    void setProductos(Producto producto1, Producto producto2) throws FileNotFoundException, UnsupportedEncodingException {
        String path = ControladorRutas.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath1 = URLDecoder.decode(path + "/org/example/" + producto1.getIdProducto() + "producto.png", "UTF-8");
        String decodedPath2 = URLDecoder.decode(path + "/org/example/" + producto2.getIdProducto() + "producto.png", "UTF-8");

        InputStream stream1 = new FileInputStream(decodedPath1);
        Image imageProducto1 = new Image(stream1);

        InputStream stream2 = new FileInputStream(decodedPath2);
        Image imageProducto2 = new Image(stream2);

        versus.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(versus, 0.0);
        AnchorPane.setRightAnchor(versus, 0.0);
        versus.setAlignment(Pos.CENTER);

        versus.setText(producto1.getTitulo() + " vs " + producto2.getTitulo());

        imagen1.setImage(imageProducto1);
        imagen11.setImage(imageProducto2);

        nombreProducto1.setText(producto1.getTitulo());
        nombreProducto2.setText(producto2.getTitulo());

        Precio1.setText("$" + producto1.getValor());
        Precio2.setText("$" + producto2.getValor());

        porcentajeDescuento1.setText(producto1.getPorcentajeDesc().toString() + "% OFF");
        porcentajeDescuento2.setText(producto2.getPorcentajeDesc().toString() + "% OFF");

        cant1.setText(producto1.getCantidad().toString());
        cant2.setText(producto2.getCantidad().toString());

        ancho1.setText(producto1.getAncho().toString());
        ancho2.setText(producto2.getAncho().toString());

        peso1.setText(producto1.getPeso().toString());
        peso2.setText(producto2.getPeso().toString());

        alto1.setText(producto1.getAlto().toString());
        alto2.setText(producto2.getAlto().toString());

        largo1.setText(producto1.getLargo().toString());
        largo2.setText(producto2.getLargo().toString());

        color1.setText(producto1.getColor());
        color2.setText(producto2.getColor());
    }
}

