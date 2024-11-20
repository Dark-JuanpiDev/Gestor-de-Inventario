package pe.edu.utp.Formulario;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import pe.edu.utp.Formulario.Proveedores.Proveedor;
import pe.edu.utp.Formulario.Ventas.Venta;
import pe.edu.utp.Formulario.iniciarSesion.Login;
import pe.edu.utp.utils.TextUTP;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class txtAndpdf {

    public enum TipoEvento {PEDIDO, INFO, VENTA}

    public txtAndpdf(String filename) throws Exception {
        // 1. El filemname deber ser un archivo no directorio
        File file = new File(filename);
        if (file.isDirectory()) {
            throw new Exception("El filename debe ser un archivo");
        }
        this.filename = filename;
    }

    private String filename;

    public void log(String mensaje, TipoEvento tipoEvento) throws IOException {

        //          [         strTimepo            ]  [   EVENTO    ]
        // Formato: AÑO-MES-DIA HORA:MINUTO:SEGUNDO - TIPO_EXCEPCION - MENSAJE

        // 1. Obtener el tiempo actual
        LocalDateTime tiempo = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM//dd HH:mm:ss");

        // 2. Obtenemos el tiempo y el evento convertido a cadenas
        String strTiempo = tiempo.format(fmt);
        String strEvento = tipoEvento.toString();

        // 3. Creamos el log (String)
        String log_format = "%s - %s - %n%s%n";
        String log = String.format(log_format, strTiempo, strEvento, mensaje);

        // 4. Guardamos el log en el disco
        TextUTP.append(log, this.filename);
    }

    public void logVent(String mensaje, TipoEvento tipoEvento) throws IOException {

        //          [         strTimepo            ]  [   EVENTO    ]
        // Formato: AÑO-MES-DIA HORA:MINUTO:SEGUNDO - TIPO_EXCEPCION - MENSAJE

        // 1. Obtener el tiempo actual
        LocalDateTime tiempo = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM//dd HH:mm:ss");

        // 2. Obtenemos el tiempo y el evento convertido a cadenas
        String strTiempo = tiempo.format(fmt);
        String strEvento = tipoEvento.toString();

        // 3. Creamos el log (String)
        String log_format = "%s - %s - %s%n";
        String log = String.format(log_format, strTiempo, strEvento, mensaje);

        // 4. Guardamos el log en el disco
        TextUTP.append(log, this.filename);
    }

    public static boolean GenerarPDFconClientes(DefaultTableModel modelF, String desc) throws Exception {

        String ruta = "..\\Proyecto_POO\\src\\main\\resources\\PDF\\Vendido.pdf"; // Ruta de creación

        try {
            PdfWriter writer = new PdfWriter(ruta);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            // Logo de empresa
            Image logo = new Image(ImageDataFactory.create("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_pequeño.png")); // Reemplaza con la ruta de tu imagen
            logo.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Titulo
            Paragraph title = new Paragraph();
            title.add("\nTienda de componentes de informatica\n");
            title.add("'TechGear'\n\n\n");
            title.setTextAlignment(TextAlignment.CENTER);
            title.setHorizontalAlignment(HorizontalAlignment.CENTER);
            title.setVerticalAlignment(VerticalAlignment.MIDDLE);

            // Direccion de empresa
            Paragraph direccionEmpresa = new Paragraph();
            direccionEmpresa.add("Dirección de la empresa\n");
            direccionEmpresa.add("123 Calle Norwind\n");
            direccionEmpresa.add("Ciudad Springfield, FLorida");
            direccionEmpresa.setHorizontalAlignment(HorizontalAlignment.LEFT);
            direccionEmpresa.setVerticalAlignment(VerticalAlignment.MIDDLE);
            direccionEmpresa.setTextAlignment(TextAlignment.LEFT);

            //Lema de la empresa
            Paragraph lemaEmpresa = new Paragraph();
            lemaEmpresa.add("Los mejores en calidad!");
            lemaEmpresa.setHorizontalAlignment(HorizontalAlignment.LEFT);
            lemaEmpresa.setVerticalAlignment(VerticalAlignment.MIDDLE);
            lemaEmpresa.setTextAlignment(TextAlignment.LEFT);
            lemaEmpresa.setItalic();

            //Separador
            Image separador1 = new Image(ImageDataFactory.create("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\separador.png")); // Reemplaza con la ruta de tu imagen
            separador1.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Información del cliente y vendedor
            Paragraph customerInfo = new Paragraph();
            customerInfo.add("Cliente: " + Venta.getApellidoPat() + " " + Venta.getApellidoMat() + ", " + Venta.getNombreClient() + "\n");
            customerInfo.add("DNI: " + Venta.getDni() + "\n");
            customerInfo.add("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n");
            customerInfo.add("Vendedor: " + Login.getApellidoPat() + " " + Login.getApellidoMat() + ", " + Login.getNombre());
            customerInfo.setTextAlignment(TextAlignment.LEFT);
            customerInfo.setHorizontalAlignment(HorizontalAlignment.LEFT);

            // Separador
            Image separador2 = new Image(ImageDataFactory.create("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\separador.png")); // Reemplaza con la ruta de tu imagen
            separador2.setHorizontalAlignment(HorizontalAlignment.CENTER);

            Paragraph compraResumen = new Paragraph();
            compraResumen.add("Resumen de compra: ");
            compraResumen.setTextAlignment(TextAlignment.LEFT);
            compraResumen.setHorizontalAlignment(HorizontalAlignment.LEFT);

            // Crear una tabla con 4 columnas.
            Table table = new Table(4);
            table.setWidth(UnitValue.createPercentValue(100));

            // Agregar encabezados a la tabla. Los encabezados tienen un color de fondo gris y el texto está centrado.
            table.addCell(new Cell().add(new Paragraph("Código").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph("Descripción").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph("Precio").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph("Importe").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));

            // Recorrer las filas de la tabla y agregar los datos a la cadena de productos
            for (int row = 0; row < modelF.getRowCount(); row++) {

                String codigo = modelF.getValueAt(row, 0).toString();
                String nombre = modelF.getValueAt(row, 1).toString();
                String precio = modelF.getValueAt(row, 2).toString();
                String cantidad = modelF.getValueAt(row, 3).toString();
                String proveedor = modelF.getValueAt(row, 5).toString();

                double precioProd = Double.parseDouble(precio);
                int cantidadProd = Integer.parseInt(cantidad);

                double importe = precioProd * cantidadProd;

                // Agregar la primera fila de datos a la tabla.
                table.addCell(new Cell().add(new Paragraph(codigo)));
                table.addCell(new Cell().add(new Paragraph(cantidad + " " + nombre + " " + proveedor)));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", precioProd)).setTextAlignment(TextAlignment.RIGHT)));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", importe)).setTextAlignment(TextAlignment.RIGHT)));
            }
            table.setBorder(new SolidBorder(DeviceGray.BLACK, 1));

            // sEPARADOR VACIO
            Paragraph separadorVacio1 = new Paragraph();
            separadorVacio1.add("\n");

            // Crear una tabla para datos finales
            Table table2 = new Table(2);
            table2.setWidth(UnitValue.createPercentValue(46));

            // Agregar encabezados a la tabla. Los encabezados tienen un color de fondo gris y el texto está centrado.
            table2.addCell(new Cell().add(new Paragraph("Subtotal: ").setTextAlignment(TextAlignment.LEFT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.addCell(new Cell().add(new Paragraph("S/." + String.format("%.2f", Venta.getAntesDesc())).setTextAlignment(TextAlignment.RIGHT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));

            table2.addCell(new Cell().add(new Paragraph("Descuento:").setTextAlignment(TextAlignment.LEFT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.addCell(new Cell().add(new Paragraph(Venta.clienteVipPDF(desc)).setTextAlignment(TextAlignment.RIGHT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));

            table2.addCell(new Cell().add(new Paragraph("Total:").setTextAlignment(TextAlignment.LEFT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.addCell(new Cell().add(new Paragraph("S/." + String.format("%.2f", Venta.getPrecioTotal())).setTextAlignment(TextAlignment.RIGHT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.setBorder(new SolidBorder(DeviceGray.WHITE, 1));
            table2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            table2.setVerticalAlignment(VerticalAlignment.MIDDLE);

            // sEPARADOR VACIO
            Paragraph separadorVacio2 = new Paragraph();
            separadorVacio2.add("\n\n\n\n");

            // Información de empresa
            Paragraph finalEmpresa = new Paragraph();
            finalEmpresa.add("Contactanos a:\n");
            finalEmpresa.add("901824071");
            finalEmpresa.add("Example@outlook.com");
            finalEmpresa.setTextAlignment(TextAlignment.LEFT);
            finalEmpresa.setHorizontalAlignment(HorizontalAlignment.LEFT);
            finalEmpresa.setVerticalAlignment(VerticalAlignment.BOTTOM);

            // Agregar elementos al documento
            document.add(logo);
            document.add(title);
            document.add(direccionEmpresa);
            document.add(lemaEmpresa);
            document.add(separador1);
            document.add(customerInfo);
            document.add(separador2);
            document.add(compraResumen);
            document.add(table);
            document.add(separadorVacio1);
            document.add(table2);
            document.add(separadorVacio2);
            document.add(finalEmpresa);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    public static boolean GenerarPDFsinClientes(DefaultTableModel modelF, String desc) throws Exception {

        String ruta = "..\\Proyecto_POO\\src\\main\\resources\\PDF\\Vendido.pdf"; // Reemplaza con la ruta de salida deseada

        try {
            PdfWriter writer = new PdfWriter(ruta);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            // Logo de empresa
            Image logo = new Image(ImageDataFactory.create("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_pequeño.png")); // Reemplaza con la ruta de tu imagen
            logo.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Titulo
            Paragraph title = new Paragraph();
            title.add("\nTienda de componentes de informatica\n");
            title.add("'TechGear'\n\n\n");
            title.setTextAlignment(TextAlignment.CENTER);
            title.setHorizontalAlignment(HorizontalAlignment.CENTER);
            title.setVerticalAlignment(VerticalAlignment.MIDDLE);

            // Direccion de empresa
            Paragraph direccionEmpresa = new Paragraph();
            direccionEmpresa.add("Dirección de la empresa\n");
            direccionEmpresa.add("123 Calle Norwind\n");
            direccionEmpresa.add("Ciudad Springfield, FLorida");
            direccionEmpresa.setHorizontalAlignment(HorizontalAlignment.LEFT);
            direccionEmpresa.setVerticalAlignment(VerticalAlignment.MIDDLE);
            direccionEmpresa.setTextAlignment(TextAlignment.LEFT);

            //Lema de la empresa
            Paragraph lemaEmpresa = new Paragraph();
            lemaEmpresa.add("Los mejores en calidad!");
            lemaEmpresa.setHorizontalAlignment(HorizontalAlignment.LEFT);
            lemaEmpresa.setVerticalAlignment(VerticalAlignment.MIDDLE);
            lemaEmpresa.setTextAlignment(TextAlignment.LEFT);
            lemaEmpresa.setItalic();

            //Separador
            Image separador1 = new Image(ImageDataFactory.create("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\separador.png")); // Reemplaza con la ruta de tu imagen
            separador1.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Información del cliente y vendedor
            Paragraph customerInfo = new Paragraph();
            customerInfo.add("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n");
            customerInfo.add("Vendedor: " + Login.getApellidoPat() + " " + Login.getApellidoMat() + ", " + Login.getNombre());
            customerInfo.setTextAlignment(TextAlignment.LEFT);
            customerInfo.setHorizontalAlignment(HorizontalAlignment.LEFT);

            // Separador
            Image separador2 = new Image(ImageDataFactory.create("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\separador.png")); // Reemplaza con la ruta de tu imagen
            separador2.setHorizontalAlignment(HorizontalAlignment.CENTER);

            Paragraph compraResumen = new Paragraph();
            compraResumen.add("Resumen de compra: ");
            compraResumen.setTextAlignment(TextAlignment.LEFT);
            compraResumen.setHorizontalAlignment(HorizontalAlignment.LEFT);

            // Crear una tabla con 4 columnas.
            Table table = new Table(4);
            table.setWidth(UnitValue.createPercentValue(100));

            // Agregar encabezados a la tabla. Los encabezados tienen un color de fondo gris y el texto está centrado.
            table.addCell(new Cell().add(new Paragraph("Código").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph("Descripción").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph("Precio").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph("Importe").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));

            // Recorrer las filas de la tabla y agregar los datos a la cadena de productos
            for (int row = 0; row < modelF.getRowCount(); row++) {

                String codigo = modelF.getValueAt(row, 0).toString();
                String nombre = modelF.getValueAt(row, 1).toString();
                String precio = modelF.getValueAt(row, 2).toString();
                String cantidad = modelF.getValueAt(row, 3).toString();
                String proveedor = modelF.getValueAt(row, 5).toString();

                double precioProd = Double.parseDouble(precio);
                int cantidadProd = Integer.parseInt(cantidad);

                double importe = precioProd * cantidadProd;

                // Agregar la primera fila de datos a la tabla.
                table.addCell(new Cell().add(new Paragraph(codigo)));
                table.addCell(new Cell().add(new Paragraph(cantidad + " " + nombre + " " + proveedor)));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", precioProd)).setTextAlignment(TextAlignment.RIGHT)));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", importe)).setTextAlignment(TextAlignment.RIGHT)));
            }
            table.setBorder(new SolidBorder(DeviceGray.BLACK, 1));

            // sEPARADOR VACIO
            Paragraph separadorVacio1 = new Paragraph();
            separadorVacio1.add("\n");

            // Crear una tabla para datos finales
            Table table2 = new Table(2);
            table2.setWidth(UnitValue.createPercentValue(46));

            // Agregar encabezados a la tabla. Los encabezados tienen un color de fondo gris y el texto está centrado.
            table2.addCell(new Cell().add(new Paragraph("Subtotal: ").setTextAlignment(TextAlignment.LEFT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.addCell(new Cell().add(new Paragraph("S/." + String.format("%.2f", Venta.getAntesDesc())).setTextAlignment(TextAlignment.RIGHT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));

            table2.addCell(new Cell().add(new Paragraph("Descuento:").setTextAlignment(TextAlignment.LEFT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.addCell(new Cell().add(new Paragraph(Venta.clienteVipPDF(desc)).setTextAlignment(TextAlignment.RIGHT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));

            table2.addCell(new Cell().add(new Paragraph("Total:").setTextAlignment(TextAlignment.LEFT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.addCell(new Cell().add(new Paragraph("S/." + String.format("%.2f", Venta.getPrecioTotal())).setTextAlignment(TextAlignment.RIGHT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.setBorder(new SolidBorder(DeviceGray.WHITE, 1));
            table2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            table2.setVerticalAlignment(VerticalAlignment.MIDDLE);

            // sEPARADOR VACIO
            Paragraph separadorVacio2 = new Paragraph();
            separadorVacio2.add("\n\n\n\n");

            // Información de empresa
            Paragraph finalEmpresa = new Paragraph();
            finalEmpresa.add("Contactanos a:\n");
            finalEmpresa.add("901824071");
            finalEmpresa.add("Example@outlook.com");
            finalEmpresa.setTextAlignment(TextAlignment.LEFT);
            finalEmpresa.setHorizontalAlignment(HorizontalAlignment.LEFT);
            finalEmpresa.setVerticalAlignment(VerticalAlignment.BOTTOM);

            // Agregar elementos al documento
            document.add(logo);
            document.add(title);
            document.add(direccionEmpresa);
            document.add(lemaEmpresa);
            document.add(separador1);
            document.add(customerInfo);
            document.add(separador2);
            document.add(compraResumen);
            document.add(table);
            document.add(separadorVacio1);
            document.add(table2);
            document.add(separadorVacio2);
            document.add(finalEmpresa);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    public static boolean GenerarPDFinfoProveedor(DefaultTableModel modelF,int productoC, int categoriaC, int stockC) throws Exception {

        String ruta = "..\\Proyecto_POO\\src\\main\\resources\\PDF\\Info_Proveedor.pdf"; // Ruta de creación

        try {
            PdfWriter writer = new PdfWriter(ruta);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            // Logo de empresa
            Image logo = new Image(ImageDataFactory.create("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\Logo_pequeño.png")); // Reemplaza con la ruta de tu imagen
            logo.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Titulo
            Paragraph title = new Paragraph();
            title.add("\nTienda de componentes de informatica\n");
            title.add("'TechGear'\n\n\n");
            title.setTextAlignment(TextAlignment.CENTER);
            title.setHorizontalAlignment(HorizontalAlignment.CENTER);
            title.setVerticalAlignment(VerticalAlignment.MIDDLE);

            // Direccion de empresa
            Paragraph direccionEmpresa = new Paragraph();
            direccionEmpresa.add("Información del Proveedor:\n");
            direccionEmpresa.add(Proveedor.getNombre_proveedor() + "\n");
            direccionEmpresa.setHorizontalAlignment(HorizontalAlignment.LEFT);
            direccionEmpresa.setVerticalAlignment(VerticalAlignment.MIDDLE);
            direccionEmpresa.setTextAlignment(TextAlignment.LEFT);

            //Separador
            Image separador1 = new Image(ImageDataFactory.create("..\\Proyecto_POO\\src\\main\\java\\pe\\edu\\utp\\imagenes\\separador.png")); // Reemplaza con la ruta de tu imagen
            separador1.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Crear una tabla con 4 columnas.
            Table table = new Table(3);
            table.setWidth(UnitValue.createPercentValue(100));

            // Agregar encabezados a la tabla. Los encabezados tienen un color de fondo gris y el texto está centrado.
            table.addCell(new Cell().add(new Paragraph("Producto").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph("Categoria").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph("Stock").setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER)));

            // Recorrer las filas de la tabla y agregar los datos a la cadena de productos
            for (int row = 0; row < modelF.getRowCount(); row++) {

                String producto = modelF.getValueAt(row, 0).toString();
                String categoria = modelF.getValueAt(row, 1).toString();
                String stock = modelF.getValueAt(row, 2).toString();

                int stockProd = Integer.parseInt(stock);

                // Agregar la primera fila de datos a la tabla.
                table.addCell(new Cell().add(new Paragraph(producto)));
                table.addCell(new Cell().add(new Paragraph(categoria)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(stockProd))));
            }
            table.setBorder(new SolidBorder(DeviceGray.BLACK, 1));

            // sEPARADOR VACIO
            Paragraph separadorVacio1 = new Paragraph();
            separadorVacio1.add("\n");

            // Crear una tabla para datos finales
            Table table2 = new Table(2);
            table2.setWidth(UnitValue.createPercentValue(46));

            // Agregar encabezados a la tabla. Los encabezados tienen un color de fondo gris y el texto está centrado.
            table2.addCell(new Cell().add(new Paragraph("Total Producto: ").setTextAlignment(TextAlignment.LEFT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.addCell(new Cell().add(new Paragraph(String.valueOf(productoC)).setTextAlignment(TextAlignment.RIGHT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));

            table2.addCell(new Cell().add(new Paragraph("Total Categoria:").setTextAlignment(TextAlignment.LEFT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.addCell(new Cell().add(new Paragraph(String.valueOf(categoriaC)).setTextAlignment(TextAlignment.RIGHT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));

            table2.addCell(new Cell().add(new Paragraph("Total Stock:").setTextAlignment(TextAlignment.LEFT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.addCell(new Cell().add(new Paragraph(String.valueOf(stockC)).setTextAlignment(TextAlignment.RIGHT)).setBorder(new SolidBorder(DeviceGray.WHITE, 1)));
            table2.setBorder(new SolidBorder(DeviceGray.WHITE, 1));
            table2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            table2.setVerticalAlignment(VerticalAlignment.MIDDLE);

            // sEPARADOR VACIO
            Paragraph separadorVacio2 = new Paragraph();
            separadorVacio2.add("\n\n\n\n");

            // Agregar elementos al documento
            document.add(logo);
            document.add(title);
            document.add(direccionEmpresa);
            document.add(separador1);
            document.add(table);
            document.add(separadorVacio1);
            document.add(table2);
            document.add(separadorVacio2);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }


}
