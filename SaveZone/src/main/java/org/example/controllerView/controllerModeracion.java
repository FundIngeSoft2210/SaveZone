package org.example.controllerView;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.example.AccesoDatos.ControladorBD;
import org.example.Entidades.Producto;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionProductos.GestionProducto;
import org.example.Gestion.GestionUsuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class controllerModeracion implements Initializable {

    @FXML
    private Button boton_moderar;

    @FXML
    private ComboBox<String> publicaciones;

    @FXML
    private ComboBox<String> usuarios;

    @FXML
    private ComboBox<String> dar_adm;

    @FXML
    void moderar(ActionEvent event) throws SQLException, ClassNotFoundException {
        GestionUsuario gestionUsuario = new GestionUsuario();
        GestionProducto gestionProducto = new GestionProducto();
        ControladorBD controladorBD = new ControladorBD();

        if (usuarios.getSelectionModel().getSelectedItem() != null) {
            gestionUsuario.eliminarUsuario(controladorBD.obtenerUsuariosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM USUARIO WHERE USUARIO = '" + usuarios.getSelectionModel().getSelectedItem() + "'")).get(0));
            System.out.println("[!] Moderador " + ControladorRutas.getUsuario().getUsuario() + " eliminó al usuario " + usuarios.getSelectionModel().getSelectedItem());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("SafeZone | Informe de moderación");
            alert.setContentText("Eliminó al usuario " + usuarios.getSelectionModel().getSelectedItem() + " correctamente.");
            alert.showAndWait();
        }

        if (publicaciones.getSelectionModel().getSelectedItem() != null) {
            Producto productoModerar = controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO WHERE TITULO = '" + publicaciones.getSelectionModel().getSelectedItem() + "'")).get(0);
            productoModerar.setEstadoProductoID(3);
            gestionProducto.modificarProducto(productoModerar, productoModerar.getTitulo());
            System.out.println("[!] Moderador " + ControladorRutas.getUsuario().getUsuario() + " eliminó el producto " + productoModerar.getTitulo());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("SafeZone | Informe de moderación");
            alert.setContentText("Eliminó el producto " + productoModerar.getTitulo() + " correctamente.");
            alert.showAndWait();
        }

        if (dar_adm.getSelectionModel().getSelectedItem() != null) {
            Usuario usuarioOtorgarAdm = controladorBD.obtenerUsuariosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM USUARIO WHERE USUARIO = '" + usuarios.getSelectionModel().getSelectedItem() + "'")).get(0);
            gestionUsuario.otorgarAdministrador(usuarioOtorgarAdm);
            System.out.println("[!] Moderador " + ControladorRutas.getUsuario().getUsuario() + " otorgó permisos de administrador al usuario " + usuarios.getSelectionModel().getSelectedItem());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("SafeZone | Informe de moderación");
            alert.setContentText("Otorgó permisos de administrador al usuario " + usuarios.getSelectionModel().getSelectedItem() + " correctamente.");
            alert.showAndWait();
        }
    }

    @FXML
    void volverInicio(ActionEvent event) throws Exception {
        ControladorRutas.launchPantallaPrincipal();
        Stage myStage = (Stage) this.boton_moderar.getScene().getWindow();
        myStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControladorBD controladorBD = new ControladorBD();
        ObservableList<String> listaUsuarios = FXCollections.observableArrayList();
        ObservableList<String> listaProductos = FXCollections.observableArrayList();
        try {
            for (Usuario usuario : controladorBD.obtenerUsuariosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM USUARIO WHERE TIPOUSUARIOID = 1")))
                listaUsuarios.add(usuario.getUsuario());

            for (Producto producto : controladorBD.obtenerProductosConsulta(controladorBD.ejecutarConsulta("SELECT * FROM PRODUCTO")))
                listaProductos.add(producto.getTitulo());

            usuarios.setItems(listaUsuarios);
            publicaciones.setItems(listaProductos);
            dar_adm.setItems(listaUsuarios);
        } catch (SQLException e) {
            System.out.println("[Error SQL en la sentencia " + e.getSQLState() + "] " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
