package org.example.Gestion;

import org.example.Entidades.Usuarios.Usuario;
import org.junit.jupiter.api.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestionUsuarioTest {

    private GestionUsuario gestionUsuario;
    private Usuario usuario;
    private Date fecha;

    private String nombre, apellido, nomUsuario, email, contrasena, telefono, direccion;

    int ciudadID = 1;

    @BeforeEach
    void setUp() throws ParseException {
        gestionUsuario = new GestionUsuario();
        nombre = "Juan";
        apellido = "Perez";
        nomUsuario = "juan";
        email = "s.v.alpizar@hotmail.com";
        contrasena = "12345";
        telefono = "123456789";
        direccion = "Calle falsa 123";
        fecha = new Date();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        fecha = formato.parse("2002-06-01");
        usuario = new Usuario(nombre, apellido, nomUsuario, email, contrasena, fecha, telefono, direccion, ciudadID);
    }

    @Test
    @Order(1)
    @DisplayName("Prueba para registrar un usuario")
    void crearUsuario() {
        assertTrue(gestionUsuario.crearUsuario(usuario));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(2)
    @DisplayName("Prueba para registrar un usuario repetido")
    void crearUsuarioRepetido() {
        assertFalse(gestionUsuario.crearUsuario(usuario));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Prueba para modificar un usuario")
    void modificarUsuario() {
        usuario.setCorreo("s.v.alpizar0106@gmail.com");
        assertTrue(gestionUsuario.modificarUsuario(usuario));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(4)
    @DisplayName("Prueba para eliminar un usuario")
    void eliminarUsuario() {
        assertTrue(gestionUsuario.eliminarUsuario(usuario));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(5)
    @DisplayName("Prueba para recuperar la contrasena")
    void recuperarContrasena() {
        assertTrue(gestionUsuario.recuperarContrasena("rojases"));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(6)
    @DisplayName("Prueba para no autenticar un usuario con contraseña incorrecta")
    void autenticarUsuarioIncorrecto() {
        assertEquals(null, gestionUsuario.autenticarUsuario("rojases", "12345"));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(7)
    @DisplayName("Prueba para autenticar un usuario")
    void autenticarUsuarioCorrecto() {
        assertNotNull(gestionUsuario.autenticarUsuario("rojases", "Esteban123"));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}