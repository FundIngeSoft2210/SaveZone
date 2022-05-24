package org.example.controllerView;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionProductos.GestionProducto;
import javafx.scene.input.MouseEvent;
import javax.sound.sampled.Control;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controllerPaginaInicio implements Initializable {

    ControladorBD controladorBD = new ControladorBD();
    @FXML
    private Button Boton_Ayuda;

    @FXML
    private Button Boton_Favoritos;

    @FXML
    private Button Boton_Historial;

    @FXML
    private Button Boton_VerMisProductos;

    @FXML
    private ComboBox<String> Boton_categorias;

    @FXML
    private Button Boton_populares;

    @FXML
    private Button ButtonPerfil;

    @FXML
    private Button Button_uscar;

    @FXML
    private ImageView ImagenProducto;

    @FXML
    private ImageView ImagenProducto3;

    @FXML
    private TextField Nombre1;

    @FXML
    private Button Producto;

    @FXML
    private Button Producto1;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Button boton_pag_principal;

    @FXML
    private ImageView imagenproducto2;

    @FXML
    private Button masVendidos;

    @FXML
    private Button producto3;

    @FXML
    private Button recomendados;

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
    void Perfil(ActionEvent event) throws Exception {
        String opcion = this.Boton_Perfil.getSelectionModel().getSelectedItem();
        if (opcion.equalsIgnoreCase("Perfil")){
            irAPerfil(event);
        }
    }

    @FXML
    void BuscarProductos(ActionEvent event) throws Exception {
        ControladorDespliegueProductos controladorDespliegueProductos = new ControladorDespliegueProductos();
        GestionProducto gestionProducto = new GestionProducto();
        ArrayList <Producto> productos = gestionProducto.buscarProducto(Nombre1.getText());
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
    void Categorias(ActionEvent event) throws Exception {
        String categoria = this.Boton_categorias.getSelectionModel().getSelectedItem();
        Integer categoriaId = Integer.parseInt(controladorBD.ejecutarConsulta("SELECT * FROM CATEGORIA WHERE " +
                "NOMBRE = '" + categoria + "'").getString(1)) ;
        ArrayList<Producto> productos = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE CategoriaID = "+categoriaId));
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        try {
            controladorDespliegue.desplegarProductos("/Principal", productos);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("LO SENTIMOS");
            alert.setContentText("No hay productos disponibles de esta categoria");
            alert.showAndWait();
        }
        ControladorRutas.launchPantallaPrincipal(true);
        Stage myStage = (Stage) this.Boton_categorias.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Favoritos(ActionEvent event) throws Exception {
        if (ControladorRutas.getUsuario() != null) {
            Integer usuarioId = ControladorRutas.getUsuario().getId();
            ArrayList<Producto> productosFav = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID IN (SELECT PRODUCTOID FROM PRODUCTOFAVORITO WHERE USUARIOID = " + usuarioId + ")"));
            ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
            controladorDespliegue.desplegarProductos("/Favoritos", productosFav);
            ControladorRutas.launchFavoritos();
            Stage myStage = (Stage) this.Boton_Favoritos.getScene().getWindow();
            myStage.close();
        }
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
        Stage myStage = (Stage) this.Boton_populares.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Productos(ActionEvent event) throws Exception {
        ControladorRutas.launchMisProductos();
        Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void VerDetallesProducto(ActionEvent event) throws Exception {
        Button temp = (Button) event.getSource();
        ControladorRutas.launchDetallesProducto(Integer.parseInt(temp.getId()));
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void VolverInicio(ActionEvent event) throws Exception {
        ControladorRutas.launchPantallaPrincipal();
        Stage myStage = (Stage) this.RegresarAlInicio.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void pagMasVendidoa(ActionEvent event) throws Exception{
        ControladorRutas.launchMasVendido();
        Stage myStage = (Stage) this.masVendidos.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void pagPrincipal(ActionEvent event) throws Exception{
        ControladorRutas.launchPantallaPrincipal();
        Stage myStage = (Stage) this.RegresarAlInicio.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void pagRecomendados(ActionEvent event) throws Exception{
        ControladorRutas.launchPagRecomendados();
        Stage myStage = (Stage) this.recomendados.getScene().getWindow();
        myStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControladorBD controladorBD = new ControladorBD();
        Resultset rs;
        ObservableList<String> listaCatego;
        ObservableList<String> listaPerfil = FXCollections.observableArrayList();
        if (ControladorRutas.getUsuario() == null){
            this.Boton_Ayuda.setDisable(true);
            this.Boton_Favoritos.setDisable(true);
            this.Boton_VerMisProductos.setDisable(true);
            this.Boton_Historial.setDisable(true);
            this.Boton_Ayuda.setVisible(false);
            this.Boton_Favoritos.setVisible(false);
            this.Boton_VerMisProductos.setVisible(false);
            this.Boton_Historial.setVisible(false);
            listaPerfil.add("Log in");
            listaPerfil.add("Sign up");
            Boton_Perfil.setItems(listaPerfil);
        } else {
            listaPerfil.add("Log out");
            listaPerfil.add("Perfil");
            Boton_Perfil.setItems(listaPerfil);
        }
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
    void irAPerfil(ActionEvent event) throws Exception {
        ControladorRutas.launchVista_Perfil();
        Stage myStage = (Stage) this.Boton_Historial.getScene().getWindow();
        myStage.close();
    }


    @FXML
    void verDetallesProducto(ActionEvent event) throws Exception {
        Button temp = (Button) event.getSource();
        ControladorRutas.launchDetallesProducto(Integer.parseInt(temp.getId()));
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
    }

}

