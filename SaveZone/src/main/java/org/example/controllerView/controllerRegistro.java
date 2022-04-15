package org.example.controllerView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.Entidades.Usuarios.Usuario;
import org.example.Gestion.GestionUsuario;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.*;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class controllerRegistro {
    @FXML
    private TextField Apellido;

    @FXML
    private Button Button_Cancelar;

    @FXML
    private Button Button_Registrarse;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField nombreUser;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField correoCuenta;

    @FXML
    private TextField direccionCuenta;

    @FXML
    private DatePicker fechaDeNacimientoCuenta;

    @FXML
    private TextField telefonoCuenta;
    @FXML
    void Cancelar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista_inicio.fxml"));
        Parent root = loader.load();
        GuiControler guiControler = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
        stage.setTitle("Acceder");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();

        Stage myStage = (Stage) this.Button_Cancelar.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void Registro(ActionEvent event)  throws IOException {
        GestionUsuario gestionUsuario = new GestionUsuario();
        try {
            String nombre, apellidos, correo, nombreUsuario, telefono, direccion, contrasena;
            LocalDate fechaNac;

            nombre = this.Nombre.getText();
            if (nombre == null || nombre.isEmpty()){
                throw new Exception("name");
            }

            nombreUsuario = this.nombreUser.getText();
            if (nombreUsuario == null || nombreUsuario.isEmpty()){
                throw new Exception("user");
            }

            Double num;
            telefono = this.telefonoCuenta.getText();
            if (telefono != null && !telefono.isEmpty() && (telefono.length() == 10 || telefono.length() == 7)){
                num = Double.parseDouble(telefono);
            }

            direccion = this.direccionCuenta.getText();
            if (direccion == null || direccion.isEmpty()){
                throw new Exception("dir");
            }

            apellidos = this.Apellido.getText();
            if (apellidos == null || apellidos.isEmpty()){
                throw new Exception("apellidos");
            }

            correo = this.correoCuenta.getText();
            Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$");
            Matcher matcher = pattern.matcher(correo);
            if (correo == null || correo.isEmpty() || matcher.find() == false){
                throw new Exception("correo");
            }

            contrasena = this.contraseña.getText();
            if (contrasena == null || contrasena.isEmpty()){
                throw new Exception("contra");
            }

            fechaNac = this.fechaDeNacimientoCuenta.getValue();
            Instant instant = Instant.from(fechaNac.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date = Date.from(instant);
            Usuario user = new Usuario(nombre, apellidos, nombreUsuario, contrasena, correo, date,
                    telefono, direccion, 1);
            Boolean creado = gestionUsuario.crearUsuario(user);

            if (creado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("CONFIRMACIÓN");
                alert.setContentText("Estimado cliente su registro se realizó correctamente, ");
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista_inicio.fxml"));
                Parent root = loader.load();
                GuiControler guiControler = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
                stage.getIcons().add(new Image(getClass().getResourceAsStream("../logo.jpg")));
                stage.setTitle("Acceder");
                stage.setScene(scene);
                stage.setMaximized(false);
                stage.setResizable(false);
                stage.show();
                stage.show();

                Stage myStage = (Stage) this.Button_Cancelar.getScene().getWindow();
                myStage.close();
            } else {
                throw new Exception();
            }
        } catch (IOException ex) {
            Logger.getLogger(GuiControler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El numero ingresado no es válido");
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Algún dato no fue ingresado correctamente o su número/correo ya está registrado en el sistema. Intente de nuevo");
            alert.showAndWait();
        }
    }
}

