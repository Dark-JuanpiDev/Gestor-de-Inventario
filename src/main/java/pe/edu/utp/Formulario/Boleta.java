package pe.edu.utp.Formulario;

import pe.edu.utp.Formulario.PrincipalUser.Compra;
import pe.edu.utp.Formulario.Ventas.Venta;
import pe.edu.utp.Formulario.iniciarSesion.Login;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Boleta {

    public static boolean GenerarBoletaconClientes(DefaultTableModel modelF, String desc) throws Exception {

        String header = """
                -------------------------------------------------------
                ------------------- Boleta de Compra ------------------
                -------------------------------------------------------
                - Cliente: %s %s, %s
                - DNI: %s
                - Fecha: %s
                -
                - Descuento: %s
                -------------------------------------------------------
                - Detalles de Compra:
                - 
                - Codigo   Nombre                Cantidad      Precio
                """;

        String data = """
                - %-7s  %s %-13s   %-8s      %-6.2f
                """;

        // Formatea el valor con un ancho mínimo de 7 caracteres
        String footer = """
                - 
                -------------------------------------------------------
                - Total a pagar: S/. %.2f
                -
                - Vendedor: %s %s, %s
                -------------------------------------------------------
                -
                -                 GRACIAS POR SU COMPRA!
                -
                -------------------------------------------------------
                """;

        String cabecera = String.format(header, Venta.getApellidoPat(), Venta.getApellidoMat(), Venta.getNombreClient(),
                Venta.getDni(), obtenerFechaActual(), Venta.clienteVipB(desc));

        // Inicializar una cadena para almacenar todos los productos
        StringBuilder items = new StringBuilder();

        // Recorrer las filas de la tabla y agregar los datos a la cadena de productos
        for (int row = 0; row < modelF.getRowCount(); row++) {
            String codigo = modelF.getValueAt(row, 0).toString();
            String nombre = modelF.getValueAt(row, 1).toString();
            String precio = modelF.getValueAt(row, 2).toString();
            String cantidad = modelF.getValueAt(row, 3).toString();
            String proveedor = modelF.getValueAt(row, 5).toString();

            double precioProd = Double.parseDouble(precio);
            Venta.setPrecio(precioProd);

            String item = String.format(data, codigo, nombre, proveedor, cantidad, Venta.getPrecio());
            items.append(item);
        }

        String item2 = String.format(footer, Venta.getPrecioTotal(), Login.getApellidoPat(), Login.getApellidoMat(),
                Login.getNombre());


        String menu = cabecera + items.toString() + item2;

        String file = "..\\Proyecto_POO\\src\\main\\resources\\TXT\\Boleta.txt";
        txtAndpdf errorlog = new txtAndpdf(file);
        String mensaje = menu;
        errorlog.log(mensaje, txtAndpdf.TipoEvento.VENTA);

        System.out.println(menu);

        return true;
    }

    public static boolean GenerarBoletasinbClientes(DefaultTableModel modelF, String desc) throws Exception {

        String header = """
                -------------------------------------------------------
                ------------------- Boleta de Compra ------------------
                -------------------------------------------------------
                - Fecha: %s
                -
                - Descuento: %s
                -------------------------------------------------------
                - Detalles de Compra:
                - 
                - Codigo   Nombre                Cantidad      Precio
                """;

        String data = """
                - %-7s  %s %-13s  %-8s      %-6.2f
                """;

        // Formatea el valor con un ancho mínimo de 7 caracteres
        String footer = """
                - 
                -------------------------------------------------------
                - Total a pagar: S/. %.2f
                -
                - Vendedor: %s %s, %s
                -------------------------------------------------------
                -
                -                 GRACIAS POR SU COMPRA!
                -
                -------------------------------------------------------
                
                """;

        String cabecera = String.format(header, obtenerFechaActual(), Venta.clienteVipB(desc));

        // Inicializar una cadena para almacenar todos los productos
        StringBuilder items = new StringBuilder();

        // Recorrer las filas de la tabla y agregar los datos a la cadena de productos
        for (int row = 0; row < modelF.getRowCount(); row++) {
            String codigo = modelF.getValueAt(row, 0).toString();
            String nombre = modelF.getValueAt(row, 1).toString();
            String precio = modelF.getValueAt(row, 2).toString();
            String cantidad = modelF.getValueAt(row, 3).toString();
            String proveedor = modelF.getValueAt(row, 5).toString();

            double precioProd = Double.parseDouble(precio);
            Venta.setPrecio(precioProd);

            String item = String.format(data, codigo, nombre, proveedor, cantidad, Venta.getPrecio());
            items.append(item);
        }

        String item2 = String.format(footer, Venta.getPrecioTotal(), Login.getApellidoPat(), Login.getApellidoMat(),
                Login.getNombre());


        String menu = cabecera + items.toString() + item2;

        String file = "..\\Proyecto_POO\\src\\main\\resources\\TXT\\Boleta.txt";
        txtAndpdf errorlog = new txtAndpdf(file);
        String mensaje = menu;
        errorlog.log(mensaje, txtAndpdf.TipoEvento.VENTA);

        System.out.println(menu);

        return true;
    }

    private static String obtenerFechaActual() {
        // La fecha actual con SimpleDateFormat para formatear la fecha y obtenerlo en el orden que quiero
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fechaActual = new Date();
        return dateFormat.format(fechaActual);
    }

    public static boolean productVenta(DefaultTableModel modelF) throws Exception {

        String file = "..\\Proyecto_POO\\src\\main\\resources\\TXT\\Vendido.txt";
        txtAndpdf errorlog = new txtAndpdf(file);

        String data = """
                Vendedor: %s %s, %s; Codigo: %s; Nombre: %s; Cantidad: %s, Precio: %.2f, Proveedor: %s
                """;

        // Recorrer las filas de la tabla y agregar los datos a la cadena de productos
        for (int row = 0; row < modelF.getRowCount(); row++) {
            String codigo = modelF.getValueAt(row, 0).toString();
            String nombre = modelF.getValueAt(row, 1).toString();
            String precio = modelF.getValueAt(row, 2).toString();
            String cantidad = modelF.getValueAt(row, 3).toString();
            String proveedor = modelF.getValueAt(row, 5).toString();

            double precioProd = Double.parseDouble(precio);
            Venta.setPrecio(precioProd);

            String item = String.format(data,Login.getApellidoPat(), Login.getApellidoMat(),
                    Login.getNombre(), codigo, nombre, cantidad, Venta.getPrecio(), proveedor);
            errorlog.logVent(item, txtAndpdf.TipoEvento.INFO);
        }

        return true;
    }

    public static boolean productCompra(int cantidad, int codigo, String nombre, String proveedor) throws Exception {

        String file = "..\\Proyecto_POO\\src\\main\\resources\\TXT\\Pedido.txt";
        txtAndpdf errorlog = new txtAndpdf(file);

        String data = """
                User: %s %s, %s; Codigo: %d; Nombre: %s; Cantidad: %d; Precio: %.2f; Proveedor: %s
                """;

        String item = String.format(data, Login.getApellidoPat(), Login.getApellidoMat(), Login.getNombre(), codigo,
                nombre, cantidad, Compra.getPrecio(), proveedor);
        errorlog.logVent(item, txtAndpdf.TipoEvento.PEDIDO);


        return true;
    }

    public static boolean infoProveedores(String proveedor, int categoria, int producto, int stock) throws Exception {

        String file = "..\\Proyecto_POO\\src\\main\\resources\\TXT\\Info_Proveedores.txt";
        txtAndpdf errorlog = new txtAndpdf(file);

        String header = """
                - Proveedor: %s
                -------------------------------------------------------
                - Total Categorias: %d
                - Total Productos: %d
                - Total Stock: %d
                """;

        String item = String.format(header, proveedor, categoria, producto, stock);
        errorlog.log(item, txtAndpdf.TipoEvento.INFO);


        return true;
    }

}
