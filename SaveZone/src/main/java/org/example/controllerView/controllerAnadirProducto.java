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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Categoria;
import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionProductos.GestionProducto;
import org.example.Gestion.GestionUsuario;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controllerAnadirProducto implements Initializable {

    final ControladorBD controladorBD = new ControladorBD();
    final ControladorDespliegueProductos controladorDespliegueProductos = new ControladorDespliegueProductos();
    private String encoded;

    @FXML
    private TextField Alto;

    @FXML
    private TextField Ancho;

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
    private Button Boton_favoritos;

    @FXML
    private Button Boton_vender;

    @FXML
    private Button Button_Anadir;

    @FXML
    private Button Button_Cancelar;

    @FXML
    private Button Button_uscar;

    @FXML
    private TextField Cantidad;

    @FXML
    private ComboBox<String> Categoria;

    @FXML
    private ComboBox<String> Estado;

    @FXML
    private ComboBox<String> Boton_Perfil;

    @FXML
    private TextField Descripcion_Producto;

    @FXML
    private TextField Largo;

    @FXML
    private TextField Nombre1;

    @FXML
    private TextField Nombre_Producto;

    @FXML
    private TextField Peso;

    @FXML
    private TextField Porcentaje_Descuento;

    @FXML
    private TextField Precio;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private TextField Color;

    @FXML
    private Button subir_Producto;
    @FXML
    void AnadirProducto(ActionEvent event) throws IOException {
        GestionProducto gestionProducto = new GestionProducto();
        ControladorBD controladorBD = new ControladorBD();
        try{
            String nombre_Producto, Descripcion,color, categoriaSel;
            Integer cantidad, valor, porcentaje_Descuento , ciudadID, categoriaID;
            Float  peso,largo,alto,ancho;
            Usuario vendedor;


            nombre_Producto = this.Nombre_Producto.getText();
            if (nombre_Producto == null || nombre_Producto.isEmpty()){
                throw new Exception("nombre_Producto");
            }
            Descripcion = this.Descripcion_Producto.getText();
            if (Descripcion == null || Descripcion.isEmpty()){
                throw new Exception("Descripcion");
            }
            cantidad = Integer.parseInt(this.Cantidad.getText());
            if (cantidad == null ){
                throw new Exception("cantidad");
            }
            peso = Float.parseFloat(this.Peso.getText());
            if (peso == null ){
                throw new Exception("peso");
            }
            valor = Integer.parseInt(this.Precio.getText());
            if (valor == null ){
                throw new Exception("valor");
            }
            porcentaje_Descuento = Integer.parseInt(this.Porcentaje_Descuento.getText());
            if (porcentaje_Descuento == null ){
                throw new Exception("porcentaje_Descuento");
            }
            largo = Float.parseFloat(this.Largo.getText());
            if (largo == null ){
                throw new Exception("largo");
            }
            alto = Float.parseFloat(this.Alto.getText());
            if (alto == null ){
                throw new Exception("alto");
            }
            ancho = Float.parseFloat(this.Ancho.getText());
            if (ancho == null ){
                throw new Exception("ancho");
            }
            color = this.Color.getText();
            if (color == null || color.isEmpty() ){
                throw new Exception("color");
            }
            vendedor = ControladorRutas.getUsuario();
            System.out.println(vendedor);
            ciudadID = ControladorRutas.getUsuario().getCiudadID();
            System.out.println(ciudadID);
            categoriaSel= this.Categoria.getSelectionModel().getSelectedItem();
            if (categoriaSel == null || categoriaSel.isEmpty() ){
                throw new Exception("categoriaSel");
            }

            categoriaID = Integer.parseInt(controladorBD.ejecutarConsulta("SELECT * FROM CATEGORIA WHERE " +
                    "NOMBRE = '" + categoriaSel + "'").getString(1)) ;

            Producto producto = new Producto(vendedor, nombre_Producto, cantidad,Descripcion, peso, valor, porcentaje_Descuento, alto, largo, ancho , color ,ciudadID, categoriaID );
            Boolean creado = gestionProducto.crearProducto(producto);
            producto.setIdProducto(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE TITULO = '" + producto.getTitulo() + "' AND VENDEDORID = " + producto.getVendedor().getId()).getInt("ID"));
            controladorBD.ejecutarInsert("INSERT INTO `safezone_db`.`IMAGENES` (`ProductoID`, `Base64`, `Principal`) VALUES ('" + producto.getIdProducto() + "', '" + encoded + "', b'1')");


            if(creado){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("CONFIRMACI??N");
                alert.setContentText("Estimado usuario su producto se cre?? correctamente, ");
                alert.showAndWait();

                ControladorRutas.launchMisProductos();
                Stage myStage = (Stage) this.Button_Anadir.getScene().getWindow();
                myStage.close();
            }else{
                throw new Exception();
            }
        } catch(IOException ex){
            Logger.getLogger(GuiControler.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Alg??n dato no fue ingresado correctamente. Intente de nuevo");
            alert.showAndWait();
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
    void Cancelar(ActionEvent event) throws Exception {
        ControladorRutas.launchMisProductos();
        Stage myStage = (Stage) this.Button_Cancelar.getScene().getWindow();
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
    void SubirImagenProducto(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Im??genes png",  "*.png");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(myStage);
        encoded = Base64.getEncoder().encodeToString(Files.readAllBytes(selectedFile.toPath()));
        // refresh y poner la img en pantalla...
    }

    @FXML
    void VenderProductos(ActionEvent event) throws Exception {
        // insert del producto en bd...
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControladorBD controladorBD = new ControladorBD();
        Resultset rs;
        ObservableList<String> listaCatego;
        ObservableList<String> listaPerfil = FXCollections.observableArrayList();
        ObservableList<String> listaEstados = FXCollections.observableArrayList();
        listaEstados.add("Agotado");
        listaEstados.add("Oculto");
        listaEstados.add("En venta");
        Estado.setItems(listaEstados);
        listaPerfil.add("Log out");
        listaPerfil.add("Perfil");
        Boton_Perfil.setItems(listaPerfil);
        try {
            listaCatego = controladorBD.obtenerDeptos(controladorBD.ejecutarConsulta("SELECT NOMBRE FROM CATEGORIA"));
            Boton_categorias.setItems(listaCatego);
            Categoria.setItems(listaCatego);
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
    void Favoritos(ActionEvent event) throws Exception {
        Integer usuarioId = ControladorRutas.getUsuario().getId();
        ArrayList<Producto> productosFav = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID IN (SELECT PRODUCTOID FROM PRODUCTOFAVORITO WHERE USUARIOID = "+usuarioId+")"));
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("/Favoritos", productosFav);
        ControladorRutas.launchFavoritos();
        Stage myStage = (Stage) this.Boton_favoritos.getScene().getWindow();
        myStage.close();

    }
}
