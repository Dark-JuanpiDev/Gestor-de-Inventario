package pe.edu.utp.Formulario.Ventas;

import pe.edu.utp.General;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Venta extends General {

    private static String nombreClient;
    private static String apellidoPat;
    private static String apellidoMat;
    private static String dni;
    private static double precio;
    private static double precioTotal;
    private static boolean vip;
    private static double antesDesc;

    public Venta(int codigo, String nombre, String nombreClient, String apellidoPat, String apellidoMat, String dni, double precio, boolean vip, double precioTotal, double antesDesc) {
        super(codigo, nombre);
        this.nombreClient = nombreClient;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.dni = dni;
        this.precio = precio;
        this.vip = vip;
        this.precioTotal = precioTotal;
        this.antesDesc = antesDesc;
    }

    public static String getNombreClient() {
        return nombreClient;
    }

    public static void setNombreClient(String nombreClient) {
        Venta.nombreClient = nombreClient;
    }

    public static String getApellidoPat() {
        return apellidoPat;
    }

    public static void setApellidoPat(String apellidoPat) {
        Venta.apellidoPat = apellidoPat;
    }

    public static String getApellidoMat() {
        return apellidoMat;
    }

    public static void setApellidoMat(String apellidoMat) {
        Venta.apellidoMat = apellidoMat;
    }

    public static String getDni() {
        return dni;
    }

    public static void setDni(String dni) {
        Venta.dni = dni;
    }

    public static double getPrecio() {
        return precio;
    }

    public static void setPrecio(double precio) {
        Venta.precio = precio;
    }

    public static double getPrecioTotal() {return precioTotal;}

    public static void setPrecioTotal(double precioTotal) {
        Venta.precioTotal = precioTotal;
    }

    public static boolean isVip() {
        return vip;
    }

    public static void setVip(boolean vip) {
        Venta.vip = vip;
    }

    public static double getAntesDesc() {
        return antesDesc;
    }

    public static void setAntesDesc(double antesDesc) {
        Venta.antesDesc = antesDesc;
    }

    public static String clienteVipB(String text) {

        if (isVip() == true) {
            return "Se aplica el " + text + "% de descuento";
        } else {
            return "No se aplica descuento";
        }

    }

    public static String clienteVipPDF(String text) {

        if (isVip() == true) {
            return text + "%";
        } else {
            return "No se aplica descuento";
        }

    }

    public static boolean eliminarStockEnBasedeDatos(int codigo, int stock , int cantidad) {
        try {

            Connection con = FormVenta.con; // Obtener la conexión de la clase principal

            String sql = "UPDATE Producto SET Stock = ? - ? WHERE Codigo = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, stock);
            statement.setInt(2, cantidad);
            statement.setInt(3, codigo);

            int filasActualizadas = statement.executeUpdate();

            // Verificar si se actualizó al menos una fila
            if (filasActualizadas > 0) {
                return true; // La actualización fue exitosa
            } else {
                return false; // No se actualizó ninguna fila (registro no encontrado)
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error en la actualización
        }
    }

    public static boolean aumentarStockEnBasedeDatos(int codigo, int stock , int cantidad) {
        try {

            Connection con = FormVenta.con; // Obtener la conexión de la clase principal

            String sql = "UPDATE Producto SET Stock = ? + ? WHERE Codigo = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, stock);
            statement.setInt(2, cantidad);
            statement.setInt(3, codigo);

            int filasActualizadas = statement.executeUpdate();

            // Verificar si se actualizó al menos una fila
            if (filasActualizadas > 0) {
                return true; // La actualización fue exitosa
            } else {
                return false; // No se actualizó ninguna fila (registro no encontrado)
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error en la actualización
        }
    }

    public static void isAgregarCliente(String dni, String nombreClient, String apellidoPat, String apellidoMat) throws SQLException {
        Connection con = FormVenta.con; // Obtener la conexión de la clase principal

        try {

            // Esta parte del codigo se utiliza para agregar los valores extraidos a la BD
            String sql = "INSERT INTO Clientes (dni, nombres, apellido_Pat, apellido_Mat) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, dni);
            statement.setString(2, nombreClient);
            statement.setString(3, apellidoPat);
            statement.setString(4, apellidoMat);

            // Ejecutar la consulta
            statement.executeUpdate();

            // Cerrar el statement
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex; // Re-lanzar la excepción para manejarla en la clase que llame a este método
        }

    }

    public static String obtenerCliente(String dni) {

        String nombreUsuario = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";

        try {

            Connection con = FormVenta.con; // Obtener la conexión de la clase principal

            String sql = "SELECT nombres, apellido_Pat, apellido_Mat FROM Clientes WHERE dni = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, dni);

            // Ejecuta la consulta
            ResultSet resultSet = statement.executeQuery();

            // Verifica si se encontró un usuario
            if (resultSet.next()) {
                nombreUsuario = resultSet.getString("nombres");
                apellidoPaterno = resultSet.getString("apellido_Pat");
                apellidoMaterno = resultSet.getString("apellido_Mat");
            }

            setDni(dni);
            setNombreClient(nombreUsuario);
            setApellidoPat(apellidoPaterno);
            setApellidoMat(apellidoMaterno);

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";

    }

    public static boolean encontrarCliente(String dni) {
        boolean clienteEncontrado = false;

        String nombreUsuario = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";

        try {
            Connection con = FormVenta.con; // Obtener la conexión de la clase principal

            String sql = "SELECT nombres, apellido_Pat, apellido_Mat FROM Clientes WHERE dni = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, dni);

            // Ejecuta la consulta
            ResultSet resultSet = statement.executeQuery();

            // Verifica si se encontró un usuario
            if (resultSet.next()) {
                clienteEncontrado = true;
                nombreUsuario = resultSet.getString("nombres");
                apellidoPaterno = resultSet.getString("apellido_Pat");
                apellidoMaterno = resultSet.getString("apellido_Mat");
            }

            setDni(dni);
            setNombreClient(nombreUsuario);
            setApellidoPat(apellidoPaterno);
            setApellidoMat(apellidoMaterno);

            // Cerrar el statement después de usarlo
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clienteEncontrado;
    }

    public static void isAgregarMovimiento(String codigoVend, String nombreVend, String apellidoPat, String apellidoMat, DefaultTableModel modelF) throws SQLException {
        Connection con = FormVenta.con; // Obtener la conexión de la clase principal

        // Recorrer las filas de la tabla y agregar los datos a la cadena de productos
        for (int row = 0; row < modelF.getRowCount(); row++) {
            String codigo = modelF.getValueAt(row, 0).toString();
            String nombre = modelF.getValueAt(row, 1).toString();
            String precio = modelF.getValueAt(row, 2).toString();
            String cantidad = modelF.getValueAt(row, 3).toString();
            String proveedor = modelF.getValueAt(row, 5).toString();

            int cod = Integer.parseInt(codigo);
            double precioProd = Double.parseDouble(precio);
            int cant = Integer.parseInt(cantidad);
            Venta.setPrecio(precioProd);

            try {

                // Esta parte del codigo se utiliza para agregar los valores extraidos a la BD
                String sql = "INSERT INTO Movimientos (codigo_Vend, nombres_Vend, apellidoPat_Vend, apellidoMat_Vend, codigo_Prod, producto, cantidad, precio, proveedor) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, codigoVend);
                statement.setString(2, nombreVend);
                statement.setString(3, apellidoPat);
                statement.setString(4, apellidoMat);
                statement.setInt(5, cod);
                statement.setString(6, nombre);
                statement.setInt(7, cant);
                statement.setDouble(8, Venta.getPrecio());
                statement.setString(9, proveedor);

                // Ejecutar la consulta
                statement.executeUpdate();

                // Cerrar el statement
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw ex; // Re-lanzar la excepción para manejarla en la clase que llame a este método
            }
        }

    }

}
