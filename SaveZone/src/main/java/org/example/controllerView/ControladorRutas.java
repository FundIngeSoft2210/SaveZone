package org.example.controllerView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.Gestion.GestionProductos.GestionProducto;

public class ControladorRutas {
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

    public static void launchProductosPopulares () throws Exception {
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
}
