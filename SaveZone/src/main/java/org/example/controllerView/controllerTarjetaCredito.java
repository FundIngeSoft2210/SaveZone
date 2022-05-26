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
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Producto;
import org.example.Entidades.Tarjeta;
import org.example.Gestion.GestionProductos.GestionPedido;
import org.example.Gestion.GestionProductos.GestionProducto;
import org.example.Gestion.GestionProductos.GestionTarjeta;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class controllerTarjetaCredito implements Initializable {

    ControladorBD controladorBD = new ControladorBD();

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
    private Button Button_Cancelar;

    @FXML
    private Button Button_Pagar;

    @FXML
    private Button Button_uscar;

    @FXML
    private TextField CVV;

    @FXML
    private Tab Codensa;

    @FXML
    private ComboBox<Integer> Cuotas;

    @FXML
    private DatePicker FechaVencimientoCodensa;

    @FXML
    private DatePicker FechaVencimientoMC;

    @FXML
    private Tab MC;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Nombre14;

    @FXML
    private TextField NombreTitular;

    @FXML
    private TextField NumeroTarjeta;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private Tab Visa;

    @FXML
    private Label correo;

    @FXML
    private Label descripcion;

    @FXML
    private DatePicker fechaVencimientoVisa;

    @FXML
    private Label iva;

    @FXML
    private Label nombreText;

    @FXML
    private Label numReferencia;

    @FXML
    private Label total;

    @FXML
    private Label totalCompra;

    @FXML
    private Label subTotalText;

    @FXML
    private Label ivaText;

    @FXML
    private Label totalText;

    @FXML
    private TextField Nombre1;

    @FXML
    private Button agregarTarjeta;

    @FXML
    private TextField anoVence;

    @FXML
    private TextField mesVence;


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
        nombreText.setText(ControladorRutas.getUsuario().getNombre());
        correo.setText(ControladorRutas.getUsuario().getCorreo());
        subTotalText.setText(String.valueOf(ControladorRutas.getPedido().getSubtotal()));
        ivaText.setText(String.valueOf(ControladorRutas.getPedido().getSubtotal()*0.19));
        totalText.setText(String.valueOf(ControladorRutas.getPedido().getTotal()));
        numReferencia.setText(String.valueOf(ControladorRutas.getPedido().getId()));
        descripcion.setText(ControladorRutas.getProducto().getTitulo());

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
    void AgregarTarjeta(ActionEvent event) throws Exception{
        GestionTarjeta gestionTarjeta = new GestionTarjeta();
        GestionPedido gestionPedido = new GestionPedido();
        String numeroTarjeta, cvv,anoVencimiento,mesVencimiento;
        Integer usuarioId,Activo;

        numeroTarjeta=NumeroTarjeta.getText();
        cvv=CVV.getText();
        anoVencimiento=anoVence.getText();
        mesVencimiento=mesVence.getText();
        usuarioId=ControladorRutas.getUsuario().getId();
        Activo=1;

        int tarjetaExistente = gestionTarjeta.buscarTarjeta(numeroTarjeta);
        System.out.println(tarjetaExistente);
        if(tarjetaExistente==0){
            Tarjeta tarjeta = new Tarjeta(usuarioId,numeroTarjeta,cvv,anoVencimiento,mesVencimiento,Activo);


            boolean creada = gestionTarjeta.crearTarjeta(tarjeta);
            int tarjetaId = gestionTarjeta.buscarTarjeta(numeroTarjeta);
            gestionPedido.actualizarPedido(ControladorRutas.getPedido().getId(),tarjetaId);
            if(creada){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("CONFIRMACIÓN");
                alert.setContentText("Estimado cliente se ha creado la tarjeta correctamente");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Estimado cliente hubo un problema en la creacion de su tarjeta");
                alert.showAndWait();
            }
        }else{
            ControladorRutas.getPedido().setTarjetaId(tarjetaExistente);
            gestionPedido.actualizarPedido(ControladorRutas.getPedido().getId(),tarjetaExistente);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("CONFIRMACIÓN");
            alert.setContentText("Estimado cliente se ha creado la tarjeta correctamente");
            alert.showAndWait();
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
    void Cancelar(ActionEvent event) {

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
    void ConfirmarDatosParaCompra(ActionEvent event) {

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
