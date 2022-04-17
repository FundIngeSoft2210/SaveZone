package org.example.controllerView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionProductos.GestionProducto;

public class ControladorRutas {

    protected static Usuario usuario = null;
    protected static Producto producto = null;

    public static Producto getProducto() {
        return producto;
    }

    public static void setProducto(Producto producto) {
        ControladorRutas.producto = producto;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        ControladorRutas.usuario = usuario;
    }

    public static void ultimoUser (Usuario user){
        usuario = user;
    }

    public static void launchPantallaInicio() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../vista_inicio.fxml"));
        Parent root = loader.load();
        GuiControler guiControler = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Acceder");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchPantallaPrincipal() throws Exception {
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("/Principal", gestion.buscarProducto(""), 20, 114);

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Principal.fxml"));
        Parent root = loader.load();
        controllerPaginaInicio controllerPaginaInicio = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Página Inicio");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchVista_Acceder() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Vista_Acceder.fxml"));
        Parent root = loader.load();
        controllerAcceder controllerAcceder = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Acceder");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchVista_Registro() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Vista_Registro.fxml"));
        Parent root = loader.load();
        controllerRegistro controllerRegistro = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Registro");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchMisProductos() throws Exception {
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarMisProductos("/MisProductos", gestion.buscarProducto(""), 114);

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../MisProductos.fxml"));
        Parent root = loader.load();
        controllerMisProductos controllerMisProductos = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Ver mis Productos");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchAnadirProducto() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../anadirProducto.fxml"));
        Parent root = loader.load();
        controllerAnadirProducto controllerVender = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Vender Tu Producto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchModificarProducto() throws Exception{
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../modificarProducto.fxml"));
        Parent root = loader.load();
        controllerModificarProducto controllerModificarProducto = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Modificar Producto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchEliminarProducto() throws Exception{
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../eliminarProducto.fxml"));
        Parent root = loader.load();
        controllerEliminarProducto controllerEliminarProducto = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Eliminar Producto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchProductosPopulares() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../ProductosPopulares.fxml"));
        Parent root = loader.load();
        controllerPopulares controllerPopulares = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Productos Populares");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchAnadirProductos() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../anadirProducto.fxml"));
        Parent root = loader.load();
        controllerAnadirProducto controllerVender = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("VenderTuProducto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchHistorial() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Historial.fxml"));
        Parent root = loader.load();
        controllerHistorial controllerHistorial = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Historiales");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchConQuePodemosAyudarte() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../ConQuePodemosAyudarte.fxml"));
        Parent root = loader.load();
        controllerAyuda controllerAyuda = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Ayuda");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchOlvidasteContra() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Vista_olvidasteContrasena.fxml"));
        Parent root = loader.load();
        controllerRecuperarContrasena controllerRecuperarContrasena = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Recuperar Contraseña");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchDetallesProducto() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../comprarProducto.fxml"));
        Parent root = loader.load();
        controllerProductoAComprar controllerProductoAComprar = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Comprar Producto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchComprar() throws Exception{
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Paso1.fxml"));
        Parent root = loader.load();
        controllerPago controllerPago = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Pago Paso 1");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchMetodoPago() throws Exception{
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Paso2.fxml"));
        Parent root = loader.load();
        controllerEscogerMetodoPago controllerEscogerMetodoPago = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Escoger Metodo De Pago");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchMetodoCodensa() throws Exception{
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../PagoCredito.fxml"));
        Parent root = loader.load();
        controllerTarjetaCredito controllerTarjetaCredito = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Tarjeta Crédito");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchMetodoMC() throws Exception{
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../PagoCredito.fxml"));
        Parent root = loader.load();
        controllerTarjetaCredito controllerTarjetaCredito = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Tarjeta Crédito");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchMetodoVisa() throws Exception{
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../PagoCredito.fxml"));
        Parent root = loader.load();
        controllerTarjetaCredito controllerTarjetaCredito = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Tarjeta Crédito");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchMetodoPSE() throws Exception{
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../PagoPSE.fxml"));
        Parent root = loader.load();
        controllerPagoPSE controllerPagoPSE = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Pago PSE");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchHistorialCompras() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../HistorialCompras.fxml"));
        Parent root = loader.load();
        controllerHistorialCompras controllerHistorialCompras = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Historial Compras");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchHistorialVentas() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../HistorialVendido.fxml"));
        Parent root = loader.load();
        controllerHistorialVendedor controllerHistorialVendedor = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Historial Ventas");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
    public static void launchFavoritos() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Favoritos.fxml"));
        Parent root = loader.load();
        controllerFavoritos controllerFavoritos = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Tus Favoritos");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
}
