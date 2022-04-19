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
import org.example.Gestion.GestionProductos.GestionProducto;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
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
    private Label color;

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
    void Buscar(ActionEvent event) throws Exception {
        BuscarProductos(event);
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
        controladorDespliegue.desplegarProductos("/Principal", productos);
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
        String path = ControladorRutas.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path + "/org/example/" + producto.getIdProducto() + "producto.png", "UTF-8");
        InputStream stream = new FileInputStream(decodedPath);
        Image image = new Image(stream);
        imagenProducto.setImage(image);

        nombreProducto.setText(producto.getTitulo());
        Precio.setText("$" + String.valueOf(producto.getValor()));
        if (producto.getPorcentajeDesc() > 0)
            porcentajeDescuento.setText(String.valueOf(producto.getPorcentajeDesc()) + "% OFF");
        else
            porcentajeDescuento.setVisible(false);
        cant.setText("Quedan " + String.valueOf(producto.getCantidad()) + " unidades en stock.");
        ancho.setText(String.valueOf(producto.getAncho()) + " cm");
        peso.setText(String.valueOf(producto.getPeso()) + " cm");
        alto.setText(String.valueOf(producto.getAlto()) + " cm");
        color.setText(producto.getColor());
        descripcion.setText(producto.getDescripcion());
        ControladorRutas.setProducto(producto);
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
        controladorDespliegue.desplegarProductos("/Favoritos", productosFav);
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

            ArrayList<Producto> productosFav = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID IN (SELECT PRODUCTOID FROM PRODUCTOFAVORITO WHERE USUARIOID = "+usuarioId+")"));
            ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
            controladorDespliegue.desplegarProductos("/Favoritos", productosFav);

            ControladorRutas.launchFavoritos();
            Stage myStage = (Stage) this.Button_uscar.getScene().getWindow();
            myStage.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
