package org.example.controllerView;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Producto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class controllerProductoAComprar implements Initializable {

    Producto p;

    @FXML
    private Button Boton_Ayuda;

    @FXML
    private Button Boton_Historial;

    @FXML
    private Button Boton_VerMisProductos;

    @FXML
    private ComboBox<String> Boton_categorias;

    @FXML
    private Button Boton_populares;

    @FXML
    private Button Boton_vender;

    @FXML
    private Button Button_AnadirAFavoritos;

    @FXML
    private Button Boton_favoritos;

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
    private Button botonComprar;

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
    void Buscar(ActionEvent event) throws IOException{

    }

    @FXML
    void BuscarProductos(ActionEvent event) throws IOException{

    }

    @FXML
    void Categorias(ActionEvent event) throws IOException{

    }

    @FXML
    void Comprar(ActionEvent event) throws Exception {
        ControladorRutas.launchComprar();
        Stage myStage = (Stage) this.botonComprar.getScene().getWindow();
        myStage.close();
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
    void setProducto(Producto producto){
        nombreProducto.setText(producto.getTitulo());
        Precio.setText(String.valueOf(producto.getValor()));
        porcentajeDescuento.setText(String.valueOf(producto.getPorcentajeDesc()));
        cant.setText(String.valueOf(producto.getCantidad()));
        ancho.setText(String.valueOf(producto.getAncho()));
        peso.setText(String.valueOf(producto.getPeso()));
        alto.setText(String.valueOf(producto.getAlto()));
        descripcion.setText(producto.getDescripcion());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControladorBD controladorBD = new ControladorBD();
        Resultset rs;
        ObservableList<String> listaCatego;
        try {
            listaCatego = controladorBD.obtenerDeptos(controladorBD.ejecutarConsulta("SELECT NOMBRE FROM CATEGORIA"));
            Boton_categorias.setItems(listaCatego);
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void Favoritos(ActionEvent event) throws Exception {
        ControladorRutas.launchFavoritos();
        Stage myStage = (Stage) this.Boton_favoritos.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void AnadirAFavoritos(ActionEvent event) throws Exception{
        ControladorRutas.launchFavoritos();
        Stage myStage = (Stage) this.Button_AnadirAFavoritos.getScene().getWindow();
        myStage.close();
    }

}
