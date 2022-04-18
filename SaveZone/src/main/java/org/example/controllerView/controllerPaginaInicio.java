package org.example.controllerView;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionProductos.GestionProducto;

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
    private ScrollBar scroll;

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
    private Button RegresarAlInicio;

    @FXML
    private ImageView ImagenProducto;

    @FXML
    private TextField Nombre1;

    @FXML
    private Button Producto;

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
    void Categorias(ActionEvent event) throws Exception {
        String categoria = this.Boton_categorias.getSelectionModel().getSelectedItem();
        Integer categoriaId = Integer.parseInt(controladorBD.ejecutarConsulta("SELECT * FROM CATEGORIA WHERE " +
                "NOMBRE = '" + categoria + "'").getString(1)) ;
        ArrayList<Producto> productos = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE CategoriaID = "+categoriaId));
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("/Principal", productos, 20, 114);
        ControladorRutas.launchPantallaPrincipal();
        Stage myStage = (Stage) this.Boton_categorias.getScene().getWindow();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scroll.setMin(0);
        scroll.setMax(2000);
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
        Integer usuarioId = ControladorRutas.getUsuario().getId();
        ArrayList<Producto> productosFav = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID IN (SELECT PRODUCTOID FROM PRODUCTOFAVORITO WHERE USUARIOID = "+usuarioId+")"));
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("/Favoritos", productosFav, 20, 114);
        ControladorRutas.launchFavoritos();
        Stage myStage = (Stage) this.Boton_Favoritos.getScene().getWindow();
        myStage.close();
    }
}

