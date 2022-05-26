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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Pedido;
import org.example.Entidades.Producto;
import org.example.Gestion.GestionProductos.GestionPedido;
import org.example.Gestion.GestionProductos.GestionProducto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class controllerPago implements Initializable {

    @FXML
    private TextField Apartamento;

    @FXML
    private Button Boton_Favoritos;

    @FXML
    private TextField Apellido;

    @FXML
    private Button Boton_Ayuda;

    @FXML
    private Button Boton_Historial;

    @FXML
    private Button Boton_VerMisProductos;

    @FXML
    private Button Boton_cancelar;

    @FXML
    private ComboBox<String> Boton_categorias;

    @FXML
    private Button Boton_confirmar;

    @FXML
    private Button Boton_populares;

    @FXML
    private Button Boton_vender;

    @FXML
    private Button Button_Nombre_Producto;

    @FXML
    private Button Button_Valor_Producto;

    @FXML
    private Button Button_uscar;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Nombre1;

    @FXML
    private ComboBox<String> Pais;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private TextField correoCuenta;

    @FXML
    private TextField direccionCuenta;

    @FXML
    private TextField telefonoCuenta;

    @FXML
    private ComboBox<String> Boton_Perfil;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControladorBD controladorBD = new ControladorBD();
        Resultset rs;
        ObservableList<String> listaCatego;
        ObservableList<String> listaPerfil = FXCollections.observableArrayList();
        listaPerfil.add("Log out");
        listaPerfil.add("Perfil");
        Boton_Perfil.setItems(listaPerfil);
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
    void hacerPedido(ActionEvent event) throws Exception {
        GestionPedido gestionPedido = new GestionPedido();
        ControladorBD controladorBD = new ControladorBD();
        Random numeroAleatorio = new Random();
        String direccionO,direccionD,guiaRastreo;
        Float pesoTotal,subTotal,total;
        Integer productoId,compradorId,tarjetaId,estadoPedidoId,estadoPagoId,cantidad;

        direccionO=controladorBD.ejecutarConsulta("Select direccion from producto p, usuario u where p.vendedorid=u.id").getString(1);
        direccionD=this.direccionCuenta.getText()+this.Apartamento.getText();
        guiaRastreo=String.valueOf(numeroAleatorio.nextInt(5000-1000+1)+1000);
        pesoTotal=ControladorRutas.getProducto().getValor()+(float)(ControladorRutas.getProducto().getValor()*0.19);
        subTotal=(float)(ControladorRutas.getProducto().getValor());
        total=ControladorRutas.getProducto().getValor()+(float)(ControladorRutas.getProducto().getValor()*0.19);
        productoId=ControladorRutas.getProducto().getIdProducto();
        compradorId=ControladorRutas.getUsuario().getId();
        tarjetaId=1;
        estadoPagoId=1;
        estadoPedidoId=1;
        cantidad=1;


        Pedido pedido = new Pedido(productoId,compradorId,tarjetaId,estadoPedidoId,estadoPagoId,direccionO,direccionD,guiaRastreo,pesoTotal,cantidad,subTotal,total);
        gestionPedido.crearPedido(pedido);
        Integer idPedidoBuscado = controladorBD.ejecutarConsulta("SELECT ID FROM safezone_db.pedido ORDER BY ID DESC").getInt(1);
        pedido.setId(idPedidoBuscado);
        ControladorRutas.setPedido(pedido);
        ControladorRutas.launchMetodoPago();
        Stage myStage = (Stage) this.Boton_confirmar.getScene().getWindow();
        myStage.close();
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
    void Cancelar(ActionEvent event) throws Exception {
        Button temp = (Button) event.getSource();
        ControladorRutas.launchDetallesProducto(Integer.parseInt(temp.getId()));
        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Categorias(ActionEvent event) {

    }

    @FXML
    void ConfirmarDatos(ActionEvent event) throws Exception {
        ControladorRutas.launchMetodoPago();
        Stage myStage = (Stage) this.Boton_confirmar.getScene().getWindow();
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
}
