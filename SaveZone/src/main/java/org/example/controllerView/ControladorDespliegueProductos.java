package org.example.controllerView;

import javafx.fxml.FXML;
import org.example.AccesoDatos.ControladorPropiedades;
import org.example.Entidades.Producto;

import java.io.*;
import java.util.ArrayList;

public class ControladorDespliegueProductos {
    public void desplegarProductos(String archivo, ArrayList<Producto> productosDesplegar, Integer x_inicial, Integer y_inicial) throws Exception {
        Integer x_actual = x_inicial, y_actual = y_inicial;
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        String resourcesPath = controladorPropiedades.getPropiedad("resourcesPath");
        String path = resourcesPath + archivo + ".fxml", pathCopia = resourcesPath + archivo + "Buffer" + ".fxml", etiquetaProducto = null;
        System.out.println("[!] Llamada al armado del FXML sobre " + path);

        File file = new File (path);
        File fileCopia = new File (pathCopia);

        if (!file.exists())
            throw new FileNotFoundException("[!] No se pudo encontrar el archivo " + archivo + ".fxml en la ruta especificada (despliegueProductos).");

        BufferedReader in = new BufferedReader(new FileReader (file.getAbsoluteFile()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileCopia.getAbsoluteFile(), false));

        String fileLine = in.readLine();

        while (!fileLine.contains("<!--DESPL-->")) {
            bw.write(fileLine + "\n");
            fileLine = in.readLine();
            if (fileLine == null)
                throw new FileNotFoundException("[!] No se pudo encontrar el indicador de despliegue en el archivo " + archivo + ".fxml en la ruta especificada (despliegueProductos).");
        }

        bw.write("\n<!--DESPL-->\n");

        // Etiqueta quemada del producto XML.

        for (Producto producto : productosDesplegar) {
            base64ToLocal(producto.getImgdata(), producto.getIdProducto() + "producto");
            etiquetaProducto = " <AnchorPane layoutX=\"" + x_actual + "\" layoutY=\"" + y_actual + "\" prefHeight=\"201.0\" prefWidth=\"222.0\" style=\"-fx-background-color: white;\">\n" +
                    "               <children>\n" +
                    "                  <Button fx:id=\"" + producto.getIdProducto() + "\" layoutX=\"3.0\" layoutY=\"8.0\" mnemonicParsing=\"false\" onAction=\"#VerDetallesProducto\" prefHeight=\"182.0\" prefWidth=\"215.0\" style=\"-fx-background-color: white;\">\n" +
                    "                     <graphic>\n" +
                    "                        <ImageView fx:id=\"ImagenProducto\" fitHeight=\"88.0\" fitWidth=\"192.0\" pickOnBounds=\"true\" preserveRatio=\"true\">\n" +
                    "                           <image>\n" +
                    "                              <Image url=\"@" + producto.getIdProducto() + "producto.png\" />\n" +
                    "                           </image>\n" +
                    "                        </ImageView>\n" +
                    "                     </graphic>\n" +
                    "                  </Button>\n" +
                    "                  <Label layoutX=\"56.0\" layoutY=\"153.0\" text=\"" + producto.getTitulo() + "\" />\n" +
                    "               </children>\n" +
                    "            </AnchorPane>";

            x_actual += 235;

            if (x_actual > 235 * 3) {
                y_actual += 210;
                x_actual = x_inicial;
            }

            bw.write(etiquetaProducto);
        }

        bw.write("\n<!--DESPL_FINAL-->\n"); // Indicador de despliegue

        while (!in.readLine().contains("<!--DESPL_FINAL-->"));

        while ((fileLine = in.readLine()) != null) {
            bw.write(fileLine + "\n");
        }

        in.close();
        bw.close();

        copiarBufferOriginal(file.getAbsolutePath(), fileCopia.getAbsolutePath());
    }

    private void copiarBufferOriginal (String pathOriginal, String pathBuffer) throws Exception {
        File file = new File (pathOriginal);
        File fileBuffer = new File (pathBuffer);

        String fileLine;

        BufferedReader in = new BufferedReader(new FileReader (fileBuffer.getAbsoluteFile()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), false));

        while ((fileLine = in.readLine()) != null) {
            bw.write(fileLine + "\n");
        }

        in.close();
        bw.close();

        if (!fileBuffer.delete())
            throw new Exception("[!] Error al eliminar el buffer del FXML.");
    }

    public void desplegarMisProductos(String archivo, ArrayList<Producto> productosDesplegar, Integer y_inicial) throws Exception {
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        Integer y_actual = y_inicial;

        String resourcesPath = controladorPropiedades.getPropiedad("resourcesPath");
        String path = resourcesPath + archivo + ".fxml", pathCopia = resourcesPath + archivo + "Buffer" + ".fxml", etiquetaProducto = null;
        System.out.println("[!] Llamada al armado del FXML sobre " + path);


        File file = new File (path);
        File fileCopia = new File (pathCopia);

        if (!file.exists())
            throw new FileNotFoundException("[!] No se pudo encontrar el archivo " + archivo + ".fxml en la ruta especificada (despliegueProductos).");

        BufferedReader in = new BufferedReader(new FileReader (file.getAbsoluteFile()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileCopia.getAbsoluteFile(), false));

        String fileLine = in.readLine();

        while (!fileLine.contains("<!--DESPL-->")) {
            bw.write(fileLine + "\n");
            fileLine = in.readLine();
            if (fileLine == null)
                throw new FileNotFoundException("[!] No se pudo encontrar el indicador de despliegue en el archivo " + archivo + ".fxml en la ruta especificada (despliegueProductos).");
        }

        bw.write("\n<!--DESPL-->\n");

        // Etiqueta quemada del producto XML.

        for (Producto producto : productosDesplegar) {
            base64ToLocal(producto.getImgdata(), producto.getIdProducto() + "producto");
            etiquetaProducto = "<AnchorPane layoutX=\"311.0\" layoutY=\"" + y_actual + "\" prefHeight=\"102.0\" prefWidth=\"351.0\" style=\"-fx-background-color: white;\">\n" +
                    "               <children>\n" +
                    "                  <Button fx:id=\"" + producto.getIdProducto() + "\" layoutX=\"2.0\" layoutY=\"9.0\" mnemonicParsing=\"false\" onAction=\"#VerDetallesProducto\" prefHeight=\"62.0\" prefWidth=\"328.0\" style=\"-fx-background-color: white;\" />\n" +
                    "                  <Label fx:id=\"NombreProducto\" layoutX=\"132.0\" layoutY=\"31.0\" text=\"" + producto.getTitulo() + "\" />\n" +
                    "                  <ImageView fx:id=\"ImagenProducto\" fitHeight=\"50.0\" fitWidth=\"106.0\" layoutX=\"14.0\" layoutY=\"22.0\" pickOnBounds=\"true\" preserveRatio=\"true\">\n" +
                    "                     <image>\n" +
                    "                              <Image url=\"@" + producto.getIdProducto() + "producto.png\" />\n" +
                    "                     </image>\n" +
                    "                  </ImageView>\n" +
                    "                  <Button fx:id=\"Button_ModificarProducto\" layoutX=\"133.0\" layoutY=\"64.0\" mnemonicParsing=\"false\" onAction=\"#ModificarProducto\" style=\"-fx-background-color: #3fdfd4;\" text=\"Modificar\" />\n" +
                    "                  <Button fx:id=\"Boton_eliminarProducto\" layoutX=\"226.0\" layoutY=\"64.0\" mnemonicParsing=\"false\" onAction=\"#EliminarProducto\" style=\"-fx-background-color: #3fdfd4;\" text=\"Eliminar\" />\n" +
                    "               </children>\n" +
                    "            </AnchorPane>";

            y_actual += 115;
            bw.write(etiquetaProducto);
        }

        bw.write("\n<!--DESPL_FINAL-->\n"); // Indicador de despliegue

        while (!in.readLine().contains("<!--DESPL_FINAL-->"));

        while ((fileLine = in.readLine()) != null) {
            bw.write(fileLine + "\n");
        }

        in.close();
        bw.close();

        copiarBufferOriginal(file.getAbsolutePath(), fileCopia.getAbsolutePath());
    }

    private static void base64ToLocal(byte[] base64Img, String nombre) throws IOException {
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        OutputStream stream = new FileOutputStream(controladorPropiedades.getPropiedad("resourcesPath") + "/" + nombre + ".png");
        stream.write(base64Img);
        stream.close();
    }


}
