package org.example.controllerView;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Producto;
import org.example.Gestion.GestionProductos.GestionProducto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controllerModificarProducto implements Initializable {
    ControladorBD controladorBD = new ControladorBD();

    @FXML
    private Button Boton_Ayuda;

    @FXML
    private Button Boton_Historial;

    @FXML
    private Button Boton_VerMisProductos;
    @FXML
    private Button Boton_favoritos;

    @FXML
    private ComboBox<String> Boton_categorias;

    @FXML
    private Button Boton_populares;

    @FXML
    private Button Boton_vender;

    @FXML
    private Button Button_Cancelar;

    @FXML
    private Button Button_Modificar;

    @FXML
    private Button Button_uscar;

    @FXML
    private Button Cambiar_Imagen;

    @FXML
    private TextField Cambiar_Precio;

    @FXML
    private ComboBox<String> Cambiar_categoria;

    @FXML
    private TextField Nombre1;

    @FXML
    private TextField NuevaCantidad;

    @FXML
    private TextField Nueva_Descripcion_Producto;

    @FXML
    private TextField NuevoAlto;

    @FXML
    private TextField NuevoAncho;

    @FXML
    private TextField NuevoLargo;

    @FXML
    private TextField NuevoPeso;

    @FXML
    private TextField Nuevo_Nombre_Producto;

    @FXML
    private TextField Nuevo_Porcentaje_Descuento;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    void AceptarModificarProducto(ActionEvent event) throws Exception {
        try{
            GestionProducto gestionProducto = new GestionProducto();
            Producto p = new Producto();
            p.setCantidad(Integer.parseInt(NuevaCantidad.getText()));
            p.setTitulo(Nuevo_Nombre_Producto.getText());
            p.setDescripcion(Nueva_Descripcion_Producto.getText());
            p.setPeso(Float.parseFloat(NuevoPeso.getText()));
            p.setAlto(Float.parseFloat(NuevoAlto.getText()));
            p.setLargo(Float.parseFloat(NuevoLargo.getText()));
            p.setAncho(Float.parseFloat(NuevoAncho.getText()));
            p.setValor(Integer.parseInt(Cambiar_Precio.getText()));
            p.setPorcentajeDesc(Integer.parseInt(Nuevo_Porcentaje_Descuento.getText()));
            p.setVendedor(ControladorRutas.getUsuario());
            p.setCiudadID(ControladorRutas.getProducto().getCiudadID());
            p.setEstadoProductoID(ControladorRutas.getProducto().getEstadoProductoID());
            controladorBD.ejecutarInsert("UPDATE `safezone_db`.`producto` SET TITULO = " +
                    "'" + p.getTitulo() + "', CANTIDAD = " + p.getCantidad() + ", DESCRIPCION = '" + p.getDescripcion() +
                    "', PESO = " + p.getPeso() + ", VALOR = " + p.getValor() + ", PORCENTAJEDESCUENTO = " +
                    p.getPorcentajeDesc() + ", ALTO = " + p.getAlto() + ", LARGO = " + p.getLargo() + ", ANCHO = " + p.getAncho()
                    + ", COLOR = '" + p.getColor() + "', CIUDADID = " + p.getCiudadID() + ", ESTADOPRODUCTOID = " + p.getEstadoProductoID() +
                    " WHERE TITULO = '" + ControladorRutas.getProducto().getTitulo() + "' AND VENDEDORID = " + p.getVendedor().getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("CONFIRMACIÓN");
            alert.setContentText("Estimado usuario el producto fue Actualizado");
            alert.showAndWait();
            ControladorRutas.launchMisProductos();
            Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
            myStage.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

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
        controladorDespliegueProductos.desplegarProductos("/Principal",productos, 20, 114);
        ControladorRutas.launchPantallaPrincipal(true);
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
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
    void NuevaImagen(ActionEvent event) {

    }

    @FXML
    void setProducto(Producto producto) throws IOException{
        /**controladorDespliegueProductos.base64ToLocal(producto.getImgdata(), producto.getIdProducto()+"producto.png");
         Image image = new Image(controladorPropiedades.getPropiedad("resourcesPath") + "/" + producto.getIdProducto() + "producto.png");
         System.out.println(image.getUrl());
         ImageIO.read()
         Image image  = ImageIO.read(producto.getImgdata()); // Opening again as an Image

         imagenProducto.setImage();*/
        Nuevo_Nombre_Producto.setText(producto.getTitulo());
        Cambiar_categoria.getSelectionModel().select(producto.getCategoria());
        Cambiar_Precio.setText(String.valueOf(producto.getValor()));
        Nuevo_Porcentaje_Descuento.setText(String.valueOf(producto.getPorcentajeDesc()));
        NuevoPeso.setText(String.valueOf(producto.getPeso()));
        NuevoAlto.setText(String.valueOf(producto.getAlto()));
        NuevoAncho.setText(String.valueOf(producto.getAncho()));
        NuevoLargo.setText(String.valueOf(producto.getLargo()));
        NuevaCantidad.setText(String.valueOf(producto.getCantidad()));
        Nueva_Descripcion_Producto.setText(producto.getDescripcion());
        ControladorRutas.setProducto(producto);
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
        Integer usuarioId = ControladorRutas.getUsuario().getId();
        ArrayList<Producto> productosFav = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID IN (SELECT PRODUCTOID FROM PRODUCTOFAVORITO WHERE USUARIOID = "+usuarioId+")"));
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("/Favoritos", productosFav, 20, 114);
        ControladorRutas.launchFavoritos();
        Stage myStage = (Stage) this.Boton_favoritos.getScene().getWindow();
        myStage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ControladorBD controladorBD = new ControladorBD();
        Resultset rs;
        ObservableList<String> listaCatego;
        try {
            listaCatego = controladorBD.obtenerDeptos(controladorBD.ejecutarConsulta("SELECT NOMBRE FROM CATEGORIA"));
            Boton_categorias.setItems(listaCatego);
            Cambiar_categoria.setItems(listaCatego);
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
