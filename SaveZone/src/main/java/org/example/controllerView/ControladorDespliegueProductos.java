package org.example.controllerView;

import javafx.fxml.FXML;
import org.example.AccesoDatos.ControladorPropiedades;
import org.example.Entidades.Producto;

import java.io.*;
import java.util.ArrayList;

public class ControladorDespliegueProductos {
    public void desplegarProductos(String archivo, ArrayList<Producto> productosDesplegar, Integer x_inicial, Integer y_inicial) throws IOException {
        System.out.println("");
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        String resourcesPath = controladorPropiedades.getPropiedad("resourcesPath");
        String path = resourcesPath + archivo + ".fxml", pathCopia = resourcesPath + archivo + "Buffer" + ".fxml";
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

        // Etiqueta quemada del producto XML.

        for (Producto producto : productosDesplegar) {
            String etiquetaProducto = " <AnchorPane layoutX=\"" + x_inicial + "\" layoutY=\"" + y_inicial + "\" prefHeight=\"201.0\" prefWidth=\"222.0\" style=\"-fx-background-color: white;\">\n" +
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
            x_inicial += 250;
            bw.write(etiquetaProducto);
            System.out.println("Producto desplegado.");
        }

        bw.write("\n<!--DESPL-->\n"); // Indicador de despliegue

        while ((fileLine = in.readLine()) != null) {
            bw.write(fileLine + "\n");
        }

        in.close();
        bw.close();

        copiarBufferOriginal(file.getAbsolutePath(), fileCopia.getAbsolutePath());
    }

    private void copiarBufferOriginal (String pathOriginal, String pathBuffer) throws IOException {
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
    }
}
