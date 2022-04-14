package org.example.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.Entidades.Producto;
import org.example.Gestion.GestionProductos.GestionProducto;

import java.io.IOException;
import java.util.ArrayList;

public class controllerPaginaInicio {

    @FXML
    private Button Boton_Ayuda;

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
    private Button Button_uscar;

    @FXML
    private Button RegresarAlInicio;

    @FXML
    private ImageView ImagenProducto;

    @FXML
    private TextField Nombre1;

    @FXML
    private Button Producto;

    @FXML
    void Ayuda(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ConQuePodemosAyudarte.fxml"));
        Parent root = loader.load();
        controllerAyuda controllerAyuda = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Ayuda");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_Ayuda.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void Buscar(ActionEvent event) throws IOException{

    }

    @FXML
    void BuscarProductos(ActionEvent event) throws IOException{

    }

    @FXML
    void Categorias(ActionEvent event) throws IOException{

    }

    @FXML
    void Historial(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Historial.fxml"));
        Parent root = loader.load();
        controllerHistorial controllerHistorial = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Historiales");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_Historial.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void Populares(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ProductosPopulares.fxml"));
        Parent root = loader.load();
        controllerPopulares controllerPopulares = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Productos Populares");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Productos(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MisProductos.fxml"));
        Parent root = loader.load();
        controllerMisProductos controllerMisProductos = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Ver mis Productos");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_VerMisProductos.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void VenderProductos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../anadirProducto.fxml"));
        Parent root = loader.load();
        controllerAnadirProducto controllerVender = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("VenderTuProducto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Boton_vender.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void VerDetallesProducto(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../comprarProducto.fxml"));
        Parent root = loader.load();
        controllerProductoAComprar controllerProductoAComprar = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Comprar Producto");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Producto.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void VolverInicio(ActionEvent event) throws IOException {
        // Ejemplo de despliegue.
        GestionProducto gestion = new GestionProducto();
        ControladorDespliegueProductos controladorDespliegue = new ControladorDespliegueProductos();

        System.out.println("Iniciado.");
        ArrayList<org.example.Entidades.Producto> desplegar = new ArrayList<>();
        desplegar = gestion.buscarProducto("airpods");
        System.out.println("[!] Llamada al armado del FXML sobre pantalla principal.");
        try {
            controladorDespliegue.desplegarProductos("/Principal", desplegar, 20, 114);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Principal.fxml"));
        Parent root = loader.load();
        controllerPaginaInicio controllerPaginaInicio = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Página Inicio");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.RegresarAlInicio.getScene().getWindow();
        myStage.close();

    }

}

