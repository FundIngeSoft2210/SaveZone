package org.example.controllerView;

import javafx.fxml.FXML;
import org.example.Entidades.Producto;

import java.io.*;
import java.util.ArrayList;

public class ControladorDespliegueProductos {
    public void desplegarProductos(String archivo, ArrayList<Producto> productosDesplegar, Integer x_inicial, Integer y_inicial) throws IOException {
        String path = "src/main/resources/org/example/" + archivo;
        File file = new File (path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));

        // Etiqueta quemada del producto XML.

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
                "                  <Label layoutX=\"56.0\" layoutY=\"153.0\" text=\" Cámara Samsung\" />\n" +
                "               </children>\n" +
                "            </AnchorPane>";
        System.out.println(etiquetaProducto);
        if (!file.exists())
            throw new FileNotFoundException("[!] No se pudo encontrar el archivo " + archivo + " en la ruta especificada (despliegueProductos).");
        for (Producto producto : productosDesplegar) {
            // Código repetir FXML

        }
    }
}
