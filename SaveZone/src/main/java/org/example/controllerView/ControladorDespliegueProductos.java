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
            etiquetaProducto = " <AnchorPane layoutX=\"" + x_actual + "\" layoutY=\"" + y_actual + "\" prefHeight=\"201.0\" prefWidth=\"222.0\" style=\"-fx-background-color: white;\">\n" +
                    "               <children>\n" +
                    "                  <Button fx:id=\"Producto\" layoutX=\"3.0\" layoutY=\"8.0\" mnemonicParsing=\"false\" onAction=\"#VerDetallesProducto\" prefHeight=\"182.0\" prefWidth=\"215.0\" style=\"-fx-background-color: white;\">\n" +
                    "                     <graphic>\n" +
                    "                        <ImageView fx:id=\"ImagenProducto\" fitHeight=\"88.0\" fitWidth=\"192.0\" pickOnBounds=\"true\" preserveRatio=\"true\">\n" +
                    "                           <image>\n" +
                    "                              <Image url=\"@1.PNG\" />\n" +
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
}
