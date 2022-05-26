package org.example.controllerView;

import javafx.fxml.FXML;
import org.example.AccesoDatos.ControladorPropiedades;
import org.example.Entidades.Producto;

import java.io.*;
import java.util.ArrayList;

public class ControladorDespliegueProductos {
    public void desplegarProductosComparacion(String archivo, ArrayList<Producto> productosDesplegar) throws Exception {
        Integer x_actual = 50, y_actual = 350;
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

        if (productosDesplegar == null) {
            productosDesplegar = new ArrayList<Producto>();
        }

        for (Producto producto : productosDesplegar) {
            if (producto.getEstadoProductoID() == 6 || producto.getEstadoProductoID() == 3) continue;
            base64ToLocal(producto.getImgdata(), producto.getIdProducto() + "producto");
            etiquetaProducto = " <Button fx:id=\"" + producto.getIdProducto() + "\" alignment=\"CENTER\" contentDisplay=\"TOP\" ellipsisString=\"\" layoutX=\"" + x_actual + "\" layoutY=\"" + y_actual + "\" mnemonicParsing=\"false\" onAction=\"#CompararProducto\" prefHeight=\"131.0\" prefWidth=\"116.0\" text=\"" + producto.getTitulo() + "\" textAlignment=\"CENTER\" textOverrun=\"LEADING_WORD_ELLIPSIS\" wrapText=\"true\" styleClass=\"productos_despliegue\">\n" +
                    "                           <graphic>\n" +
                    "                              <ImageView fx:id=\"ImagenProducto\" fitHeight=\"88.0\" fitWidth=\"192.0\" pickOnBounds=\"true\" preserveRatio=\"true\">\n" +
                    "                                 <image>\n" +
                    "                                    <Image url=\"@" + producto.getIdProducto() + "producto.png\" />\n" +
                    "                                 </image>\n" +
                    "                              </ImageView>\n" +
                    "                           </graphic>\n" +
                    "                        </Button>\n";
            x_actual += 185;

            if (x_actual > 185 * 2) {
                y_actual += 160;
                x_actual = 50;
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

    public void desplegarProductos(String archivo, ArrayList<Producto> productosDesplegar) throws Exception {
        Integer x_actual = 20, y_actual = 20;
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
            if (producto.getEstadoProductoID() == 6 || producto.getEstadoProductoID() == 3) continue;
            base64ToLocal(producto.getImgdata(), producto.getIdProducto() + "producto");
            etiquetaProducto = "<children>\n" +
                    " <Button fx:id=\"" + producto.getIdProducto() + "\" alignment=\"CENTER\" contentDisplay=\"TOP\" ellipsisString=\"\" layoutX=\"" + x_actual + "\" layoutY=\"" + y_actual + "\" mnemonicParsing=\"false\" onAction=\"#VerDetallesProducto\" prefHeight=\"182.0\" prefWidth=\"182.0\" text=\"" + producto.getTitulo() + "\" textAlignment=\"CENTER\" textOverrun=\"LEADING_WORD_ELLIPSIS\" wrapText=\"true\" styleClass=\"productos_despliegue\">\n" +
                    "                           <graphic>\n" +
                    "                              <ImageView fx:id=\"ImagenProducto\" fitHeight=\"88.0\" fitWidth=\"192.0\" pickOnBounds=\"true\" preserveRatio=\"true\">\n" +
                    "                                 <image>\n" +
                    "                                    <Image url=\"@" + producto.getIdProducto() + "producto.png\" />\n" +
                    "                                 </image>\n" +
                    "                              </ImageView>\n" +
                    "                           </graphic>\n" +
                    "                        </Button>\n" +
                    "                     </children>";


            /**etiquetaProducto = " <AnchorPane layoutX=\"" + x_actual + "\" layoutY=\"" + y_actual + "\" prefHeight=\"201.0\" prefWidth=\"222.0\" style=\"-fx-background-color: white;\">\n" +
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
                    "                  <Label layoutX=\"63.0\" layoutY=\"153.0\" text=\"" + producto.getTitulo() + "\" textAlignment=\"CENTER\"/>\n" +
                    "               </children>\n" +
                    "            </AnchorPane>";
            */
            x_actual += 235;

            if (x_actual > 235 * 4) {
                y_actual += 200;
                x_actual = 20;
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

    public void desplegarPedido (String archivo, ArrayList<Producto> productosDesplegar) throws Exception {
        Integer x_actual = 20, y_actual = 20;
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
            System.out.println("pedido: " + producto.getCantidad());
            if (producto.getEstadoProductoID() == 6 || producto.getEstadoProductoID() == 3) continue;
            base64ToLocal(producto.getImgdata(), producto.getIdProducto() + "producto");
            etiquetaProducto = "<children>\n" +
                    " <Button fx:id=\"" + producto.getCantidad() + "\" alignment=\"CENTER\" contentDisplay=\"TOP\" ellipsisString=\"\" layoutX=\"" + x_actual + "\" layoutY=\"" + y_actual + "\" mnemonicParsing=\"false\" onAction=\"#VerDetallesProducto\" prefHeight=\"182.0\" prefWidth=\"182.0\" text=\"" + producto.getTitulo() + "\" textAlignment=\"CENTER\" textOverrun=\"LEADING_WORD_ELLIPSIS\" wrapText=\"true\" styleClass=\"productos_despliegue\">\n" +
                    "                           <graphic>\n" +
                    "                              <ImageView fx:id=\"ImagenProducto\" fitHeight=\"88.0\" fitWidth=\"192.0\" pickOnBounds=\"true\" preserveRatio=\"true\">\n" +
                    "                                 <image>\n" +
                    "                                    <Image url=\"@" + producto.getIdProducto() + "producto.png\" />\n" +
                    "                                 </image>\n" +
                    "                              </ImageView>\n" +
                    "                           </graphic>\n" +
                    "                        </Button>\n" +
                    "                     </children>";


            /**etiquetaProducto = " <AnchorPane layoutX=\"" + x_actual + "\" layoutY=\"" + y_actual + "\" prefHeight=\"201.0\" prefWidth=\"222.0\" style=\"-fx-background-color: white;\">\n" +
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
                    "                  <Label layoutX=\"63.0\" layoutY=\"153.0\" text=\"" + producto.getTitulo() + "\" textAlignment=\"CENTER\"/>\n" +
                    "               </children>\n" +
                    "            </AnchorPane>";
            */
            x_actual += 235;

            if (x_actual > 235 * 4) {
                y_actual += 200;
                x_actual = 20;
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
        y_inicial = 0;
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
            if (producto.getEstadoProductoID() == 6 || producto.getEstadoProductoID() == 3) continue;
            base64ToLocal(producto.getImgdata(), producto.getIdProducto() + "producto");

            etiquetaProducto = "<children>\n" +
                    "                  <Button fx:id=\"" + producto.getIdProducto() + "\" alignment=\"CENTER\" contentDisplay=\"TOP\" ellipsisString=\"\" graphicTextGap=\"10.0\" layoutX=\"73.0\" layoutY=\"" + y_actual + "11.0\" mnemonicParsing=\"false\" onAction=\"#VerDetallesProducto\" prefHeight=\"80.0\" prefWidth=\"116.0\" style=\"-fx-background-color: white;\" text=\"" + producto.getTitulo() + "\">\n" +
                    "                           <graphic>\n" +
                    "                              <ImageView fx:id=\"ImagenProducto\" fitHeight=\"50.0\" fitWidth=\"106.0\" nodeOrientation=\"INHERIT\" pickOnBounds=\"true\" preserveRatio=\"true\">\n" +
                    "                                 <image>\n" +
                    "                              <Image url=\"@" + producto.getIdProducto() + "producto.png\" />\n" +
                    "                                 </image>\n" +
                    "                              </ImageView>\n" +
                    "                           </graphic>\n" +
                    "                        </Button>\n" +
                    "                  <Button fx:id=\"" + producto.getIdProducto() + "\" layoutX=\"212.0\" layoutY=\"" + y_actual + 23 + "\" mnemonicParsing=\"false\" onAction=\"#ModificarProducto\" style=\"-fx-background-color: #3fdfd4;\" text=\"Modificar\" />\n" +
                    "                  <Button fx:id=\"" + producto.getIdProducto() + "\" layoutX=\"212.0\" layoutY=\"" + y_actual + 54 + "\" mnemonicParsing=\"false\" onAction=\"#EliminarProducto\" prefHeight=\"25.0\" prefWidth=\"67.0\" style=\"-fx-background-color: #3fdfd4;\" text=\"Eliminar\" />\n" +
                    "                     </children>\n" +
                    "                     ";

            /**etiquetaProducto = "<AnchorPane layoutX=\"311.0\" layoutY=\"" + y_actual + "\" prefHeight=\"102.0\" prefWidth=\"351.0\" style=\"-fx-background-color: white;\">\n" +
                    "               <children>\n" +
                    "                  <Button fx:id=\"" + producto.getIdProducto() + "\" layoutX=\"2.0\" layoutY=\"9.0\" mnemonicParsing=\"false\" onAction=\"#VerDetallesProducto\" prefHeight=\"62.0\" prefWidth=\"328.0\" style=\"-fx-background-color: white;\" />\n" +
                    "                  <Label fx:id=\"NombreProducto\" layoutX=\"132.0\" layoutY=\"31.0\" text=\"" + producto.getTitulo() + "\" />\n" +
                    "                  <ImageView fx:id=\"ImagenProducto\" fitHeight=\"50.0\" fitWidth=\"106.0\" layoutX=\"14.0\" layoutY=\"22.0\" pickOnBounds=\"true\" preserveRatio=\"true\">\n" +
                    "                     <image>\n" +
                    "                              <Image url=\"@" + producto.getIdProducto() + "producto.png\" />\n" +
                    "                     </image>\n" +
                    "                  </ImageView>\n" +
                    "                  <Button fx:id=\"" + producto.getIdProducto() + "\" layoutX=\"133.0\" layoutY=\"64.0\" mnemonicParsing=\"false\" onAction=\"#ModificarProducto\" style=\"-fx-background-color: #3fdfd4;\" text=\"Modificar\" />\n" +
                    "                  <Button fx:id=\"" + producto.getIdProducto() + "\" layoutX=\"226.0\" layoutY=\"64.0\" mnemonicParsing=\"false\" onAction=\"#EliminarProducto\" style=\"-fx-background-color: #3fdfd4;\" text=\"Eliminar\" />\n" +
                    "               </children>\n" +
                    "            </AnchorPane>"; */

            y_actual += 1;
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

    public void base64ToLocal(byte[] base64Img, String nombre) throws IOException {
        ControladorPropiedades controladorPropiedades = new ControladorPropiedades();
        OutputStream stream = new FileOutputStream(controladorPropiedades.getPropiedad("resourcesPath") + "/" + nombre + ".png");
        stream.write(base64Img);
        stream.close();
    }


}
