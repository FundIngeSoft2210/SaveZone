package org.example.controllerView;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Producto;
import org.example.Gestion.GestionProductos.GestionProducto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controllerSeguimientoLlenarDatos implements Initializable {

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
    private Label HoraEntrega;

    @FXML
    private Label pesoText;

    @FXML
    private TextField Nombre1;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Button botonSiguiente;

    @FXML
    private Label cant;

    @FXML
    private Label ciudadDestino;

    @FXML
    private Label ciudadRecogida;

    @FXML
    private Label direccion;

    @FXML
    private Label fechaEntrega;

    @FXML
    private Label nombreContacto;

    @FXML
    private Label numGuia;

    @FXML
    private Label peso;

    @FXML
    private Label remitente;

    @FXML
    private Label tipo;

    @FXML
    private Label ciudadDestinoText;


    @FXML
    private Label fechaEntregaText;

    @FXML
    private Label CiudadOText;

    @FXML
    private Label ciudadDText;

    @FXML
    private Label direccionText;

    @FXML
    private Label contactoText;

    @FXML
    private Label tipoProductoText;

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
            alert.setTitle("Error de b??squeda");
            alert.setContentText("No existen productos con los par??metros de busqueda solicitados.");
            alert.showAndWait();
            return;
        } else if (Nombre1.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error de b??squeda");
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
    void Favoritos(ActionEvent event) throws Exception {
        Integer usuarioId = ControladorRutas.getUsuario().getId();
        ArrayList<Producto> productosFav = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID IN (SELECT PRODUCTOID FROM PRODUCTOFAVORITO WHERE USUARIOID = "+usuarioId+")"));
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("/Favoritos", productosFav);
        ControladorRutas.launchFavoritos();
        Stage myStage = (Stage) this.Boton_Favoritos.getScene().getWindow();
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
    void VolverInicio(ActionEvent event) throws Exception {
        ControladorRutas.launchPantallaPrincipal();
        Stage myStage = (Stage) this.RegresarAlInicio.getScene().getWindow();
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
        int pedidoId=ControladorRutas.getPedido().getId();
        try {
            CiudadOText.setText(controladorBD.ejecutarConsulta("Select DireccionOrigen FROM pedido where ID="+pedidoId).getString(1));
            ciudadDText.setText(controladorBD.ejecutarConsulta("Select DireccionDestino FROM pedido where ID="+pedidoId).getString(1));
            contactoText.setText(controladorBD.ejecutarConsulta("SELECT user.Nombre FROM usuario user, producto pro, pedido ped where ped.ID="+pedidoId+" AND pro.ID=ped.ProductoID AND user.ID=pro.VendedorID").getString(1));
            direccionText.setText(controladorBD.ejecutarConsulta("SELECT user.Direccion FROM usuario user, producto pro, pedido ped where ped.ID="+pedidoId+" AND pro.ID=ped.ProductoID AND user.ID=pro.VendedorID").getString(1));
            tipoProductoText.setText(controladorBD.ejecutarConsulta("SELECT cat.Nombre FROM categoria cat, pedido ped, producto pro where ped.ID="+pedidoId+" AND ped.ProductoID=pro.ID AND pro.CategoriaID=cat.ID").getString(1));
            pesoText.setText(controladorBD.ejecutarConsulta("SELECT pro.Peso FROM pedido ped, producto pro where ped.ID="+pedidoId+" AND ped.ProductoID=pro.ID").getString(1));
            ciudadDestinoText.setText(controladorBD.ejecutarConsulta("Select DireccionDestino FROM pedido where ID="+pedidoId).getString(1));
            nombreContacto.setText(ControladorRutas.getUsuario().getNombre());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    @FXML
    void irSiguiente(ActionEvent event) throws Exception{
        ControladorRutas.launchEnRuta();
        Stage myStage = (Stage) this.botonSiguiente.getScene().getWindow();
        myStage.close();
    }

}
