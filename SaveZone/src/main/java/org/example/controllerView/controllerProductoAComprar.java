package org.example.controllerView;

import com.mysql.cj.protocol.Resultset;
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
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.AccesoDatos.ControladorPropiedades;
import org.example.Entidades.Producto;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;

public class controllerProductoAComprar implements Initializable {

    Producto p;

    ControladorDespliegueProductos controladorDespliegueProductos = new ControladorDespliegueProductos();
    ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
    ControladorBD controladorBD = new ControladorBD();

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
    private ImageView imagenProducto;

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
    void setProducto(Producto producto) throws IOException {
        /**controladorDespliegueProductos.base64ToLocal(producto.getImgdata(), producto.getIdProducto()+"producto.png");
        Image image = new Image(controladorPropiedades.getPropiedad("resourcesPath") + "/" + producto.getIdProducto() + "producto.png");
        System.out.println(image.getUrl());
        ImageIO.read()
        Image image  = ImageIO.read(producto.getImgdata()); // Opening again as an Image

        imagenProducto.setImage();*/
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
        Integer usuarioId = ControladorRutas.getUsuario().getId();
        ArrayList<Producto> productosFav = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID IN (SELECT PRODUCTOID FROM PRODUCTOFAVORITO WHERE USUARIOID = "+usuarioId+")"));
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("/Principal", productosFav, 20, 114);
        ControladorRutas.launchFavoritos();
        Stage myStage = (Stage) this.Boton_favoritos.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void AnadirAFavoritos(ActionEvent event) throws Exception{
        try {
            Integer usuarioId, productoId;
            usuarioId = ControladorRutas.getUsuario().getId();
            productoId = ControladorRutas.getProducto().getIdProducto();
            String queryInsert = "INSERT INTO `safezone_db`.`productofavorito` (`UsuarioID`, `ProductoID`) VALUES " +
                    "(" + usuarioId + ", " + productoId+ ")";
            controladorBD.ejecutarInsert(queryInsert);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("CONFIRMACIÓN");
            alert.setContentText("Estimado usuario el producto se agrego a favoritos ");
            alert.showAndWait();

            ControladorRutas.launchFavoritos();
            Stage myStage = (Stage) this.Button_AnadirAFavoritos.getScene().getWindow();
            myStage.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
