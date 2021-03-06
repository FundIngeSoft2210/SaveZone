package org.example.controllerView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Pedido;
import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionProductos.GestionProducto;

import java.util.ArrayList;

public class ControladorRutas {

    protected static Usuario usuario = null;
    protected static Producto producto = null;

    protected static Pedido pedido = null;

    protected static Producto productoComparacion = null;

    protected static ControladorBD controladorBD = new ControladorBD();


    public static Producto getProductoComparacion() {
        return productoComparacion;
    }

    public static void setProductoComparacion(Producto productoComparacion) {
        ControladorRutas.productoComparacion = productoComparacion;
    }

    public static ControladorBD getControladorBD() {
        return controladorBD;
    }

    public static void setControladorBD(ControladorBD controladorBD) {
        ControladorRutas.controladorBD = controladorBD;
    }

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

    public static Pedido getPedido() {
        return pedido;
    }

    public static void setPedido(Pedido pedido) {
        ControladorRutas.pedido = pedido;
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
        stage.setTitle("SafeZone | Acceder");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchPantallaPrincipal(Boolean categorias) throws Exception {
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Principal.fxml"));
        Parent root = loader.load();
        controllerPaginaInicio controllerPaginaInicio = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Inicio");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchPantallaPrincipal() throws Exception {
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("Principal", gestion.buscarProducto(""));

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Principal.fxml"));
        Parent root = loader.load();
        controllerPaginaInicio controllerPaginaInicio = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Inicio");
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
        stage.setTitle("SafeZone | Acceder");
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
        stage.setTitle("SafeZone | Registro");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchMisProductos() throws Exception {
        try {
            GestionProducto gestion = new GestionProducto();
            ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
            ArrayList<Producto> productos = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE VendedorID = "+ControladorRutas.getUsuario().getId()));
            controladorDespliegue.desplegarMisProductos("/MisProductos",productos , 114);

            FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../MisProductos.fxml"));
            Parent root = loader.load();
            controllerMisProductos controllerMisProductos = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
            stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
            stage.setTitle("SafeZone | Ver mis productos");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
            stage.show();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
        }
    }

    public static void launchAnadirProducto() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../anadirProducto.fxml"));
            Parent root = loader.load();
            controllerAnadirProducto controllerVender = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
            stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
            stage.setTitle("SafeZone | Vender producto");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
            stage.show();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
        }
    }

    public static void launchModificarProducto(Integer id) throws Exception{
        producto = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID = "+id)).get(0);

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../modificarProducto.fxml"));
        Parent root = loader.load();
        controllerModificarProducto controllerModificarProducto = loader.getController();
        controllerModificarProducto.setProducto(producto);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Modificar producto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

    }

    public static void launchEliminarProducto(Integer id) throws Exception{
        producto = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID = "+id)).get(0);
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../eliminarProducto.fxml"));
        Parent root = loader.load();
        controllerEliminarProducto controllerEliminarProducto = loader.getController();
        controllerEliminarProducto.setProducto(producto);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Eliminar producto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchProductosPopulares() throws Exception {
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("/ProductosPopulares", gestion.buscarProducto(""));

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../ProductosPopulares.fxml"));
        Parent root = loader.load();
        controllerPopulares controllerPopulares = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Productos populares");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchHistorial() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Historial.fxml"));
            Parent root = loader.load();
            controllerHistorial controllerHistorial = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
            stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
            stage.setTitle("SafeZone | Historiales");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
            stage.show();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
        }
    }

    public static void launchConQuePodemosAyudarte() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../ConQuePodemosAyudarte.fxml"));
            Parent root = loader.load();
            controllerAyuda controllerAyuda = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
            stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
            stage.setTitle("SafeZone | Ayuda");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
            stage.show();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
        }
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
        stage.setTitle("SafeZone | Recuperar contrase??a");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchDetallesProducto(Integer id) throws Exception {
        producto = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID = "+id)).get(0);

        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        ArrayList <Producto> productosRelacionados = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE CATEGORIAID = " + producto.getCategoria() + " AND ID != " + producto.getIdProducto()));
        controladorDespliegue.desplegarProductosComparacion("comprarProducto", productosRelacionados);

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../comprarProducto.fxml"));
        Parent root = loader.load();
        controllerProductoAComprar controllerProductoAComprar = loader.getController();
        controllerProductoAComprar.setProducto(producto);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | " + producto.getTitulo());
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchComprar() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Paso1.fxml"));
            Parent root = loader.load();
            controllerPago controllerPago = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
            stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
            stage.setTitle("SafeZone | Pago");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
            stage.show();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
        }
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
        stage.setTitle("SafeZone | Escoger metodo de pago");
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
        stage.setTitle("SafeZone | Tarjeta de cr??dito");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchPagoExitoso() throws Exception{
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Exito.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("Pago Exitoso");
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
        stage.setTitle("SafeZone | Tarjeta de cr??dito");
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
        stage.setTitle("SafeZone | Tarjeta de cr??dito");
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
        stage.setTitle("SafeZone | Pago PSE");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchHistorialCompras() throws Exception {
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarPedido("/HistorialCompras",controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT pro.ID,pro.VendedorId,ped.ID,pro.Titulo,pro.Descripcion,pro.Valor,pro.PorcentajeDescuento,pro.Peso,pro.Alto,pro.Largo,pro.Ancho,pro.Color,pro.EstadoProductoID,pro.CiudadID,pro.CategoriaID FROM PRODUCTO pro, pedido ped WHERE pro.VendedorId="+ControladorRutas.getUsuario().getId()+" AND pro.ID=ped.ProductoID")));

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../HistorialCompras.fxml"));
        Parent root = loader.load();
        controllerHistorialCompras controllerHistorialCompras = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Historial compras");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchHistorialVentas() throws Exception {
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("/HistorialVendido", controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT pro.ID,pro.VendedorId,pro.Cantidad,pro.Titulo,pro.Descripcion,pro.Valor,pro.PorcentajeDescuento,pro.Peso,pro.Alto,pro.Largo,pro.Ancho,pro.Color,pro.EstadoProductoID,pro.CiudadID,pro.CategoriaID FROM PRODUCTO pro, pedido ped WHERE pro.VendedorId="+ControladorRutas.getUsuario().getId()+" AND pro.ID=ped.ProductoID")));

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../HistorialVendido.fxml"));
        Parent root = loader.load();
        controllerHistorialVendedor controllerHistorialVendedor = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Historial de ventas");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
    public static void launchFavoritos() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Favoritos.fxml"));
            Parent root = loader.load();
            controllerFavoritos controllerFavoritos = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
            stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
            stage.setTitle("SafeZone | Tus favoritos");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();

        }
    }
    public static void launchVista_Perfil() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../perfil.fxml"));
        Parent root = loader.load();
        controllerPerfil controllerPerfil = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Perfil");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
    public static void launchPagRecomendados() throws Exception {
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("PagRecomendados", gestion.buscarProducto(""));

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../PagRecomendados.fxml"));
        Parent root = loader.load();
        controllerRecomendado controllerRecomendado = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Productos recomendados");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
    public static void launchMasVendido() throws Exception {
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();
        controladorDespliegue.desplegarProductos("pagMasVendidos", gestion.buscarProducto(""));

        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../pagMasVendidos.fxml"));
        Parent root = loader.load();
        controllerMasVendido controllerMasVendido = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Productos m??s vendidos");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
    public static void launchLlenarDatosEnvio() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Seguimiento1.fxml"));
        Parent root = loader.load();
        controllerSeguimientoLlenarDatos controllerSeguimientoLlenarDatos = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Llenar datos de env??o");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
    public static void launchEnRuta() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Seguimiento2.fxml"));
        Parent root = loader.load();
        controllerSeguimientoRastreo controllerSeguimientoRastreo = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Rastrear env??o");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
    public static void launchEntregado() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Seguimiento3.fxml"));
        Parent root = loader.load();
        controllerSeguimientoEntregado controllerSeguimientoEntregado = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Env??o entregado");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
    public static void launchCalificacion() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../calificarCompra.fxml"));
        Parent root = loader.load();
        controllerCalificarCompra controllerCalificarCompra = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Calificar compra");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
    public static void launchComparador(Integer ProductoCompararID) throws Exception {
        productoComparacion = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE ID = " + ProductoCompararID)).get(0);
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../Comparador.fxml"));
        Parent root = loader.load();
        controllerComparador ControllerComparador = loader.getController();
        ControllerComparador.setProductos(producto, productoComparacion);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Calificar compra");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchCambiarContrasena() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../cambiarContrasena.fxml"));
        Parent root = loader.load();
        controllerCambiarContrasena controllercambiarContrasena = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Cambiar contrase??a");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }

    public static void launchModeracion() throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorRutas.class.getResource("../HerramientasModeracion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(ControladorRutas.class.getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(ControladorRutas.class.getResourceAsStream("../logo.jpg")));
        stage.setTitle("SafeZone | Moderaci??n de contenido");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
    }
}
