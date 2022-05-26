package org.example.controllerView;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Producto;
import org.example.Gestion.GestionProductos.GestionProducto;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controllerEscogerMetodoPago implements Initializable {

    @FXML
    private Button Boton_Ayuda;

    @FXML
    private Button Boton_Historial;

    @FXML
    private Button Boton_Favoritos;


    @FXML
    private Button Boton_VerMisProductos;

    @FXML
    private ComboBox<String> Boton_categorias;

    @FXML
    private Button Boton_populares;

    @FXML
    private Button Boton_vender;

    @FXML
    private Button Button_uscar;

    @FXML
    private Button Codensa;

    @FXML
    private Button MC;

    @FXML
    private ImageView MasterCard;

    @FXML
    private TextField Nombre1;

    @FXML
    private Button PSE;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Label correo;

    @FXML
    private Label descripcionCompra;

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
    private Button visa;

    @FXML
    private Label subTotalText;

    @FXML
    private Label ivaText;

    @FXML
    private Label totalText;

    @FXML
    private Label nombreText;

    @FXML
    private Label correoText;

    @FXML
    private ComboBox<String> Boton_Perfil;

    @FXML
    void Ayuda(ActionEvent event) throws Exception {
        ControladorRutas.launchConQuePodemosAyudarte();
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Buscar(ActionEvent event) throws Exception {
        BuscarProductos(event);
    }

    @FXML
    void BuscarProductos(ActionEvent event) throws Exception {
        ControladorDespliegueProductos controladorDespliegueProductos = new ControladorDespliegueProductos();
        GestionProducto gestionProducto = new GestionProducto();
        ArrayList<Producto> productos = gestionProducto.buscarProducto(Nombre1.getText());
        if (productos == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error de búsqueda");
            alert.setContentText("No existen productos con los parámetros de busqueda solicitados.");
            alert.showAndWait();
            return;
        } else if (Nombre1.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error de búsqueda");
            alert.setContentText("Ingrese algo en el campo de busqueda.");
            alert.showAndWait();
            return;
        }
        controladorDespliegueProductos.desplegarProductos("/Principal",productos);
        ControladorRutas.launchPantallaPrincipal(true);
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Categorias(ActionEvent event) {

    }

    @FXML
    void PagarPorCodensa(ActionEvent event) throws Exception {
        ControladorRutas.launchMetodoCodensa();
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void PagarPorMC(ActionEvent event) throws Exception{
        ControladorRutas.launchMetodoMC();
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void PagarPorVisa(ActionEvent event) throws Exception{
        ControladorRutas.launchMetodoVisa();
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void PagoPSE(ActionEvent event) throws Exception{
        ControladorRutas.launchMetodoPSE();
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
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
    void Favoritos(ActionEvent event) throws Exception {
        ControladorRutas.launchFavoritos();
        Stage myStage = (Stage) this.Boton_Favoritos.getScene().getWindow();
        myStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControladorBD controladorBD = new ControladorBD();
        Resultset rs;
        ObservableList<String> listaCatego;
        ObservableList<String> listaPerfil = FXCollections.observableArrayList();
        listaPerfil.add("Log out");
        listaPerfil.add("Perfil");
        Boton_Perfil.setItems(listaPerfil);
        nombreText.setText(ControladorRutas.getUsuario().getNombre());
        correoText.setText(ControladorRutas.getUsuario().getCorreo());
        subTotalText.setText(String.valueOf(ControladorRutas.getPedido().getSubtotal()));
        ivaText.setText(String.valueOf(ControladorRutas.getPedido().getSubtotal()*0.19));
        totalText.setText(String.valueOf(ControladorRutas.getPedido().getTotal()));
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
    void Perfil(ActionEvent event) throws Exception {
        String opcion = this.Boton_Perfil.getSelectionModel().getSelectedItem();
        if (opcion.equalsIgnoreCase("Perfil")){
            irAPerfil(event);
        } else if (opcion.equalsIgnoreCase("Log in")){
            ControladorRutas.launchVista_Acceder();
            Stage myStage = (Stage) this.Boton_Perfil.getScene().getWindow();
            myStage.close();
        } else if (opcion.equalsIgnoreCase("Sign up")){
            ControladorRutas.launchVista_Registro();
            Stage myStage = (Stage) this.Boton_Perfil.getScene().getWindow();
            myStage.close();
        } else if (opcion.equalsIgnoreCase("Log out")){
            ControladorRutas.usuario = null;
            ControladorRutas.launchPantallaInicio();
            Stage myStage = (Stage) this.Boton_Perfil.getScene().getWindow();
            myStage.close();
        }
    }

    @FXML
    void irAPerfil(ActionEvent event) throws Exception {
        ControladorRutas.launchVista_Perfil();
        Stage myStage = (Stage) this.Boton_Historial.getScene().getWindow();
        myStage.close();
    }

}
