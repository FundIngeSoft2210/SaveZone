package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Producto;

import java.io.IOException;
import java.util.ArrayList;

public class controllerEliminarProducto {

    ControladorBD controladorBD = new ControladorBD();

    @FXML
    private Button Boton_Ayuda;
    @FXML
    private Button Boton_favoritos;

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
    private Button Button_Cancelar;

    @FXML
    private Button Button_Eliminar;

    @FXML
    private Button Button_uscar;

    @FXML
    private TextField Nombre1;

    @FXML
    private Label NombreProducto;

    @FXML
    private ImageView imagenProducto;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Label ValorProducto;

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
    void Cancelar(ActionEvent event) throws Exception {
        ControladorRutas.launchMisProductos();
        Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void setProducto(Producto producto) throws IOException{
        /**controladorDespliegueProductos.base64ToLocal(producto.getImgdata(), producto.getIdProducto()+"producto.png");
         Image image = new Image(controladorPropiedades.getPropiedad("resourcesPath") + "/" + producto.getIdProducto() + "producto.png");
         System.out.println(image.getUrl());
         ImageIO.read()
         Image image  = ImageIO.read(producto.getImgdata()); // Opening again as an Image

         imagenProducto.setImage();*/
        NombreProducto.setText(producto.getTitulo());
        ValorProducto.setText(String.valueOf(producto.getValor()));
        ControladorRutas.setProducto(producto);
    }

    @FXML
    void Categorias(ActionEvent event) throws Exception {
        String categoria = (String) this.Boton_categorias.getSelectionModel().getSelectedItem();
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
    void EliminarProducto(ActionEvent event) throws Exception {
        try{
            controladorBD.ejecutarInsert("UPDATE PRODUCTO set EstadoProductoID = 6 WHERE ID = "+ControladorRutas.getProducto().getIdProducto());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("CONFIRMACIÓN");
            alert.setContentText("Estimado usuario el producto fue eliminado");
            alert.showAndWait();

            ControladorRutas.launchMisProductos();
            Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
            myStage.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
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


}
